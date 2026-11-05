// Temporary, transparent logging of the selected netmgrd stack's file imports.
#include <dlfcn.h>
#include <errno.h>
#include <fcntl.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <unistd.h>

#ifdef __ANDROID__
#include <android/log.h>
#endif

namespace {
thread_local bool logging = false;
unsigned failures = 0;

void emit(const char* message, bool failure = true) {
    if (logging) return;
    logging = true;
#ifdef __ANDROID__
    __android_log_write(failure ? ANDROID_LOG_WARN : ANDROID_LOG_INFO,
                        "ObakeNetmgrTrace", message);
#else
    (void)failure;
    fprintf(stderr, "ObakeNetmgrTrace: %s\n", message);
#endif
    logging = false;
}

template <typename Fn> Fn resolve(const char* name) {
    void* address = dlsym(RTLD_NEXT, name);
    if (!address) {
        char message[160];
        snprintf(message, sizeof(message), "NETMGR_TRACE failure=resolve symbol=%s", name);
        emit(message);
        abort(); // Never silently substitute behavior for an unavailable import.
    }
    return reinterpret_cast<Fn>(address);
}

void denied(const char* operation, const char* path, int dirfd, int error) {
    if (logging || (error != EACCES && error != EPERM)) return;
    // Bound startup diagnostics even if a daemon repeatedly retries a failure.
    unsigned count = __atomic_fetch_add(&failures, 1u, __ATOMIC_RELAXED);
    if (count >= 64) return;
    char safePath[193];
    size_t i = 0;
    if (path) {
        for (; i < sizeof(safePath) - 1 && path[i]; ++i) {
            unsigned char c = static_cast<unsigned char>(path[i]);
            safePath[i] = (c >= 32 && c < 127) ? static_cast<char>(c) : '?';
        }
    }
    safePath[i] = '\0';
    char message[384];
    snprintf(message, sizeof(message),
             "NETMGR_TRACE op=%s path=%s dirfd=%d errno=%d pid=%d uid=%u euid=%u gid=%u egid=%u",
             operation, path ? safePath : "(null)", dirfd, error, getpid(),
             getuid(), geteuid(), getgid(), getegid());
    emit(message);
    if (count == 63) emit("NETMGR_TRACE file_failure_limit=64 reached");
}

void identity(const char* operation, unsigned target, int result, int error) {
    char message[256];
    snprintf(message, sizeof(message),
             "NETMGR_TRACE op=%s target=%u result=%d errno=%d pid=%d uid=%u euid=%u gid=%u egid=%u",
             operation, target, result, result < 0 ? error : 0, getpid(),
             getuid(), geteuid(), getgid(), getegid());
    emit(message, result < 0);
}

__attribute__((constructor)) void loaded() {
    int saved = errno;
    identity("loaded", 0, 0, 0);
    errno = saved;
}
}

// Resolve before dispatch, restoring incoming errno so successful calls retain
// libc's behavior. Preserve the actual call's errno across all logging.
#define REAL(name, type) \
    int incoming = errno; \
    static type real = resolve<type>(name); \
    errno = incoming
#define FILE_RESULT(operation, path, dirfd) \
    int saved = errno; \
    if (result < 0) denied(operation, path, dirfd, saved); \
    errno = saved; \
    return result

extern "C" int open(const char* path, int flags, ...) {
    typedef int (*Fn)(const char*, int, ...);
    REAL("open", Fn);
    mode_t mode = 0;
    if ((flags & O_CREAT) || ((flags & O_TMPFILE) == O_TMPFILE)) {
        va_list args;
        va_start(args, flags);
        mode = static_cast<mode_t>(va_arg(args, int));
        va_end(args);
    }
    int result = real(path, flags, mode);
    FILE_RESULT("open", path, AT_FDCWD);
}

extern "C" int openat(int dirfd, const char* path, int flags, ...) {
    typedef int (*Fn)(int, const char*, int, ...);
    REAL("openat", Fn);
    mode_t mode = 0;
    if ((flags & O_CREAT) || ((flags & O_TMPFILE) == O_TMPFILE)) {
        va_list args;
        va_start(args, flags);
        mode = static_cast<mode_t>(va_arg(args, int));
        va_end(args);
    }
    int result = real(dirfd, path, flags, mode);
    FILE_RESULT("openat", path, dirfd);
}

extern "C" FILE* fopen(const char* path, const char* mode) {
    typedef FILE* (*Fn)(const char*, const char*);
    REAL("fopen", Fn);
    FILE* result = real(path, mode);
    int saved = errno;
    if (!result) denied("fopen", path, AT_FDCWD, saved);
    errno = saved;
    return result;
}

extern "C" FILE* freopen(const char* path, const char* mode, FILE* stream) {
    typedef FILE* (*Fn)(const char*, const char*, FILE*);
    REAL("freopen", Fn);
    FILE* result = real(path, mode, stream);
    int saved = errno;
    if (!result) denied("freopen", path, AT_FDCWD, saved);
    errno = saved;
    return result;
}

extern "C" int stat(const char* path, struct stat* buffer) {
    typedef int (*Fn)(const char*, struct stat*);
    REAL("stat", Fn);
    int result = real(path, buffer);
    FILE_RESULT("stat", path, AT_FDCWD);
}

extern "C" int mkdir(const char* path, mode_t mode) {
    typedef int (*Fn)(const char*, mode_t);
    REAL("mkdir", Fn);
    int result = real(path, mode);
    FILE_RESULT("mkdir", path, AT_FDCWD);
}

extern "C" int unlink(const char* path) {
    typedef int (*Fn)(const char*);
    REAL("unlink", Fn);
    int result = real(path);
    FILE_RESULT("unlink", path, AT_FDCWD);
}

extern "C" int setuid(uid_t uid) {
    typedef int (*Fn)(uid_t);
    REAL("setuid", Fn);
    int result = real(uid);
    int saved = errno;
    identity("setuid", uid, result, saved);
    errno = saved;
    return result;
}

extern "C" int setgid(gid_t gid) {
    typedef int (*Fn)(gid_t);
    REAL("setgid", Fn);
    int result = real(gid);
    int saved = errno;
    identity("setgid", gid, result, saved);
    errno = saved;
    return result;
}
