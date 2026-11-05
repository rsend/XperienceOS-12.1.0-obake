package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class MessageType {
    public static final int MWI_MSG_FAX = 3;
    public static final int MWI_MSG_INVALID = 7;
    public static final int MWI_MSG_MULTIMEDIA = 5;
    public static final int MWI_MSG_NONE = 0;
    public static final int MWI_MSG_PAGER = 4;
    public static final int MWI_MSG_TEXT = 6;
    public static final int MWI_MSG_VIDEO = 2;
    public static final int MWI_MSG_VOICE = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "MWI_MSG_NONE";
        }
        if (o == 1) {
            return "MWI_MSG_VOICE";
        }
        if (o == 2) {
            return "MWI_MSG_VIDEO";
        }
        if (o == 3) {
            return "MWI_MSG_FAX";
        }
        if (o == 4) {
            return "MWI_MSG_PAGER";
        }
        if (o == 5) {
            return "MWI_MSG_MULTIMEDIA";
        }
        if (o == 6) {
            return "MWI_MSG_TEXT";
        }
        if (o == 7) {
            return "MWI_MSG_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("MWI_MSG_NONE");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("MWI_MSG_VOICE");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("MWI_MSG_VIDEO");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("MWI_MSG_FAX");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("MWI_MSG_PAGER");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("MWI_MSG_MULTIMEDIA");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("MWI_MSG_TEXT");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("MWI_MSG_INVALID");
            flipped |= 7;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
