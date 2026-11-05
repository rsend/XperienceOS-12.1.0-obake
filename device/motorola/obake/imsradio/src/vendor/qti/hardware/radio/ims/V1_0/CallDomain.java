package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class CallDomain {
    public static final int CALL_DOMAIN_AUTOMATIC = 3;
    public static final int CALL_DOMAIN_CS = 1;
    public static final int CALL_DOMAIN_INVALID = 5;
    public static final int CALL_DOMAIN_NOT_SET = 4;
    public static final int CALL_DOMAIN_PS = 2;
    public static final int CALL_DOMAIN_UNKNOWN = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "CALL_DOMAIN_UNKNOWN";
        }
        if (o == 1) {
            return "CALL_DOMAIN_CS";
        }
        if (o == 2) {
            return "CALL_DOMAIN_PS";
        }
        if (o == 3) {
            return "CALL_DOMAIN_AUTOMATIC";
        }
        if (o == 4) {
            return "CALL_DOMAIN_NOT_SET";
        }
        if (o == 5) {
            return "CALL_DOMAIN_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("CALL_DOMAIN_UNKNOWN");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("CALL_DOMAIN_CS");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("CALL_DOMAIN_PS");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CALL_DOMAIN_AUTOMATIC");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("CALL_DOMAIN_NOT_SET");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("CALL_DOMAIN_INVALID");
            flipped |= 5;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
