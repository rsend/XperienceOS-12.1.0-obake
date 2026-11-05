package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class CallState {
    public static final int CALL_ACTIVE = 0;
    public static final int CALL_ALERTING = 3;
    public static final int CALL_DIALING = 2;
    public static final int CALL_END = 6;
    public static final int CALL_HOLDING = 1;
    public static final int CALL_INCOMING = 4;
    public static final int CALL_STATE_INVALID = 7;
    public static final int CALL_WAITING = 5;

    public static final String toString(int o) {
        if (o == 0) {
            return "CALL_ACTIVE";
        }
        if (o == 1) {
            return "CALL_HOLDING";
        }
        if (o == 2) {
            return "CALL_DIALING";
        }
        if (o == 3) {
            return "CALL_ALERTING";
        }
        if (o == 4) {
            return "CALL_INCOMING";
        }
        if (o == 5) {
            return "CALL_WAITING";
        }
        if (o == 6) {
            return "CALL_END";
        }
        if (o == 7) {
            return "CALL_STATE_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("CALL_ACTIVE");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("CALL_HOLDING");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("CALL_DIALING");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CALL_ALERTING");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("CALL_INCOMING");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("CALL_WAITING");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("CALL_END");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("CALL_STATE_INVALID");
            flipped |= 7;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
