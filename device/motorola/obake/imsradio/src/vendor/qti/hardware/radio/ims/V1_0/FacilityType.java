package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class FacilityType {
    public static final int FACILITY_BAIC = 5;
    public static final int FACILITY_BAICa = 11;
    public static final int FACILITY_BAICr = 6;
    public static final int FACILITY_BAOC = 2;
    public static final int FACILITY_BAOIC = 3;
    public static final int FACILITY_BAOICxH = 4;
    public static final int FACILITY_BA_ALL = 7;
    public static final int FACILITY_BA_MO = 8;
    public static final int FACILITY_BA_MT = 9;
    public static final int FACILITY_BS_MT = 10;
    public static final int FACILITY_CLIP = 0;
    public static final int FACILITY_COLP = 1;
    public static final int FACILITY_INVALID = 12;

    public static final String toString(int o) {
        if (o == 0) {
            return "FACILITY_CLIP";
        }
        if (o == 1) {
            return "FACILITY_COLP";
        }
        if (o == 2) {
            return "FACILITY_BAOC";
        }
        if (o == 3) {
            return "FACILITY_BAOIC";
        }
        if (o == 4) {
            return "FACILITY_BAOICxH";
        }
        if (o == 5) {
            return "FACILITY_BAIC";
        }
        if (o == 6) {
            return "FACILITY_BAICr";
        }
        if (o == 7) {
            return "FACILITY_BA_ALL";
        }
        if (o == 8) {
            return "FACILITY_BA_MO";
        }
        if (o == 9) {
            return "FACILITY_BA_MT";
        }
        if (o == 10) {
            return "FACILITY_BS_MT";
        }
        if (o == 11) {
            return "FACILITY_BAICa";
        }
        if (o == 12) {
            return "FACILITY_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("FACILITY_CLIP");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("FACILITY_COLP");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("FACILITY_BAOC");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("FACILITY_BAOIC");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("FACILITY_BAOICxH");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("FACILITY_BAIC");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("FACILITY_BAICr");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("FACILITY_BA_ALL");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("FACILITY_BA_MO");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("FACILITY_BA_MT");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("FACILITY_BS_MT");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("FACILITY_BAICa");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("FACILITY_INVALID");
            flipped |= 12;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
