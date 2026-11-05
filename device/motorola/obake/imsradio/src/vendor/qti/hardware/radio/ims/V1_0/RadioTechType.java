package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class RadioTechType {
    public static final int RADIO_TECH_1xRTT = 7;
    public static final int RADIO_TECH_ANY = 0;
    public static final int RADIO_TECH_EDGE = 3;
    public static final int RADIO_TECH_EHRPD = 14;
    public static final int RADIO_TECH_EVDO_0 = 8;
    public static final int RADIO_TECH_EVDO_A = 9;
    public static final int RADIO_TECH_EVDO_B = 13;
    public static final int RADIO_TECH_GPRS = 2;
    public static final int RADIO_TECH_GSM = 17;
    public static final int RADIO_TECH_HSDPA = 10;
    public static final int RADIO_TECH_HSPA = 12;
    public static final int RADIO_TECH_HSPAP = 16;
    public static final int RADIO_TECH_HSUPA = 11;
    public static final int RADIO_TECH_INVALID = 21;
    public static final int RADIO_TECH_IS95A = 5;
    public static final int RADIO_TECH_IS95B = 6;
    public static final int RADIO_TECH_IWLAN = 20;
    public static final int RADIO_TECH_LTE = 15;
    public static final int RADIO_TECH_TD_SCDMA = 18;
    public static final int RADIO_TECH_UMTS = 4;
    public static final int RADIO_TECH_UNKNOWN = 1;
    public static final int RADIO_TECH_WIFI = 19;

    public static final String toString(int o) {
        if (o == 0) {
            return "RADIO_TECH_ANY";
        }
        if (o == 1) {
            return "RADIO_TECH_UNKNOWN";
        }
        if (o == 2) {
            return "RADIO_TECH_GPRS";
        }
        if (o == 3) {
            return "RADIO_TECH_EDGE";
        }
        if (o == 4) {
            return "RADIO_TECH_UMTS";
        }
        if (o == 5) {
            return "RADIO_TECH_IS95A";
        }
        if (o == 6) {
            return "RADIO_TECH_IS95B";
        }
        if (o == 7) {
            return "RADIO_TECH_1xRTT";
        }
        if (o == 8) {
            return "RADIO_TECH_EVDO_0";
        }
        if (o == 9) {
            return "RADIO_TECH_EVDO_A";
        }
        if (o == 10) {
            return "RADIO_TECH_HSDPA";
        }
        if (o == 11) {
            return "RADIO_TECH_HSUPA";
        }
        if (o == 12) {
            return "RADIO_TECH_HSPA";
        }
        if (o == 13) {
            return "RADIO_TECH_EVDO_B";
        }
        if (o == 14) {
            return "RADIO_TECH_EHRPD";
        }
        if (o == 15) {
            return "RADIO_TECH_LTE";
        }
        if (o == 16) {
            return "RADIO_TECH_HSPAP";
        }
        if (o == 17) {
            return "RADIO_TECH_GSM";
        }
        if (o == 18) {
            return "RADIO_TECH_TD_SCDMA";
        }
        if (o == 19) {
            return "RADIO_TECH_WIFI";
        }
        if (o == 20) {
            return "RADIO_TECH_IWLAN";
        }
        if (o == 21) {
            return "RADIO_TECH_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("RADIO_TECH_ANY");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("RADIO_TECH_UNKNOWN");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("RADIO_TECH_GPRS");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("RADIO_TECH_EDGE");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("RADIO_TECH_UMTS");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("RADIO_TECH_IS95A");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("RADIO_TECH_IS95B");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("RADIO_TECH_1xRTT");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("RADIO_TECH_EVDO_0");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("RADIO_TECH_EVDO_A");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("RADIO_TECH_HSDPA");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("RADIO_TECH_HSUPA");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("RADIO_TECH_HSPA");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("RADIO_TECH_EVDO_B");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("RADIO_TECH_EHRPD");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("RADIO_TECH_LTE");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("RADIO_TECH_HSPAP");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("RADIO_TECH_GSM");
            flipped |= 17;
        }
        if ((o & 18) == 18) {
            list.add("RADIO_TECH_TD_SCDMA");
            flipped |= 18;
        }
        if ((o & 19) == 19) {
            list.add("RADIO_TECH_WIFI");
            flipped |= 19;
        }
        if ((o & 20) == 20) {
            list.add("RADIO_TECH_IWLAN");
            flipped |= 20;
        }
        if ((o & 21) == 21) {
            list.add("RADIO_TECH_INVALID");
            flipped |= 21;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
