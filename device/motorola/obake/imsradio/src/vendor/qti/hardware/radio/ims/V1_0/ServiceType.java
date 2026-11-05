package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ServiceType {
    public static final int INVALID = 3;
    public static final int SMS = 0;
    public static final int VOIP = 1;
    public static final int VT = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "SMS";
        }
        if (o == 1) {
            return "VOIP";
        }
        if (o == 2) {
            return "VT";
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
            list.add("SMS");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("VOIP");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("VT");
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
