package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class BlockReasonType {
    public static final int HANDOVER_FAILURE = 2;
    public static final int INVALID = 4;
    public static final int OTHER_FAILURE = 3;
    public static final int PDP_FAILURE = 0;
    public static final int REGISTRATION_FAILURE = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "PDP_FAILURE";
        }
        if (o == 1) {
            return "REGISTRATION_FAILURE";
        }
        if (o == 2) {
            return "HANDOVER_FAILURE";
        }
        if (o == 3) {
            return "OTHER_FAILURE";
        }
        if (o == 4) {
            return "INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("PDP_FAILURE");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("REGISTRATION_FAILURE");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("HANDOVER_FAILURE");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("OTHER_FAILURE");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("INVALID");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
