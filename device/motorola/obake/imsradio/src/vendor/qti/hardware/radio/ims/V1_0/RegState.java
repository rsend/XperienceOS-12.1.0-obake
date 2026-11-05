package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class RegState {
    public static final int INVALID = 3;
    public static final int NOT_REGISTERED = 1;
    public static final int REGISTERED = 0;
    public static final int REGISTERING = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "REGISTERED";
        }
        if (o == 1) {
            return "NOT_REGISTERED";
        }
        if (o == 2) {
            return "REGISTERING";
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
            list.add("REGISTERED");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("NOT_REGISTERED");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("REGISTERING");
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
