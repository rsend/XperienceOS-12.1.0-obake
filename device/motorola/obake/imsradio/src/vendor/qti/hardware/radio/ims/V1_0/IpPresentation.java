package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class IpPresentation {
    public static final int IP_PRESENTATION_INVALID = 3;
    public static final int IP_PRESENTATION_NUM_ALLOWED = 0;
    public static final int IP_PRESENTATION_NUM_DEFAULT = 2;
    public static final int IP_PRESENTATION_NUM_RESTRICTED = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "IP_PRESENTATION_NUM_ALLOWED";
        }
        if (o == 1) {
            return "IP_PRESENTATION_NUM_RESTRICTED";
        }
        if (o == 2) {
            return "IP_PRESENTATION_NUM_DEFAULT";
        }
        if (o == 3) {
            return "IP_PRESENTATION_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("IP_PRESENTATION_NUM_ALLOWED");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("IP_PRESENTATION_NUM_RESTRICTED");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("IP_PRESENTATION_NUM_DEFAULT");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("IP_PRESENTATION_INVALID");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
