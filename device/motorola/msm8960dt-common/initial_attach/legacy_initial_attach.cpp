/*
 * Copyright (C) 2026 The XPerience Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#define LOG_TAG "legacy_initial_attach"

#include <dlfcn.h>
#include <errno.h>
#include <limits.h>
#include <signal.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <string>
#include <vector>

#include "legacy_initial_attach.h"

namespace {

constexpr char kLegacyLibraryDirectory[] = "/system/lib/radio-su6-77/";
constexpr char kQmiPort[] = "rmnet0";
constexpr unsigned kWatchdogSeconds = 20;
constexpr size_t kMaximumAttachProfiles = 10;
constexpr size_t kMaximumApnLength = 100;
constexpr size_t kMaximumCredentialLength = 127;
constexpr size_t kOpaqueProfileSize = 4096;
constexpr size_t kProfileGuardSize = 64;
constexpr int kQdpSuccess = 1;
constexpr int kQmiSuccess = 0;
constexpr uint64_t kProfilePdpTypeMask = 0x2;
constexpr uint64_t kProfileApnMask = 0x4;
constexpr uint64_t kProfileUsernameMask = 0x200;
constexpr uint64_t kProfilePasswordMask = 0x400;
constexpr uint64_t kProfileAuthMask = 0x800;

enum ExitCode {
    kSuccess = 0,
    kUsageError = 2,
    kLibraryError = 3,
    kInitializationError = 4,
    kProfileError = 5,
    kModemError = 6,
    kVerificationError = 7,
    kRollbackError = 8,
};

enum QdpProfilePdnType {
    kPdnTypeInvalid = -1,
    kPdnTypeIpv4 = 0,
    kPdnTypeIpv6 = 1,
    kPdnTypeIpv4v6 = 2,
};

enum QdpTechnology {
    kTechnologyNone = 0,
    kTechnology3gpp = 1,
    kTechnology3gpp2 = 2,
    kTechnologyEpc = 3,
};

enum QdpError {
    kQdpErrorNone = 0,
    kQdpErrorOnlyIpv4 = 1,
    kQdpErrorOnlyIpv6 = 2,
    kQdpErrorMaximum = 3,
};

struct QdpErrorInfo {
    QdpError error;
    QdpTechnology technology;
};

struct AttachPdnList {
    int length;
    int profiles[kMaximumAttachProfiles];
};

struct WdsProfileId {
    uint32_t validDataRead;
    int32_t technology;
    uint32_t profileIndex;
};

struct UmtsProfilePrefix {
    uint64_t parameterMask;
    char profileName[32];
    int32_t pdpType;
    char apn[101];
};

struct OpaqueProfile {
    alignas(8) uint8_t data[kOpaqueProfileSize];
    uint8_t guard[kProfileGuardSize];
};

static_assert(sizeof(AttachPdnList) == 44,
              "legacy WDS LTE attach-list ABI changed");
static_assert(sizeof(WdsProfileId) == 12,
              "legacy WDS profile-ID ABI changed");
static_assert(offsetof(UmtsProfilePrefix, apn) == 44,
              "legacy WDS UMTS profile-prefix ABI changed");

typedef int (*QdpInitFn)(const char* port);
typedef int (*QdpProfileLookupFn)(const char** parameters,
                                  unsigned int* profile3gpp,
                                  QdpProfilePdnType* pdnType3gpp,
                                  unsigned int* profile3gpp2,
                                  QdpProfilePdnType* pdnType3gpp2,
                                  QdpErrorInfo* errorInfo);
typedef int (*QdpLteAttachProfileLookupFn)(
        const char** parameters, unsigned int* profileLte,
        QdpProfilePdnType* pdnTypeLte, void* previousProfileParameters,
        QdpErrorInfo* errorInfo);
typedef int (*WdsGetAttachListFn)(int handle, AttachPdnList* list,
                                  int* qmiError);
typedef int (*WdsSetAttachListFn)(int handle, AttachPdnList* list,
                                  int* qmiError);
typedef int (*WdsGetMaximumAttachProfilesFn)(int handle, int* maximum,
                                             int* qmiError);
typedef int (*WdsQueryProfileFn)(int handle, WdsProfileId* profileId,
                                 void* parameters, int* qmiError);
typedef int (*WdsModifyProfileFn)(int handle, WdsProfileId* profileId,
                                  void* parameters, int* qmiError);

struct Api {
    std::vector<void*> libraries;
    QdpInitFn qdpInit;
    QdpProfileLookupFn qdpProfileLookup;
    QdpLteAttachProfileLookupFn qdpLteAttachProfileLookup;
    WdsGetAttachListFn getAttachList;
    WdsSetAttachListFn setAttachList;
    WdsGetMaximumAttachProfilesFn getMaximumAttachProfiles;
    WdsQueryProfileFn queryProfile;
    WdsModifyProfileFn modifyProfile;
    int* wdsHandle;
};

struct OperationFailure {
    int nativeResult;
    int qmiError;
};

void watchdogHandler(int) {
    static const char message[] =
            "ERROR watchdog timeout during legacy QDP/WDS operation\n";
    write(STDERR_FILENO, message, sizeof(message) - 1);
    _exit(124);
}


bool isAsciiAlphanumeric(char value) {
    return (value >= 'a' && value <= 'z') ||
           (value >= 'A' && value <= 'Z') ||
           (value >= '0' && value <= '9');
}

bool validateApn(const char* apn) {
    if (apn == nullptr) return false;
    const size_t length = strlen(apn);
    if (length == 0 || length > kMaximumApnLength || apn[0] == '.' ||
        apn[length - 1] == '.') {
        return false;
    }

    bool labelHasCharacter = false;
    for (size_t index = 0; index < length; ++index) {
        const char value = apn[index];
        if (value == '.') {
            if (!labelHasCharacter || apn[index - 1] == '-') return false;
            labelHasCharacter = false;
            continue;
        }
        if (!isAsciiAlphanumeric(value) && value != '-') return false;
        if (!labelHasCharacter && value == '-') return false;
        labelHasCharacter = true;
    }
    return labelHasCharacter && apn[length - 1] != '-';
}

bool validateProtocol(const char* protocol) {
    return protocol != nullptr &&
           (strcmp(protocol, "IP") == 0 || strcmp(protocol, "IPV6") == 0 ||
            strcmp(protocol, "IPV4V6") == 0);
}


bool validateCredential(const char* value) {
    if (value == nullptr || strlen(value) > kMaximumCredentialLength) {
        return false;
    }
    for (const unsigned char* cursor =
                 reinterpret_cast<const unsigned char*>(value);
         *cursor != '\0'; ++cursor) {
        if (*cursor < 0x20 || *cursor > 0x7e) return false;
    }
    return true;
}

template <typename Function>
bool loadSymbol(void* library, const char* name, Function* output) {
    dlerror();
    void* symbol = dlsym(library, name);
    const char* error = dlerror();
    if (error != nullptr || symbol == nullptr) {
        fprintf(stderr, "ERROR missing symbol %s: %s\n", name,
                error != nullptr ? error : "not found");
        return false;
    }
    *output = reinterpret_cast<Function>(symbol);
    return true;
}

bool openLibrary(const char* name, std::vector<void*>* libraries,
                 void** output) {
    const std::string path = std::string(kLegacyLibraryDirectory) + name;
    void* library = dlopen(path.c_str(), RTLD_NOW | RTLD_GLOBAL);
    if (library == nullptr) {
        fprintf(stderr, "ERROR dlopen %s failed: %s\n", path.c_str(),
                dlerror());
        return false;
    }
    libraries->push_back(library);
    *output = library;
    return true;
}

bool verifyLoadedLibraries() {
    const char* required[] = {
        "libdiag.so", "libdsutils.so", "libqmi.so", "libqdp.so",
    };
    bool found[sizeof(required) / sizeof(required[0])] = {};
    bool wrongPath = false;
    FILE* maps = fopen("/proc/self/maps", "r");
    if (maps == nullptr) {
        fprintf(stderr, "ERROR cannot inspect /proc/self/maps: %s\n",
                strerror(errno));
        return false;
    }

    char line[1024] = {};
    while (fgets(line, sizeof(line), maps) != nullptr) {
        for (size_t index = 0;
             index < sizeof(required) / sizeof(required[0]); ++index) {
            if (strstr(line, required[index]) == nullptr) continue;
            found[index] = true;
            if (strstr(line, kLegacyLibraryDirectory) == nullptr) {
                fprintf(stderr, "ERROR mismatched library mapping: %s", line);
                wrongPath = true;
            }
        }
    }
    fclose(maps);

    bool missing = false;
    for (size_t index = 0;
         index < sizeof(required) / sizeof(required[0]); ++index) {
        if (!found[index]) {
            fprintf(stderr, "ERROR required library was not loaded: %s\n",
                    required[index]);
            missing = true;
        }
    }
    return !missing && !wrongPath;
}

bool loadApi(Api* api) {
    api->qdpInit = nullptr;
    api->qdpProfileLookup = nullptr;
    api->qdpLteAttachProfileLookup = nullptr;
    api->getAttachList = nullptr;
    api->setAttachList = nullptr;
    api->getMaximumAttachProfiles = nullptr;
    api->queryProfile = nullptr;
    api->modifyProfile = nullptr;
    api->wdsHandle = nullptr;

    void* ignored = nullptr;
    void* qmi = nullptr;
    void* qdp = nullptr;
    if (!openLibrary("libdiag.so", &api->libraries, &ignored) ||
        !openLibrary("libdsutils.so", &api->libraries, &ignored) ||
        !openLibrary("libqmi.so", &api->libraries, &qmi) ||
        !openLibrary("libqdp.so", &api->libraries, &qdp)) {
        return false;
    }
    if (!verifyLoadedLibraries()) return false;

    return loadSymbol(qdp, "qdp_init", &api->qdpInit) &&
           loadSymbol(qdp, "qdp_profile_look_up", &api->qdpProfileLookup) &&
           loadSymbol(qdp, "qdp_lte_attach_profile_lookup",
                      &api->qdpLteAttachProfileLookup) &&
           loadSymbol(qdp, "global_qmi_wds_hndl", &api->wdsHandle) &&
           loadSymbol(qmi, "qmi_wds_get_lte_attach_pdn_list",
                      &api->getAttachList) &&
           loadSymbol(qmi, "qmi_wds_set_lte_attach_pdn_list",
                      &api->setAttachList) &&
           loadSymbol(qmi, "qmi_wds_get_lte_max_attach_pdn_num",
                      &api->getMaximumAttachProfiles) &&
           loadSymbol(qmi, "qmi_wds_query_profile", &api->queryProfile) &&
           loadSymbol(qmi, "qmi_wds_modify_profile", &api->modifyProfile);
}


bool validList(const AttachPdnList& list) {
    if (list.length < 0 ||
        list.length > static_cast<int>(kMaximumAttachProfiles)) {
        fprintf(stderr, "ERROR modem returned invalid attach-list length=%d\n",
                list.length);
        return false;
    }
    for (int index = 0; index < list.length; ++index) {
        if (list.profiles[index] < 0) {
            fprintf(stderr,
                    "ERROR modem returned invalid profile at index=%d value=%d\n",
                    index, list.profiles[index]);
            return false;
        }
    }
    return true;
}

bool getAttachList(const Api& api, AttachPdnList* list,
                   OperationFailure* failure = nullptr) {
    memset(list, 0, sizeof(*list));
    int qmiError = 0;
    const int result =
            api.getAttachList(*api.wdsHandle, list, &qmiError);
    if (failure != nullptr) {
        failure->nativeResult = result;
        failure->qmiError = qmiError;
    }
    if (result != kQmiSuccess) {
        fprintf(stderr,
                "ERROR get LTE attach PDN list failed result=%d qmi_error=%d "
                "handle=%d\n",
                result, qmiError, *api.wdsHandle);
        return false;
    }
    return validList(*list);
}


void initializeOpaqueProfile(OpaqueProfile* profile) {
    memset(profile, 0, sizeof(*profile));
    memset(profile->guard, 0xa5, sizeof(profile->guard));
}

bool validProfileGuard(const OpaqueProfile& profile) {
    for (size_t index = 0; index < sizeof(profile.guard); ++index) {
        if (profile.guard[index] != 0xa5) {
            fprintf(stderr,
                    "ERROR legacy WDS profile ABI exceeded the guarded "
                    "%zu-byte buffer\n",
                    sizeof(profile.data));
            return false;
        }
    }
    return true;
}

bool queryProfileForTechnology(const Api& api, int32_t technology,
                               unsigned int profileIndex,
                               OpaqueProfile* parameters,
                               OperationFailure* failure = nullptr) {
    initializeOpaqueProfile(parameters);
    WdsProfileId profileId = {
        0,  // validDataRead is output-only for this operation.
        technology,
        profileIndex,
    };
    int qmiError = 0;
    const int result = api.queryProfile(*api.wdsHandle, &profileId,
                                        parameters->data, &qmiError);
    if (failure != nullptr) {
        failure->nativeResult = result;
        failure->qmiError = qmiError;
    }
    if (!validProfileGuard(*parameters)) return false;
    if (result != kQmiSuccess) {
        fprintf(stderr,
                "ERROR query profile technology=%d profile=%u failed "
                "result=%d qmi_error=%d handle=%d\n",
                technology, profileIndex, result, qmiError, *api.wdsHandle);
        return false;
    }
    return true;
}

bool queryProfile(const Api& api, unsigned int profileIndex,
                  OpaqueProfile* parameters,
                  OperationFailure* failure = nullptr) {
    return queryProfileForTechnology(api, 0, profileIndex, parameters, failure);
}

bool modifyProfile(const Api& api, unsigned int profileIndex,
                   const OpaqueProfile& parameters,
                   OperationFailure* failure = nullptr) {
    OpaqueProfile mutableParameters = parameters;
    WdsProfileId profileId = {
        0,
        0,  // QMI_WDS_PROFILE_TECH_3GPP
        profileIndex,
    };
    int qmiError = 0;
    const int result = api.modifyProfile(*api.wdsHandle, &profileId,
                                         mutableParameters.data, &qmiError);
    if (failure != nullptr) {
        failure->nativeResult = result;
        failure->qmiError = qmiError;
    }
    if (!validProfileGuard(mutableParameters)) return false;
    if (result != kQmiSuccess) {
        fprintf(stderr,
                "ERROR restore 3GPP profile=%u failed result=%d qmi_error=%d "
                "handle=%d\n",
                profileIndex, result, qmiError, *api.wdsHandle);
        return false;
    }
    return true;
}

const UmtsProfilePrefix* getUmtsPrefix(const OpaqueProfile& profile) {
    return reinterpret_cast<const UmtsProfilePrefix*>(profile.data);
}

bool validProfileApn(const OpaqueProfile& profile) {
    const UmtsProfilePrefix* prefix = getUmtsPrefix(profile);
    return (prefix->parameterMask & kProfileApnMask) != 0 &&
           memchr(prefix->apn, '\0', sizeof(prefix->apn)) != nullptr;
}


bool equalProfiles(const OpaqueProfile& first, const OpaqueProfile& second) {
    return memcmp(first.data, second.data, sizeof(first.data)) == 0;
}

int expectedPdpType(const char* protocol) {
    if (strcmp(protocol, "IP") == 0) return 0;
    if (strcmp(protocol, "IPV6") == 0) return 2;
    return 3;  // IPV4V6
}

uint64_t requiredRollbackMask(const char* user, const char* password) {
    uint64_t mask = kProfilePdpTypeMask | kProfileApnMask | kProfileAuthMask;
    if (user[0] != '\0') mask |= kProfileUsernameMask;
    if (password[0] != '\0') mask |= kProfilePasswordMask;
    return mask;
}

bool equalLists(const AttachPdnList& first, const AttachPdnList& second) {
    if (first.length != second.length) return false;
    for (int index = 0; index < first.length; ++index) {
        if (first.profiles[index] != second.profiles[index]) return false;
    }
    return true;
}

bool setAttachList(const Api& api, const AttachPdnList& requested,
                   OperationFailure* failure = nullptr) {
    AttachPdnList mutableRequested = requested;
    int qmiError = 0;
    const int result = api.setAttachList(*api.wdsHandle, &mutableRequested,
                                         &qmiError);
    if (failure != nullptr) {
        failure->nativeResult = result;
        failure->qmiError = qmiError;
    }
    if (result != kQmiSuccess) {
        fprintf(stderr,
                "ERROR set LTE attach PDN list failed result=%d qmi_error=%d "
                "handle=%d\n",
                result, qmiError, *api.wdsHandle);
        return false;
    }
    return true;
}


int initialize(Api* api) {
    if (!loadApi(api)) return kLibraryError;
    const int result = api->qdpInit(kQmiPort);
    if (result != kQdpSuccess) {
        fprintf(stderr, "ERROR qdp_init port=%s result=%d\n", kQmiPort,
                result);
        return kInitializationError;
    }
    if (api->wdsHandle == nullptr || *api->wdsHandle < 0) {
        fprintf(stderr, "ERROR invalid global QDP WDS handle=%d\n",
                api->wdsHandle == nullptr ? -1 : *api->wdsHandle);
        return kInitializationError;
    }
    return kSuccess;
}


initial_attach::Result runPermanentApply(const Api& api, const char* apn,
                                         const char* protocol,
                                         const char* auth, const char* user,
                                         const char* password) {
    initial_attach::Result result = {};
    result.status = initial_attach::kStatusSuccess;

    OperationFailure operation = {};
    AttachPdnList originalList = {};
    if (!getAttachList(api, &originalList, &operation) ||
        originalList.length == 0) {
        result.status = initial_attach::kStatusModemFailure;
        result.stage = initial_attach::kStageReadAttachList;
        result.nativeResult = operation.nativeResult;
        result.qmiError = operation.qmiError;
        return result;
    }

    const unsigned int activeProfile =
            static_cast<unsigned int>(originalList.profiles[0]);
    result.profileId = activeProfile;
    OpaqueProfile baseline = {};
    operation = {};
    if (!queryProfile(api, activeProfile, &baseline, &operation)) {
        result.status = initial_attach::kStatusModemFailure;
        result.stage = initial_attach::kStageReadBaselineProfile;
        result.nativeResult = operation.nativeResult;
        result.qmiError = operation.qmiError;
        return result;
    }

    const UmtsProfilePrefix* baselinePrefix = getUmtsPrefix(baseline);
    const uint64_t rollbackMask = requiredRollbackMask(user, password);
    if ((baselinePrefix->parameterMask & rollbackMask) != rollbackMask) {
        result.status = initial_attach::kStatusVerificationFailure;
        result.stage = initial_attach::kStageCheckRollbackCoverage;
        return result;
    }

    const char* parameters[7] = {};
    parameters[0] = "1";
    parameters[2] = apn;
    parameters[3] = user;
    parameters[4] = password;
    parameters[5] = auth;
    parameters[6] = protocol;

    OpaqueProfile qdpSavedProfile = {};
    initializeOpaqueProfile(&qdpSavedProfile);
    unsigned int outputProfile = activeProfile;
    QdpProfilePdnType pdnType = kPdnTypeInvalid;
    QdpErrorInfo errorInfo = {kQdpErrorNone, kTechnologyNone};
    const int lookup = api.qdpLteAttachProfileLookup(
            parameters, &outputProfile, &pdnType, qdpSavedProfile.data,
            &errorInfo);

    bool updateVerified = true;
    if (lookup != kQdpSuccess) {
        result.status = initial_attach::kStatusModemFailure;
        result.stage = initial_attach::kStageUpdateProfile;
        result.nativeResult = lookup;
        result.qmiError = static_cast<int32_t>(errorInfo.error);
        updateVerified = false;
    } else if (!validProfileGuard(qdpSavedProfile) ||
               outputProfile != activeProfile ||
               !equalProfiles(qdpSavedProfile, baseline)) {
        result.status = initial_attach::kStatusVerificationFailure;
        result.stage = initial_attach::kStageVerifyProfile;
        updateVerified = false;
    }

    OpaqueProfile updated = {};
    operation = {};
    const bool updatedQuery =
            queryProfile(api, activeProfile, &updated, &operation);
    if (!updatedQuery) {
        result.status = initial_attach::kStatusModemFailure;
        result.stage = initial_attach::kStageVerifyProfile;
        result.nativeResult = operation.nativeResult;
        result.qmiError = operation.qmiError;
        updateVerified = false;
    } else {
        const UmtsProfilePrefix* updatedPrefix = getUmtsPrefix(updated);
        if (!validProfileApn(updated) || strcmp(updatedPrefix->apn, apn) != 0 ||
            (updatedPrefix->parameterMask & kProfilePdpTypeMask) == 0 ||
            updatedPrefix->pdpType != expectedPdpType(protocol)) {
            result.status = initial_attach::kStatusVerificationFailure;
            result.stage = initial_attach::kStageVerifyProfile;
            updateVerified = false;
        }
    }

    // Experimental .03R association refresh. A failed radio cycle passed
    // sentinel profile 65535 to ds3g despite correct public 3GPP/list
    // readbacks. Re-submit the unchanged list after the verified profile
    // update so a controlled device test can determine whether WDS rebuilds
    // the internal LTE/EPC association. Do not infer success from readback;
    // the decisive gate is a subsequent DIAG attach trace.
    if (updateVerified) {
        operation = {};
        if (!setAttachList(api, originalList, &operation)) {
            result.status = initial_attach::kStatusModemFailure;
            result.stage = initial_attach::kStageRefreshAttachList;
            result.nativeResult = operation.nativeResult;
            result.qmiError = operation.qmiError;
            updateVerified = false;
        }
    }

    AttachPdnList updatedList = {};
    operation = {};
    const bool updatedListRead =
            getAttachList(api, &updatedList, &operation);
    if (!updatedListRead || !equalLists(originalList, updatedList)) {
        result.status = updatedListRead
                ? initial_attach::kStatusVerificationFailure
                : initial_attach::kStatusModemFailure;
        result.stage = initial_attach::kStageVerifyAttachList;
        result.nativeResult = operation.nativeResult;
        result.qmiError = operation.qmiError;
        updateVerified = false;
    }

    if (updateVerified) return result;

    result.rollbackAttempted = 1;
    operation = {};
    const bool modified =
            modifyProfile(api, activeProfile, baseline, &operation);
    OpaqueProfile restored = {};
    OperationFailure queryFailure = {};
    const bool restoredQuery =
            queryProfile(api, activeProfile, &restored, &queryFailure);
    AttachPdnList finalList = {};
    OperationFailure listFailure = {};
    const bool finalListRead =
            getAttachList(api, &finalList, &listFailure);
    result.rollbackVerified =
            modified && restoredQuery && finalListRead &&
            equalProfiles(baseline, restored) &&
            equalLists(originalList, finalList);
    if (!result.rollbackVerified) {
        result.status = initial_attach::kStatusRollbackFailure;
        result.stage = initial_attach::kStageVerifyRollback;
        if (!modified) {
            result.nativeResult = operation.nativeResult;
            result.qmiError = operation.qmiError;
        } else if (!restoredQuery) {
            result.nativeResult = queryFailure.nativeResult;
            result.qmiError = queryFailure.qmiError;
        } else if (!finalListRead) {
            result.nativeResult = listFailure.nativeResult;
            result.qmiError = listFailure.qmiError;
        }
    }
    return result;
}

}  // namespace

initial_attach::Result initial_attach::apply(
        const char* apn, const char* protocol, int authType,
        const char* username, const char* password) {
    initial_attach::Result result = {};
    result.status = initial_attach::kStatusInvalidRequest;
    result.stage = initial_attach::kStageValidate;
    if (username == nullptr) username = "";
    if (password == nullptr) password = "";

    int normalizedAuth = authType;
    if (normalizedAuth == -1) {
        normalizedAuth = username[0] == '\0' ? 0 : 3;
    }
    char auth[2] = {static_cast<char>('0' + normalizedAuth), '\0'};
    if (normalizedAuth < 0 || normalizedAuth > 3 || !validateApn(apn) ||
        !validateProtocol(protocol) || !validateCredential(username) ||
        !validateCredential(password)) {
        return result;
    }

    signal(SIGALRM, watchdogHandler);
    alarm(kWatchdogSeconds);
    Api api = {};
    const int initialization = initialize(&api);
    if (initialization != kSuccess) {
        result.status = initialization == kLibraryError
                ? initial_attach::kStatusLibraryFailure
                : initial_attach::kStatusModemFailure;
        result.stage = initialization == kLibraryError
                ? initial_attach::kStageLoadLibraries
                : initial_attach::kStageInitializeQdp;
        result.nativeResult = initialization;
        alarm(0);
        return result;
    }

    result = runPermanentApply(api, apn, protocol, auth, username, password);
    alarm(0);
    return result;
}
