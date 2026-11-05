package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class VoWiFiCallQualityInfo {
    public static final int VOWIFI_CALL_QUALITY_BAD = 3;
    public static final int VOWIFI_CALL_QUALITY_EXCELLENT = 1;
    public static final int VOWIFI_CALL_QUALITY_FAIR = 2;
    public static final int VOWIFI_CALL_QUALITY_INVALID = 4;
    public static final int VOWIFI_CALL_QUALITY_NONE = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "VOWIFI_CALL_QUALITY_NONE";
        }
        if (o == 1) {
            return "VOWIFI_CALL_QUALITY_EXCELLENT";
        }
        if (o == 2) {
            return "VOWIFI_CALL_QUALITY_FAIR";
        }
        if (o == 3) {
            return "VOWIFI_CALL_QUALITY_BAD";
        }
        if (o == 4) {
            return "VOWIFI_CALL_QUALITY_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("VOWIFI_CALL_QUALITY_NONE");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("VOWIFI_CALL_QUALITY_EXCELLENT");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("VOWIFI_CALL_QUALITY_FAIR");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("VOWIFI_CALL_QUALITY_BAD");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("VOWIFI_CALL_QUALITY_INVALID");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
