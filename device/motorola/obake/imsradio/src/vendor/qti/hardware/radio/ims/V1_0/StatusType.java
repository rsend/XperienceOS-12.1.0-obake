package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class StatusType {
    public static final int STATUS_DISABLED = 0;
    public static final int STATUS_ENABLED = 2;
    public static final int STATUS_INVALID = 4;
    public static final int STATUS_NOT_SUPPORTED = 3;
    public static final int STATUS_PARTIALLY_ENABLED = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "STATUS_DISABLED";
        }
        if (o == 1) {
            return "STATUS_PARTIALLY_ENABLED";
        }
        if (o == 2) {
            return "STATUS_ENABLED";
        }
        if (o == 3) {
            return "STATUS_NOT_SUPPORTED";
        }
        if (o == 4) {
            return "STATUS_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("STATUS_DISABLED");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("STATUS_PARTIALLY_ENABLED");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("STATUS_ENABLED");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("STATUS_NOT_SUPPORTED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("STATUS_INVALID");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
