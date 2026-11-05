package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ColrInfo {
    public final SipErrorInfo errorDetails = new SipErrorInfo();
    public boolean hasErrorDetails;
    public int presentation;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == ColrInfo.class) {
            ColrInfo other = (ColrInfo) otherObject;
            return this.presentation == other.presentation && this.hasErrorDetails == other.hasErrorDetails && HidlSupport.deepEquals(this.errorDetails, other.errorDetails);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.presentation))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasErrorDetails))), Integer.valueOf(HidlSupport.deepHashCode(this.errorDetails)));
    }

    public final String toString() {
        return "{.presentation = " + IpPresentation.toString(this.presentation) + ", .hasErrorDetails = " + this.hasErrorDetails + ", .errorDetails = " + this.errorDetails + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(32L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<ColrInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<ColrInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ColrInfo _hidl_vec_element = new ColrInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 32);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.presentation = _hidl_blob.getInt32(0 + _hidl_offset);
        this.hasErrorDetails = _hidl_blob.getBool(4 + _hidl_offset);
        this.errorDetails.readEmbeddedFromParcel(parcel, _hidl_blob, 8 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(32);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<ColrInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 32);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.presentation);
        _hidl_blob.putBool(4 + _hidl_offset, this.hasErrorDetails);
        this.errorDetails.writeEmbeddedToBlob(_hidl_blob, 8 + _hidl_offset);
    }
}
