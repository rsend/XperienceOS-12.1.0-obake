/* Copyright (C) 2026 The XPerience Project */

#define LOG_TAG "ims_dpl_bootstrapd"

#include "ims_initial_registration.h"
#include "ims_long_timer_bridge.h"

#include <cutils/properties.h>
#include <dlfcn.h>
#include <errno.h>
#include <log/log.h>
#include <pthread.h>
#include <signal.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

namespace {

constexpr char kRcsLibrary[] = "lib-imsrcs.so";
constexpr char kStatusProperty[] = "sys.ims.dpl.status";
constexpr char kPolicyRulePriority[] = "13900";
constexpr unsigned kStartupTimeoutSeconds = 60;
constexpr unsigned kHealthIntervalSeconds = 5;
constexpr unsigned kShutdownTimeoutSeconds = 5;
constexpr size_t kMaximumActiveTimers = 64;

typedef void (*StatusCallbackFn)(unsigned status);
typedef void (*StartTimerFn)(unsigned intervalMilliseconds,
                             void* logicalHandle, void** nativeHandle);
typedef void (*StopTimerFn)(void* nativeHandle);
typedef int (*StartRcsServiceFn)(const void* callbacks);
typedef int (*StopRcsServiceFn)();
typedef int (*IsRcsServiceStartedFn)();
typedef void (*TimerExpiredFn)(void* logicalHandle);
typedef void* (*GetRcsInitObjectFn)();
typedef int (*RcsInitIsServiceStartedFn)(void* object);
typedef void* (*GetRcsObjectFn)();
typedef void (*TraceResetFn)();
typedef int (*TraceGetIntFn)();

struct TimerCallbacks {
    StartTimerFn start;
    StopTimerFn stop;
};

struct RcsCallbacks {
    StatusCallbackFn status;
    TimerCallbacks* timers;
};

struct TimerTask {
    pthread_t thread;
    pthread_mutex_t lock;
    pthread_cond_t condition;
    TimerTask* next;
    void* logicalHandle;
    unsigned intervalMilliseconds;
    bool cancelled;
    bool selfDestroy;
};

pthread_mutex_t gTimerListLock = PTHREAD_MUTEX_INITIALIZER;
TimerTask* gTimerList = nullptr;
size_t gActiveTimerCount = 0;
TimerExpiredFn gTimerExpired = nullptr;
volatile sig_atomic_t gStopRequested = 0;
volatile sig_atomic_t gStatusEventCount = 0;
volatile sig_atomic_t gLastStatus = -1;
volatile sig_atomic_t gTimerFailureCount = 0;

void setStatus(const char* status) {
    // Temporary IMS_DIAG logging; remove with the packaged diagnostic patch.
    static char previousStatus[PROP_VALUE_MAX] = {};
    if (strcmp(previousStatus, status) != 0) {
        char message[128];
        snprintf(message, sizeof(message), "IMS_DIAG bootstrap status=%s", status);
        __android_log_write(ANDROID_LOG_INFO, "ObakeImsDiag", message);
        snprintf(previousStatus, sizeof(previousStatus), "%s", status);
    }
    if (property_set(kStatusProperty, status) != 0) {
        ALOGE("cannot set %s=%s", kStatusProperty, status);
    }
}

void signalHandler(int) {
    gStopRequested = 1;
}

void statusCallback(unsigned status) {
    gLastStatus = static_cast<sig_atomic_t>(status);
    if (gStatusEventCount < 0x7fff) ++gStatusEventCount;
}

void destroyTimerTask(TimerTask* task) {
    if (task == nullptr) return;
    pthread_cond_destroy(&task->condition);
    pthread_mutex_destroy(&task->lock);
    free(task);
}

void removeTimerTaskLocked(TimerTask* task) {
    TimerTask** cursor = &gTimerList;
    while (*cursor != nullptr) {
        if (*cursor == task) {
            *cursor = task->next;
            task->next = nullptr;
            if (gActiveTimerCount > 0) --gActiveTimerCount;
            return;
        }
        cursor = &(*cursor)->next;
    }
}

void* timerThread(void* argument) {
    TimerTask* const task = static_cast<TimerTask*>(argument);
    timespec deadline = {};
    bool shouldFire = false;
    if (clock_gettime(CLOCK_REALTIME, &deadline) != 0) {
        ++gTimerFailureCount;
    } else {
        const uint64_t nanoseconds = static_cast<uint64_t>(deadline.tv_nsec) +
                static_cast<uint64_t>(task->intervalMilliseconds % 1000) *
                        1000000ULL;
        deadline.tv_sec += task->intervalMilliseconds / 1000 +
                static_cast<time_t>(nanoseconds / 1000000000ULL);
        deadline.tv_nsec = static_cast<long>(nanoseconds % 1000000000ULL);

        pthread_mutex_lock(&task->lock);
        int waitResult = 0;
        while (!task->cancelled && waitResult != ETIMEDOUT) {
            waitResult = pthread_cond_timedwait(&task->condition, &task->lock,
                                                &deadline);
            if (waitResult != 0 && waitResult != ETIMEDOUT) {
                ++gTimerFailureCount;
                task->cancelled = true;
            }
        }
        shouldFire = !task->cancelled && waitResult == ETIMEDOUT;
        pthread_mutex_unlock(&task->lock);
    }

    if (shouldFire && gTimerExpired != nullptr) {
        gTimerExpired(task->logicalHandle);
    }
    pthread_mutex_lock(&task->lock);
    const bool selfDestroy = task->selfDestroy;
    pthread_mutex_unlock(&task->lock);
    if (selfDestroy) destroyTimerTask(task);
    return nullptr;
}

void stopTimer(void* nativeHandle) {
    if (nativeHandle == nullptr) return;
    pthread_mutex_lock(&gTimerListLock);
    TimerTask* task = gTimerList;
    while (task != nullptr && task != nativeHandle &&
           task->logicalHandle != nativeHandle) {
        task = task->next;
    }
    if (task != nullptr) removeTimerTaskLocked(task);
    pthread_mutex_unlock(&gTimerListLock);
    if (task == nullptr) {
        ++gTimerFailureCount;
        ALOGE("cannot stop unknown DPL timer handle=%p", nativeHandle);
        return;
    }

    pthread_mutex_lock(&task->lock);
    task->cancelled = true;
    pthread_cond_signal(&task->condition);
    const bool calledFromTimer = pthread_equal(pthread_self(), task->thread);
    if (calledFromTimer) task->selfDestroy = true;
    pthread_mutex_unlock(&task->lock);
    if (!calledFromTimer) {
        if (pthread_join(task->thread, nullptr) != 0) ++gTimerFailureCount;
        destroyTimerTask(task);
    }
}

void startTimer(unsigned intervalMilliseconds, void* logicalHandle,
                void** nativeHandle) {
    if (nativeHandle != nullptr) *nativeHandle = nullptr;
    if (logicalHandle == nullptr || gTimerExpired == nullptr) {
        ++gTimerFailureCount;
        return;
    }
    pthread_mutex_lock(&gTimerListLock);
    const bool atLimit = gActiveTimerCount >= kMaximumActiveTimers;
    pthread_mutex_unlock(&gTimerListLock);
    if (atLimit) {
        ++gTimerFailureCount;
        ALOGE("DPL timer limit reached limit=%zu", kMaximumActiveTimers);
        return;
    }

    TimerTask* const task =
            static_cast<TimerTask*>(calloc(1, sizeof(TimerTask)));
    if (task == nullptr) {
        ++gTimerFailureCount;
        return;
    }
    task->logicalHandle = logicalHandle;
    task->intervalMilliseconds = intervalMilliseconds;
    if (pthread_mutex_init(&task->lock, nullptr) != 0) {
        ++gTimerFailureCount;
        free(task);
        return;
    }
    if (pthread_cond_init(&task->condition, nullptr) != 0) {
        ++gTimerFailureCount;
        pthread_mutex_destroy(&task->lock);
        free(task);
        return;
    }
    pthread_mutex_lock(&gTimerListLock);
    task->next = gTimerList;
    gTimerList = task;
    ++gActiveTimerCount;
    pthread_mutex_unlock(&gTimerListLock);
    const int result = pthread_create(&task->thread, nullptr, timerThread, task);
    if (result != 0) {
        pthread_mutex_lock(&gTimerListLock);
        removeTimerTaskLocked(task);
        pthread_mutex_unlock(&gTimerListLock);
        ++gTimerFailureCount;
        destroyTimerTask(task);
        return;
    }
    if (nativeHandle != nullptr) *nativeHandle = task;
}

[[noreturn]] void shutdownService(StopRcsServiceFn stopService, int exitStatus) {
    ALOGI("stopping stock DPL service; shutdown deadline=%u seconds",
          kShutdownTimeoutSeconds);
    // The stock stop waits for its DPL worker and normally terminates the
    // process. Bound that wait when an upstream service has already failed.
    signal(SIGALRM, SIG_DFL);
    alarm(kShutdownTimeoutSeconds);
    imscompat::shutdownInitialRegistration();
    const int stopResult = stopService();
    ALOGE("stock DPL stop returned unexpectedly result=%d", stopResult);
    // A return does not prove that vendor workers stopped. Keep the library,
    // callback storage, and timers alive until kernel process teardown; do
    // not dlclose, run exit destructors, or race vendor timer cancellation.
    _exit(exitStatus == 0 ? EXIT_FAILURE : exitStatus);
}

bool sleepSeconds(unsigned seconds) {
    timespec remaining = {static_cast<time_t>(seconds), 0};
    while (nanosleep(&remaining, &remaining) != 0) {
        if (errno == EINTR) {
            if (gStopRequested) return false;
            continue;
        }
        ALOGE("DPL health sleep failed: %s", strerror(errno));
        return false;
    }
    return true;
}

template <typename T>
bool resolve(void* library, const char* symbol, T* destination) {
    dlerror();
    void* const address = dlsym(library, symbol);
    const char* const error = dlerror();
    if (address == nullptr || error != nullptr) {
        ALOGE("DPL dependency missing symbol=%s detail=%s", symbol,
              error == nullptr ? "null" : error);
        return false;
    }
    *destination = reinterpret_cast<T>(address);
    return true;
}

int cleanupPolicyRule() {
    execl("/system/bin/ip", "ip", "-6", "rule", "del", "pref",
          kPolicyRulePriority, static_cast<char*>(nullptr));
    ALOGE("cannot execute DPL policy cleanup: %s", strerror(errno));
    return EXIT_FAILURE;
}

}  // namespace

int main(int argc, char** argv) {
    if (argc == 2 && strcmp(argv[1], "cleanup") == 0) {
        return cleanupPolicyRule();
    }
    if (argc != 2 || strcmp(argv[1], "serve") != 0) {
        fprintf(stderr, "Usage: %s <serve|cleanup>\n", argv[0]);
        return 2;
    }

    signal(SIGTERM, signalHandler);
    signal(SIGINT, signalHandler);
    setStatus("starting");
    void* const library = dlopen(kRcsLibrary, RTLD_NOW | RTLD_LOCAL);
    if (library == nullptr) {
        ALOGE("cannot load %s: %s", kRcsLibrary, dlerror());
        setStatus("dependency_error");
        return 3;
    }

    StartRcsServiceFn startService = nullptr;
    StopRcsServiceFn stopService = nullptr;
    IsRcsServiceStartedFn isStarted = nullptr;
    GetRcsInitObjectFn getRcsInitObject = nullptr;
    RcsInitIsServiceStartedFn internalIsStarted = nullptr;
    GetRcsObjectFn getRcsObject = nullptr;
    TraceResetFn traceReset = nullptr;
    TraceGetIntFn sendCalls = nullptr;
    TraceGetIntFn sendFailures = nullptr;
    TraceGetIntFn receiveCalls = nullptr;
    TraceGetIntFn receiveFailures = nullptr;
    TraceGetIntFn scopeApplied = nullptr;
    TraceGetIntFn scopeFailures = nullptr;
    TraceGetIntFn ruleApplied = nullptr;
    TraceGetIntFn ruleFailures = nullptr;

    const bool resolved =
            resolve(library, "QRcsStartRCSService", &startService) &&
            resolve(library, "QRcsStopRCSService", &stopService) &&
            resolve(library, "QRcsIsRCSServiceStarted", &isStarted) &&
            resolve(library, "QRcsAMTimerExpired", &gTimerExpired) &&
            resolve(library, "_ZN7RcsInit16GetRcsInitObjectEv",
                    &getRcsInitObject) &&
            resolve(library, "_ZN7RcsInit24RcsInit_IsServiceStartedEv",
                    &internalIsStarted) &&
            resolve(library, "_ZN6IMSRcs12GetRcsObjectEv", &getRcsObject) &&
            resolve(RTLD_DEFAULT, "imsDplTraceReset", &traceReset) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetSendCallCount", &sendCalls) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetSendFailureCount",
                    &sendFailures) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetReceiveCallCount",
                    &receiveCalls) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetReceiveFailureCount",
                    &receiveFailures) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetScopeFixAppliedCount",
                    &scopeApplied) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetScopeFixFailureCount",
                    &scopeFailures) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetPolicyRuleAppliedCount",
                    &ruleApplied) &&
            resolve(RTLD_DEFAULT, "imsDplTraceGetPolicyRuleFailureCount",
                    &ruleFailures);
    if (!resolved) {
        setStatus("dependency_error");
        dlclose(library);
        return 3;
    }

    TimerCallbacks timerCallbacks = {startTimer, stopTimer};
    RcsCallbacks callbacks = {statusCallback, &timerCallbacks};
    traceReset();
    const int startResult = startService(&callbacks);
    if (startResult != 1) {
        ALOGE("DPL bootstrap rejected start result=%d", startResult);
        setStatus("start_rejected");
        shutdownService(stopService, 4);
    }
    if (!imscompat::initializeLongTimerBridge(library)) {
        setStatus("timer_bridge_failed");
        shutdownService(stopService, 4);
    }

    setStatus("initializing");
    bool ready = false;
    for (unsigned elapsed = 0;
         elapsed < kStartupTimeoutSeconds && !gStopRequested; ++elapsed) {
        void* const object = getRcsInitObject();
        const int internal = object == nullptr ? -1 : internalIsStarted(object);
        const bool transportReady = sendCalls() > 0 && receiveCalls() > 0 &&
                sendFailures() == 0 && receiveFailures() == 0 &&
                scopeApplied() > 0 && scopeFailures() == 0 &&
                ruleApplied() > 0 && ruleFailures() == 0;
        if (isStarted() == 1 && internal == 1 && gStatusEventCount > 0 &&
                gLastStatus == 1 && transportReady) {
            ready = true;
            break;
        }
        if (gStatusEventCount > 0 && gLastStatus != 1) break;
        if (!sleepSeconds(1)) break;
    }
    if (!ready) {
        ALOGE("DPL readiness failed public=%d callback=%d sends=%d/%d "
              "receives=%d/%d scope=%d/%d rule=%d/%d",
              isStarted(), static_cast<int>(gLastStatus), sendCalls(),
              sendFailures(), receiveCalls(), receiveFailures(),
              scopeApplied(), scopeFailures(), ruleApplied(), ruleFailures());
        setStatus(gStopRequested ? "stopping" : "startup_failed");
        shutdownService(stopService, gStopRequested ? 0 : 4);
    }

    setStatus("active");
    if (!imscompat::initializeInitialRegistration(library, getRcsObject) &&
            imscompat::isInitialRegistrationEnabled()) {
        ALOGE("initial IMS registration bootstrap is unavailable");
    }
    while (!gStopRequested) {
        imscompat::reconcileInitialRegistration();
        if (!sleepSeconds(kHealthIntervalSeconds)) break;
        void* const object = getRcsInitObject();
        if (isStarted() != 1 || object == nullptr ||
                internalIsStarted(object) != 1 || gTimerFailureCount != 0) {
            setStatus("lost_readiness");
            ALOGE("DPL lost readiness public=%d internal=%d timer_failures=%d",
                  isStarted(), object == nullptr ? -1 : internalIsStarted(object),
                  static_cast<int>(gTimerFailureCount));
            shutdownService(stopService, 5);
        }
    }

    setStatus("stopping");
    shutdownService(stopService, 0);
}
