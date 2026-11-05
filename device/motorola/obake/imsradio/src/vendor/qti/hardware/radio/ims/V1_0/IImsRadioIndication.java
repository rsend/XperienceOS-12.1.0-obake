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
public interface IImsRadioIndication extends IBase {
    public static final String kInterfaceName = "vendor.qti.hardware.radio.ims@1.0::IImsRadioIndication";

    IHwBinder asBinder();

    DebugInfo getDebugInfo() throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void onCallStateChanged(ArrayList<CallInfo> arrayList) throws RemoteException;

    void onEnterEmergencyCallBackMode() throws RemoteException;

    void onExitEmergencyCallBackMode() throws RemoteException;

    void onGeolocationInfoRequested(double d, double d2) throws RemoteException;

    void onHandover(HandoverInfo handoverInfo) throws RemoteException;

    void onImsSubConfigChanged(ImsSubConfigInfo imsSubConfigInfo) throws RemoteException;

    void onMessageWaiting(MessageWaitingIndication messageWaitingIndication) throws RemoteException;

    void onModifyCall(CallModifyInfo callModifyInfo) throws RemoteException;

    void onParticipantStatusInfo(ParticipantStatusInfo participantStatusInfo) throws RemoteException;

    void onRadioStateChanged(int i) throws RemoteException;

    void onRefreshConferenceInfo(ConferenceInfo conferenceInfo) throws RemoteException;

    void onRefreshViceInfo(ViceInfo viceInfo) throws RemoteException;

    void onRegistrationBlockStatus(boolean z, BlockStatus blockStatus, boolean z2, BlockStatus blockStatus2) throws RemoteException;

    void onRegistrationChanged(RegistrationInfo registrationInfo) throws RemoteException;

    void onRing() throws RemoteException;

    void onRingbackTone(int i) throws RemoteException;

    void onRttMessageReceived(String str) throws RemoteException;

    void onServiceStatusChanged(ArrayList<ServiceStatusInfo> arrayList) throws RemoteException;

    void onSuppServiceNotification(SuppServiceNotification suppServiceNotification) throws RemoteException;

    void onTtyNotification(TtyInfo ttyInfo) throws RemoteException;

    void onVoWiFiCallQuality(int i) throws RemoteException;

    void ping() throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IImsRadioIndication asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface queryLocalInterface = binder.queryLocalInterface(kInterfaceName);
        if (queryLocalInterface != null && (queryLocalInterface instanceof IImsRadioIndication)) {
            return (IImsRadioIndication) queryLocalInterface;
        }
        IImsRadioIndication proxy = new Proxy(binder);
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

    static IImsRadioIndication castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IImsRadioIndication getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IImsRadioIndication getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }

    /* loaded from: classes.dex */
    public static final class Proxy implements IImsRadioIndication {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(remote);
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.qti.hardware.radio.ims@1.0::IImsRadioIndication]@Proxy";
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onCallStateChanged(ArrayList<CallInfo> callList) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            CallInfo.writeVectorToParcel(_hidl_request, callList);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRing() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRingbackTone(int tone) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(tone);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRegistrationChanged(RegistrationInfo registration) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            registration.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onHandover(HandoverInfo handover) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            handover.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onServiceStatusChanged(ArrayList<ServiceStatusInfo> srvStatusList) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            ServiceStatusInfo.writeVectorToParcel(_hidl_request, srvStatusList);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRadioStateChanged(int radioState) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(radioState);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onEnterEmergencyCallBackMode() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onExitEmergencyCallBackMode() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onTtyNotification(TtyInfo ttyInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            ttyInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRefreshConferenceInfo(ConferenceInfo conferenceInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            conferenceInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRefreshViceInfo(ViceInfo viceInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            viceInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onModifyCall(CallModifyInfo callModifyInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            callModifyInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onSuppServiceNotification(SuppServiceNotification suppServiceNotification) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            suppServiceNotification.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onMessageWaiting(MessageWaitingIndication messageWaitingIndication) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            messageWaitingIndication.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onGeolocationInfoRequested(double lat, double lon) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            _hidl_request.writeDouble(lat);
            _hidl_request.writeDouble(lon);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onImsSubConfigChanged(ImsSubConfigInfo config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onParticipantStatusInfo(ParticipantStatusInfo participantStatusInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            participantStatusInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRegistrationBlockStatus(boolean hasBlockStatusOnWwan, BlockStatus blockStatusOnWwan, boolean hasBlockStatusOnWlan, BlockStatus blockStatusOnWlan) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            _hidl_request.writeBool(hasBlockStatusOnWwan);
            blockStatusOnWwan.writeToParcel(_hidl_request);
            _hidl_request.writeBool(hasBlockStatusOnWlan);
            blockStatusOnWlan.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onRttMessageReceived(String message) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            _hidl_request.writeString(message);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public void onVoWiFiCallQuality(int voWiFiCallQualityInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IImsRadioIndication.kInterfaceName);
            _hidl_request.writeInt32(voWiFiCallQualityInfo);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
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

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends HwBinder implements IImsRadioIndication {
        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IImsRadioIndication.kInterfaceName, "android.hidl.base@1.0::IBase"));
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final String interfaceDescriptor() {
            return IImsRadioIndication.kInterfaceName;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{84, -29, -21, 20, 5, -54, 90, -88, -95, 51, 29, 10, -32, 44, 52, -102, 73, -44, -112, 100, -108, -50, 14, -28, -80, -113, -106, -67, -46, 72, 79, -37}, new byte[]{-67, -38, -74, 24, 77, 122, 52, 109, -90, -96, 125, -64, -126, -116, -15, -102, 105, 111, 76, -86, 54, 17, -59, 31, 46, 20, 86, 90, 20, -76, 15, -39}));
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final void setHALInstrumentation() {
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final void ping() {
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = -1;
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final void notifySyspropsChanged() {
            SystemProperties.reportSyspropChanged();
        }

        @Override // vendor.qti.hardware.radio.ims.V1_0.IImsRadioIndication
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IImsRadioIndication.kInterfaceName.equals(descriptor)) {
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
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    ArrayList<CallInfo> callList = CallInfo.readVectorFromParcel(_hidl_request);
                    onCallStateChanged(callList);
                    return;
                case 2:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    onRing();
                    return;
                case 3:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    int tone = _hidl_request.readInt32();
                    onRingbackTone(tone);
                    return;
                case 4:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    RegistrationInfo registration = new RegistrationInfo();
                    registration.readFromParcel(_hidl_request);
                    onRegistrationChanged(registration);
                    return;
                case 5:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    HandoverInfo handover = new HandoverInfo();
                    handover.readFromParcel(_hidl_request);
                    onHandover(handover);
                    return;
                case 6:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    ArrayList<ServiceStatusInfo> srvStatusList = ServiceStatusInfo.readVectorFromParcel(_hidl_request);
                    onServiceStatusChanged(srvStatusList);
                    return;
                case 7:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    int radioState = _hidl_request.readInt32();
                    onRadioStateChanged(radioState);
                    return;
                case 8:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    onEnterEmergencyCallBackMode();
                    return;
                case 9:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    onExitEmergencyCallBackMode();
                    return;
                case 10:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    TtyInfo ttyInfo = new TtyInfo();
                    ttyInfo.readFromParcel(_hidl_request);
                    onTtyNotification(ttyInfo);
                    return;
                case 11:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    ConferenceInfo conferenceInfo = new ConferenceInfo();
                    conferenceInfo.readFromParcel(_hidl_request);
                    onRefreshConferenceInfo(conferenceInfo);
                    return;
                case 12:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    ViceInfo viceInfo = new ViceInfo();
                    viceInfo.readFromParcel(_hidl_request);
                    onRefreshViceInfo(viceInfo);
                    return;
                case 13:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    CallModifyInfo callModifyInfo = new CallModifyInfo();
                    callModifyInfo.readFromParcel(_hidl_request);
                    onModifyCall(callModifyInfo);
                    return;
                case 14:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    SuppServiceNotification suppServiceNotification = new SuppServiceNotification();
                    suppServiceNotification.readFromParcel(_hidl_request);
                    onSuppServiceNotification(suppServiceNotification);
                    return;
                case 15:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    MessageWaitingIndication messageWaitingIndication = new MessageWaitingIndication();
                    messageWaitingIndication.readFromParcel(_hidl_request);
                    onMessageWaiting(messageWaitingIndication);
                    return;
                case 16:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    double lat = _hidl_request.readDouble();
                    double lon = _hidl_request.readDouble();
                    onGeolocationInfoRequested(lat, lon);
                    return;
                case 17:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    ImsSubConfigInfo config = new ImsSubConfigInfo();
                    config.readFromParcel(_hidl_request);
                    onImsSubConfigChanged(config);
                    return;
                case 18:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    ParticipantStatusInfo participantStatusInfo = new ParticipantStatusInfo();
                    participantStatusInfo.readFromParcel(_hidl_request);
                    onParticipantStatusInfo(participantStatusInfo);
                    return;
                case 19:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    boolean hasBlockStatusOnWwan = _hidl_request.readBool();
                    BlockStatus blockStatusOnWwan = new BlockStatus();
                    blockStatusOnWwan.readFromParcel(_hidl_request);
                    boolean hasBlockStatusOnWlan = _hidl_request.readBool();
                    BlockStatus blockStatusOnWlan = new BlockStatus();
                    blockStatusOnWlan.readFromParcel(_hidl_request);
                    onRegistrationBlockStatus(hasBlockStatusOnWwan, blockStatusOnWwan, hasBlockStatusOnWlan, blockStatusOnWlan);
                    return;
                case 20:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    String message = _hidl_request.readString();
                    onRttMessageReceived(message);
                    return;
                case 21:
                    _hidl_request.enforceInterface(IImsRadioIndication.kInterfaceName);
                    int voWiFiCallQualityInfo = _hidl_request.readInt32();
                    onVoWiFiCallQuality(voWiFiCallQualityInfo);
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
