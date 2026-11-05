package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CallModifyInfo {
    private static final int HIDL_STRUCT_SIZE = 112;
    public final CallDetails callDetails = new CallDetails();
    public int callIndex;
    public int failCause;
    public boolean hasCallDetails;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == CallModifyInfo.class) {
            CallModifyInfo other = (CallModifyInfo) otherObject;
            return this.callIndex == other.callIndex && this.hasCallDetails == other.hasCallDetails && HidlSupport.deepEquals(this.callDetails, other.callDetails) && this.failCause == other.failCause;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.callIndex))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasCallDetails))), Integer.valueOf(HidlSupport.deepHashCode(this.callDetails)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.failCause))));
    }

    public final String toString() {
        return "{.callIndex = " + this.callIndex + ", .hasCallDetails = " + this.hasCallDetails + ", .callDetails = " + this.callDetails + ", .failCause = " + CallModifyFailCause.toString(this.failCause) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(112L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<CallModifyInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<CallModifyInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * HIDL_STRUCT_SIZE, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            CallModifyInfo _hidl_vec_element = new CallModifyInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * HIDL_STRUCT_SIZE);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.callIndex = _hidl_blob.getInt32(0 + _hidl_offset);
        this.hasCallDetails = _hidl_blob.getBool(4 + _hidl_offset);
        this.callDetails.readEmbeddedFromParcel(parcel, _hidl_blob, 8 + _hidl_offset);
        this.failCause = _hidl_blob.getInt32(104 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(HIDL_STRUCT_SIZE);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<CallModifyInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * HIDL_STRUCT_SIZE);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * HIDL_STRUCT_SIZE);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.callIndex);
        _hidl_blob.putBool(4 + _hidl_offset, this.hasCallDetails);
        this.callDetails.writeEmbeddedToBlob(_hidl_blob, 8 + _hidl_offset);
        _hidl_blob.putInt32(104 + _hidl_offset, this.failCause);
    }
}
