package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class MessageSummary {
    public int newMessageCount;
    public int newUrgentMessageCount;
    public int oldMessageCount;
    public int oldUrgentMessageCount;
    public int type;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == MessageSummary.class) {
            MessageSummary other = (MessageSummary) otherObject;
            return this.type == other.type && this.newMessageCount == other.newMessageCount && this.oldMessageCount == other.oldMessageCount && this.newUrgentMessageCount == other.newUrgentMessageCount && this.oldUrgentMessageCount == other.oldUrgentMessageCount;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.newMessageCount))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.oldMessageCount))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.newUrgentMessageCount))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.oldUrgentMessageCount))));
    }

    public final String toString() {
        return "{.type = " + MessageType.toString(this.type) + ", .newMessageCount = " + this.newMessageCount + ", .oldMessageCount = " + this.oldMessageCount + ", .newUrgentMessageCount = " + this.newUrgentMessageCount + ", .oldUrgentMessageCount = " + this.oldUrgentMessageCount + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(20L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<MessageSummary> readVectorFromParcel(HwParcel parcel) {
        ArrayList<MessageSummary> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 20, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            MessageSummary _hidl_vec_element = new MessageSummary();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 20);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.type = _hidl_blob.getInt32(0 + _hidl_offset);
        this.newMessageCount = _hidl_blob.getInt32(4 + _hidl_offset);
        this.oldMessageCount = _hidl_blob.getInt32(8 + _hidl_offset);
        this.newUrgentMessageCount = _hidl_blob.getInt32(12 + _hidl_offset);
        this.oldUrgentMessageCount = _hidl_blob.getInt32(16 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(20);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<MessageSummary> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 20);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 20);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.type);
        _hidl_blob.putInt32(4 + _hidl_offset, this.newMessageCount);
        _hidl_blob.putInt32(8 + _hidl_offset, this.oldMessageCount);
        _hidl_blob.putInt32(12 + _hidl_offset, this.newUrgentMessageCount);
        _hidl_blob.putInt32(16 + _hidl_offset, this.oldUrgentMessageCount);
    }
}
