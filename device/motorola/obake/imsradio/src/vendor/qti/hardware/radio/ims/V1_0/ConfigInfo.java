package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ConfigInfo {
    public boolean boolValue;
    public int errorCause;
    public boolean hasBoolValue;
    public int intValue;
    public int item;
    public String stringValue = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == ConfigInfo.class) {
            ConfigInfo other = (ConfigInfo) otherObject;
            return this.item == other.item && this.hasBoolValue == other.hasBoolValue && this.boolValue == other.boolValue && this.intValue == other.intValue && HidlSupport.deepEquals(this.stringValue, other.stringValue) && this.errorCause == other.errorCause;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.item))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasBoolValue))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.boolValue))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.intValue))), Integer.valueOf(HidlSupport.deepHashCode(this.stringValue)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.errorCause))));
    }

    public final String toString() {
        return "{.item = " + ConfigItem.toString(this.item) + ", .hasBoolValue = " + this.hasBoolValue + ", .boolValue = " + this.boolValue + ", .intValue = " + this.intValue + ", .stringValue = " + this.stringValue + ", .errorCause = " + ConfigFailureCause.toString(this.errorCause) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(40L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<ConfigInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<ConfigInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ConfigInfo _hidl_vec_element = new ConfigInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.item = _hidl_blob.getInt32(_hidl_offset + 0);
        this.hasBoolValue = _hidl_blob.getBool(4 + _hidl_offset);
        this.boolValue = _hidl_blob.getBool(5 + _hidl_offset);
        this.intValue = _hidl_blob.getInt32(8 + _hidl_offset);
        this.stringValue = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(this.stringValue.getBytes().length + 1, _hidl_blob.handle(), 0 + _hidl_offset + 16, false);
        this.errorCause = _hidl_blob.getInt32(32 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(40);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<ConfigInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 40);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 40);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.item);
        _hidl_blob.putBool(4 + _hidl_offset, this.hasBoolValue);
        _hidl_blob.putBool(5 + _hidl_offset, this.boolValue);
        _hidl_blob.putInt32(8 + _hidl_offset, this.intValue);
        _hidl_blob.putString(16 + _hidl_offset, this.stringValue);
        _hidl_blob.putInt32(32 + _hidl_offset, this.errorCause);
    }
}
