/*
 * Copyright (C) 2026 The XPerience Project
 * Licensed under the Apache License, Version 2.0.
 */

#define LOG_TAG "initial_attach_apnd"

#include <arpa/inet.h>
#include <cutils/sockets.h>
#include <errno.h>
#include <fcntl.h>
#include <log/log.h>
#include <poll.h>
#include <signal.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#include <string>

#include "legacy_initial_attach.h"

namespace {

constexpr char kSocketName[] = "initial_attach_apn";
constexpr uint32_t kMagic = 0x49414150;  // IAAP
constexpr uint16_t kVersion = 1;
constexpr uint16_t kCommandApply = 1;
constexpr size_t kRequestHeaderSize = 32;
constexpr size_t kResponseSize = 36;
constexpr size_t kMaximumApnLength = 100;
constexpr size_t kMaximumProtocolLength = 6;
constexpr size_t kMaximumCredentialLength = 127;
constexpr int kIoTimeoutMilliseconds = 5000;
constexpr unsigned kChildWatchdogSeconds = 30;
constexpr uid_t kRootUid = 0;
constexpr uid_t kRadioUid = 1001;

uint16_t readU16(const uint8_t* input) {
    uint16_t value = 0;
    memcpy(&value, input, sizeof(value));
    return ntohs(value);
}

uint32_t readU32(const uint8_t* input) {
    uint32_t value = 0;
    memcpy(&value, input, sizeof(value));
    return ntohl(value);
}

void writeU16(uint8_t* output, uint16_t value) {
    value = htons(value);
    memcpy(output, &value, sizeof(value));
}

void writeU32(uint8_t* output, uint32_t value) {
    value = htonl(value);
    memcpy(output, &value, sizeof(value));
}

int64_t monotonicMilliseconds() {
    timespec now = {};
    if (clock_gettime(CLOCK_MONOTONIC, &now) != 0) return -1;
    return static_cast<int64_t>(now.tv_sec) * 1000 + now.tv_nsec / 1000000;
}

bool transferExact(int fd, void* buffer, size_t length, bool writing) {
    uint8_t* cursor = static_cast<uint8_t*>(buffer);
    size_t remaining = length;
    const int64_t start = monotonicMilliseconds();
    if (start < 0) return false;
    const int64_t deadline = start + kIoTimeoutMilliseconds;

    while (remaining != 0) {
        const int64_t now = monotonicMilliseconds();
        if (now < 0 || now >= deadline) return false;
        pollfd descriptor = {};
        descriptor.fd = fd;
        descriptor.events = writing ? POLLOUT : POLLIN;
        const int pollResult = poll(
                &descriptor, 1, static_cast<int>(deadline - now));
        if (pollResult == 0) return false;
        if (pollResult < 0) {
            if (errno == EINTR) continue;
            return false;
        }
        if ((descriptor.revents & (POLLERR | POLLHUP | POLLNVAL)) != 0 &&
            (descriptor.revents & descriptor.events) == 0) {
            return false;
        }

        const ssize_t transferred = writing
                ? send(fd, cursor, remaining, MSG_NOSIGNAL)
                : recv(fd, cursor, remaining, 0);
        if (transferred == 0) return false;
        if (transferred < 0) {
            if (errno == EINTR || errno == EAGAIN || errno == EWOULDBLOCK) {
                continue;
            }
            return false;
        }
        cursor += transferred;
        remaining -= static_cast<size_t>(transferred);
    }
    return true;
}

bool containsNul(const std::string& value) {
    return value.find('\0') != std::string::npos;
}

void sendResponse(int fd, uint32_t requestId,
                  const initial_attach::Result& result) {
    uint8_t response[kResponseSize] = {};
    writeU32(response, kMagic);
    writeU16(response + 4, kVersion);
    writeU16(response + 6, static_cast<uint16_t>(result.status));
    writeU32(response + 8, requestId);
    writeU32(response + 12, static_cast<uint32_t>(result.stage));
    writeU32(response + 16, static_cast<uint32_t>(result.nativeResult));
    writeU32(response + 20, static_cast<uint32_t>(result.qmiError));
    writeU32(response + 24, result.profileId);
    writeU32(response + 28,
             static_cast<uint32_t>(result.rollbackAttempted));
    writeU32(response + 32,
             static_cast<uint32_t>(result.rollbackVerified));
    if (!transferExact(fd, response, sizeof(response), true)) {
        ALOGE("response write failed: request_id=%u status=%d errno=%d",
              requestId, result.status, errno);
    }
}

initial_attach::Result invalidResult() {
    initial_attach::Result result = {};
    result.status = initial_attach::kStatusInvalidRequest;
    result.stage = initial_attach::kStageValidate;
    return result;
}

void handleClient(int fd) {
    uint8_t header[kRequestHeaderSize] = {};
    if (!transferExact(fd, header, sizeof(header), false)) {
        ALOGE("request header read failed: errno=%d", errno);
        return;
    }

    const uint32_t magic = readU32(header);
    const uint16_t version = readU16(header + 4);
    const uint16_t command = readU16(header + 6);
    const uint32_t requestId = readU32(header + 8);
    const int32_t authType = static_cast<int32_t>(readU32(header + 12));
    const uint32_t apnLength = readU32(header + 16);
    const uint32_t protocolLength = readU32(header + 20);
    const uint32_t usernameLength = readU32(header + 24);
    const uint32_t passwordLength = readU32(header + 28);

    if (magic != kMagic || version != kVersion || command != kCommandApply ||
        apnLength == 0 || apnLength > kMaximumApnLength ||
        protocolLength == 0 || protocolLength > kMaximumProtocolLength ||
        usernameLength > kMaximumCredentialLength ||
        passwordLength > kMaximumCredentialLength) {
        ALOGW("invalid request header: request_id=%u version=%u command=%u",
              requestId, version, command);
        sendResponse(fd, requestId, invalidResult());
        return;
    }

    const size_t payloadLength = static_cast<size_t>(apnLength) +
            protocolLength + usernameLength + passwordLength;
    std::string payload(payloadLength, '\0');
    if (!transferExact(fd, &payload[0], payload.size(), false)) {
        ALOGE("request payload read failed: request_id=%u errno=%d",
              requestId, errno);
        sendResponse(fd, requestId, invalidResult());
        return;
    }

    size_t offset = 0;
    const std::string apn = payload.substr(offset, apnLength);
    offset += apnLength;
    const std::string protocol = payload.substr(offset, protocolLength);
    offset += protocolLength;
    const std::string username = payload.substr(offset, usernameLength);
    offset += usernameLength;
    const std::string password = payload.substr(offset, passwordLength);
    if (containsNul(apn) || containsNul(protocol) || containsNul(username) ||
        containsNul(password)) {
        ALOGW("embedded NUL rejected: request_id=%u", requestId);
        sendResponse(fd, requestId, invalidResult());
        return;
    }

    const initial_attach::Result result = initial_attach::apply(
            apn.c_str(), protocol.c_str(), authType, username.c_str(),
            password.c_str());
    if (result.status != initial_attach::kStatusSuccess) {
        ALOGE("apply failed: request_id=%u status=%d stage=%d native=%d "
              "qmi=%d profile=%u rollback_attempted=%d rollback_verified=%d",
              requestId, result.status, result.stage, result.nativeResult,
              result.qmiError, result.profileId, result.rollbackAttempted,
              result.rollbackVerified);
    }
    sendResponse(fd, requestId, result);
}

void childWatchdog(int) {
    static const char message[] = "initial_attach_apnd child watchdog expired\n";
    write(STDERR_FILENO, message, sizeof(message) - 1);
    _exit(124);
}

bool allowedPeer(int fd, ucred* credentials) {
    socklen_t length = sizeof(*credentials);
    memset(credentials, 0, sizeof(*credentials));
    if (getsockopt(fd, SOL_SOCKET, SO_PEERCRED, credentials, &length) != 0 ||
        length != sizeof(*credentials)) {
        return false;
    }
    return credentials->uid == kRootUid || credentials->uid == kRadioUid;
}

}  // namespace

int main() {
    signal(SIGPIPE, SIG_IGN);
    const int server = android_get_control_socket(kSocketName);
    if (server < 0) {
        ALOGE("control socket unavailable: name=%s errno=%d", kSocketName,
              errno);
        return 1;
    }
    if (fcntl(server, F_SETFD, FD_CLOEXEC) != 0) {
        ALOGE("cannot set control socket close-on-exec: errno=%d", errno);
        return 1;
    }
    if (listen(server, 2) != 0) {
        ALOGE("listen failed: errno=%d", errno);
        return 1;
    }

    for (;;) {
        const int client = accept(server, nullptr, nullptr);
        if (client < 0) {
            if (errno == EINTR) continue;
            ALOGE("accept failed: errno=%d", errno);
            return 1;
        }
        ucred credentials = {};
        if (!allowedPeer(client, &credentials)) {
            ALOGW("client rejected: uid=%u pid=%d errno=%d",
                  credentials.uid, credentials.pid, errno);
            close(client);
            continue;
        }

        const pid_t child = fork();
        if (child < 0) {
            ALOGE("fork failed: errno=%d", errno);
            close(client);
            continue;
        }
        if (child == 0) {
            close(server);
            signal(SIGALRM, childWatchdog);
            alarm(kChildWatchdogSeconds);
            handleClient(client);
            close(client);
            _exit(0);
        }

        close(client);
        int status = 0;
        while (waitpid(child, &status, 0) < 0) {
            if (errno == EINTR) continue;
            ALOGE("waitpid failed: child=%d errno=%d", child, errno);
            break;
        }
        if (!WIFEXITED(status) || WEXITSTATUS(status) != 0) {
            ALOGE("request child failed: child=%d status=0x%x", child,
                  status);
        }
    }
}
