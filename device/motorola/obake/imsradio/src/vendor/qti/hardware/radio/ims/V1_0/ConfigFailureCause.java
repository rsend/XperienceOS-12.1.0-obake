package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ConfigFailureCause {
    public static final int CONFIG_FAILURE_INVALID = 6;
    public static final int CONFIG_FILE_NOT_AVAILABLE = 2;
    public static final int CONFIG_IMS_NOT_READY = 1;
    public static final int CONFIG_NO_ERR = 0;
    public static final int CONFIG_OTHER_INTERNAL_ERR = 5;
    public static final int CONFIG_READ_FAILED = 3;
    public static final int CONFIG_WRITE_FAILED = 4;

    public static final String toString(int o) {
        if (o == 0) {
            return "CONFIG_NO_ERR";
        }
        if (o == 1) {
            return "CONFIG_IMS_NOT_READY";
        }
        if (o == 2) {
            return "CONFIG_FILE_NOT_AVAILABLE";
        }
        if (o == 3) {
            return "CONFIG_READ_FAILED";
        }
        if (o == 4) {
            return "CONFIG_WRITE_FAILED";
        }
        if (o == 5) {
            return "CONFIG_OTHER_INTERNAL_ERR";
        }
        if (o == 6) {
            return "CONFIG_FAILURE_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("CONFIG_NO_ERR");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("CONFIG_IMS_NOT_READY");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("CONFIG_FILE_NOT_AVAILABLE");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CONFIG_READ_FAILED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("CONFIG_WRITE_FAILED");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("CONFIG_OTHER_INTERNAL_ERR");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("CONFIG_FAILURE_INVALID");
            flipped |= 6;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
