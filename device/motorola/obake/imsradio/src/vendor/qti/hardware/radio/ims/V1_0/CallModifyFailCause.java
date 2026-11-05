package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class CallModifyFailCause {
    public static final int E_CANCELLED = 4;
    public static final int E_GENERIC_FAILURE = 2;
    public static final int E_HOLD_RESUME_CANCELED = 11;
    public static final int E_HOLD_RESUME_FAILED = 10;
    public static final int E_IMS_DEREGISTERED = 8;
    public static final int E_INVALID = 13;
    public static final int E_INVALID_PARAMETER = 6;
    public static final int E_NETWORK_NOT_SUPPORTED = 9;
    public static final int E_RADIO_NOT_AVAILABLE = 1;
    public static final int E_REINVITE_COLLISION = 12;
    public static final int E_REJECTED_BY_REMOTE = 7;
    public static final int E_REQUEST_NOT_SUPPORTED = 3;
    public static final int E_SUCCESS = 0;
    public static final int E_UNUSED = 5;

    public static final String toString(int o) {
        if (o == 0) {
            return "E_SUCCESS";
        }
        if (o == 1) {
            return "E_RADIO_NOT_AVAILABLE";
        }
        if (o == 2) {
            return "E_GENERIC_FAILURE";
        }
        if (o == 3) {
            return "E_REQUEST_NOT_SUPPORTED";
        }
        if (o == 4) {
            return "E_CANCELLED";
        }
        if (o == 5) {
            return "E_UNUSED";
        }
        if (o == 6) {
            return "E_INVALID_PARAMETER";
        }
        if (o == 7) {
            return "E_REJECTED_BY_REMOTE";
        }
        if (o == 8) {
            return "E_IMS_DEREGISTERED";
        }
        if (o == 9) {
            return "E_NETWORK_NOT_SUPPORTED";
        }
        if (o == 10) {
            return "E_HOLD_RESUME_FAILED";
        }
        if (o == 11) {
            return "E_HOLD_RESUME_CANCELED";
        }
        if (o == 12) {
            return "E_REINVITE_COLLISION";
        }
        if (o == 13) {
            return "E_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("E_SUCCESS");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("E_RADIO_NOT_AVAILABLE");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("E_GENERIC_FAILURE");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("E_REQUEST_NOT_SUPPORTED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("E_CANCELLED");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("E_UNUSED");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("E_INVALID_PARAMETER");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("E_REJECTED_BY_REMOTE");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("E_IMS_DEREGISTERED");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("E_NETWORK_NOT_SUPPORTED");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("E_HOLD_RESUME_FAILED");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("E_HOLD_RESUME_CANCELED");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("E_REINVITE_COLLISION");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("E_INVALID");
            flipped |= 13;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
