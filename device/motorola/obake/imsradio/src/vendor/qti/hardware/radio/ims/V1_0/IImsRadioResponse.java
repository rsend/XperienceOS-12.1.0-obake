package vendor.qti.hardware.radio.ims.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import android.os.SystemProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public interface IImsRadioResponse extends IBase {
    public static final String kInterfaceName = "vendor.qti.hardware.radio.ims@1.0::IImsRadioResponse";

    void addParticipantResponse(int i, int i2) throws RemoteException;

    void answerResponse(int i, int i2) throws RemoteException;

    IHwBinder asBinder();

    void cancelModifyCallResponse(int i, int i2) throws RemoteException;

    void conferenceResponse(int i, int i2, SipErrorInfo sipErrorInfo) throws RemoteException;

    void deflectCallResponse(int i, int i2) throws RemoteException;

    void dialResponse(int i, int i2) throws RemoteException;

    void exitEmergencyCallbackModeResponse(int i, int i2) throws RemoteException;

    void explicitCallTransferResponse(int i, int i2) throws RemoteException;

    void getCallWaitingResponse(int i, int i2, int i3, int i4, SipErrorInfo sipErrorInfo) throws RemoteException;

    void getClipResponse(int i, int i2, ClipProvisionStatus clipProvisionStatus) throws RemoteException;

    void getClirResponse(int i, int i2, ClirInfo clirInfo, boolean z) throws RemoteException;

    void getColrResponse(int i, int i2, ColrInfo colrInfo) throws RemoteException;

    void getConfigResponse(int i, int i2, ConfigInfo configInfo) throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getImsSubConfigResponse(int i, int i2, ImsSubConfigInfo imsSubConfigInfo) throws RemoteException;

    void getRegistrationResponse(int i, int i2, RegistrationInfo registrationInfo) throws RemoteException;

    void getRtpErrorStatisticsResponse(int i, int i2, long j) throws RemoteException;

    void getRtpStatisticsResponse(int i, int i2, long j) throws RemoteException;

    void hangupResponse(int i, int i2) throws RemoteException;

    void holdResponse(int i, int i2, SipErrorInfo sipErrorInfo) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    void modifyCallConfirmResponse(int i, int i2) throws RemoteException;

    void modifyCallInitiateResponse(int i, int i2) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    void queryCallForwardStatusResponse(int i, int i2, ArrayList<CallForwardInfo> arrayList, SipErrorInfo sipErrorInfo) throws RemoteException;

    void queryServiceStatusResponse(int i, int i2, ArrayList<ServiceStatusInfo> arrayList) throws RemoteException;

    void requestRegistrationChangeResponse(int i, int i2) throws RemoteException;

    void resumeResponse(int i, int i2, SipErrorInfo sipErrorInfo) throws RemoteException;

    void sendDtmfResponse(int i, int i2) throws RemoteException;

    void sendGeolocationInfoResponse(int i, int i2) throws RemoteException;

    void sendRttMessageResponse(int i, int i2) throws RemoteException;

    void setClirResponse(int i, int i2) throws RemoteException;

    void setConfigResponse(int i, int i2, ConfigInfo configInfo) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void setServiceStatusResponse(int i, int i2) throws RemoteException;

    void setSuppServiceNotificationResponse(int i, int i2, int i3) throws RemoteException;

    void setUiTTYModeResponse(int i, int i2) throws RemoteException;

    void startDtmfResponse(int i, int i2) throws RemoteException;

    void stopDtmfResponse(int i, int i2) throws RemoteException;

    void suppServiceStatusResponse(int i, int i2, SuppServiceStatus suppServiceStatus) throws RemoteException;

    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IImsRadioResponse asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface queryLocalInterface = binder.queryLocalInterface(kInterfaceName);
        if (queryLocalInterface != null && (queryLocalInterface instanceof IImsRadioResponse)) {
            return (IImsRadioResponse) queryLocalInterface;
        }
        IImsRadioResponse proxy = new Proxy(binder);
        try {
            for (String descriptor : proxy.interfaceChain()) {
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static IImsRadioResponse castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IImsRadioResponse getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IImsRadioResponse getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }

    /* loaded from: classes.dex */
    public static final class Proxy implements IImsRadioResponse {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(remote);
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.qti.hardware.radio.ims@1.0::IImsRadioResponse]@Proxy";
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void dialResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void answerResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void hangupResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void requestRegistrationChangeResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void queryServiceStatusResponse(int token, int errorCode, ArrayList<ServiceStatusInfo> srvStatusList) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            ServiceStatusInfo.writeVectorToParcel(_hidl_request, srvStatusList);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void setServiceStatusResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void holdResponse(int token, int errorCode, SipErrorInfo sipError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            sipError.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void resumeResponse(int token, int errorCode, SipErrorInfo sipError) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            sipError.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void setConfigResponse(int token, int errorCode, ConfigInfo config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getConfigResponse(int token, int errorCode, ConfigInfo config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getRegistrationResponse(int token, int errorCode, RegistrationInfo registration) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            registration.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void suppServiceStatusResponse(int token, int errorCode, SuppServiceStatus suppServiceStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            suppServiceStatus.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void conferenceResponse(int token, int errorCode, SipErrorInfo errorInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            errorInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getClipResponse(int token, int errorCode, ClipProvisionStatus clipProvisionStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            clipProvisionStatus.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getClirResponse(int token, int errorCode, ClirInfo clirInfo, boolean hasClirInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            clirInfo.writeToParcel(_hidl_request);
            _hidl_request.writeBool(hasClirInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void setClirResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getColrResponse(int token, int errorCode, ColrInfo colrInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            colrInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void exitEmergencyCallbackModeResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void sendDtmfResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void startDtmfResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void stopDtmfResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void setUiTTYModeResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void modifyCallInitiateResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void modifyCallConfirmResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void queryCallForwardStatusResponse(int token, int errorCode, ArrayList<CallForwardInfo> callForwardInfoList, SipErrorInfo errorDetails) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            CallForwardInfo.writeVectorToParcel(_hidl_request, callForwardInfoList);
            errorDetails.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getCallWaitingResponse(int token, int errorCode, int serviceStatus, int serviceClass, SipErrorInfo errorDetails) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            _hidl_request.writeInt32(serviceStatus);
            _hidl_request.writeInt32(serviceClass);
            errorDetails.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void explicitCallTransferResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void setSuppServiceNotificationResponse(int token, int errorCode, int serviceStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            _hidl_request.writeInt32(serviceStatus);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getRtpStatisticsResponse(int token, int errorCode, long packetCount) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            _hidl_request.writeInt64(packetCount);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getRtpErrorStatisticsResponse(int token, int errorCode, long packetErrorCount) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            _hidl_request.writeInt64(packetErrorCount);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void addParticipantResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void deflectCallResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void sendGeolocationInfoResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void getImsSubConfigResponse(int token, int errorCode, ImsSubConfigInfo subConfigInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            subConfigInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void sendRttMessageResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void cancelModifyCallResponse(int token, int errorCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioResponse.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(errorCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public String interfaceDescriptor() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<byte[]> _hidl_out_hashchain = new ArrayList<>();
                HwBlob _hidl_blob = _hidl_reply.readBuffer(16L);
                int _hidl_vec_size = _hidl_blob.getInt32(8L);
                HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    byte[] _hidl_vec_element = new byte[32];
                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                        _hidl_vec_element[_hidl_index_1_0] = childBlob.getInt8(_hidl_array_offset_1);
                        _hidl_array_offset_1++;
                    }
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void setHALInstrumentation() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void ping() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends HwBinder implements IImsRadioResponse {
        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IImsRadioResponse.kInterfaceName, "android.hidl.base@1.0::IBase"));
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final String interfaceDescriptor() {
            return IImsRadioResponse.kInterfaceName;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{12, -43, -1, 120, -61, -17, -86, -112, 42, -101, -91, 56, -4, 14, 28, -122, -62, -108, -78, -15, -109, -36, 97, -16, 120, 96, -125, 57, -103, -67, -6, -107}, new byte[]{-67, -38, -74, 24, 77, 122, 52, 109, -90, -96, 125, -64, -126, -116, -15, -102, 105, 111, 76, -86, 54, 17, -59, 31, 46, 20, 86, 90, 20, -76, 15, -39}));
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final void setHALInstrumentation() {
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final void ping() {
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = -1;
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final void notifySyspropsChanged() {
            SystemProperties.reportSyspropChanged();
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioResponse
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IImsRadioResponse.kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int _hidl_code, HwParcel _hidl_request, HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token = _hidl_request.readInt32();
                    int errorCode = _hidl_request.readInt32();
                    dialResponse(token, errorCode);
                    return;
                case 2:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token2 = _hidl_request.readInt32();
                    int errorCode2 = _hidl_request.readInt32();
                    answerResponse(token2, errorCode2);
                    return;
                case 3:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token3 = _hidl_request.readInt32();
                    int errorCode3 = _hidl_request.readInt32();
                    hangupResponse(token3, errorCode3);
                    return;
                case 4:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token4 = _hidl_request.readInt32();
                    int errorCode4 = _hidl_request.readInt32();
                    requestRegistrationChangeResponse(token4, errorCode4);
                    return;
                case 5:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token5 = _hidl_request.readInt32();
                    int errorCode5 = _hidl_request.readInt32();
                    ArrayList<ServiceStatusInfo> srvStatusList = ServiceStatusInfo.readVectorFromParcel(_hidl_request);
                    queryServiceStatusResponse(token5, errorCode5, srvStatusList);
                    return;
                case 6:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token6 = _hidl_request.readInt32();
                    int errorCode6 = _hidl_request.readInt32();
                    setServiceStatusResponse(token6, errorCode6);
                    return;
                case 7:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token7 = _hidl_request.readInt32();
                    int errorCode7 = _hidl_request.readInt32();
                    SipErrorInfo sipError = new SipErrorInfo();
                    sipError.readFromParcel(_hidl_request);
                    holdResponse(token7, errorCode7, sipError);
                    return;
                case 8:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token8 = _hidl_request.readInt32();
                    int errorCode8 = _hidl_request.readInt32();
                    SipErrorInfo sipError2 = new SipErrorInfo();
                    sipError2.readFromParcel(_hidl_request);
                    resumeResponse(token8, errorCode8, sipError2);
                    return;
                case 9:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token9 = _hidl_request.readInt32();
                    int errorCode9 = _hidl_request.readInt32();
                    ConfigInfo config = new ConfigInfo();
                    config.readFromParcel(_hidl_request);
                    setConfigResponse(token9, errorCode9, config);
                    return;
                case 10:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token10 = _hidl_request.readInt32();
                    int errorCode10 = _hidl_request.readInt32();
                    ConfigInfo config2 = new ConfigInfo();
                    config2.readFromParcel(_hidl_request);
                    getConfigResponse(token10, errorCode10, config2);
                    return;
                case 11:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token11 = _hidl_request.readInt32();
                    int errorCode11 = _hidl_request.readInt32();
                    RegistrationInfo registration = new RegistrationInfo();
                    registration.readFromParcel(_hidl_request);
                    getRegistrationResponse(token11, errorCode11, registration);
                    return;
                case 12:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token12 = _hidl_request.readInt32();
                    int errorCode12 = _hidl_request.readInt32();
                    SuppServiceStatus suppServiceStatus = new SuppServiceStatus();
                    suppServiceStatus.readFromParcel(_hidl_request);
                    suppServiceStatusResponse(token12, errorCode12, suppServiceStatus);
                    return;
                case 13:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token13 = _hidl_request.readInt32();
                    int errorCode13 = _hidl_request.readInt32();
                    SipErrorInfo errorInfo = new SipErrorInfo();
                    errorInfo.readFromParcel(_hidl_request);
                    conferenceResponse(token13, errorCode13, errorInfo);
                    return;
                case 14:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token14 = _hidl_request.readInt32();
                    int errorCode14 = _hidl_request.readInt32();
                    ClipProvisionStatus clipProvisionStatus = new ClipProvisionStatus();
                    clipProvisionStatus.readFromParcel(_hidl_request);
                    getClipResponse(token14, errorCode14, clipProvisionStatus);
                    return;
                case 15:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token15 = _hidl_request.readInt32();
                    int errorCode15 = _hidl_request.readInt32();
                    ClirInfo clirInfo = new ClirInfo();
                    clirInfo.readFromParcel(_hidl_request);
                    boolean hasClirInfo = _hidl_request.readBool();
                    getClirResponse(token15, errorCode15, clirInfo, hasClirInfo);
                    return;
                case 16:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token16 = _hidl_request.readInt32();
                    int errorCode16 = _hidl_request.readInt32();
                    setClirResponse(token16, errorCode16);
                    return;
                case 17:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token17 = _hidl_request.readInt32();
                    int errorCode17 = _hidl_request.readInt32();
                    ColrInfo colrInfo = new ColrInfo();
                    colrInfo.readFromParcel(_hidl_request);
                    getColrResponse(token17, errorCode17, colrInfo);
                    return;
                case 18:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token18 = _hidl_request.readInt32();
                    int errorCode18 = _hidl_request.readInt32();
                    exitEmergencyCallbackModeResponse(token18, errorCode18);
                    return;
                case 19:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token19 = _hidl_request.readInt32();
                    int errorCode19 = _hidl_request.readInt32();
                    sendDtmfResponse(token19, errorCode19);
                    return;
                case 20:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token20 = _hidl_request.readInt32();
                    int errorCode20 = _hidl_request.readInt32();
                    startDtmfResponse(token20, errorCode20);
                    return;
                case 21:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token21 = _hidl_request.readInt32();
                    int errorCode21 = _hidl_request.readInt32();
                    stopDtmfResponse(token21, errorCode21);
                    return;
                case 22:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token22 = _hidl_request.readInt32();
                    int errorCode22 = _hidl_request.readInt32();
                    setUiTTYModeResponse(token22, errorCode22);
                    return;
                case 23:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token23 = _hidl_request.readInt32();
                    int errorCode23 = _hidl_request.readInt32();
                    modifyCallInitiateResponse(token23, errorCode23);
                    return;
                case 24:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token24 = _hidl_request.readInt32();
                    int errorCode24 = _hidl_request.readInt32();
                    modifyCallConfirmResponse(token24, errorCode24);
                    return;
                case 25:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token25 = _hidl_request.readInt32();
                    int errorCode25 = _hidl_request.readInt32();
                    ArrayList<CallForwardInfo> callForwardInfoList = CallForwardInfo.readVectorFromParcel(_hidl_request);
                    SipErrorInfo errorDetails = new SipErrorInfo();
                    errorDetails.readFromParcel(_hidl_request);
                    queryCallForwardStatusResponse(token25, errorCode25, callForwardInfoList, errorDetails);
                    return;
                case 26:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token26 = _hidl_request.readInt32();
                    int errorCode26 = _hidl_request.readInt32();
                    int serviceStatus = _hidl_request.readInt32();
                    int serviceClass = _hidl_request.readInt32();
                    SipErrorInfo errorDetails2 = new SipErrorInfo();
                    errorDetails2.readFromParcel(_hidl_request);
                    getCallWaitingResponse(token26, errorCode26, serviceStatus, serviceClass, errorDetails2);
                    return;
                case 27:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token27 = _hidl_request.readInt32();
                    int errorCode27 = _hidl_request.readInt32();
                    explicitCallTransferResponse(token27, errorCode27);
                    return;
                case 28:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token28 = _hidl_request.readInt32();
                    int errorCode28 = _hidl_request.readInt32();
                    int serviceStatus2 = _hidl_request.readInt32();
                    setSuppServiceNotificationResponse(token28, errorCode28, serviceStatus2);
                    return;
                case 29:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token29 = _hidl_request.readInt32();
                    int errorCode29 = _hidl_request.readInt32();
                    long packetCount = _hidl_request.readInt64();
                    getRtpStatisticsResponse(token29, errorCode29, packetCount);
                    return;
                case 30:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token30 = _hidl_request.readInt32();
                    int errorCode30 = _hidl_request.readInt32();
                    long packetErrorCount = _hidl_request.readInt64();
                    getRtpErrorStatisticsResponse(token30, errorCode30, packetErrorCount);
                    return;
                case 31:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token31 = _hidl_request.readInt32();
                    int errorCode31 = _hidl_request.readInt32();
                    addParticipantResponse(token31, errorCode31);
                    return;
                case 32:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token32 = _hidl_request.readInt32();
                    int errorCode32 = _hidl_request.readInt32();
                    deflectCallResponse(token32, errorCode32);
                    return;
                case 33:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token33 = _hidl_request.readInt32();
                    int errorCode33 = _hidl_request.readInt32();
                    sendGeolocationInfoResponse(token33, errorCode33);
                    return;
                case 34:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token34 = _hidl_request.readInt32();
                    int errorCode34 = _hidl_request.readInt32();
                    ImsSubConfigInfo subConfigInfo = new ImsSubConfigInfo();
                    subConfigInfo.readFromParcel(_hidl_request);
                    getImsSubConfigResponse(token34, errorCode34, subConfigInfo);
                    return;
                case 35:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token35 = _hidl_request.readInt32();
                    int errorCode35 = _hidl_request.readInt32();
                    sendRttMessageResponse(token35, errorCode35);
                    return;
                case 36:
                    _hidl_request.enforceInterface(IImsRadioResponse.kInterfaceName);
                    int token36 = _hidl_request.readInt32();
                    int errorCode36 = _hidl_request.readInt32();
                    cancelModifyCallResponse(token36, errorCode36);
                    return;
                case 256067662:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    ArrayList<String> _hidl_out_descriptors = interfaceChain();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeStringVector(_hidl_out_descriptors);
                    _hidl_reply.send();
                    return;
                case 256131655:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 256136003:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    String _hidl_out_descriptor = interfaceDescriptor();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_descriptor);
                    _hidl_reply.send();
                    return;
                case 256398152:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                    _hidl_reply.writeStatus(0);
                    HwBlob _hidl_blob = new HwBlob(16);
                    int _hidl_vec_size = _hidl_out_hashchain.size();
                    _hidl_blob.putInt32(8L, _hidl_vec_size);
                    _hidl_blob.putBool(12L, false);
                    HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
                    for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                        long _hidl_array_offset_1 = _hidl_index_0 * 32;
                        for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                            childBlob.putInt8(_hidl_array_offset_1, _hidl_out_hashchain.get(_hidl_index_0)[_hidl_index_1_0]);
                            _hidl_array_offset_1++;
                        }
                    }
                    _hidl_blob.putBlob(0L, childBlob);
                    _hidl_reply.writeBuffer(_hidl_blob);
                    _hidl_reply.send();
                    return;
                case 256462420:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    setHALInstrumentation();
                    return;
                case 256660548:
                case 256921159:
                case 257250372:
                default:
                    return;
                case 257049926:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    DebugInfo _hidl_out_info = getDebugInfo();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_info.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 257120595:
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    notifySyspropsChanged();
                    return;
            }
        }
    }
}
