package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SuppServiceNotification {
    public int code;
    public int connId;
    public boolean hasHoldTone;
    public boolean holdTone;
    public int index;
    public int notificationType;
    public int type;
    public String number = new String();
    public String historyInfo = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == SuppServiceNotification.class) {
            SuppServiceNotification other = (SuppServiceNotification) otherObject;
            return this.notificationType == other.notificationType && this.code == other.code && this.index == other.index && this.type == other.type && HidlSupport.deepEquals(this.number, other.number) && this.connId == other.connId && HidlSupport.deepEquals(this.historyInfo, other.historyInfo) && this.hasHoldTone == other.hasHoldTone && this.holdTone == other.holdTone;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notificationType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.code))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.index))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.connId))), Integer.valueOf(HidlSupport.deepHashCode(this.historyInfo)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasHoldTone))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.holdTone))));
    }

    public final String toString() {
        return "{.notificationType = " + NotificationType.toString(this.notificationType) + ", .code = " + this.code + ", .index = " + this.index + ", .type = " + this.type + ", .number = " + this.number + ", .connId = " + this.connId + ", .historyInfo = " + this.historyInfo + ", .hasHoldTone = " + this.hasHoldTone + ", .holdTone = " + this.holdTone + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(64L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<SuppServiceNotification> readVectorFromParcel(HwParcel parcel) {
        ArrayList<SuppServiceNotification> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            SuppServiceNotification _hidl_vec_element = new SuppServiceNotification();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.notificationType = _hidl_blob.getInt32(0 + _hidl_offset);
        this.code = _hidl_blob.getInt32(4 + _hidl_offset);
        this.index = _hidl_blob.getInt32(8 + _hidl_offset);
        this.type = _hidl_blob.getInt32(12 + _hidl_offset);
        this.number = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.number.getBytes().length + 1, _hidl_blob.handle(), 0 + 16 + _hidl_offset, false);
        this.connId = _hidl_blob.getInt32(32 + _hidl_offset);
        this.historyInfo = _hidl_blob.getString(40 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.historyInfo.getBytes().length + 1, _hidl_blob.handle(), 0 + 40 + _hidl_offset, false);
        this.hasHoldTone = _hidl_blob.getBool(56 + _hidl_offset);
        this.holdTone = _hidl_blob.getBool(57 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<SuppServiceNotification> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.notificationType);
        _hidl_blob.putInt32(4 + _hidl_offset, this.code);
        _hidl_blob.putInt32(8 + _hidl_offset, this.index);
        _hidl_blob.putInt32(12 + _hidl_offset, this.type);
        _hidl_blob.putString(16 + _hidl_offset, this.number);
        _hidl_blob.putInt32(32 + _hidl_offset, this.connId);
        _hidl_blob.putString(40 + _hidl_offset, this.historyInfo);
        _hidl_blob.putBool(56 + _hidl_offset, this.hasHoldTone);
        _hidl_blob.putBool(57 + _hidl_offset, this.holdTone);
    }
}
