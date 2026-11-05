package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class MessageDetails {
    public int priority;
    public int type;
    public String toAddress = new String();
    public String fromAddress = new String();
    public String subject = new String();
    public String date = new String();
    public String id = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == MessageDetails.class) {
            MessageDetails other = (MessageDetails) otherObject;
            return HidlSupport.deepEquals(this.toAddress, other.toAddress) && HidlSupport.deepEquals(this.fromAddress, other.fromAddress) && HidlSupport.deepEquals(this.subject, other.subject) && HidlSupport.deepEquals(this.date, other.date) && this.priority == other.priority && HidlSupport.deepEquals(this.id, other.id) && this.type == other.type;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.toAddress)), Integer.valueOf(HidlSupport.deepHashCode(this.fromAddress)), Integer.valueOf(HidlSupport.deepHashCode(this.subject)), Integer.valueOf(HidlSupport.deepHashCode(this.date)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.priority))), Integer.valueOf(HidlSupport.deepHashCode(this.id)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))));
    }

    public final String toString() {
        return "{.toAddress = " + this.toAddress + ", .fromAddress = " + this.fromAddress + ", .subject = " + this.subject + ", .date = " + this.date + ", .priority = " + MessagePriority.toString(this.priority) + ", .id = " + this.id + ", .type = " + MessageType.toString(this.type) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(96L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<MessageDetails> readVectorFromParcel(HwParcel parcel) {
        ArrayList<MessageDetails> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 96, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            MessageDetails _hidl_vec_element = new MessageDetails();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 96);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.toAddress = _hidl_blob.getString(0 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.toAddress.getBytes().length + 1, _hidl_blob.handle(), 0 + 0 + _hidl_offset, false);
        this.fromAddress = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.fromAddress.getBytes().length + 1, _hidl_blob.handle(), 0 + 16 + _hidl_offset, false);
        this.subject = _hidl_blob.getString(32 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.subject.getBytes().length + 1, _hidl_blob.handle(), 0 + 32 + _hidl_offset, false);
        this.date = _hidl_blob.getString(48 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.date.getBytes().length + 1, _hidl_blob.handle(), 0 + 48 + _hidl_offset, false);
        this.priority = _hidl_blob.getInt32(64 + _hidl_offset);
        this.id = _hidl_blob.getString(72 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.id.getBytes().length + 1, _hidl_blob.handle(), 0 + 72 + _hidl_offset, false);
        this.type = _hidl_blob.getInt32(88 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(96);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<MessageDetails> _hidl_vec) {
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
        _hidl_blob.putString(0 + _hidl_offset, this.toAddress);
        _hidl_blob.putString(16 + _hidl_offset, this.fromAddress);
        _hidl_blob.putString(32 + _hidl_offset, this.subject);
        _hidl_blob.putString(48 + _hidl_offset, this.date);
        _hidl_blob.putInt32(64 + _hidl_offset, this.priority);
        _hidl_blob.putString(72 + _hidl_offset, this.id);
        _hidl_blob.putInt32(88 + _hidl_offset, this.type);
    }
}
