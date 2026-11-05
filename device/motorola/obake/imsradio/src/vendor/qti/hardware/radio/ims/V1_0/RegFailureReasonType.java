package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class RegFailureReasonType {
    public static final int CALL_MANAGER_DEFINED = 3;
    public static final int EHRPD = 6;
    public static final int HANDOFF = 9;
    public static final int INTERNAL = 2;
    public static final int INVALID = 10;
    public static final int IPV6 = 7;
    public static final int IWLAN = 8;
    public static final int MOBILE_IP = 1;
    public static final int PPP = 5;
    public static final int TYPE_3GPP_SPEC_DEFINED = 4;
    public static final int UNSPECIFIED = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNSPECIFIED";
        }
        if (o == 1) {
            return "MOBILE_IP";
        }
        if (o == 2) {
            return "INTERNAL";
        }
        if (o == 3) {
            return "CALL_MANAGER_DEFINED";
        }
        if (o == 4) {
            return "TYPE_3GPP_SPEC_DEFINED";
        }
        if (o == 5) {
            return "PPP";
        }
        if (o == 6) {
            return "EHRPD";
        }
        if (o == 7) {
            return "IPV6";
        }
        if (o == 8) {
            return "IWLAN";
        }
        if (o == 9) {
            return "HANDOFF";
        }
        if (o == 10) {
            return "INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("UNSPECIFIED");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("MOBILE_IP");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("INTERNAL");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CALL_MANAGER_DEFINED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("TYPE_3GPP_SPEC_DEFINED");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("PPP");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("EHRPD");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("IPV6");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("IWLAN");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("HANDOFF");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("INVALID");
            flipped |= 10;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
