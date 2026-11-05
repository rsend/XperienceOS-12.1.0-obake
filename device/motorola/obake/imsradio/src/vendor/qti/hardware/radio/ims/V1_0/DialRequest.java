package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class DialRequest {
    public String address = new String();
    public final CallDetails callDetails = new CallDetails();
    public int clirMode;
    public boolean hasCallDetails;
    public boolean hasIsCallPull;
    public boolean hasIsConferenceUri;
    public boolean hasIsEncrypted;
    public boolean isCallPull;
    public boolean isConferenceUri;
    public boolean isEncrypted;
    public int presentation;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == DialRequest.class) {
            DialRequest other = (DialRequest) otherObject;
            return HidlSupport.deepEquals(this.address, other.address) && this.clirMode == other.clirMode && this.presentation == other.presentation && this.hasCallDetails == other.hasCallDetails && HidlSupport.deepEquals(this.callDetails, other.callDetails) && this.hasIsConferenceUri == other.hasIsConferenceUri && this.isConferenceUri == other.isConferenceUri && this.hasIsCallPull == other.hasIsCallPull && this.isCallPull == other.isCallPull && this.hasIsEncrypted == other.hasIsEncrypted && this.isEncrypted == other.isEncrypted;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.address)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.clirMode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.presentation))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasCallDetails))), Integer.valueOf(HidlSupport.deepHashCode(this.callDetails)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsConferenceUri))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isConferenceUri))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsCallPull))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isCallPull))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsEncrypted))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isEncrypted))));
    }

    public final String toString() {
        return "{.address = " + this.address + ", .clirMode = " + this.clirMode + ", .presentation = " + IpPresentation.toString(this.presentation) + ", .hasCallDetails = " + this.hasCallDetails + ", .callDetails = " + this.callDetails + ", .hasIsConferenceUri = " + this.hasIsConferenceUri + ", .isConferenceUri = " + this.isConferenceUri + ", .hasIsCallPull = " + this.hasIsCallPull + ", .isCallPull = " + this.isCallPull + ", .hasIsEncrypted = " + this.hasIsEncrypted + ", .isEncrypted = " + this.isEncrypted + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(136L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<DialRequest> readVectorFromParcel(HwParcel parcel) {
        ArrayList<DialRequest> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 136, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            DialRequest _hidl_vec_element = new DialRequest();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 136);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.address = _hidl_blob.getString(_hidl_offset + 0);
        parcel.readEmbeddedBuffer(this.address.getBytes().length + 1, _hidl_blob.handle(), 0 + _hidl_offset + 0, false);
        this.clirMode = _hidl_blob.getInt32(16 + _hidl_offset);
        this.presentation = _hidl_blob.getInt32(20 + _hidl_offset);
        this.hasCallDetails = _hidl_blob.getBool(24 + _hidl_offset);
        this.callDetails.readEmbeddedFromParcel(parcel, _hidl_blob, 32 + _hidl_offset);
        this.hasIsConferenceUri = _hidl_blob.getBool(128 + _hidl_offset);
        this.isConferenceUri = _hidl_blob.getBool(129 + _hidl_offset);
        this.hasIsCallPull = _hidl_blob.getBool(130 + _hidl_offset);
        this.isCallPull = _hidl_blob.getBool(131 + _hidl_offset);
        this.hasIsEncrypted = _hidl_blob.getBool(132 + _hidl_offset);
        this.isEncrypted = _hidl_blob.getBool(133 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(136);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<DialRequest> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 136);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 136);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.address);
        _hidl_blob.putInt32(16 + _hidl_offset, this.clirMode);
        _hidl_blob.putInt32(20 + _hidl_offset, this.presentation);
        _hidl_blob.putBool(24 + _hidl_offset, this.hasCallDetails);
        this.callDetails.writeEmbeddedToBlob(_hidl_blob, 32 + _hidl_offset);
        _hidl_blob.putBool(128 + _hidl_offset, this.hasIsConferenceUri);
        _hidl_blob.putBool(129 + _hidl_offset, this.isConferenceUri);
        _hidl_blob.putBool(130 + _hidl_offset, this.hasIsCallPull);
        _hidl_blob.putBool(131 + _hidl_offset, this.isCallPull);
        _hidl_blob.putBool(132 + _hidl_offset, this.hasIsEncrypted);
        _hidl_blob.putBool(133 + _hidl_offset, this.isEncrypted);
    }
}
