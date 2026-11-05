package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class MessageWaitingIndication {
    public final ArrayList<MessageSummary> messageSummary = new ArrayList<>();
    public String ueAddress = new String();
    public final ArrayList<MessageDetails> messageDetails = new ArrayList<>();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == MessageWaitingIndication.class) {
            MessageWaitingIndication other = (MessageWaitingIndication) otherObject;
            return HidlSupport.deepEquals(this.messageSummary, other.messageSummary) && HidlSupport.deepEquals(this.ueAddress, other.ueAddress) && HidlSupport.deepEquals(this.messageDetails, other.messageDetails);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.messageSummary)), Integer.valueOf(HidlSupport.deepHashCode(this.ueAddress)), Integer.valueOf(HidlSupport.deepHashCode(this.messageDetails)));
    }

    public final String toString() {
        return "{.messageSummary = " + this.messageSummary + ", .ueAddress = " + this.ueAddress + ", .messageDetails = " + this.messageDetails + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(48L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<MessageWaitingIndication> readVectorFromParcel(HwParcel parcel) {
        ArrayList<MessageWaitingIndication> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 48, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            MessageWaitingIndication _hidl_vec_element = new MessageWaitingIndication();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 48);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_vec_size = _hidl_blob.getInt32(0 + _hidl_offset + 8);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 20, _hidl_blob.handle(), 0 + _hidl_offset + 0, true);
        this.messageSummary.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            MessageSummary _hidl_vec_element = new MessageSummary();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 20);
            this.messageSummary.add(_hidl_vec_element);
        }
        this.ueAddress = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.ueAddress.getBytes().length + 1, _hidl_blob.handle(), 16 + _hidl_offset + 0, false);
        int _hidl_vec_size2 = _hidl_blob.getInt32(32 + _hidl_offset + 8);
        HwBlob childBlob2 = parcel.readEmbeddedBuffer(_hidl_vec_size2 * 96, _hidl_blob.handle(), 32 + _hidl_offset + 0, true);
        this.messageDetails.clear();
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            MessageDetails _hidl_vec_element2 = new MessageDetails();
            _hidl_vec_element2.readEmbeddedFromParcel(parcel, childBlob2, _hidl_index_02 * 96);
            this.messageDetails.add(_hidl_vec_element2);
        }
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(48);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<MessageWaitingIndication> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 48);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 48);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        int _hidl_vec_size = this.messageSummary.size();
        _hidl_blob.putInt32(0 + _hidl_offset + 8, _hidl_vec_size);
        _hidl_blob.putBool(0 + _hidl_offset + 12, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 20);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            this.messageSummary.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 20);
        }
        _hidl_blob.putBlob(0 + _hidl_offset + 0, childBlob);
        _hidl_blob.putString(16 + _hidl_offset, this.ueAddress);
        int _hidl_vec_size2 = this.messageDetails.size();
        _hidl_blob.putInt32(32 + _hidl_offset + 8, _hidl_vec_size2);
        _hidl_blob.putBool(32 + _hidl_offset + 12, false);
        HwBlob childBlob2 = new HwBlob(_hidl_vec_size2 * 96);
        for (int _hidl_index_02 = 0; _hidl_index_02 < _hidl_vec_size2; _hidl_index_02++) {
            this.messageDetails.get(_hidl_index_02).writeEmbeddedToBlob(childBlob2, _hidl_index_02 * 96);
        }
        _hidl_blob.putBlob(32 + _hidl_offset + 0, childBlob2);
    }
}
