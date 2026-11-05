package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class TtyMode {
    public static final int TTY_MODE_FULL = 1;
    public static final int TTY_MODE_HCO = 2;
    public static final int TTY_MODE_INVALID = 4;
    public static final int TTY_MODE_OFF = 0;
    public static final int TTY_MODE_VCO = 3;

    public static final String toString(int o) {
        if (o == 0) {
            return "TTY_MODE_OFF";
        }
        if (o == 1) {
            return "TTY_MODE_FULL";
        }
        if (o == 2) {
            return "TTY_MODE_HCO";
        }
        if (o == 3) {
            return "TTY_MODE_VCO";
        }
        if (o == 4) {
            return "TTY_MODE_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("TTY_MODE_OFF");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("TTY_MODE_FULL");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("TTY_MODE_HCO");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("TTY_MODE_VCO");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("TTY_MODE_INVALID");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
