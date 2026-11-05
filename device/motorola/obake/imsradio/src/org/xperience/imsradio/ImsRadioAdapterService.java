package org.xperience.imsradio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.util.Log;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.MessageMicro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import vendor.qti.hardware.radio.ims.V1_0.*;

/** Publishes the Oreo Qualcomm IMS HIDL ABI over Motorola's SU6-7.7 IMS socket. */
public final class ImsRadioAdapterService extends Service {
    private static final String TAG = "ObakeImsRadio";
    private static final String INSTANCE = "imsradio0";
    private ImsRadioImpl mRadio;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!SystemProperties.getBoolean("persist.ims.radio_adapter.enabled", true)) {
            Log.i(TAG, "IMS radio adapter disabled");
            stopSelf();
            return;
        }
        try {
            mRadio = new ImsRadioImpl(readEarlyVolteIntent());
            mRadio.start();
            mRadio.registerAsService(INSTANCE);
            Log.i(TAG, "Published IImsRadio/" + INSTANCE);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to publish IImsRadio/" + INSTANCE, e);
            if (mRadio != null) mRadio.stop();
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        if (mRadio != null) mRadio.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    private int readEarlyVolteIntent() {
        if (!SystemProperties.getBoolean("persist.ims.qipcall_bootstrap.enabled", true)) {
            return -1;
        }
        try {
            boolean enabled = Settings.Global.getInt(getContentResolver(),
                    Settings.Global.ENHANCED_4G_MODE_ENABLED, 1) == 1;
            boolean nonTty = Settings.Secure.getInt(getContentResolver(),
                    Settings.Secure.PREFERRED_TTY_MODE, TelecomManager.TTY_MODE_OFF)
                    == TelecomManager.TTY_MODE_OFF;
            return enabled && nonTty ? 1 : 0;
        } catch (RuntimeException e) {
            Log.w(TAG, "Early VoLTE intent unavailable; deferring to framework", e);
            return -1;
        }
    }

    private static final class ImsRadioImpl extends IImsRadio.Stub
            implements LegacyImsSocket.Listener {
        private static final int RADIO_NOT_AVAILABLE = LegacyImsQmi.E_RADIO_NOT_AVAILABLE;
        private static final int GENERIC_FAILURE = LegacyImsQmi.E_GENERIC_FAILURE;
        private static final int INVALID_PARAMETER = LegacyImsQmi.E_INVALID_PARAMETER;
        private static final int NOT_SUPPORTED = LegacyImsQmi.E_REQUEST_NOT_SUPPORTED;
        private static final int MAX_POLICY_JOBS = 8;
        private static final long POLICY_TIMEOUT_MS = 32_000;
        private static final int EARLY_POLICY_TOKEN = -3;
        // Reserved for the adapter's session-local GET_CURRENT_CALLS query.
        private static final int CALL_LIST_QUERY_TOKEN = -2;
        private static final long CALL_LIST_TIMEOUT_MS = 5_000;
        private static final int MAX_IMS_CALLS = 16;
        private static final int MAX_IMS_CALL_BYTES = 2_048;
        private final Object mCallbackLock = new Object();
        private final Object mPolicyLock = new Object();
        private final Object mCallListLock = new Object();
        private final LegacyImsSocket mSocket = new LegacyImsSocket(this);
        private final Map<Integer, ServiceStatusInfo> mPendingPolicies = new HashMap<>();
        private final Map<Integer, byte[]> mActiveCalls = new HashMap<>();
        private final ThreadPoolExecutor mPolicyExecutor = new ThreadPoolExecutor(
                1, 1, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(MAX_POLICY_JOBS));
        private int mPolicyJobs;
        private volatile Process mPolicyProcess;
        private volatile int mEarlyPolicy;
        private volatile boolean mEarlyPolicyApplied;
        private volatile boolean mFrameworkPolicyConfirmed;
        private boolean mCallListPending;
        private boolean mCallListDirty;
        private IImsRadioResponse mResponse;
        private IImsRadioIndication mIndication;

        ImsRadioImpl(int earlyPolicy) {
            mEarlyPolicy = earlyPolicy;
            if (earlyPolicy >= 0) {
                SystemProperties.set("sys.ims.volte.intent", Integer.toString(earlyPolicy));
            }
        }

        void start() {
            mSocket.start();
            if (mEarlyPolicy >= 0) {
                try {
                    mPolicyExecutor.execute(this::applyEarlyQipcallPolicy);
                } catch (RejectedExecutionException e) {
                    Log.e(TAG, "Unable to queue early QIPCALL policy", e);
                }
            }
        }
        void stop() {
            mSocket.stop();
            Process process = mPolicyProcess;
            if (process != null) process.destroy();
            mPolicyExecutor.shutdownNow();
        }

        @Override
        public void setCallback(IImsRadioResponse response, IImsRadioIndication indication) {
            synchronized (mCallbackLock) {
                mResponse = response;
                mIndication = indication;
            }
            requestCallListRefresh("callback");
        }

        private boolean enabled() {
            return SystemProperties.getBoolean("persist.ims.radio_adapter.enabled", true);
        }

        private boolean send(int token, int requestId, MessageMicro payload) {
            if (token == CALL_LIST_QUERY_TOKEN) {
                Log.e(TAG, "Rejecting client use of reserved call-list token id=" + requestId);
                respondError(token, requestId, INVALID_PARAMETER);
                return false;
            }
            boolean submitted = enabled() && mSocket.submit(token, requestId, payload);
            if (!submitted) {
                respondError(token, requestId, RADIO_NOT_AVAILABLE);
            }
            return submitted;
        }

        @Override public void getImsRegistrationState(int token) {
            send(token, LegacyImsQmi.REQUEST_IMS_REGISTRATION_STATE, null);
        }

        @Override public void queryServiceStatus(int token) {
            send(token, LegacyImsQmi.REQUEST_QUERY_SERVICE_STATUS, null);
        }

        @Override public void setServiceStatus(int token, ServiceStatusInfo info) {
            int policy = classifyLteVoicePolicy(info);
            if (policy == -1) {
                String access = info.accTechStatus.isEmpty() ? "none" :
                        "network=" + info.accTechStatus.get(0).networkMode +
                        ",status=" + info.accTechStatus.get(0).status +
                        ",restrict=" + info.accTechStatus.get(0).restrictCause;
                Log.w(TAG, "Rejecting ambiguous LTE voice policy token=" + token +
                        " valid=" + info.hasIsValid + "/" + info.isValid +
                        " type=" + info.type + " callType=" + info.callType +
                        " outerStatus=" + info.status +
                        " outerRestrict=" + info.restrictCause +
                        " accessCount=" + info.accTechStatus.size() +
                        " access=" + access);
                respondError(token, LegacyImsQmi.REQUEST_SET_SERVICE_STATUS,
                        INVALID_PARAMETER);
                return;
            }
            boolean bootstrapMismatch = policy >= 0 && mEarlyPolicyApplied &&
                    !mFrameworkPolicyConfirmed && policy != mEarlyPolicy;
            if (policy >= 0 && mEarlyPolicyApplied && policy == mEarlyPolicy) {
                mFrameworkPolicyConfirmed = true;
                SystemProperties.set("sys.ims.volte.intent", Integer.toString(policy));
                Log.i(TAG, "Framework confirmed early QIPCALL policy desired=" + policy);
            } else if (bootstrapMismatch &&
                    !"0".equals(SystemProperties.get("sys.ims.carrier.volte", ""))) {
                // QIms emits disabled defaults before CarrierConfig is loaded.
                // Legacy QCRIL applies request 30 to QIPCALL itself, so merely
                // skipping our policy helper is insufficient: forwarding this
                // request would still undo fresh user intent.  Acknowledge the
                // transient default locally and wait for loaded home-SIM policy.
                Log.i(TAG, "Suppressing pre-carrier service status token=" + token +
                        " desired=" + policy + " early=" + mEarlyPolicy);
                IImsRadioResponse target;
                synchronized (mCallbackLock) { target = mResponse; }
                if (target == null) {
                    Log.w(TAG, "No IMS response callback for suppressed service status token=" +
                            token);
                } else {
                    deliverServiceStatusResponse(target, token, LegacyImsQmi.E_SUCCESS);
                }
                return;
            }
            if (bootstrapMismatch) {
                Log.i(TAG, "Accepting carrier-authoritative service status token=" + token +
                        " desired=" + policy + " carrierVolte=0");
            }
            if (policy >= 0) {
                SystemProperties.set("sys.ims.volte.intent", Integer.toString(policy));
            }
            // Any mismatch that survives the pre-carrier suppression above is
            // authoritative and needs the same QIPCALL verification as an
            // ordinary framework policy update.
            boolean tracked = policy >= 0 &&
                    SystemProperties.getBoolean("persist.ims.qipcall_bridge.enabled", true);
            if (tracked) {
                synchronized (mPolicyLock) {
                    if (mPolicyJobs >= MAX_POLICY_JOBS ||
                            mPendingPolicies.containsKey(token)) {
                        Log.w(TAG, "QIPCALL policy queue full or duplicate token=" + token);
                        respondError(token, LegacyImsQmi.REQUEST_SET_SERVICE_STATUS,
                                GENERIC_FAILURE);
                        return;
                    }
                    mPendingPolicies.put(token, copyServiceStatus(info));
                    mPolicyJobs++;
                }
            }
            if (!send(token, LegacyImsQmi.REQUEST_SET_SERVICE_STATUS, toLegacy(info)) && tracked) {
                finishPendingPolicy(token);
            }
        }

        @Override public void dial(int token, DialRequest request) {
            LegacyImsQmi.Dial dial = new LegacyImsQmi.Dial()
                    .setAddress(request.address).setClir(request.clirMode)
                    .setPresentation(request.presentation);
            if (request.hasCallDetails) dial.setCallDetails(toLegacy(request.callDetails));
            if (request.hasIsConferenceUri) dial.setIsConferenceUri(request.isConferenceUri);
            if (request.hasIsCallPull) dial.setIsCallPull(request.isCallPull);
            if (request.hasIsEncrypted) dial.setIsEncrypted(request.isEncrypted);
            send(token, LegacyImsQmi.REQUEST_DIAL, dial);
        }

        @Override public void answer(int token, int callType, int presentation, int mode) {
            LegacyImsQmi.Answer answer = new LegacyImsQmi.Answer()
                    .setCallType(callType).setPresentation(presentation);
            if (mode >= 0) answer.setRttMode(mode);
            send(token, LegacyImsQmi.REQUEST_ANSWER, answer);
        }

        @Override public void hangup(int token, HangupRequestInfo info) {
            LegacyImsQmi.Hangup hangup = new LegacyImsQmi.Hangup();
            if (info.connIndex >= 0) hangup.setConnIndex(info.connIndex);
            if (info.hasMultiParty) hangup.setMultiParty(info.multiParty);
            if (!info.connUri.isEmpty()) hangup.setConnUri(info.connUri);
            if (info.conf_id >= 0) hangup.setConfId(info.conf_id);
            send(token, LegacyImsQmi.REQUEST_HANGUP, hangup);
        }

        @Override
        public void onResponse(LegacyImsQmi.MsgTag tag, byte[] payload) {
            int token = tag.getToken();
            if (token == CALL_LIST_QUERY_TOKEN) {
                handleCallListResponse(tag, payload);
                return;
            }
            if (tag.getId() == LegacyImsQmi.REQUEST_SET_SERVICE_STATUS) {
                ServiceStatusInfo requestedStatus;
                synchronized (mPolicyLock) {
                    requestedStatus = mPendingPolicies.remove(token);
                }
                if (requestedStatus != null) {
                    IImsRadioResponse target;
                    synchronized (mCallbackLock) { target = mResponse; }
                    if (tag.getError() != LegacyImsQmi.E_SUCCESS || target == null) {
                        finishPolicyJob();
                        if (target != null) {
                            deliverServiceStatusResponse(target, token, tag.getError());
                        }
                        return;
                    }
                    try {
                        final boolean requested = classifyLteVoicePolicy(requestedStatus) == 1;
                        mPolicyExecutor.execute(() -> {
                            boolean applied = applyQipcallPolicy(requested, token);
                            finishPolicyJob();
                            deliverServiceStatusResponse(target, token,
                                    applied ? LegacyImsQmi.E_SUCCESS : GENERIC_FAILURE);
                        });
                    } catch (RejectedExecutionException e) {
                        finishPolicyJob();
                        Log.e(TAG, "QIPCALL worker rejected token=" + token, e);
                        deliverServiceStatusResponse(target, token, GENERIC_FAILURE);
                    }
                    return;
                }
            }
            IImsRadioResponse response;
            synchronized (mCallbackLock) { response = mResponse; }
            if (response == null) return;
            int error = tag.getError();
            try {
                switch (tag.getId()) {
                    case LegacyImsQmi.REQUEST_IMS_REGISTRATION_STATE:
                        response.getRegistrationResponse(token, error,
                                payload.length == 0 ? new RegistrationInfo()
                                        : toHidl(LegacyImsQmi.Registration.parseFrom(payload)));
                        break;
                    case LegacyImsQmi.REQUEST_QUERY_SERVICE_STATUS:
                        ArrayList<ServiceStatusInfo> status = payload.length == 0
                                ? new ArrayList<>()
                                : toHidl(LegacyImsQmi.SrvStatusList.parseFrom(payload));
                        response.queryServiceStatusResponse(token, error, status);
                        break;
                    case LegacyImsQmi.REQUEST_SET_SERVICE_STATUS:
                        response.setServiceStatusResponse(token, error);
                        break;
                    case LegacyImsQmi.REQUEST_IMS_REG_STATE_CHANGE:
                        response.requestRegistrationChangeResponse(token, error);
                        break;
                    case LegacyImsQmi.REQUEST_DIAL: response.dialResponse(token, error); break;
                    case LegacyImsQmi.REQUEST_ANSWER: response.answerResponse(token, error); break;
                    case LegacyImsQmi.REQUEST_HANGUP: response.hangupResponse(token, error); break;
                    default:
                        Log.w(TAG, "No response mapping for request " + tag.getId());
                }
            } catch (IOException | RemoteException e) {
                Log.e(TAG, "Failed to deliver IMS response id=" + tag.getId(), e);
            }
        }

        @Override
        public void onUnsolicited(LegacyImsQmi.MsgTag tag, byte[] payload) {
            if (tag.getId() == LegacyImsQmi.UNSOL_RESPONSE_CALL_STATE_CHANGED) {
                if (payload.length == 0) {
                    requestCallListRefresh("call-state notification");
                } else {
                    try {
                        deliverCallList(LegacyImsQmi.CallList.parseFrom(payload));
                    } catch (IOException e) {
                        Log.e(TAG, "Rejecting malformed IMS call-state payload bytes=" +
                                payload.length, e);
                    }
                }
                return;
            }
            IImsRadioIndication indication;
            synchronized (mCallbackLock) { indication = mIndication; }
            if (indication == null) return;
            try {
                switch (tag.getId()) {
                    case LegacyImsQmi.UNSOL_CALL_RING: indication.onRing(); break;
                    case LegacyImsQmi.UNSOL_RINGBACK_TONE:
                        indication.onRingbackTone(LegacyImsQmi.RingBackTone
                                .parseFrom(payload).getFlag());
                        break;
                    case LegacyImsQmi.UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED:
                        indication.onRegistrationChanged(toHidl(
                                LegacyImsQmi.Registration.parseFrom(payload)));
                        break;
                    case LegacyImsQmi.UNSOL_ENTER_EMERGENCY_CALLBACK_MODE:
                        indication.onEnterEmergencyCallBackMode(); break;
                    case LegacyImsQmi.UNSOL_EXIT_EMERGENCY_CALLBACK_MODE:
                        indication.onExitEmergencyCallBackMode(); break;
                    case LegacyImsQmi.UNSOL_SRV_STATUS_UPDATE:
                        ArrayList<ServiceStatusInfo> status = toHidl(
                                LegacyImsQmi.SrvStatusList.parseFrom(payload));
                        indication.onServiceStatusChanged(status);
                        break;
                    case LegacyImsQmi.UNSOL_TTY_NOTIFICATION:
                        indication.onTtyNotification(toHidl(
                                LegacyImsQmi.TtyNotify.parseFrom(payload)));
                        break;
                    case LegacyImsQmi.UNSOL_RADIO_STATE_CHANGED:
                        indication.onRadioStateChanged(toRadioState(
                                LegacyImsQmi.RadioStateChanged.parseFrom(payload).getState()));
                        break;
                    default:
                        Log.d(TAG, "Ignoring unsupported IMS indication " + tag.getId());
                }
            } catch (IOException | RemoteException e) {
                Log.e(TAG, "Failed to deliver IMS indication id=" + tag.getId(), e);
            }
        }

        @Override public void onTransportUnavailable(int token, int requestId) {
            if (token == CALL_LIST_QUERY_TOKEN) {
                synchronized (mCallListLock) {
                    mCallListPending = false;
                }
                Log.e(TAG, "Legacy GET_CURRENT_CALLS timed out or transport disconnected");
                return;
            }
            if (requestId == LegacyImsQmi.REQUEST_SET_SERVICE_STATUS) {
                finishPendingPolicy(token);
            }
            respondError(token, requestId, RADIO_NOT_AVAILABLE);
        }

        private void requestCallListRefresh(String reason) {
            synchronized (mCallListLock) {
                if (mCallListPending) {
                    mCallListDirty = true;
                    return;
                }
                mCallListPending = true;
                mCallListDirty = false;
            }
            if (!enabled() || !mSocket.submit(CALL_LIST_QUERY_TOKEN,
                    LegacyImsQmi.REQUEST_GET_CURRENT_CALLS, null, CALL_LIST_TIMEOUT_MS)) {
                synchronized (mCallListLock) {
                    mCallListPending = false;
                }
                Log.e(TAG, "Unable to queue legacy GET_CURRENT_CALLS reason=" + reason);
            }
        }

        private void handleCallListResponse(LegacyImsQmi.MsgTag tag, byte[] payload) {
            boolean queryAgain;
            synchronized (mCallListLock) {
                if (!mCallListPending || tag.getId() != LegacyImsQmi.REQUEST_GET_CURRENT_CALLS) {
                    Log.e(TAG, "Rejecting unexpected internal call-list response id=" +
                            tag.getId());
                    return;
                }
                mCallListPending = false;
                queryAgain = mCallListDirty;
                mCallListDirty = false;
            }
            if (tag.getError() != LegacyImsQmi.E_SUCCESS) {
                Log.e(TAG, "Legacy GET_CURRENT_CALLS failed error=" + tag.getError());
                return;
            }
            try {
                deliverCallList(LegacyImsQmi.CallList.parseFrom(payload));
            } catch (IOException e) {
                Log.e(TAG, "Rejecting malformed GET_CURRENT_CALLS payload bytes=" +
                        payload.length, e);
                return;
            }
            if (queryAgain) requestCallListRefresh("coalesced notification");
        }

        private void deliverCallList(LegacyImsQmi.CallList snapshot)
                throws IOException {
            if (snapshot.getCallAttributesCount() > MAX_IMS_CALLS) {
                throw new IOException("too many IMS calls: " +
                        snapshot.getCallAttributesCount());
            }
            ArrayList<CallInfo> calls = new ArrayList<>();
            Map<Integer, byte[]> nextActive = new HashMap<>();
            for (LegacyImsQmi.CallList.Call call : snapshot.getCallAttributesList()) {
                if (!call.hasState() || !call.hasIndex() || !call.hasCallDetails() ||
                        call.getIndex() <= 0 || call.getState() < 0 ||
                        call.getState() > LegacyImsQmi.CALL_END) {
                    throw new IOException("invalid IMS call identity/state/details");
                }
                byte[] encoded = call.toByteArray();
                if (encoded.length > MAX_IMS_CALL_BYTES ||
                        nextActive.containsKey(call.getIndex())) {
                    throw new IOException("oversized or duplicate IMS call index=" +
                            call.getIndex());
                }
                calls.add(toHidl(call));
                if (call.getState() != LegacyImsQmi.CALL_END) {
                    nextActive.put(call.getIndex(), encoded);
                }
            }
            synchronized (mCallListLock) {
                for (Map.Entry<Integer, byte[]> previous : mActiveCalls.entrySet()) {
                    if (!nextActive.containsKey(previous.getKey())) {
                        LegacyImsQmi.CallList.Call ended =
                                LegacyImsQmi.CallList.Call.parseFrom(previous.getValue());
                        ended.setState(LegacyImsQmi.CALL_END);
                        calls.add(toHidl(ended));
                    }
                }
            }
            IImsRadioIndication indication;
            synchronized (mCallbackLock) { indication = mIndication; }
            if (indication == null) {
                Log.w(TAG, "No IMS indication callback for current call list");
                return;
            }
            try {
                indication.onCallStateChanged(calls);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to deliver current IMS call list", e);
                return;
            }
            synchronized (mCallListLock) {
                mActiveCalls.clear();
                mActiveCalls.putAll(nextActive);
            }
        }

        private void finishPendingPolicy(int token) {
            synchronized (mPolicyLock) {
                if (mPendingPolicies.remove(token) != null && mPolicyJobs > 0) {
                    mPolicyJobs--;
                }
            }
        }

        private void finishPolicyJob() {
            synchronized (mPolicyLock) {
                if (mPolicyJobs > 0) mPolicyJobs--;
            }
        }

        private static void deliverServiceStatusResponse(IImsRadioResponse response,
                int token, int error) {
            try {
                response.setServiceStatusResponse(token, error);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to deliver deferred service response token=" + token, e);
            }
        }

        private boolean applyQipcallPolicy(boolean enabled, int token) {
            Process process = null;
            String detail = "";
            try {
                ProcessBuilder builder = new ProcessBuilder(
                        "/system/bin/ims_qipcall_policy", enabled ? "1" : "0");
                // Qualcomm's QMI libraries are very noisy on stderr. Inherit it
                // so their diagnostics remain visible without filling the
                // child's stdout pipe and blocking policy completion.
                builder.redirectError(ProcessBuilder.Redirect.INHERIT);
                builder.environment().put("LD_LIBRARY_PATH",
                        "/system/lib/radio-su6-77:/system/lib:/system/vendor/lib");
                builder.environment().put("LD_PRELOAD", "/system/lib/libshim_log.so");
                process = builder.start();
                mPolicyProcess = process;
                long deadline = SystemClock.elapsedRealtime() + POLICY_TIMEOUT_MS;
                int exit = -1;
                while (SystemClock.elapsedRealtime() < deadline) {
                    try {
                        exit = process.exitValue();
                        break;
                    } catch (IllegalThreadStateException running) {
                        SystemClock.sleep(100);
                    }
                }
                if (exit < 0) {
                    process.destroy();
                    Log.e(TAG, "QIPCALL policy timeout token=" + token);
                    return false;
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null && output.length() < 512) {
                    if (output.length() != 0) output.append(';');
                    output.append(line);
                }
                detail = output.toString();
                if (exit != 0) {
                    Log.e(TAG, "QIPCALL policy failed token=" + token + " desired=" +
                            (enabled ? 1 : 0) + " exit=" + exit + " detail=" + detail);
                    return false;
                }
                Log.i(TAG, "QIPCALL policy verified token=" + token + " desired=" +
                        (enabled ? 1 : 0) + " detail=" + detail);
                return true;
            } catch (IOException e) {
                Log.e(TAG, "Unable to run QIPCALL policy token=" + token, e);
                return false;
            } finally {
                if (mPolicyProcess == process) mPolicyProcess = null;
            }
        }

        private void applyEarlyQipcallPolicy() {
            if (applyQipcallPolicy(mEarlyPolicy == 1, EARLY_POLICY_TOKEN)) {
                mEarlyPolicyApplied = true;
                Log.i(TAG, "Applied early QIPCALL policy desired=" + mEarlyPolicy);
            } else {
                mEarlyPolicy = -1;
            }
        }

        // -2 is unrelated, -1 is an ambiguous LTE voice policy, 0/1 is the
        // explicit modem VoLTE value to verify after legacy request 30 succeeds.
        private static int classifyLteVoicePolicy(ServiceStatusInfo info) {
            if (info.callType != CallType.CALL_TYPE_VOICE) return -2;
            if (!info.hasIsValid) return -1;
            if (!info.isValid && info.accTechStatus.isEmpty()) return 0;
            if (!info.isValid || info.accTechStatus.size() != 1) return -1;
            StatusForAccessTech access = info.accTechStatus.get(0);
            if (access.networkMode != RadioTechType.RADIO_TECH_LTE) return -2;
            if ((access.status != StatusType.STATUS_DISABLED &&
                    access.status != StatusType.STATUS_ENABLED) ||
                    info.restrictCause != 0 || access.restrictCause != 0) {
                return -1;
            }
            return access.status == StatusType.STATUS_ENABLED ? 1 : 0;
        }

        private void respondError(int token, int requestId, int error) {
            IImsRadioResponse response;
            synchronized (mCallbackLock) { response = mResponse; }
            if (response == null) return;
            try {
                switch (requestId) {
                    case LegacyImsQmi.REQUEST_IMS_REGISTRATION_STATE:
                        response.getRegistrationResponse(token, error, new RegistrationInfo()); break;
                    case LegacyImsQmi.REQUEST_QUERY_SERVICE_STATUS:
                        response.queryServiceStatusResponse(token, error, new ArrayList<>()); break;
                    case LegacyImsQmi.REQUEST_SET_SERVICE_STATUS:
                        response.setServiceStatusResponse(token, error); break;
                    case LegacyImsQmi.REQUEST_IMS_REG_STATE_CHANGE:
                        response.requestRegistrationChangeResponse(token, error); break;
                    case LegacyImsQmi.REQUEST_DIAL: response.dialResponse(token, error); break;
                    case LegacyImsQmi.REQUEST_ANSWER: response.answerResponse(token, error); break;
                    case LegacyImsQmi.REQUEST_HANGUP: response.hangupResponse(token, error); break;
                    default: respondUnsupported(response, token, requestId, error);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to return IMS error for request " + requestId, e);
            }
        }

        private static void respondUnsupported(IImsRadioResponse r, int t, int id, int e)
                throws RemoteException {
            switch (id) {
                case LegacyImsQmi.REQUEST_ADD_PARTICIPANT: r.addParticipantResponse(t,e); break;
                case LegacyImsQmi.REQUEST_CONFERENCE: r.conferenceResponse(t,e,new SipErrorInfo()); break;
                case LegacyImsQmi.REQUEST_DEFLECT_CALL: r.deflectCallResponse(t,e); break;
                case LegacyImsQmi.REQUEST_EXIT_EMERGENCY_CALLBACK_MODE: r.exitEmergencyCallbackModeResponse(t,e); break;
                case LegacyImsQmi.REQUEST_EXPLICIT_CALL_TRANSFER: r.explicitCallTransferResponse(t,e); break;
                case LegacyImsQmi.REQUEST_QUERY_CALL_WAITING: r.getCallWaitingResponse(t,e,0,0,new SipErrorInfo()); break;
                case LegacyImsQmi.REQUEST_QUERY_CLIP: r.getClipResponse(t,e,new ClipProvisionStatus()); break;
                case LegacyImsQmi.REQUEST_GET_CLIR: r.getClirResponse(t,e,new ClirInfo(),false); break;
                case LegacyImsQmi.REQUEST_GET_COLR: r.getColrResponse(t,e,new ColrInfo()); break;
                case LegacyImsQmi.REQUEST_GET_IMS_CONFIG: r.getConfigResponse(t,e,new ConfigInfo()); break;
                case LegacyImsQmi.REQUEST_GET_IMS_SUB_CONFIG: r.getImsSubConfigResponse(t,e,new ImsSubConfigInfo()); break;
                case LegacyImsQmi.REQUEST_GET_RTP_ERROR_STATISTICS: r.getRtpErrorStatisticsResponse(t,e,0); break;
                case LegacyImsQmi.REQUEST_GET_RTP_STATISTICS: r.getRtpStatisticsResponse(t,e,0); break;
                case LegacyImsQmi.REQUEST_HOLD: r.holdResponse(t,e,new SipErrorInfo()); break;
                case LegacyImsQmi.REQUEST_MODIFY_CALL_CONFIRM: r.modifyCallConfirmResponse(t,e); break;
                case LegacyImsQmi.REQUEST_MODIFY_CALL_INITIATE: r.modifyCallInitiateResponse(t,e); break;
                case LegacyImsQmi.REQUEST_QUERY_CALL_FORWARD_STATUS: r.queryCallForwardStatusResponse(t,e,new ArrayList<>(),new SipErrorInfo()); break;
                case LegacyImsQmi.REQUEST_RESUME: r.resumeResponse(t,e,new SipErrorInfo()); break;
                case LegacyImsQmi.REQUEST_DTMF: r.sendDtmfResponse(t,e); break;
                case LegacyImsQmi.REQUEST_SEND_GEOLOCATION_INFO: r.sendGeolocationInfoResponse(t,e); break;
                case LegacyImsQmi.REQUEST_SEND_RTT_MSG: r.sendRttMessageResponse(t,e); break;
                case LegacyImsQmi.REQUEST_SET_CLIR: r.setClirResponse(t,e); break;
                case LegacyImsQmi.REQUEST_SET_IMS_CONFIG: r.setConfigResponse(t,e,new ConfigInfo()); break;
                case LegacyImsQmi.REQUEST_SET_SUPP_SVC_NOTIFICATION: r.setSuppServiceNotificationResponse(t,e,0); break;
                case LegacyImsQmi.REQUEST_SEND_UI_TTY_MODE: r.setUiTTYModeResponse(t,e); break;
                case LegacyImsQmi.REQUEST_DTMF_START: r.startDtmfResponse(t,e); break;
                case LegacyImsQmi.REQUEST_DTMF_STOP: r.stopDtmfResponse(t,e); break;
                case LegacyImsQmi.REQUEST_SUPP_SVC_STATUS: r.suppServiceStatusResponse(t,e,new SuppServiceStatus()); break;
                case LegacyImsQmi.REQUEST_CANCEL_MODIFY_CALL: r.cancelModifyCallResponse(t,e); break;
                default: Log.w(TAG, "Unknown unsupported IMS request " + id);
            }
        }

        private void unsupported(int token, int requestId) {
            respondError(token, requestId, NOT_SUPPORTED);
        }

        @Override public void addParticipant(int t, DialRequest v) { unsupported(t,LegacyImsQmi.REQUEST_ADD_PARTICIPANT); }
        @Override public void cancelModifyCall(int t,int v) { unsupported(t,LegacyImsQmi.REQUEST_CANCEL_MODIFY_CALL); }
        @Override public void conference(int t) { unsupported(t,LegacyImsQmi.REQUEST_CONFERENCE); }
        @Override public void deflectCall(int t,DeflectRequestInfo v) { unsupported(t,LegacyImsQmi.REQUEST_DEFLECT_CALL); }
        @Override public void exitEmergencyCallbackMode(int t) { unsupported(t,LegacyImsQmi.REQUEST_EXIT_EMERGENCY_CALLBACK_MODE); }
        @Override public void explicitCallTransfer(int t,ExplicitCallTransferInfo v) { unsupported(t,LegacyImsQmi.REQUEST_EXPLICIT_CALL_TRANSFER); }
        @Override public void getCallWaiting(int t,int v) { unsupported(t,LegacyImsQmi.REQUEST_QUERY_CALL_WAITING); }
        @Override public void getClip(int t) { unsupported(t,LegacyImsQmi.REQUEST_QUERY_CLIP); }
        @Override public void getClir(int t) { unsupported(t,LegacyImsQmi.REQUEST_GET_CLIR); }
        @Override public void getColr(int t) { unsupported(t,LegacyImsQmi.REQUEST_GET_COLR); }
        @Override public void getConfig(int t,ConfigInfo v) { unsupported(t,LegacyImsQmi.REQUEST_GET_IMS_CONFIG); }
        @Override public void getImsSubConfig(int t) { unsupported(t,LegacyImsQmi.REQUEST_GET_IMS_SUB_CONFIG); }
        @Override public void getRtpErrorStatistics(int t) { unsupported(t,LegacyImsQmi.REQUEST_GET_RTP_ERROR_STATISTICS); }
        @Override public void getRtpStatistics(int t) { unsupported(t,LegacyImsQmi.REQUEST_GET_RTP_STATISTICS); }
        @Override public void hold(int t,int v) { unsupported(t,LegacyImsQmi.REQUEST_HOLD); }
        @Override public void modifyCallConfirm(int t,CallModifyInfo v) { unsupported(t,LegacyImsQmi.REQUEST_MODIFY_CALL_CONFIRM); }
        @Override public void modifyCallInitiate(int t,CallModifyInfo v) { unsupported(t,LegacyImsQmi.REQUEST_MODIFY_CALL_INITIATE); }
        @Override public void queryCallForwardStatus(int t,CallForwardInfo v) { unsupported(t,LegacyImsQmi.REQUEST_QUERY_CALL_FORWARD_STATUS); }
        @Override public void requestRegistrationChange(int token, int state) {
            if (state < RegState.REGISTERED || state > RegState.REGISTERING) {
                respondError(token, LegacyImsQmi.REQUEST_IMS_REG_STATE_CHANGE,
                        INVALID_PARAMETER);
                return;
            }
            send(token, LegacyImsQmi.REQUEST_IMS_REG_STATE_CHANGE,
                    new LegacyImsQmi.Registration().setState(fromHidlRegistration(state)));
        }
        @Override public void resume(int t,int v) { unsupported(t,LegacyImsQmi.REQUEST_RESUME); }
        @Override public void sendDtmf(int t,DtmfInfo v) { unsupported(t,LegacyImsQmi.REQUEST_DTMF); }
        @Override public void sendGeolocationInfo(int t,double a,double b,AddressInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SEND_GEOLOCATION_INFO); }
        @Override public void sendRttMessage(int t,String v) { unsupported(t,LegacyImsQmi.REQUEST_SEND_RTT_MSG); }
        @Override public void setCallForwardStatus(int t,CallForwardInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SET_CALL_FORWARD_STATUS); }
        @Override public void setCallWaiting(int t,int a,int b) { unsupported(t,LegacyImsQmi.REQUEST_SET_CALL_WAITING); }
        @Override public void setClir(int t,ClirInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SET_CLIR); }
        @Override public void setColr(int t,ColrInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SET_COLR); }
        @Override public void setConfig(int t,ConfigInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SET_IMS_CONFIG); }
        @Override public void setSuppServiceNotification(int t,int v) { unsupported(t,LegacyImsQmi.REQUEST_SET_SUPP_SVC_NOTIFICATION); }
        @Override public void setUiTtyMode(int t,TtyInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SEND_UI_TTY_MODE); }
        @Override public void startDtmf(int t,DtmfInfo v) { unsupported(t,LegacyImsQmi.REQUEST_DTMF_START); }
        @Override public void stopDtmf(int t) { unsupported(t,LegacyImsQmi.REQUEST_DTMF_STOP); }
        @Override public void suppServiceStatus(int t,int a,int b,CbNumListInfo v) { unsupported(t,LegacyImsQmi.REQUEST_SUPP_SVC_STATUS); }

        private static LegacyImsQmi.CallDetails toLegacy(CallDetails in) {
            LegacyImsQmi.CallDetails out = new LegacyImsQmi.CallDetails()
                    .setCallType(in.callType).setCallDomain(in.callDomain)
                    .setExtrasLength(in.extras.size()).setCallSubstate(in.callSubstate)
                    .setMediaId(in.mediaId).setCauseCode(in.causeCode).setRttMode(in.rttMode);
            for (String extra : in.extras) out.addExtras(extra);
            if (!in.sipAlternateUri.isEmpty()) out.setSipAlternateUri(in.sipAlternateUri);
            return out;
        }

        private static ServiceStatusInfo copyServiceStatus(ServiceStatusInfo in) {
            ServiceStatusInfo out = new ServiceStatusInfo();
            out.hasIsValid = in.hasIsValid;
            out.isValid = in.isValid;
            out.type = in.type;
            out.callType = in.callType;
            out.status = in.status;
            out.userdata.addAll(in.userdata);
            out.restrictCause = in.restrictCause;
            out.rttMode = in.rttMode;
            for (StatusForAccessTech access : in.accTechStatus) {
                StatusForAccessTech copied = new StatusForAccessTech();
                copied.networkMode = access.networkMode;
                copied.status = access.status;
                copied.restrictCause = access.restrictCause;
                copied.hasRegistration = access.hasRegistration;
                if (copied.hasRegistration) copy(access.registration, copied.registration);
                out.accTechStatus.add(copied);
            }
            return out;
        }

        private static LegacyImsQmi.Info toLegacy(ServiceStatusInfo in) {
            LegacyImsQmi.Info info = new LegacyImsQmi.Info()
                    .setIsValid(in.hasIsValid && in.isValid).setType(in.type)
                    .setCallType(in.callType).setStatus(in.status)
                    .setRestrictCause(in.restrictCause).setRttMode(in.rttMode);
            if (!in.userdata.isEmpty()) info.setUserdata(ByteStringMicro.copyFrom(bytes(in.userdata)));
            for (StatusForAccessTech access : in.accTechStatus) {
                LegacyImsQmi.StatusForAccessTech legacy = new LegacyImsQmi.StatusForAccessTech()
                        .setNetworkMode(fromHidlRadioTech(access.networkMode))
                        .setStatus(access.status).setRestrictionCause(access.restrictCause);
                if (access.hasRegistration) legacy.setRegistered(toLegacy(access.registration));
                info.addAccTechStatus(legacy);
            }
            return info;
        }

        private static LegacyImsQmi.Registration toLegacy(RegistrationInfo in) {
            LegacyImsQmi.Registration out = new LegacyImsQmi.Registration()
                    .setState(fromHidlRegistration(in.state)).setErrorCode(in.errorCode)
                    .setRadioTech(fromHidlRadioTech(in.radioTech));
            if (!in.errorMessage.isEmpty()) out.setErrorMessage(in.errorMessage);
            if (!in.pAssociatedUris.isEmpty()) out.setPAssociatedUris(in.pAssociatedUris);
            return out;
        }

        private static RegistrationInfo toHidl(LegacyImsQmi.Registration in) {
            RegistrationInfo out = new RegistrationInfo();
            out.state = toHidlRegistration(in.getState());
            out.errorCode = in.getErrorCode();
            out.errorMessage = in.getErrorMessage();
            out.radioTech = toHidlRadioTech(in.getRadioTech());
            out.pAssociatedUris = in.getPAssociatedUris();
            return out;
        }

        private static ArrayList<ServiceStatusInfo> toHidl(LegacyImsQmi.SrvStatusList in) {
            ArrayList<ServiceStatusInfo> result = new ArrayList<>();
            for (LegacyImsQmi.Info item : in.getSrvStatusInfoList()) {
                ServiceStatusInfo out = new ServiceStatusInfo();
                out.hasIsValid = item.hasIsValid(); out.isValid = item.getIsValid();
                out.type = item.getType(); out.callType = item.getCallType();
                out.status = item.getStatus(); out.restrictCause = item.getRestrictCause();
                out.rttMode = item.getRttMode();
                if (item.hasUserdata()) addBytes(out.userdata, item.getUserdata().toByteArray());
                for (LegacyImsQmi.StatusForAccessTech access : item.getAccTechStatusList()) {
                    StatusForAccessTech status = new StatusForAccessTech();
                    status.networkMode = toHidlRadioTech(access.getNetworkMode());
                    status.status = access.getStatus();
                    status.restrictCause = access.getRestrictionCause();
                    status.hasRegistration = access.hasRegistered();
                    if (status.hasRegistration) copy(toHidl(access.getRegistered()), status.registration);
                    out.accTechStatus.add(status);
                }
                result.add(out);
            }
            return result;
        }

        private static ArrayList<CallInfo> toHidl(LegacyImsQmi.CallList in) {
            ArrayList<CallInfo> result = new ArrayList<>();
            for (LegacyImsQmi.CallList.Call call : in.getCallAttributesList()) {
                result.add(toHidl(call));
            }
            return result;
        }

        private static CallInfo toHidl(LegacyImsQmi.CallList.Call call) {
            CallInfo out = new CallInfo();
            out.state=call.getState(); out.index=call.getIndex(); out.toa=call.getToa();
            out.hasIsMpty=call.hasIsMpty(); out.isMpty=call.getIsMpty();
            out.hasIsMT=call.hasIsMT(); out.isMT=call.getIsMT();
            out.als=call.getAls(); out.hasIsVoice=call.hasIsVoice(); out.isVoice=call.getIsVoice();
            out.hasIsVoicePrivacy=call.hasIsVoicePrivacy(); out.isVoicePrivacy=call.getIsVoicePrivacy();
            out.number=call.getNumber(); out.numberPresentation=call.getNumberPresentation();
            out.name=call.getName(); out.namePresentation=call.getNamePresentation();
            out.hasIsEncrypted=call.hasIsEncrypted(); out.isEncrypted=call.getIsEncrypted();
            out.hasIsCalledPartyRinging=call.hasIsCalledPartyRinging();
            out.isCalledPartyRinging=call.getIsCalledPartyRinging(); out.historyInfo=call.getHistoryInfo();
            out.hasCallDetails=call.hasCallDetails();
            if (out.hasCallDetails) copy(call.getCallDetails(),out.callDetails);
            return out;
        }

        private static TtyInfo toHidl(LegacyImsQmi.TtyNotify in) {
            TtyInfo out = new TtyInfo(); out.mode = in.getMode();
            if (in.hasUserData()) addBytes(out.userData, in.getUserData().toByteArray());
            return out;
        }

        private static void copy(LegacyImsQmi.CallDetails in, CallDetails out) {
            out.callType=in.getCallType(); out.callDomain=in.getCallDomain();
            out.extrasLength=in.getExtrasCount(); out.extras.addAll(in.getExtrasList());
            out.callSubstate=in.getCallSubstate(); out.mediaId=in.getMediaId();
            out.causeCode=in.getCauseCode(); out.rttMode=in.getRttMode();
            out.sipAlternateUri=in.getSipAlternateUri();
        }

        private static void copy(RegistrationInfo in, RegistrationInfo out) {
            out.state=in.state; out.errorCode=in.errorCode; out.errorMessage=in.errorMessage;
            out.radioTech=in.radioTech; out.pAssociatedUris=in.pAssociatedUris;
        }
        private static byte[] bytes(ArrayList<Byte> in) { byte[] b=new byte[in.size()]; for(int i=0;i<b.length;i++) b[i]=in.get(i); return b; }
        private static void addBytes(ArrayList<Byte> out,byte[] in) { for(byte b:in) out.add(b); }
        private static int toHidlRadioTech(int v) { return v >= -1 && v <= 19 ? v + 1 : 21; }
        private static int fromHidlRadioTech(int v) { return v >= 0 && v <= 20 ? v - 1 : 0; }
        private static int toHidlRegistration(int v) { return v==1?0:v==2?1:v==3?2:3; }
        private static int fromHidlRegistration(int v) { return v==0?1:v==1?2:v==2?3:0; }
        private static int toRadioState(int v) { return v==10?2:v==0?0:v==1?1:3; }
    }
}
