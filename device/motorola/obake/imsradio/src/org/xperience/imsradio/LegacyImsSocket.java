package org.xperience.imsradio;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/** Bounded, single-owner transport for the SU6-7.7 legacy IMS protobuf socket. */
final class LegacyImsSocket {
    interface Listener {
        void onResponse(LegacyImsQmi.MsgTag tag, byte[] payload);
        void onUnsolicited(LegacyImsQmi.MsgTag tag, byte[] payload);
        void onTransportUnavailable(int token, int requestId);
    }

    private static final String TAG = "ObakeImsSocket";
    private static final String SOCKET = "/dev/socket/qmux_radio/rild_ims";
    private static final String SOCKET_PERMISSION_REPAIR_PROPERTY =
            "sys.ims.rild_socket_fix";
    private static final long SOCKET_PERMISSION_REPAIR_INTERVAL_MS = 8_000;
    private static final int MAX_FRAME = 64 * 1024;
    private static final int MAX_PENDING = 64;
    private static final long REQUEST_TIMEOUT_MS = 60_000;

    private final Listener mListener;
    private final ArrayBlockingQueue<Request> mWrites = new ArrayBlockingQueue<>(MAX_PENDING);
    private final HashMap<Integer, Pending> mPending = new HashMap<>();
    private final Object mLock = new Object();
    private volatile boolean mRunning;
    private LocalSocket mSocket;
    private long mLastPermissionRepairMs;

    LegacyImsSocket(Listener listener) {
        mListener = listener;
    }

    void start() {
        if (mRunning) return;
        mRunning = true;
        Thread thread = new Thread(this::run, "ims-radio-socket");
        thread.setDaemon(true);
        thread.start();
    }

    void stop() {
        mRunning = false;
        closeSocket();
        failAll();
    }

    boolean submit(int token, int requestId, MessageMicro payload) {
        return submit(token, requestId, payload, REQUEST_TIMEOUT_MS);
    }

    boolean submit(int token, int requestId, MessageMicro payload, long timeoutMs) {
        if (timeoutMs <= 0) return false;
        if (!mRunning) return false;
        Request request = new Request(token, requestId,
                payload == null ? new byte[0] : payload.toByteArray());
        synchronized (mLock) {
            if (mPending.containsKey(token) || mPending.size() >= MAX_PENDING) return false;
            mPending.put(token, new Pending(requestId,
                    SystemClock.elapsedRealtime() + timeoutMs));
        }
        if (!mWrites.offer(request)) {
            synchronized (mLock) { mPending.remove(token); }
            return false;
        }
        return true;
    }

    private void run() {
        long retryMs = 250;
        while (mRunning) {
            try {
                connect();
                retryMs = 250;
                Thread reader = new Thread(this::readLoop, "ims-radio-reader");
                reader.setDaemon(true);
                reader.start();
                while (mRunning && mSocket != null) {
                    expireRequests();
                    Request request = mWrites.poll(250, TimeUnit.MILLISECONDS);
                    if (request != null) write(request);
                    if (!reader.isAlive()) throw new EOFException("IMS socket reader stopped");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (IOException e) {
                if (mRunning) Log.w(TAG, "IMS socket unavailable; retrying", e);
            } finally {
                closeSocket();
                failAll();
            }
            if (mRunning) {
                SystemClock.sleep(retryMs);
                retryMs = Math.min(retryMs * 2, 8_000);
            }
        }
    }

    private void connect() throws IOException {
        LocalSocket socket = new LocalSocket();
        try {
            socket.connect(new LocalSocketAddress(
                    SOCKET, LocalSocketAddress.Namespace.FILESYSTEM));
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException closeError) {
                Log.w(TAG, "Unable to close failed IMS socket", closeError);
            }
            if (e.getMessage() != null && e.getMessage().contains("Permission denied")) {
                requestSocketPermissionRepair();
            }
            throw e;
        }
        mSocket = socket;
        Log.i(TAG, "Connected to legacy IMS socket");
    }

    private void requestSocketPermissionRepair() {
        long now = SystemClock.elapsedRealtime();
        if (now - mLastPermissionRepairMs < SOCKET_PERMISSION_REPAIR_INTERVAL_MS) return;
        mLastPermissionRepairMs = now;
        try {
            SystemProperties.set(SOCKET_PERMISSION_REPAIR_PROPERTY, "1");
        } catch (RuntimeException e) {
            Log.e(TAG, "Unable to request IMS socket permission repair", e);
        }
    }

    private void write(Request request) throws IOException {
        LocalSocket socket = mSocket;
        if (socket == null) throw new EOFException("IMS socket disconnected");
        LegacyImsQmi.MsgTag tag = new LegacyImsQmi.MsgTag()
                .setToken(request.token).setType(LegacyImsQmi.REQUEST)
                .setId(request.requestId).setError(LegacyImsQmi.E_SUCCESS);
        byte[] tagBytes = tag.toByteArray();
        ByteArrayOutputStream bodyBytes = new ByteArrayOutputStream();
        CodedOutputStreamMicro coded = CodedOutputStreamMicro.newInstance(bodyBytes);
        coded.writeRawVarint32(tagBytes.length);
        coded.writeRawBytes(tagBytes);
        coded.writeRawBytes(request.payload);
        coded.flush();
        byte[] body = bodyBytes.toByteArray();
        if (body.length == 0 || body.length > MAX_FRAME) throw new IOException("invalid outgoing IMS frame " + body.length);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeInt(body.length);
        out.write(body);
        out.flush();
    }

    private void readLoop() {
        try {
            DataInputStream in = new DataInputStream(mSocket.getInputStream());
            while (mRunning) {
                int length;
                try {
                    length = in.readInt();
                } catch (java.net.SocketTimeoutException timeout) {
                    continue;
                }
                if (length <= 0 || length > MAX_FRAME) throw new IOException("invalid incoming IMS frame " + length);
                byte[] body = new byte[length];
                in.readFully(body);
                dispatch(body);
            }
        } catch (IOException | RuntimeException e) {
            if (mRunning) Log.w(TAG, "IMS socket reader failed", e);
            closeSocket();
        }
    }

    private void dispatch(byte[] body) throws IOException {
        int[] header = readVarint32(body);
        int tagLength = header[0];
        int tagOffset = header[1];
        if (tagLength <= 0 || tagLength > body.length - tagOffset) {
            throw new IOException("invalid IMS tag length " + tagLength);
        }
        byte[] tagBytes = new byte[tagLength];
        System.arraycopy(body, tagOffset, tagBytes, 0, tagLength);
        int payloadOffset = tagOffset + tagLength;
        byte[] payload = new byte[body.length - payloadOffset];
        System.arraycopy(body, payloadOffset, payload, 0, payload.length);
        LegacyImsQmi.MsgTag tag = LegacyImsQmi.MsgTag.parseFrom(tagBytes);
        if (tag.getType() == LegacyImsQmi.RESPONSE) {
            synchronized (mLock) { mPending.remove(tag.getToken()); }
            mListener.onResponse(tag, payload);
        } else if (tag.getType() == LegacyImsQmi.UNSOL_RESPONSE) {
            mListener.onUnsolicited(tag, payload);
        } else {
            Log.w(TAG, "Ignoring IMS message with type=" + tag.getType());
        }
    }

    private static int[] readVarint32(byte[] data) throws IOException {
        int value = 0;
        for (int i = 0; i < 5 && i < data.length; i++) {
            int current = data[i] & 0xff;
            value |= (current & 0x7f) << (i * 7);
            if ((current & 0x80) == 0) return new int[] { value, i + 1 };
        }
        throw new IOException("malformed IMS tag length");
    }

    private void expireRequests() {
        long now = SystemClock.elapsedRealtime();
        synchronized (mLock) {
            Iterator<Map.Entry<Integer, Pending>> iterator = mPending.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Pending> entry = iterator.next();
                if (entry.getValue().deadlineMs <= now) {
                    mListener.onTransportUnavailable(entry.getKey(), entry.getValue().requestId);
                    iterator.remove();
                }
            }
        }
    }

    private void failAll() {
        synchronized (mLock) {
            for (Map.Entry<Integer, Pending> entry : mPending.entrySet()) {
                mListener.onTransportUnavailable(entry.getKey(), entry.getValue().requestId);
            }
            mPending.clear();
            mWrites.clear();
        }
    }

    private synchronized void closeSocket() {
        LocalSocket socket = mSocket;
        mSocket = null;
        if (socket != null) {
            try { socket.close(); } catch (IOException ignored) { /* already disconnected */ }
        }
    }

    private static final class Request {
        final int token;
        final int requestId;
        final byte[] payload;
        Request(int token, int requestId, byte[] payload) {
            this.token = token;
            this.requestId = requestId;
            this.payload = payload;
        }
    }

    private static final class Pending {
        final int requestId;
        final long deadlineMs;
        Pending(int requestId, long deadlineMs) {
            this.requestId = requestId;
            this.deadlineMs = deadlineMs;
        }
    }
}
