/*
 * Copyright (C) 2026 The XPerience Project
 * Licensed under the Apache License, Version 2.0.
 */

#ifndef MOTOROLA_MSM8960DT_LEGACY_INITIAL_ATTACH_H
#define MOTOROLA_MSM8960DT_LEGACY_INITIAL_ATTACH_H

#include <stdint.h>

namespace initial_attach {

enum Status {
    kStatusSuccess = 0,
    kStatusInvalidRequest = 1,
    kStatusLibraryFailure = 2,
    kStatusModemFailure = 3,
    kStatusVerificationFailure = 4,
    kStatusRollbackFailure = 5,
};

enum Stage {
    kStageNone = 0,
    kStageValidate = 1,
    kStageLoadLibraries = 2,
    kStageInitializeQdp = 3,
    kStageReadAttachList = 4,
    kStageReadBaselineProfile = 5,
    kStageCheckRollbackCoverage = 6,
    kStageUpdateProfile = 7,
    kStageVerifyProfile = 8,
    kStageVerifyAttachList = 9,
    kStageRollbackProfile = 10,
    kStageVerifyRollback = 11,
    // Experimental stage appended without renumbering existing wire values.
    kStageRefreshAttachList = 12,
};

struct Result {
    int32_t status;
    int32_t stage;
    int32_t nativeResult;
    int32_t qmiError;
    uint32_t profileId;
    int32_t rollbackAttempted;
    int32_t rollbackVerified;
};

// Applies the requested active-SIM APN to the modem's existing first LTE
// attach profile, including the experimental .03R attach-list association
// refresh. An auth type of -1 is normalized like Android's data-call path:
// none for an empty username, PAP/CHAP otherwise.
Result apply(const char* apn, const char* protocol, int authType,
             const char* username, const char* password);

}  // namespace initial_attach

#endif  // MOTOROLA_MSM8960DT_LEGACY_INITIAL_ATTACH_H
