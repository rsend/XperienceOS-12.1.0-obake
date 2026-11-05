package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SuppServiceStatus {
    public int facilityType;
    public boolean hasErrorDetails;
    public int status;
    public String failureCause = new String();
    public final ArrayList<CbNumListInfo> cbNumListInfo = new ArrayList<>();
    public final SipErrorInfo errorDetails = new SipErrorInfo();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == SuppServiceStatus.class) {
            SuppServiceStatus other = (SuppServiceStatus) otherObject;
            return this.status == other.status && this.facilityType == other.facilityType && HidlSupport.deepEquals(this.failureCause, other.failureCause) && HidlSupport.deepEquals(this.cbNumListInfo, other.cbNumListInfo) && this.hasErrorDetails == other.hasErrorDetails && HidlSupport.deepEquals(this.errorDetails, other.errorDetails);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.facilityType))), Integer.valueOf(HidlSupport.deepHashCode(this.failureCause)), Integer.valueOf(HidlSupport.deepHashCode(this.cbNumListInfo)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasErrorDetails))), Integer.valueOf(HidlSupport.deepHashCode(this.errorDetails)));
    }

    public final String toString() {
        return "{.status = " + ServiceClassStatus.toString(this.status) + ", .facilityType = " + FacilityType.toString(this.facilityType) + ", .failureCause = " + this.failureCause + ", .cbNumListInfo = " + this.cbNumListInfo + ", .hasErrorDetails = " + this.hasErrorDetails + ", .errorDetails = " + this.errorDetails + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(72L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<SuppServiceStatus> readVectorFromParcel(HwParcel parcel) {
        ArrayList<SuppServiceStatus> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 72, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            SuppServiceStatus _hidl_vec_element = new SuppServiceStatus();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 72);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.status = _hidl_blob.getInt32(0 + _hidl_offset);
        this.facilityType = _hidl_blob.getInt32(4 + _hidl_offset);
        this.failureCause = _hidl_blob.getString(8 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.failureCause.getBytes().length + 1, _hidl_blob.handle(), 8 + _hidl_offset + 0, false);
        int _hidl_vec_size = _hidl_blob.getInt32(24 + _hidl_offset + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 24, _hidl_blob.handle(), 24 + _hidl_offset + 0, true);
        this.cbNumListInfo.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            CbNumListInfo _hidl_vec_element = new CbNumListInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 24);
            this.cbNumListInfo.add(_hidl_vec_element);
        }
        this.hasErrorDetails = _hidl_blob.getBool(40 + _hidl_offset);
        this.errorDetails.readEmbeddedFromParcel(parcel, _hidl_blob, 48 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(72);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<SuppServiceStatus> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 72);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 72);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(_hidl_offset + 0, this.status);
        _hidl_blob.putInt32(4 + _hidl_offset, this.facilityType);
        _hidl_blob.putString(_hidl_offset + 8, this.failureCause);
        int _hidl_vec_size = this.cbNumListInfo.size();
        _hidl_blob.putInt32(_hidl_offset + 24 + 8, _hidl_vec_size);
        _hidl_blob.putBool(_hidl_offset + 24 + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 24);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.cbNumListInfo.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 24);
        }
        _hidl_blob.putBlob(_hidl_offset + 24 + 0, childBlob);
        _hidl_blob.putBool(40 + _hidl_offset, this.hasErrorDetails);
        this.errorDetails.writeEmbeddedToBlob(_hidl_blob, 48 + _hidl_offset);
    }
}
