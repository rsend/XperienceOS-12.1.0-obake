package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ServiceClassStatus {
    public static final int DISABLED = 0;
    public static final int ENABLED = 1;
    public static final int INVALID = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "DISABLED";
        }
        if (o == 1) {
            return "ENABLED";
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
            list.add("DISABLED");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("ENABLED");
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
