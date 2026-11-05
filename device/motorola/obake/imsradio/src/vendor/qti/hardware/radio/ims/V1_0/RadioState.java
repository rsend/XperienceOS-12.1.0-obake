package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class RadioState {
    public static final int RADIO_STATE_INVALID = 3;
    public static final int RADIO_STATE_OFF = 0;
    public static final int RADIO_STATE_ON = 2;
    public static final int RADIO_STATE_UNAVAILABLE = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "RADIO_STATE_OFF";
        }
        if (o == 1) {
            return "RADIO_STATE_UNAVAILABLE";
        }
        if (o == 2) {
            return "RADIO_STATE_ON";
        }
        if (o == 3) {
            return "RADIO_STATE_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("RADIO_STATE_OFF");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("RADIO_STATE_UNAVAILABLE");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("RADIO_STATE_ON");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("RADIO_STATE_INVALID");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
