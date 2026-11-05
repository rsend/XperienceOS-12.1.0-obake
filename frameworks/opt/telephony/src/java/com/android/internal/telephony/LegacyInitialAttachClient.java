/*
 * Copyright (C) 2026 The XPerience Project
 * Licensed under the Apache License, Version 2.0.
 */

package com.android.internal.telephony;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.AsyncResult;
import android.os.Message;
import android.telephony.Rlog;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Locale;

/**
 * Bounded client for the legacy Qualcomm initial-attach APN adapter.
 *
 * The protocol intentionally contains no Java serialization and is versioned
 * so a future platform port can retain this narrow boundary while replacing
 * either implementation independently.
 */
final class LegacyInitialAttachClient {
    private static final String TAG = "LegacyInitialAttach";
    private static final String SOCKET_NAME = "initial_attach_apn";
    private static final int MAGIC = 0x49414150; // IAAP
    private static final int VERSION = 1;
    private static final int COMMAND_APPLY = 1;
    private static final int STATUS_SUCCESS = 0;
    private static final int STATUS_INVALID_REQUEST = 1;
    // Longer than the daemon's 30-second child watchdog so the server owns the
    // terminal timeout and can return its structured failure when possible.
    private static final int SOCKET_TIMEOUT_MILLIS = 35000;
    private static final int MAX_APN_BYTES = 100;
    private static final int MAX_PROTOCOL_BYTES = 6;
    private static final int MAX_CREDENTIAL_BYTES = 127;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final AtomicInteger mNextRequestId = new AtomicInteger(1);
    private final ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(
            1, 1, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(1),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable, "InitialAttachApn");
                    thread.setDaemon(true);
                    return thread;
                }
            },
            new ThreadPoolExecutor.AbortPolicy());

    void apply(final String apn, final String protocol, final int authType,
            final String username, final String password, final Message result) {
        final Request request;
        try {
            request = Request.create(nextRequestId(), apn, protocol, authType,
                    username, password);
        } catch (IllegalArgumentException exception) {
            Rlog.e(TAG, "initial-attach request rejected: " + exception.getMessage());
            complete(result, new CommandException(
                    CommandException.Error.INVALID_ARGUMENTS, exception.getMessage()));
            return;
        }

        try {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    execute(request, result);
                }
            });
        } catch (RejectedExecutionException exception) {
            Rlog.e(TAG, "initial-attach request queue full: request_id="
                    + request.requestId);
            complete(result, new CommandException(
                    CommandException.Error.REQUEST_RATE_LIMITED));
        }
    }

    private int nextRequestId() {
        for (;;) {
            int requestId = mNextRequestId.getAndIncrement();
            if (requestId != 0) return requestId;
        }
    }

    private void execute(Request request, Message result) {
        LocalSocket socket = new LocalSocket();
        try {
            socket.connect(new LocalSocketAddress(
                    SOCKET_NAME, LocalSocketAddress.Namespace.RESERVED));
            socket.setSoTimeout(SOCKET_TIMEOUT_MILLIS);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeInt(MAGIC);
            output.writeShort(VERSION);
            output.writeShort(COMMAND_APPLY);
            output.writeInt(request.requestId);
            output.writeInt(request.authType);
            output.writeInt(request.apn.length);
            output.writeInt(request.protocol.length);
            output.writeInt(request.username.length);
            output.writeInt(request.password.length);
            output.write(request.apn);
            output.write(request.protocol);
            output.write(request.username);
            output.write(request.password);
            output.flush();

            DataInputStream input = new DataInputStream(socket.getInputStream());
            Response response = Response.read(input, request.requestId);
            if (response.status == STATUS_SUCCESS) {
                complete(result, null);
                return;
            }

            String context = "request_id=" + request.requestId
                    + " status=" + response.status
                    + " stage=" + response.stage
                    + " native=" + response.nativeResult
                    + " qmi=" + response.qmiError
                    + " profile=" + response.profileId
                    + " rollback_attempted=" + response.rollbackAttempted
                    + " rollback_verified=" + response.rollbackVerified;
            Rlog.e(TAG, "initial-attach apply failed: " + context);
            CommandException.Error error = response.status == STATUS_INVALID_REQUEST
                    ? CommandException.Error.INVALID_ARGUMENTS
                    : CommandException.Error.MODEM_ERR;
            complete(result, new CommandException(error, context));
        } catch (IOException exception) {
            Rlog.e(TAG, "initial-attach transport failed: request_id="
                    + request.requestId + " error=" + exception.getClass().getSimpleName());
            complete(result, new CommandException(
                    CommandException.Error.RADIO_NOT_AVAILABLE,
                    "initial-attach adapter unavailable"));
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                Rlog.w(TAG, "initial-attach socket close failed: request_id="
                        + request.requestId);
            }
        }
    }

    private static void complete(Message result, Throwable exception) {
        if (result == null) return;
        AsyncResult.forMessage(result, null, exception);
        result.sendToTarget();
    }

    private static boolean isPrintableAscii(byte[] value) {
        for (byte character : value) {
            int unsigned = character & 0xff;
            if (unsigned < 0x20 || unsigned > 0x7e) return false;
        }
        return true;
    }

    private static final class Request {
        final int requestId;
        final int authType;
        final byte[] apn;
        final byte[] protocol;
        final byte[] username;
        final byte[] password;

        private Request(int requestId, int authType, byte[] apn, byte[] protocol,
                byte[] username, byte[] password) {
            this.requestId = requestId;
            this.authType = authType;
            this.apn = apn;
            this.protocol = protocol;
            this.username = username;
            this.password = password;
        }

        static Request create(int requestId, String apn, String protocol,
                int authType, String username, String password) {
            if (apn == null || protocol == null || authType < -1 || authType > 3) {
                throw new IllegalArgumentException("invalid APN request fields");
            }
            byte[] apnBytes = apn.getBytes(UTF_8);
            byte[] protocolBytes = protocol.getBytes(UTF_8);
            byte[] usernameBytes = (username == null ? "" : username).getBytes(UTF_8);
            byte[] passwordBytes = (password == null ? "" : password).getBytes(UTF_8);
            if (apnBytes.length == 0 || apnBytes.length > MAX_APN_BYTES
                    || !isPrintableAscii(apnBytes)) {
                throw new IllegalArgumentException("invalid APN length or encoding");
            }
            String normalizedProtocol = protocol.toUpperCase(Locale.US);
            if (!("IP".equals(normalizedProtocol) || "IPV6".equals(normalizedProtocol)
                    || "IPV4V6".equals(normalizedProtocol))
                    || protocolBytes.length > MAX_PROTOCOL_BYTES) {
                throw new IllegalArgumentException("unsupported APN protocol");
            }
            protocolBytes = normalizedProtocol.getBytes(UTF_8);
            if (usernameBytes.length > MAX_CREDENTIAL_BYTES
                    || passwordBytes.length > MAX_CREDENTIAL_BYTES
                    || !isPrintableAscii(usernameBytes) || !isPrintableAscii(passwordBytes)) {
                throw new IllegalArgumentException("invalid credential length or encoding");
            }
            return new Request(requestId, authType, apnBytes, protocolBytes,
                    usernameBytes, passwordBytes);
        }
    }

    private static final class Response {
        final int status;
        final int stage;
        final int nativeResult;
        final int qmiError;
        final long profileId;
        final boolean rollbackAttempted;
        final boolean rollbackVerified;

        private Response(int status, int stage, int nativeResult, int qmiError,
                long profileId, boolean rollbackAttempted, boolean rollbackVerified) {
            this.status = status;
            this.stage = stage;
            this.nativeResult = nativeResult;
            this.qmiError = qmiError;
            this.profileId = profileId;
            this.rollbackAttempted = rollbackAttempted;
            this.rollbackVerified = rollbackVerified;
        }

        static Response read(DataInputStream input, int expectedRequestId) throws IOException {
            int magic = input.readInt();
            int version = input.readUnsignedShort();
            int status = input.readUnsignedShort();
            int requestId = input.readInt();
            int stage = input.readInt();
            int nativeResult = input.readInt();
            int qmiError = input.readInt();
            long profileId = input.readInt() & 0xffffffffL;
            boolean rollbackAttempted = input.readInt() != 0;
            boolean rollbackVerified = input.readInt() != 0;
            if (magic != MAGIC || version != VERSION || requestId != expectedRequestId) {
                throw new IOException("invalid adapter response header");
            }
            return new Response(status, stage, nativeResult, qmiError, profileId,
                    rollbackAttempted, rollbackVerified);
        }
    }
}
