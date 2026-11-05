package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ToneOperation {
    public static final int INVALID = 2;
    public static final int START = 1;
    public static final int STOP = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "STOP";
        }
        if (o == 1) {
            return "START";
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
            list.add("STOP");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("START");
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
