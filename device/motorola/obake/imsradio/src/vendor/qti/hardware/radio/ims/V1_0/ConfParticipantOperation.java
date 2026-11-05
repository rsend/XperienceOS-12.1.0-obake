package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ConfParticipantOperation {
    public static final int ADD = 0;
    public static final int INVALID = 2;
    public static final int REMOVE = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "ADD";
        }
        if (o == 1) {
            return "REMOVE";
        }
        if (o == 2) {
            return "INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("ADD");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("REMOVE");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("INVALID");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
