package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class HandoverInfo {
    public boolean hasHoExtra;
    public int srcTech;
    public int targetTech;
    public int type;
    public final Extra hoExtra = new Extra();
    public String errorCode = new String();
    public String errorMessage = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == HandoverInfo.class) {
            HandoverInfo other = (HandoverInfo) otherObject;
            return this.type == other.type && this.srcTech == other.srcTech && this.targetTech == other.targetTech && this.hasHoExtra == other.hasHoExtra && HidlSupport.deepEquals(this.hoExtra, other.hoExtra) && HidlSupport.deepEquals(this.errorCode, other.errorCode) && HidlSupport.deepEquals(this.errorMessage, other.errorMessage);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.srcTech))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.targetTech))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasHoExtra))), Integer.valueOf(HidlSupport.deepHashCode(this.hoExtra)), Integer.valueOf(HidlSupport.deepHashCode(this.errorCode)), Integer.valueOf(HidlSupport.deepHashCode(this.errorMessage)));
    }

    public final String toString() {
        return "{.type = " + HandoverType.toString(this.type) + ", .srcTech = " + RadioTechType.toString(this.srcTech) + ", .targetTech = " + RadioTechType.toString(this.targetTech) + ", .hasHoExtra = " + this.hasHoExtra + ", .hoExtra = " + this.hoExtra + ", .errorCode = " + this.errorCode + ", .errorMessage = " + this.errorMessage + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(72L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HandoverInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HandoverInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 72, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HandoverInfo _hidl_vec_element = new HandoverInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 72);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.type = _hidl_blob.getInt32(0 + _hidl_offset);
        this.srcTech = _hidl_blob.getInt32(4 + _hidl_offset);
        this.targetTech = _hidl_blob.getInt32(8 + _hidl_offset);
        this.hasHoExtra = _hidl_blob.getBool(12 + _hidl_offset);
        this.hoExtra.readEmbeddedFromParcel(parcel, _hidl_blob, 16 + _hidl_offset);
        this.errorCode = _hidl_blob.getString(40 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.errorCode.getBytes().length + 1, _hidl_blob.handle(), 0 + 40 + _hidl_offset, false);
        this.errorMessage = _hidl_blob.getString(56 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.errorMessage.getBytes().length + 1, _hidl_blob.handle(), 0 + 56 + _hidl_offset, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(72);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HandoverInfo> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.type);
        _hidl_blob.putInt32(4 + _hidl_offset, this.srcTech);
        _hidl_blob.putInt32(8 + _hidl_offset, this.targetTech);
        _hidl_blob.putBool(12 + _hidl_offset, this.hasHoExtra);
        this.hoExtra.writeEmbeddedToBlob(_hidl_blob, 16 + _hidl_offset);
        _hidl_blob.putString(40 + _hidl_offset, this.errorCode);
        _hidl_blob.putString(56 + _hidl_offset, this.errorMessage);
    }
}
