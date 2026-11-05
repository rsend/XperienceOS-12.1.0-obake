package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class AddressInfo {
    public String city = new String();
    public String state = new String();
    public String country = new String();
    public String postalCode = new String();
    public String countryCode = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == AddressInfo.class) {
            AddressInfo other = (AddressInfo) otherObject;
            return HidlSupport.deepEquals(this.city, other.city) && HidlSupport.deepEquals(this.state, other.state) && HidlSupport.deepEquals(this.country, other.country) && HidlSupport.deepEquals(this.postalCode, other.postalCode) && HidlSupport.deepEquals(this.countryCode, other.countryCode);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.city)), Integer.valueOf(HidlSupport.deepHashCode(this.state)), Integer.valueOf(HidlSupport.deepHashCode(this.country)), Integer.valueOf(HidlSupport.deepHashCode(this.postalCode)), Integer.valueOf(HidlSupport.deepHashCode(this.countryCode)));
    }

    public final String toString() {
        return "{.city = " + this.city + ", .state = " + this.state + ", .country = " + this.country + ", .postalCode = " + this.postalCode + ", .countryCode = " + this.countryCode + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(80L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<AddressInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<AddressInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 80, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            AddressInfo _hidl_vec_element = new AddressInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 80);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.city = _hidl_blob.getString(0 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.city.getBytes().length + 1, _hidl_blob.handle(), 0 + 0 + _hidl_offset, false);
        this.state = _hidl_blob.getString(16 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.state.getBytes().length + 1, _hidl_blob.handle(), 0 + 16 + _hidl_offset, false);
        this.country = _hidl_blob.getString(32 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.country.getBytes().length + 1, _hidl_blob.handle(), 0 + 32 + _hidl_offset, false);
        this.postalCode = _hidl_blob.getString(48 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.postalCode.getBytes().length + 1, _hidl_blob.handle(), 0 + 48 + _hidl_offset, false);
        this.countryCode = _hidl_blob.getString(64 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.countryCode.getBytes().length + 1, _hidl_blob.handle(), 0 + 64 + _hidl_offset, false);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(80);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<AddressInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 80);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 80);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putString(0 + _hidl_offset, this.city);
        _hidl_blob.putString(16 + _hidl_offset, this.state);
        _hidl_blob.putString(32 + _hidl_offset, this.country);
        _hidl_blob.putString(48 + _hidl_offset, this.postalCode);
        _hidl_blob.putString(64 + _hidl_offset, this.countryCode);
    }
}
