package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CallFwdTimerInfo {
    public int day;
    public int hour;
    public int minute;
    public int month;
    public int second;
    public int timezone;
    public int year;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == CallFwdTimerInfo.class) {
            CallFwdTimerInfo other = (CallFwdTimerInfo) otherObject;
            return this.year == other.year && this.month == other.month && this.day == other.day && this.hour == other.hour && this.minute == other.minute && this.second == other.second && this.timezone == other.timezone;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.year))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.month))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.day))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hour))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.minute))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.second))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timezone))));
    }

    public final String toString() {
        return "{.year = " + this.year + ", .month = " + this.month + ", .day = " + this.day + ", .hour = " + this.hour + ", .minute = " + this.minute + ", .second = " + this.second + ", .timezone = " + this.timezone + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(28L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<CallFwdTimerInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<CallFwdTimerInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 28, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            CallFwdTimerInfo _hidl_vec_element = new CallFwdTimerInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 28);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.year = _hidl_blob.getInt32(0 + _hidl_offset);
        this.month = _hidl_blob.getInt32(4 + _hidl_offset);
        this.day = _hidl_blob.getInt32(8 + _hidl_offset);
        this.hour = _hidl_blob.getInt32(12 + _hidl_offset);
        this.minute = _hidl_blob.getInt32(16 + _hidl_offset);
        this.second = _hidl_blob.getInt32(20 + _hidl_offset);
        this.timezone = _hidl_blob.getInt32(24 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(28);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<CallFwdTimerInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 28);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 28);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.year);
        _hidl_blob.putInt32(4 + _hidl_offset, this.month);
        _hidl_blob.putInt32(8 + _hidl_offset, this.day);
        _hidl_blob.putInt32(12 + _hidl_offset, this.hour);
        _hidl_blob.putInt32(16 + _hidl_offset, this.minute);
        _hidl_blob.putInt32(20 + _hidl_offset, this.second);
        _hidl_blob.putInt32(24 + _hidl_offset, this.timezone);
    }
}
