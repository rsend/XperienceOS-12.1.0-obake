package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CallDetails {
    public int callDomain;
    public int callSubstate;
    public int callType;
    public int causeCode;
    public int extrasLength;
    public int mediaId;
    public int rttMode;
    public final ArrayList<String> extras = new ArrayList<>();
    public final ArrayList<ServiceStatusInfo> localAbility = new ArrayList<>();
    public final ArrayList<ServiceStatusInfo> peerAbility = new ArrayList<>();
    public String sipAlternateUri = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == CallDetails.class) {
            CallDetails other = (CallDetails) otherObject;
            return this.callType == other.callType && this.callDomain == other.callDomain && this.extrasLength == other.extrasLength && HidlSupport.deepEquals(this.extras, other.extras) && HidlSupport.deepEquals(this.localAbility, other.localAbility) && HidlSupport.deepEquals(this.peerAbility, other.peerAbility) && this.callSubstate == other.callSubstate && this.mediaId == other.mediaId && this.causeCode == other.causeCode && this.rttMode == other.rttMode && HidlSupport.deepEquals(this.sipAlternateUri, other.sipAlternateUri);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.callType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.callDomain))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.extrasLength))), Integer.valueOf(HidlSupport.deepHashCode(this.extras)), Integer.valueOf(HidlSupport.deepHashCode(this.localAbility)), Integer.valueOf(HidlSupport.deepHashCode(this.peerAbility)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.callSubstate))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mediaId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.causeCode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rttMode))), Integer.valueOf(HidlSupport.deepHashCode(this.sipAlternateUri)));
    }

    public final String toString() {
        return "{.callType = " + CallType.toString(this.callType) + ", .callDomain = " + CallDomain.toString(this.callDomain) + ", .extrasLength = " + this.extrasLength + ", .extras = " + this.extras + ", .localAbility = " + this.localAbility + ", .peerAbility = " + this.peerAbility + ", .callSubstate = " + this.callSubstate + ", .mediaId = " + this.mediaId + ", .causeCode = " + this.causeCode + ", .rttMode = " + RttMode.toString(this.rttMode) + ", .sipAlternateUri = " + this.sipAlternateUri + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(96L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<CallDetails> readVectorFromParcel(HwParcel parcel) {
        ArrayList<CallDetails> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 96, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            CallDetails _hidl_vec_element = new CallDetails();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 96);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.callType = _hidl_blob.getInt32(0 + _hidl_offset);
        this.callDomain = _hidl_blob.getInt32(4 + _hidl_offset);
        this.extrasLength = _hidl_blob.getInt32(8 + _hidl_offset);
        int _hidl_vec_size = _hidl_blob.getInt32(16 + _hidl_offset + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 16, _hidl_blob.handle(), 16 + _hidl_offset + 0, true);
        this.extras.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            new String();
            String _hidl_vec_element = childBlob.getString(_hidl_index_0 * 16);
            parcel.readEmbeddedBuffer(_hidl_vec_element.getBytes().length + 1, childBlob.handle(), (_hidl_index_0 * 16) + 0, false);
            this.extras.add(_hidl_vec_element);
        }
        int _hidl_vec_size2 = _hidl_blob.getInt32(32 + _hidl_offset + 8);
        HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 64, _hidl_blob.handle(), 32 + _hidl_offset + 0, true);
        this.localAbility.clear();
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            ServiceStatusInfo _hidl_vec_element2 = new ServiceStatusInfo();
            _hidl_vec_element2.readEmbeddedFromParcel(parcel, childBlob2, _hidl_index_02 * 64);
            this.localAbility.add(_hidl_vec_element2);
        }
        int _hidl_vec_size3 = _hidl_blob.getInt32(48 + _hidl_offset + 8);
        HwBlob childBlob3 = parcel.readEmbeddedBuffer(_hidl_vec_size3 * 64, _hidl_blob.handle(), 48 + _hidl_offset + 0, true);
        this.peerAbility.clear();
        for (int _hidl_index_03 = 0; _hidl_index_03 < _hidl_vec_size3; _hidl_index_03++) {
            ServiceStatusInfo _hidl_vec_element3 = new ServiceStatusInfo();
            _hidl_vec_element3.readEmbeddedFromParcel(parcel, childBlob3, _hidl_index_03 * 64);
            this.peerAbility.add(_hidl_vec_element3);
        }
        this.callSubstate = _hidl_blob.getInt32(64 + _hidl_offset);
        this.mediaId = _hidl_blob.getInt32(68 + _hidl_offset);
        this.causeCode = _hidl_blob.getInt32(72 + _hidl_offset);
        this.rttMode = _hidl_blob.getInt32(76 + _hidl_offset);
        this.sipAlternateUri = _hidl_blob.getString(80 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.sipAlternateUri.getBytes().length + 1, _hidl_blob.handle(), 80 + _hidl_offset + 0, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(96);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<CallDetails> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 96);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 96);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.callType);
        _hidl_blob.putInt32(4 + _hidl_offset, this.callDomain);
        _hidl_blob.putInt32(8 + _hidl_offset, this.extrasLength);
        int _hidl_vec_size = this.extras.size();
        _hidl_blob.putInt32(16 + _hidl_offset + 8, _hidl_vec_size);
        _hidl_blob.putBool(16 + _hidl_offset + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 16);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            childBlob.putString(_hidl_index_0 * 16, this.extras.get(_hidl_index_0));
        }
        _hidl_blob.putBlob(16 + _hidl_offset + 0, childBlob);
        int _hidl_vec_size2 = this.localAbility.size();
        _hidl_blob.putInt32(32 + _hidl_offset + 8, _hidl_vec_size2);
        _hidl_blob.putBool(32 + _hidl_offset + 12, false);
        HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 64);
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            this.localAbility.get(_hidl_index_02).writeEmbeddedToBlob(childBlob2, _hidl_index_02 * 64);
        }
        _hidl_blob.putBlob(32 + _hidl_offset + 0, childBlob2);
        int _hidl_vec_size3 = this.peerAbility.size();
        _hidl_blob.putInt32(48 + _hidl_offset + 8, _hidl_vec_size3);
        _hidl_blob.putBool(48 + _hidl_offset + 12, false);
        HwBlob childBlob3 = new HwBlob(_hidl_vec_size3 * 64);
        for (int _hidl_index_03 = 0; _hidl_index_03 < _hidl_vec_size3; _hidl_index_03++) {
            this.peerAbility.get(_hidl_index_03).writeEmbeddedToBlob(childBlob3, _hidl_index_03 * 64);
        }
        _hidl_blob.putBlob(48 + _hidl_offset + 0, childBlob3);
        _hidl_blob.putInt32(64 + _hidl_offset, this.callSubstate);
        _hidl_blob.putInt32(68 + _hidl_offset, this.mediaId);
        _hidl_blob.putInt32(72 + _hidl_offset, this.causeCode);
        _hidl_blob.putInt32(76 + _hidl_offset, this.rttMode);
        _hidl_blob.putString(80 + _hidl_offset, this.sipAlternateUri);
    }
}
