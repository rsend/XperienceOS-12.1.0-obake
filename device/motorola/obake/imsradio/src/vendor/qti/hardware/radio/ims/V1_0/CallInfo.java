package vendor.qti.hardware.radio.ims.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CallInfo {
    public int als;
    public boolean hasCallDetails;
    public boolean hasFailCause;
    public boolean hasIsCalledPartyRinging;
    public boolean hasIsEncrypted;
    public boolean hasIsMT;
    public boolean hasIsMpty;
    public boolean hasIsVideoConfSupported;
    public boolean hasIsVoice;
    public boolean hasIsVoicePrivacy;
    public int index;
    public boolean isCalledPartyRinging;
    public boolean isEncrypted;
    public boolean isMT;
    public boolean isMpty;
    public boolean isVideoConfSupported;
    public boolean isVoice;
    public boolean isVoicePrivacy;
    public int namePresentation;
    public int numberPresentation;
    public int state;
    public int toa;
    public String number = new String();
    public String name = new String();
    public final CallDetails callDetails = new CallDetails();
    public final CallFailCauseResponse failCause = new CallFailCauseResponse();
    public String historyInfo = new String();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject != null && otherObject.getClass() == CallInfo.class) {
            CallInfo other = (CallInfo) otherObject;
            return this.state == other.state && this.index == other.index && this.toa == other.toa && this.hasIsMpty == other.hasIsMpty && this.isMpty == other.isMpty && this.hasIsMT == other.hasIsMT && this.isMT == other.isMT && this.als == other.als && this.hasIsVoice == other.hasIsVoice && this.isVoice == other.isVoice && this.hasIsVoicePrivacy == other.hasIsVoicePrivacy && this.isVoicePrivacy == other.isVoicePrivacy && HidlSupport.deepEquals(this.number, other.number) && this.numberPresentation == other.numberPresentation && HidlSupport.deepEquals(this.name, other.name) && this.namePresentation == other.namePresentation && this.hasCallDetails == other.hasCallDetails && HidlSupport.deepEquals(this.callDetails, other.callDetails) && this.hasFailCause == other.hasFailCause && HidlSupport.deepEquals(this.failCause, other.failCause) && this.hasIsEncrypted == other.hasIsEncrypted && this.isEncrypted == other.isEncrypted && this.hasIsCalledPartyRinging == other.hasIsCalledPartyRinging && this.isCalledPartyRinging == other.isCalledPartyRinging && HidlSupport.deepEquals(this.historyInfo, other.historyInfo) && this.hasIsVideoConfSupported == other.hasIsVideoConfSupported && this.isVideoConfSupported == other.isVideoConfSupported;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.index))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.toa))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsMpty))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isMpty))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsMT))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isMT))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.als))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsVoice))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isVoice))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsVoicePrivacy))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isVoicePrivacy))), Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberPresentation))), Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.namePresentation))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasCallDetails))), Integer.valueOf(HidlSupport.deepHashCode(this.callDetails)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasFailCause))), Integer.valueOf(HidlSupport.deepHashCode(this.failCause)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsEncrypted))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isEncrypted))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsCalledPartyRinging))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isCalledPartyRinging))), Integer.valueOf(HidlSupport.deepHashCode(this.historyInfo)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasIsVideoConfSupported))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isVideoConfSupported))));
    }

    public final String toString() {
        return "{.state = " + CallState.toString(this.state) + ", .index = " + this.index + ", .toa = " + this.toa + ", .hasIsMpty = " + this.hasIsMpty + ", .isMpty = " + this.isMpty + ", .hasIsMT = " + this.hasIsMT + ", .isMT = " + this.isMT + ", .als = " + this.als + ", .hasIsVoice = " + this.hasIsVoice + ", .isVoice = " + this.isVoice + ", .hasIsVoicePrivacy = " + this.hasIsVoicePrivacy + ", .isVoicePrivacy = " + this.isVoicePrivacy + ", .number = " + this.number + ", .numberPresentation = " + this.numberPresentation + ", .name = " + this.name + ", .namePresentation = " + this.namePresentation + ", .hasCallDetails = " + this.hasCallDetails + ", .callDetails = " + this.callDetails + ", .hasFailCause = " + this.hasFailCause + ", .failCause = " + this.failCause + ", .hasIsEncrypted = " + this.hasIsEncrypted + ", .isEncrypted = " + this.isEncrypted + ", .hasIsCalledPartyRinging = " + this.hasIsCalledPartyRinging + ", .isCalledPartyRinging = " + this.isCalledPartyRinging + ", .historyInfo = " + this.historyInfo + ", .hasIsVideoConfSupported = " + this.hasIsVideoConfSupported + ", .isVideoConfSupported = " + this.isVideoConfSupported + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(280L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<CallInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<CallInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 280, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            CallInfo _hidl_vec_element = new CallInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 280);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.state = _hidl_blob.getInt32(0 + _hidl_offset);
        this.index = _hidl_blob.getInt32(4 + _hidl_offset);
        this.toa = _hidl_blob.getInt32(8 + _hidl_offset);
        this.hasIsMpty = _hidl_blob.getBool(12 + _hidl_offset);
        this.isMpty = _hidl_blob.getBool(13 + _hidl_offset);
        this.hasIsMT = _hidl_blob.getBool(14 + _hidl_offset);
        this.isMT = _hidl_blob.getBool(15 + _hidl_offset);
        this.als = _hidl_blob.getInt32(16 + _hidl_offset);
        this.hasIsVoice = _hidl_blob.getBool(20 + _hidl_offset);
        this.isVoice = _hidl_blob.getBool(21 + _hidl_offset);
        this.hasIsVoicePrivacy = _hidl_blob.getBool(22 + _hidl_offset);
        this.isVoicePrivacy = _hidl_blob.getBool(23 + _hidl_offset);
        this.number = _hidl_blob.getString(24 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.number.getBytes().length + 1, _hidl_blob.handle(), 0 + 24 + _hidl_offset, false);
        this.numberPresentation = _hidl_blob.getInt32(40 + _hidl_offset);
        this.name = _hidl_blob.getString(48 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.name.getBytes().length + 1, _hidl_blob.handle(), 0 + 48 + _hidl_offset, false);
        this.namePresentation = _hidl_blob.getInt32(64 + _hidl_offset);
        this.hasCallDetails = _hidl_blob.getBool(68 + _hidl_offset);
        this.callDetails.readEmbeddedFromParcel(parcel, _hidl_blob, 72 + _hidl_offset);
        this.hasFailCause = _hidl_blob.getBool(168 + _hidl_offset);
        this.failCause.readEmbeddedFromParcel(parcel, _hidl_blob, 176 + _hidl_offset);
        this.hasIsEncrypted = _hidl_blob.getBool(248 + _hidl_offset);
        this.isEncrypted = _hidl_blob.getBool(249 + _hidl_offset);
        this.hasIsCalledPartyRinging = _hidl_blob.getBool(250 + _hidl_offset);
        this.isCalledPartyRinging = _hidl_blob.getBool(251 + _hidl_offset);
        this.historyInfo = _hidl_blob.getString(256 + _hidl_offset);
        parcel.readEmbeddedBuffer(this.historyInfo.getBytes().length + 1, _hidl_blob.handle(), 0 + 256 + _hidl_offset, false);
        this.hasIsVideoConfSupported = _hidl_blob.getBool(272 + _hidl_offset);
        this.isVideoConfSupported = _hidl_blob.getBool(273 + _hidl_offset);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(280);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<CallInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 280);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 280);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt32(0 + _hidl_offset, this.state);
        _hidl_blob.putInt32(4 + _hidl_offset, this.index);
        _hidl_blob.putInt32(8 + _hidl_offset, this.toa);
        _hidl_blob.putBool(12 + _hidl_offset, this.hasIsMpty);
        _hidl_blob.putBool(13 + _hidl_offset, this.isMpty);
        _hidl_blob.putBool(14 + _hidl_offset, this.hasIsMT);
        _hidl_blob.putBool(15 + _hidl_offset, this.isMT);
        _hidl_blob.putInt32(16 + _hidl_offset, this.als);
        _hidl_blob.putBool(20 + _hidl_offset, this.hasIsVoice);
        _hidl_blob.putBool(21 + _hidl_offset, this.isVoice);
        _hidl_blob.putBool(22 + _hidl_offset, this.hasIsVoicePrivacy);
        _hidl_blob.putBool(23 + _hidl_offset, this.isVoicePrivacy);
        _hidl_blob.putString(24 + _hidl_offset, this.number);
        _hidl_blob.putInt32(40 + _hidl_offset, this.numberPresentation);
        _hidl_blob.putString(48 + _hidl_offset, this.name);
        _hidl_blob.putInt32(64 + _hidl_offset, this.namePresentation);
        _hidl_blob.putBool(68 + _hidl_offset, this.hasCallDetails);
        this.callDetails.writeEmbeddedToBlob(_hidl_blob, 72 + _hidl_offset);
        _hidl_blob.putBool(168 + _hidl_offset, this.hasFailCause);
        this.failCause.writeEmbeddedToBlob(_hidl_blob, 176 + _hidl_offset);
        _hidl_blob.putBool(248 + _hidl_offset, this.hasIsEncrypted);
        _hidl_blob.putBool(249 + _hidl_offset, this.isEncrypted);
        _hidl_blob.putBool(250 + _hidl_offset, this.hasIsCalledPartyRinging);
        _hidl_blob.putBool(251 + _hidl_offset, this.isCalledPartyRinging);
        _hidl_blob.putString(256 + _hidl_offset, this.historyInfo);
        _hidl_blob.putBool(272 + _hidl_offset, this.hasIsVideoConfSupported);
        _hidl_blob.putBool(273 + _hidl_offset, this.isVideoConfSupported);
    }
}
