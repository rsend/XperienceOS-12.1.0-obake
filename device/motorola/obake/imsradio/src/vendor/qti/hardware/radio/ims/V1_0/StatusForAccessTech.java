package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class StatusForAccessTech {
    public boolean hasRegistration;
    public int networkMode;
    public final RegistrationInfo registration = new RegistrationInfo();
    public int restrictCause;
    public int status;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == StatusForAccessTech.class) {
            StatusForAccessTech other = (StatusForAccessTech) otherObject;
            return this.networkMode == other.networkMode && this.status == other.status && this.restrictCause == other.restrictCause && this.hasRegistration == other.hasRegistration && HidlSupport.deepEquals(this.registration, other.registration);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.networkMode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.restrictCause))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasRegistration))), Integer.valueOf(HidlSupport.deepHashCode(this.registration)));
    }

    public final String toString() {
        return "{.networkMode = " + RadioTechType.toString(this.networkMode) + ", .status = " + StatusType.toString(this.status) + ", .restrictCause = " + this.restrictCause + ", .hasRegistration = " + this.hasRegistration + ", .registration = " + this.registration + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(64L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<StatusForAccessTech> readVectorFromParcel(HwParcel parcel) {
        ArrayList<StatusForAccessTech> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 64, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            StatusForAccessTech _hidl_vec_element = new StatusForAccessTech();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 64);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.networkMode = _hidl_blob.getInt32(0 + _hidl_offset);
        this.status = _hidl_blob.getInt32(4 + _hidl_offset);
        this.restrictCause = _hidl_blob.getInt32(8 + _hidl_offset);
        this.hasRegistration = _hidl_blob.getBool(12 + _hidl_offset);
        this.registration.readEmbeddedFromParcel(parcel, _hidl_blob, 16 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(64);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<StatusForAccessTech> _hidl_vec) {
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
        _hidl_blob.putInt32(0 + _hidl_offset, this.networkMode);
        _hidl_blob.putInt32(4 + _hidl_offset, this.status);
        _hidl_blob.putInt32(8 + _hidl_offset, this.restrictCause);
        _hidl_blob.putBool(12 + _hidl_offset, this.hasRegistration);
        this.registration.writeEmbeddedToBlob(_hidl_blob, 16 + _hidl_offset);
    }
}
