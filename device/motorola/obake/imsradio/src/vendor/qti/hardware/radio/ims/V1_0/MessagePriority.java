package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class MessagePriority {
    public static final int MWI_MSG_PRIORITY_INVALID = 4;
    public static final int MWI_MSG_PRIORITY_LOW = 1;
    public static final int MWI_MSG_PRIORITY_NORMAL = 2;
    public static final int MWI_MSG_PRIORITY_UNKNOWN = 0;
    public static final int MWI_MSG_PRIORITY_URGENT = 3;

    public static final String toString(int o) {
        if (o == 0) {
            return "MWI_MSG_PRIORITY_UNKNOWN";
        }
        if (o == 1) {
            return "MWI_MSG_PRIORITY_LOW";
        }
        if (o == 2) {
            return "MWI_MSG_PRIORITY_NORMAL";
        }
        if (o == 3) {
            return "MWI_MSG_PRIORITY_URGENT";
        }
        if (o == 4) {
            return "MWI_MSG_PRIORITY_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("MWI_MSG_PRIORITY_UNKNOWN");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("MWI_MSG_PRIORITY_LOW");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("MWI_MSG_PRIORITY_NORMAL");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("MWI_MSG_PRIORITY_URGENT");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("MWI_MSG_PRIORITY_INVALID");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
