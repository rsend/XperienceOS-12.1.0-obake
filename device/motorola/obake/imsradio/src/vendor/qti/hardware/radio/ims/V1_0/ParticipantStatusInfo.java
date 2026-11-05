package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ParticipantStatusInfo {
    public int callId;
    public boolean hasIsEct;
    public boolean isEct;
    public int operation;
    public String participantUri = new String();
    public int sipStatus;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == ParticipantStatusInfo.class) {
            ParticipantStatusInfo other = (ParticipantStatusInfo) otherObject;
            return this.callId == other.callId && this.operation == other.operation && this.sipStatus == other.sipStatus && HidlSupport.deepEquals(this.participantUri, other.participantUri) && this.hasIsEct == other.hasIsEct && this.isEct == other.isEct;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.callId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.operation))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sipStatus))), Integer.valueOf(HidlSupport.deepHashCode(this.participantUri)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsEct))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isEct))));
    }

    public final String toString() {
        return "{.callId = " + this.callId + ", .operation = " + ConfParticipantOperation.toString(this.operation) + ", .sipStatus = " + this.sipStatus + ", .participantUri = " + this.participantUri + ", .hasIsEct = " + this.hasIsEct + ", .isEct = " + this.isEct + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(40L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<ParticipantStatusInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<ParticipantStatusInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            ParticipantStatusInfo _hidl_vec_element = new ParticipantStatusInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.callId = _hidl_blob.getInt32(_hidl_offset + 0);
        this.operation = _hidl_blob.getInt32(4 + _hidl_offset);
        this.sipStatus = _hidl_blob.getInt32(8 + _hidl_offset);
        this.participantUri = _hidl_blob.getString(_hidl_offset + 16);
        parcel.readEmbeddedBuffer(this.participantUri.getBytes().length + 1, _hidl_blob.handle(), 0 + _hidl_offset + 16, false);
        this.hasIsEct = _hidl_blob.getBool(32 + _hidl_offset);
        this.isEct = _hidl_blob.getBool(33 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(40);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<ParticipantStatusInfo> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.callId);
        _hidl_blob.putInt32(4 + _hidl_offset, this.operation);
        _hidl_blob.putInt32(8 + _hidl_offset, this.sipStatus);
        _hidl_blob.putString(16 + _hidl_offset, this.participantUri);
        _hidl_blob.putBool(32 + _hidl_offset, this.hasIsEct);
        _hidl_blob.putBool(33 + _hidl_offset, this.isEct);
    }
}
