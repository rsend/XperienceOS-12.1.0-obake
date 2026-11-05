package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class HandoverType {
    public static final int CANCEL = 3;
    public static final int COMPLETE_FAIL = 2;
    public static final int COMPLETE_SUCCESS = 1;
    public static final int INVALID = 6;
    public static final int NOT_TRIGGERED = 4;
    public static final int NOT_TRIGGERED_MOBILE_DATA_OFF = 5;
    public static final int START = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "START";
        }
        if (o == 1) {
            return "COMPLETE_SUCCESS";
        }
        if (o == 2) {
            return "COMPLETE_FAIL";
        }
        if (o == 3) {
            return "CANCEL";
        }
        if (o == 4) {
            return "NOT_TRIGGERED";
        }
        if (o == 5) {
            return "NOT_TRIGGERED_MOBILE_DATA_OFF";
        }
        if (o == 6) {
            return "INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("START");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("COMPLETE_SUCCESS");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("COMPLETE_FAIL");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CANCEL");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("NOT_TRIGGERED");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("NOT_TRIGGERED_MOBILE_DATA_OFF");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("INVALID");
            flipped |= 6;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
