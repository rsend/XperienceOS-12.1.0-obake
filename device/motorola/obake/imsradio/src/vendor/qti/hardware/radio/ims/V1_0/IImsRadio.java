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
public interface IImsRadio extends IBase {
    public static final String kInterfaceName = "vendor.qti.hardware.radio.ims@1.0::IImsRadio";

    void addParticipant(int i, DialRequest dialRequest) throws RemoteException;

    void answer(int i, int i2, int i3, int i4) throws RemoteException;

    IHwBinder asBinder();

    void cancelModifyCall(int i, int i2) throws RemoteException;

    void conference(int i) throws RemoteException;

    void deflectCall(int i, DeflectRequestInfo deflectRequestInfo) throws RemoteException;

    void dial(int i, DialRequest dialRequest) throws RemoteException;

    void exitEmergencyCallbackMode(int i) throws RemoteException;

    void explicitCallTransfer(int i, ExplicitCallTransferInfo explicitCallTransferInfo) throws RemoteException;

    void getCallWaiting(int i, int i2) throws RemoteException;

    void getClip(int i) throws RemoteException;

    void getClir(int i) throws RemoteException;

    void getColr(int i) throws RemoteException;

    void getConfig(int i, ConfigInfo configInfo) throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getImsRegistrationState(int i) throws RemoteException;

    void getImsSubConfig(int i) throws RemoteException;

    void getRtpErrorStatistics(int i) throws RemoteException;

    void getRtpStatistics(int i) throws RemoteException;

    void hangup(int i, HangupRequestInfo hangupRequestInfo) throws RemoteException;

    void hold(int i, int i2) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    void modifyCallConfirm(int i, CallModifyInfo callModifyInfo) throws RemoteException;

    void modifyCallInitiate(int i, CallModifyInfo callModifyInfo) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    void queryCallForwardStatus(int i, CallForwardInfo callForwardInfo) throws RemoteException;

    void queryServiceStatus(int i) throws RemoteException;

    void requestRegistrationChange(int i, int i2) throws RemoteException;

    void resume(int i, int i2) throws RemoteException;

    void sendDtmf(int i, DtmfInfo dtmfInfo) throws RemoteException;

    void sendGeolocationInfo(int i, double d, double d2, AddressInfo addressInfo) throws RemoteException;

    void sendRttMessage(int i, String str) throws RemoteException;

    void setCallForwardStatus(int i, CallForwardInfo callForwardInfo) throws RemoteException;

    void setCallWaiting(int i, int i2, int i3) throws RemoteException;

    void setCallback(IImsRadioResponse iImsRadioResponse, IImsRadioIndication iImsRadioIndication) throws RemoteException;

    void setClir(int i, ClirInfo clirInfo) throws RemoteException;

    void setColr(int i, ColrInfo colrInfo) throws RemoteException;

    void setConfig(int i, ConfigInfo configInfo) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    void setServiceStatus(int i, ServiceStatusInfo serviceStatusInfo) throws RemoteException;

    void setSuppServiceNotification(int i, int i2) throws RemoteException;

    void setUiTtyMode(int i, TtyInfo ttyInfo) throws RemoteException;

    void startDtmf(int i, DtmfInfo dtmfInfo) throws RemoteException;

    void stopDtmf(int i) throws RemoteException;

    void suppServiceStatus(int i, int i2, int i3, CbNumListInfo cbNumListInfo) throws RemoteException;

    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IImsRadio asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface queryLocalInterface = binder.queryLocalInterface(kInterfaceName);
        if (queryLocalInterface != null && (queryLocalInterface instanceof IImsRadio)) {
            return (IImsRadio) queryLocalInterface;
        }
        IImsRadio proxy = new Proxy(binder);
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

    static IImsRadio castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IImsRadio getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IImsRadio getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }

    /* loaded from: classes.dex */
    public static final class Proxy implements IImsRadio {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(remote);
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.qti.hardware.radio.ims@1.0::IImsRadio]@Proxy";
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setCallback(IImsRadioResponse imsRadioResponse, IImsRadioIndication imsRadioIndication) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeStrongBinder(imsRadioResponse == null ? null : imsRadioResponse.asBinder());
            _hidl_request.writeStrongBinder(imsRadioIndication != null ? imsRadioIndication.asBinder() : null);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void dial(int token, DialRequest dialRequest) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            dialRequest.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void addParticipant(int token, DialRequest dialRequest) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            dialRequest.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getImsRegistrationState(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void answer(int token, int callType, int presentation, int mode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(callType);
            _hidl_request.writeInt32(presentation);
            _hidl_request.writeInt32(mode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void hangup(int token, HangupRequestInfo hangup) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            hangup.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void requestRegistrationChange(int token, int state) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(state);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void queryServiceStatus(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setServiceStatus(int token, ServiceStatusInfo srvStatusInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            srvStatusInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void hold(int token, int callId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(callId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void resume(int token, int callId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(callId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setConfig(int token, ConfigInfo config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getConfig(int token, ConfigInfo config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void conference(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getClip(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getClir(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setClir(int token, ClirInfo clirInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            clirInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getColr(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setColr(int token, ColrInfo colrInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            colrInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void exitEmergencyCallbackMode(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void sendDtmf(int token, DtmfInfo dtmfInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            dtmfInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void startDtmf(int token, DtmfInfo dtmfInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            dtmfInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void stopDtmf(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setUiTtyMode(int token, TtyInfo ttyInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            ttyInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void modifyCallInitiate(int token, CallModifyInfo callModifyInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            callModifyInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void modifyCallConfirm(int token, CallModifyInfo callModifyInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            callModifyInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void queryCallForwardStatus(int token, CallForwardInfo callForwardInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            callForwardInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setCallForwardStatus(int token, CallForwardInfo callForwardInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            callForwardInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getCallWaiting(int token, int serviceClass) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(serviceClass);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setCallWaiting(int token, int serviceStatus, int serviceClass) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(serviceStatus);
            _hidl_request.writeInt32(serviceClass);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void setSuppServiceNotification(int token, int status) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(status);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void explicitCallTransfer(int token, ExplicitCallTransferInfo ectInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            ectInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void suppServiceStatus(int token, int operationType, int facilityType, CbNumListInfo cbNumListInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(operationType);
            _hidl_request.writeInt32(facilityType);
            cbNumListInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getRtpStatistics(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getRtpErrorStatistics(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void deflectCall(int token, DeflectRequestInfo deflectRequestInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            deflectRequestInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void sendGeolocationInfo(int token, double lat, double lon, AddressInfo addressInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeDouble(lat);
            _hidl_request.writeDouble(lon);
            addressInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(37, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void getImsSubConfig(int token) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(38, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void sendRttMessage(int token, String message) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeString(message);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(39, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public void cancelModifyCall(int token, int callId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadio.kInterfaceName);
            _hidl_request.writeInt32(token);
            _hidl_request.writeInt32(callId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(40, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends HwBinder implements IImsRadio {
        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IImsRadio.kInterfaceName, "android.hidl.base@1.0::IBase"));
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final String interfaceDescriptor() {
            return IImsRadio.kInterfaceName;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{78, 65, -10, 94, -61, -30, 94, 37, 91, 37, -38, 93, 123, 60, -79, 23, -22, 75, -25, -35, 96, 116, 95, 109, -37, -105, -89, 71, 90, 86, 126, 112}, new byte[]{-67, -38, -74, 24, 77, 122, 52, 109, -90, -96, 125, -64, -126, -116, -15, -102, 105, 111, 76, -86, 54, 17, -59, 31, 46, 20, 86, 90, 20, -76, 15, -39}));
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final void setHALInstrumentation() {
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final void ping() {
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = -1;
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final void notifySyspropsChanged() {
            SystemProperties.reportSyspropChanged();
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadio
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IImsRadio.kInterfaceName.equals(descriptor)) {
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
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    IImsRadioResponse imsRadioResponse = IImsRadioResponse.asInterface(_hidl_request.readStrongBinder());
                    IImsRadioIndication imsRadioIndication = IImsRadioIndication.asInterface(_hidl_request.readStrongBinder());
                    setCallback(imsRadioResponse, imsRadioIndication);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token = _hidl_request.readInt32();
                    DialRequest dialRequest = new DialRequest();
                    dialRequest.readFromParcel(_hidl_request);
                    dial(token, dialRequest);
                    return;
                case 3:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token2 = _hidl_request.readInt32();
                    DialRequest dialRequest2 = new DialRequest();
                    dialRequest2.readFromParcel(_hidl_request);
                    addParticipant(token2, dialRequest2);
                    return;
                case 4:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token3 = _hidl_request.readInt32();
                    getImsRegistrationState(token3);
                    return;
                case 5:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token4 = _hidl_request.readInt32();
                    int callType = _hidl_request.readInt32();
                    int presentation = _hidl_request.readInt32();
                    int mode = _hidl_request.readInt32();
                    answer(token4, callType, presentation, mode);
                    return;
                case 6:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token5 = _hidl_request.readInt32();
                    HangupRequestInfo hangup = new HangupRequestInfo();
                    hangup.readFromParcel(_hidl_request);
                    hangup(token5, hangup);
                    return;
                case 7:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token6 = _hidl_request.readInt32();
                    int state = _hidl_request.readInt32();
                    requestRegistrationChange(token6, state);
                    return;
                case 8:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token7 = _hidl_request.readInt32();
                    queryServiceStatus(token7);
                    return;
                case 9:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token8 = _hidl_request.readInt32();
                    ServiceStatusInfo srvStatusInfo = new ServiceStatusInfo();
                    srvStatusInfo.readFromParcel(_hidl_request);
                    setServiceStatus(token8, srvStatusInfo);
                    return;
                case 10:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token9 = _hidl_request.readInt32();
                    int callId = _hidl_request.readInt32();
                    hold(token9, callId);
                    return;
                case 11:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token10 = _hidl_request.readInt32();
                    int callId2 = _hidl_request.readInt32();
                    resume(token10, callId2);
                    return;
                case 12:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token11 = _hidl_request.readInt32();
                    ConfigInfo config = new ConfigInfo();
                    config.readFromParcel(_hidl_request);
                    setConfig(token11, config);
                    return;
                case 13:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token12 = _hidl_request.readInt32();
                    ConfigInfo config2 = new ConfigInfo();
                    config2.readFromParcel(_hidl_request);
                    getConfig(token12, config2);
                    return;
                case 14:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token13 = _hidl_request.readInt32();
                    conference(token13);
                    return;
                case 15:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token14 = _hidl_request.readInt32();
                    getClip(token14);
                    return;
                case 16:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token15 = _hidl_request.readInt32();
                    getClir(token15);
                    return;
                case 17:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token16 = _hidl_request.readInt32();
                    ClirInfo clirInfo = new ClirInfo();
                    clirInfo.readFromParcel(_hidl_request);
                    setClir(token16, clirInfo);
                    return;
                case 18:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token17 = _hidl_request.readInt32();
                    getColr(token17);
                    return;
                case 19:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token18 = _hidl_request.readInt32();
                    ColrInfo colrInfo = new ColrInfo();
                    colrInfo.readFromParcel(_hidl_request);
                    setColr(token18, colrInfo);
                    return;
                case 20:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token19 = _hidl_request.readInt32();
                    exitEmergencyCallbackMode(token19);
                    return;
                case 21:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token20 = _hidl_request.readInt32();
                    DtmfInfo dtmfInfo = new DtmfInfo();
                    dtmfInfo.readFromParcel(_hidl_request);
                    sendDtmf(token20, dtmfInfo);
                    return;
                case 22:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token21 = _hidl_request.readInt32();
                    DtmfInfo dtmfInfo2 = new DtmfInfo();
                    dtmfInfo2.readFromParcel(_hidl_request);
                    startDtmf(token21, dtmfInfo2);
                    return;
                case 23:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token22 = _hidl_request.readInt32();
                    stopDtmf(token22);
                    return;
                case 24:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token23 = _hidl_request.readInt32();
                    TtyInfo ttyInfo = new TtyInfo();
                    ttyInfo.readFromParcel(_hidl_request);
                    setUiTtyMode(token23, ttyInfo);
                    return;
                case 25:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token24 = _hidl_request.readInt32();
                    CallModifyInfo callModifyInfo = new CallModifyInfo();
                    callModifyInfo.readFromParcel(_hidl_request);
                    modifyCallInitiate(token24, callModifyInfo);
                    return;
                case 26:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token25 = _hidl_request.readInt32();
                    CallModifyInfo callModifyInfo2 = new CallModifyInfo();
                    callModifyInfo2.readFromParcel(_hidl_request);
                    modifyCallConfirm(token25, callModifyInfo2);
                    return;
                case 27:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token26 = _hidl_request.readInt32();
                    CallForwardInfo callForwardInfo = new CallForwardInfo();
                    callForwardInfo.readFromParcel(_hidl_request);
                    queryCallForwardStatus(token26, callForwardInfo);
                    return;
                case 28:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token27 = _hidl_request.readInt32();
                    CallForwardInfo callForwardInfo2 = new CallForwardInfo();
                    callForwardInfo2.readFromParcel(_hidl_request);
                    setCallForwardStatus(token27, callForwardInfo2);
                    return;
                case 29:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token28 = _hidl_request.readInt32();
                    int serviceClass = _hidl_request.readInt32();
                    getCallWaiting(token28, serviceClass);
                    return;
                case 30:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token29 = _hidl_request.readInt32();
                    int serviceStatus = _hidl_request.readInt32();
                    int serviceClass2 = _hidl_request.readInt32();
                    setCallWaiting(token29, serviceStatus, serviceClass2);
                    return;
                case 31:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token30 = _hidl_request.readInt32();
                    int status = _hidl_request.readInt32();
                    setSuppServiceNotification(token30, status);
                    return;
                case 32:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token31 = _hidl_request.readInt32();
                    ExplicitCallTransferInfo ectInfo = new ExplicitCallTransferInfo();
                    ectInfo.readFromParcel(_hidl_request);
                    explicitCallTransfer(token31, ectInfo);
                    return;
                case 33:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token32 = _hidl_request.readInt32();
                    int operationType = _hidl_request.readInt32();
                    int facilityType = _hidl_request.readInt32();
                    CbNumListInfo cbNumListInfo = new CbNumListInfo();
                    cbNumListInfo.readFromParcel(_hidl_request);
                    suppServiceStatus(token32, operationType, facilityType, cbNumListInfo);
                    return;
                case 34:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token33 = _hidl_request.readInt32();
                    getRtpStatistics(token33);
                    return;
                case 35:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token34 = _hidl_request.readInt32();
                    getRtpErrorStatistics(token34);
                    return;
                case 36:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token35 = _hidl_request.readInt32();
                    DeflectRequestInfo deflectRequestInfo = new DeflectRequestInfo();
                    deflectRequestInfo.readFromParcel(_hidl_request);
                    deflectCall(token35, deflectRequestInfo);
                    return;
                case 37:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token36 = _hidl_request.readInt32();
                    double lat = _hidl_request.readDouble();
                    double lon = _hidl_request.readDouble();
                    AddressInfo addressInfo = new AddressInfo();
                    addressInfo.readFromParcel(_hidl_request);
                    sendGeolocationInfo(token36, lat, lon, addressInfo);
                    return;
                case 38:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token37 = _hidl_request.readInt32();
                    getImsSubConfig(token37);
                    return;
                case 39:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token38 = _hidl_request.readInt32();
                    String message = _hidl_request.readString();
                    sendRttMessage(token38, message);
                    return;
                case 40:
                    _hidl_request.enforceInterface(IImsRadio.kInterfaceName);
                    int token39 = _hidl_request.readInt32();
                    int callId3 = _hidl_request.readInt32();
                    cancelModifyCall(token39, callId3);
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
