package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ConferenceCallState {
    public static final int BACKGROUND = 2;
    public static final int FOREGROUND = 1;
    public static final int INVALID = 3;
    public static final int RINGING = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "RINGING";
        }
        if (o == 1) {
            return "FOREGROUND";
        }
        if (o == 2) {
            return "BACKGROUND";
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
            list.add("RINGING");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("FOREGROUND");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("BACKGROUND");
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
