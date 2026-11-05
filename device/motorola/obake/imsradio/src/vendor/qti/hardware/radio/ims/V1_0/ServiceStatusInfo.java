package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ServiceStatusInfo {
    public int callType;
    public boolean hasIsValid;
    public boolean isValid;
    public int restrictCause;
    public int rttMode;
    public int status;
    public int type;
    public final ArrayList<Byte> userdata = new ArrayList<>();
    public final ArrayList<StatusForAccessTech> accTechStatus = new ArrayList<>();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == ServiceStatusInfo.class) {
            ServiceStatusInfo other = (ServiceStatusInfo) otherObject;
            return this.hasIsValid == other.hasIsValid && this.isValid == other.isValid && this.type == other.type && this.callType == other.callType && this.status == other.status && HidlSupport.deepEquals(this.userdata, other.userdata) && this.restrictCause == other.restrictCause && HidlSupport.deepEquals(this.accTechStatus, other.accTechStatus) && this.rttMode == other.rttMode;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.callType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(this.userdata)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.restrictCause))), Integer.valueOf(HidlSupport.deepHashCode(this.accTechStatus)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rttMode))));
    }

    public final String toString() {
        return "{.hasIsValid = " + this.hasIsValid + ", .isValid = " + this.isValid + ", .type = " + ServiceType.toString(this.type) + ", .callType = " + CallType.toString(this.callType) + ", .status = " + StatusType.toString(this.status) + ", .userdata = " + this.userdata + ", .restrictCause = " + this.restrictCause + ", .accTechStatus = " + this.accTechStatus + ", .rttMode = " + RttMode.toString(this.rttMode) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(64L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<ServiceStatusInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<ServiceStatusInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ServiceStatusInfo _hidl_vec_element = new ServiceStatusInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.hasIsValid = _hidl_blob.getBool(0 + _hidl_offset);
        this.isValid = _hidl_blob.getBool(1 + _hidl_offset);
        this.type = _hidl_blob.getInt32(4 + _hidl_offset);
        this.callType = _hidl_blob.getInt32(8 + _hidl_offset);
        this.status = _hidl_blob.getInt32(12 + _hidl_offset);
        int _hidl_vec_size = _hidl_blob.getInt32(16 + _hidl_offset + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 1, _hidl_blob.handle(), 16 + _hidl_offset + 0, true);
        this.userdata.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.userdata.add(Byte.valueOf(childBlob.getInt8(_hidl_index_0 * 1)));
        }
        this.restrictCause = _hidl_blob.getInt32(32 + _hidl_offset);
        int _hidl_vec_size2 = _hidl_blob.getInt32(40 + _hidl_offset + 8);
        HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 64, _hidl_blob.handle(), 40 + _hidl_offset + 0, true);
        this.accTechStatus.clear();
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            StatusForAccessTech _hidl_vec_element = new StatusForAccessTech();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob2, _hidl_index_02 * 64);
            this.accTechStatus.add(_hidl_vec_element);
        }
        this.rttMode = _hidl_blob.getInt32(56 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<ServiceStatusInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 64);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 64);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putBool(0 + _hidl_offset, this.hasIsValid);
        _hidl_blob.putBool(1 + _hidl_offset, this.isValid);
        _hidl_blob.putInt32(4 + _hidl_offset, this.type);
        _hidl_blob.putInt32(8 + _hidl_offset, this.callType);
        _hidl_blob.putInt32(12 + _hidl_offset, this.status);
        int _hidl_vec_size = this.userdata.size();
        _hidl_blob.putInt32(16 + _hidl_offset + 8, _hidl_vec_size);
        _hidl_blob.putBool(16 + _hidl_offset + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 1);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            childBlob.putInt8(_hidl_index_0 * 1, this.userdata.get(_hidl_index_0).byteValue());
        }
        _hidl_blob.putBlob(16 + _hidl_offset + 0, childBlob);
        _hidl_blob.putInt32(32 + _hidl_offset, this.restrictCause);
        int _hidl_vec_size2 = this.accTechStatus.size();
        _hidl_blob.putInt32(40 + _hidl_offset + 8, _hidl_vec_size2);
        _hidl_blob.putBool(40 + _hidl_offset + 12, false);
        HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 64);
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            this.accTechStatus.get(_hidl_index_02).writeEmbeddedToBlob(childBlob2, _hidl_index_02 * 64);
        }
        _hidl_blob.putBlob(40 + _hidl_offset + 0, childBlob2);
        _hidl_blob.putInt32(56 + _hidl_offset, this.rttMode);
    }
}
