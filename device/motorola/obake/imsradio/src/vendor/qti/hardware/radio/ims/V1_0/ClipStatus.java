package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ClipStatus {
    public static final int INVALID = 3;
    public static final int NOT_PROVISIONED = 0;
    public static final int PROVISIONED = 1;
    public static final int STATUS_UNKNOWN = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "NOT_PROVISIONED";
        }
        if (o == 1) {
            return "PROVISIONED";
        }
        if (o == 2) {
            return "STATUS_UNKNOWN";
        }
        if (o == 3) {
            return "INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("NOT_PROVISIONED");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("PROVISIONED");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("STATUS_UNKNOWN");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("INVALID");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
