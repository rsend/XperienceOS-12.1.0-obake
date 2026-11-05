/*
 * Copyright (C) 2026 The XPerience Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */

#include <algorithm>
#include <dlfcn.h>
#include <errno.h>
#include <pthread.h>
#include <signal.h>
#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

namespace {

constexpr char kImssLibrary[] = "/system/lib/radio-su6-77/lib-imss.so";
constexpr unsigned kCallbackTimeoutSeconds = 8;
constexpr unsigned kWatchdogSeconds = 30;
constexpr size_t kMaximumResponseSize = 64;

enum ExitCode {
    kSuccess = 0,
    kUsageError = 2,
    kLibraryError = 3,
    kRegistrationError = 4,
    kRequestError = 5,
    kCallbackTimeout = 6,
    kModemError = 7,
    kMalformedResponse = 8,
    kPreservationError = 9,
};

struct QipcallConfig {
    uint8_t vtCallingEnabledValid;
    uint8_t vtCallingEnabled;
    uint8_t mobileDataEnabledValid;
    uint8_t mobileDataEnabled;
    uint8_t volteEnabledValid;
    uint8_t volteEnabled;
};

struct SettingsResponseHeader {
    uint32_t qmiResponseResult;
    uint32_t qmiResponseError;
    uint8_t settingsResponseValid;
    uint8_t reserved[3];
    uint32_t settingsResponse;
};

static_assert(sizeof(QipcallConfig) == 6, "unexpected QIPCALL ABI");
static_assert(sizeof(SettingsResponseHeader) == 16,
              "unexpected IMS Settings response ABI");

typedef void (*ResponseCallback)(void*, void*, int, void*, uint32_t);
typedef void (*IndicationCallback)(void*, void*, int, void*, uint32_t);
typedef void (*ErrorCallback)(void*, void*, int);

struct ClientCallbacks {
    ResponseCallback response;
    IndicationCallback indication;
    ErrorCallback error;
};

typedef int (*RegisterFn)(const ClientCallbacks*, void*, void**);
typedef int (*DeregisterFn)(void*);
typedef int (*GetQipcallConfigFn)(void*);
typedef int (*SetQipcallConfigFn)(void*, const QipcallConfig*);

struct Api {
    void* library;
    RegisterFn registerClient;
    DeregisterFn deregisterClient;
    GetQipcallConfigFn getQipcallConfig;
    SetQipcallConfigFn setQipcallConfig;
};

struct CallbackState {
    pthread_mutex_t mutex;
    pthread_cond_t condition;
    bool complete;
    bool transportError;
    int callbackId;
    int error;
    uint8_t response[kMaximumResponseSize];
    size_t responseSize;
};

void watchdogHandler(int) {
    static const char message[] = "QIPCALL error=watchdog_timeout\n";
    write(STDERR_FILENO, message, sizeof(message) - 1);
    _exit(124);
}

void responseCallback(void*, void* context, int responseId, void* response,
                      uint32_t responseSize) {
    CallbackState* state = static_cast<CallbackState*>(context);
    pthread_mutex_lock(&state->mutex);
    state->callbackId = responseId;
    state->responseSize = std::min(static_cast<size_t>(responseSize),
                                   sizeof(state->response));
    if (response != nullptr && state->responseSize != 0) {
        memcpy(state->response, response, state->responseSize);
    }
    state->complete = true;
    pthread_cond_signal(&state->condition);
    pthread_mutex_unlock(&state->mutex);
}

void indicationCallback(void*, void*, int, void*, uint32_t) {}

void errorCallback(void*, void* context, int error) {
    CallbackState* state = static_cast<CallbackState*>(context);
    pthread_mutex_lock(&state->mutex);
    state->transportError = true;
    state->error = error;
    state->complete = true;
    pthread_cond_signal(&state->condition);
    pthread_mutex_unlock(&state->mutex);
}

template <typename Function>
bool loadSymbol(void* library, const char* name, Function* output) {
    dlerror();
    void* symbol = dlsym(library, name);
    const char* error = dlerror();
    if (error != nullptr || symbol == nullptr) {
        fprintf(stderr, "QIPCALL error=missing_symbol symbol=%s detail=%s\n",
                name, error != nullptr ? error : "not_found");
        return false;
    }
    *output = reinterpret_cast<Function>(symbol);
    return true;
}

bool loadApi(Api* api) {
    memset(api, 0, sizeof(*api));
    api->library = dlopen(kImssLibrary, RTLD_NOW | RTLD_LOCAL);
    if (api->library == nullptr) {
        fprintf(stderr, "QIPCALL error=dlopen detail=%s\n", dlerror());
        return false;
    }
    const bool loaded =
            loadSymbol(api->library, "qrcs_ims_settings_register",
                       &api->registerClient) &&
            loadSymbol(api->library, "qrcs_ims_settings_deregister",
                       &api->deregisterClient) &&
            loadSymbol(api->library, "qrcs_ims_settings_get_qipcall_config",
                       &api->getQipcallConfig) &&
            loadSymbol(api->library, "qrcs_ims_settings_set_qipcall_config",
                       &api->setQipcallConfig);
    if (!loaded) {
        dlclose(api->library);
        memset(api, 0, sizeof(*api));
    }
    return loaded;
}

void resetState(CallbackState* state) {
    pthread_mutex_lock(&state->mutex);
    state->complete = false;
    state->transportError = false;
    state->callbackId = -1;
    state->error = 0;
    state->responseSize = 0;
    memset(state->response, 0, sizeof(state->response));
    pthread_mutex_unlock(&state->mutex);
}

int waitForCallback(CallbackState* state) {
    timespec deadline = {};
    if (clock_gettime(CLOCK_REALTIME, &deadline) != 0) return kCallbackTimeout;
    deadline.tv_sec += kCallbackTimeoutSeconds;

    pthread_mutex_lock(&state->mutex);
    while (!state->complete) {
        const int result = pthread_cond_timedwait(&state->condition,
                                                 &state->mutex, &deadline);
        if (result == ETIMEDOUT) {
            pthread_mutex_unlock(&state->mutex);
            fprintf(stderr, "QIPCALL error=callback_timeout\n");
            return kCallbackTimeout;
        }
        if (result != 0) {
            pthread_mutex_unlock(&state->mutex);
            fprintf(stderr, "QIPCALL error=callback_wait detail=%s\n",
                    strerror(result));
            return kCallbackTimeout;
        }
    }
    const bool transportError = state->transportError;
    const int error = state->error;
    pthread_mutex_unlock(&state->mutex);
    if (transportError) {
        fprintf(stderr, "QIPCALL error=transport code=%d\n", error);
        return kModemError;
    }
    return kSuccess;
}

bool validSettingsResponse(const CallbackState& state) {
    if (state.responseSize < sizeof(SettingsResponseHeader)) return false;
    SettingsResponseHeader header = {};
    memcpy(&header, state.response, sizeof(header));
    return header.qmiResponseResult == 0 && header.qmiResponseError == 0 &&
            (header.settingsResponseValid == 0 || header.settingsResponse == 1);
}

int getConfig(const Api& api, void* handle, CallbackState* state,
              QipcallConfig* config) {
    resetState(state);
    if (api.getQipcallConfig(handle) != 0) return kRequestError;
    int result = waitForCallback(state);
    if (result != kSuccess) return result;
    if (state->callbackId != 0 || !validSettingsResponse(*state)) {
        return kModemError;
    }
    constexpr size_t kConfigOffset = sizeof(SettingsResponseHeader);
    if (state->responseSize < kConfigOffset + sizeof(*config)) {
        return kMalformedResponse;
    }
    memcpy(config, state->response + kConfigOffset, sizeof(*config));
    return kSuccess;
}

int setVolte(const Api& api, void* handle, CallbackState* state, bool enabled) {
    QipcallConfig update = {};
    update.volteEnabledValid = 1;
    update.volteEnabled = enabled ? 1 : 0;
    resetState(state);
    if (api.setQipcallConfig(handle, &update) != 0) return kRequestError;
    int result = waitForCallback(state);
    if (result != kSuccess) return result;
    if (state->callbackId != 1 || !validSettingsResponse(*state)) {
        return kModemError;
    }
    return kSuccess;
}

bool validBoolean(uint8_t valid, uint8_t value) {
    return valid <= 1 && (!valid || value <= 1);
}

int ensureVolte(const Api& api, void* handle, CallbackState* state,
                bool enabled) {
    QipcallConfig before = {};
    int result = getConfig(api, handle, state, &before);
    if (result != kSuccess) return result;
    if (!validBoolean(before.vtCallingEnabledValid, before.vtCallingEnabled) ||
            !validBoolean(before.mobileDataEnabledValid,
                          before.mobileDataEnabled) ||
            before.volteEnabledValid != 1 || before.volteEnabled > 1) {
        return kMalformedResponse;
    }
    if (before.volteEnabled == static_cast<uint8_t>(enabled)) {
        printf("QIPCALL desired=%u result=already_applied\n", enabled ? 1 : 0);
        return kSuccess;
    }

    result = setVolte(api, handle, state, enabled);
    if (result != kSuccess) return result;

    QipcallConfig after = {};
    result = getConfig(api, handle, state, &after);
    if (result != kSuccess) return result;
    if (after.mobileDataEnabledValid != before.mobileDataEnabledValid ||
            (before.mobileDataEnabledValid &&
             after.mobileDataEnabled != before.mobileDataEnabled) ||
            after.vtCallingEnabledValid != before.vtCallingEnabledValid ||
            (before.vtCallingEnabledValid &&
             after.vtCallingEnabled != before.vtCallingEnabled)) {
        return kPreservationError;
    }
    if (after.volteEnabledValid != 1 ||
            after.volteEnabled != static_cast<uint8_t>(enabled)) {
        return kModemError;
    }
    printf("QIPCALL desired=%u result=verified\n", enabled ? 1 : 0);
    return kSuccess;
}

}  // namespace

int main(int argc, char** argv) {
    if (argc != 2 || (strcmp(argv[1], "0") != 0 && strcmp(argv[1], "1") != 0)) {
        fprintf(stderr, "usage: ims_qipcall_policy 0|1\n");
        return kUsageError;
    }
    signal(SIGALRM, watchdogHandler);
    alarm(kWatchdogSeconds);

    Api api = {};
    if (!loadApi(&api)) return kLibraryError;

    CallbackState state = {};
    pthread_mutex_init(&state.mutex, nullptr);
    pthread_cond_init(&state.condition, nullptr);
    state.callbackId = -1;
    const ClientCallbacks callbacks = {
        responseCallback, indicationCallback, errorCallback,
    };
    void* handle = nullptr;
    const int registered = api.registerClient(&callbacks, &state, &handle);
    if (registered != 0 || handle == nullptr) {
        fprintf(stderr, "QIPCALL error=register code=%d\n", registered);
        dlclose(api.library);
        return kRegistrationError;
    }

    int result = ensureVolte(api, handle, &state, strcmp(argv[1], "1") == 0);
    const int deregistered = api.deregisterClient(handle);
    if (deregistered != 0 && result == kSuccess) result = kRegistrationError;
    pthread_cond_destroy(&state.condition);
    pthread_mutex_destroy(&state.mutex);
    dlclose(api.library);
    alarm(0);
    if (result != kSuccess) {
        fprintf(stderr, "QIPCALL result=failed exit=%d\n", result);
    }
    return result;
}
