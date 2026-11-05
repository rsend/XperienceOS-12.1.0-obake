package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class CallFailCause {
    public static final int CALL_FAIL_ACCESS_CLASS_BLOCKED = 513;
    public static final int CALL_FAIL_ANSWERED_ELSEWHERE = 510;
    public static final int CALL_FAIL_BLACKLISTED_CALL_ID = 504;
    public static final int CALL_FAIL_BUSY = 2;
    public static final int CALL_FAIL_CALL_BARRED = 6;
    public static final int CALL_FAIL_CAUSE_CALL_PULLED = 512;
    public static final int CALL_FAIL_CONGESTION = 4;
    public static final int CALL_FAIL_CS_RETRY_REQUIRED = 505;
    public static final int CALL_FAIL_EMERGENCY_PERM_FAILURE = 10;
    public static final int CALL_FAIL_EMERGENCY_TEMP_FAILURE = 9;
    public static final int CALL_FAIL_ERROR_UNSPECIFIED = 543;
    public static final int CALL_FAIL_FDN_BLOCKED = 7;
    public static final int CALL_FAIL_FEATURE_UNAVAILABLE = 507;
    public static final int CALL_FAIL_HOLD_REINVITE_COLLISION = 542;
    public static final int CALL_FAIL_HOLD_RESUME_CANCELED = 541;
    public static final int CALL_FAIL_HOLD_RESUME_FAILED = 540;
    public static final int CALL_FAIL_HO_NOT_FEASIBLE = 11;
    public static final int CALL_FAIL_IMEI_NOT_ACCEPTED = 8;
    public static final int CALL_FAIL_INCOMPATIBILITY_DESTINATION = 5;
    public static final int CALL_FAIL_INVALID = 544;
    public static final int CALL_FAIL_LOW_BATTERY = 503;
    public static final int CALL_FAIL_MEDIA_INIT_FAILED = 536;
    public static final int CALL_FAIL_MEDIA_NOT_ACCEPTABLE = 538;
    public static final int CALL_FAIL_MEDIA_NO_DATA = 537;
    public static final int CALL_FAIL_MEDIA_UNSPECIFIED_ERROR = 539;
    public static final int CALL_FAIL_MISC = 509;
    public static final int CALL_FAIL_NETWORK_UNAVAILABLE = 506;
    public static final int CALL_FAIL_NORMAL = 1;
    public static final int CALL_FAIL_NORMAL_UNSPECIFIED = 3;
    public static final int CALL_FAIL_PULL_OUT_OF_SYNC = 511;
    public static final int CALL_FAIL_SIP_BAD_ADDRESS = 521;
    public static final int CALL_FAIL_SIP_BAD_REQUEST = 515;
    public static final int CALL_FAIL_SIP_BUSY = 522;
    public static final int CALL_FAIL_SIP_ERROR = 508;
    public static final int CALL_FAIL_SIP_FORBIDDEN = 516;
    public static final int CALL_FAIL_SIP_GLOBAL_ERROR = 535;
    public static final int CALL_FAIL_SIP_NOT_ACCEPTABLE = 524;
    public static final int CALL_FAIL_SIP_NOT_FOUND = 517;
    public static final int CALL_FAIL_SIP_NOT_REACHABLE = 525;
    public static final int CALL_FAIL_SIP_NOT_SUPPORTED = 518;
    public static final int CALL_FAIL_SIP_REDIRECTED = 514;
    public static final int CALL_FAIL_SIP_REQUEST_CANCELLED = 523;
    public static final int CALL_FAIL_SIP_REQUEST_TIMEOUT = 519;
    public static final int CALL_FAIL_SIP_SERVER_BAD_GATEWAY = 528;
    public static final int CALL_FAIL_SIP_SERVER_INTERNAL_ERROR = 526;
    public static final int CALL_FAIL_SIP_SERVER_MESSAGE_TOOLARGE = 532;
    public static final int CALL_FAIL_SIP_SERVER_NOT_IMPLEMENTED = 527;
    public static final int CALL_FAIL_SIP_SERVER_PRECONDITION_FAILURE = 533;
    public static final int CALL_FAIL_SIP_SERVER_TIMEOUT = 530;
    public static final int CALL_FAIL_SIP_SERVER_VERSION_UNSUPPORTED = 531;
    public static final int CALL_FAIL_SIP_SERVICE_UNAVAILABLE = 529;
    public static final int CALL_FAIL_SIP_TEMPORARILY_UNAVAILABLE = 520;
    public static final int CALL_FAIL_SIP_USER_REJECTED = 534;
    public static final int CALL_FAIL_UNOBTAINABLE_NUMBER = 0;
    public static final int CALL_FAIL_USER_BUSY = 501;
    public static final int CALL_FAIL_USER_REJECT = 502;

    public static final String toString(int o) {
        if (o == 0) {
            return "CALL_FAIL_UNOBTAINABLE_NUMBER";
        }
        if (o == 1) {
            return "CALL_FAIL_NORMAL";
        }
        if (o == 2) {
            return "CALL_FAIL_BUSY";
        }
        if (o == 3) {
            return "CALL_FAIL_NORMAL_UNSPECIFIED";
        }
        if (o == 4) {
            return "CALL_FAIL_CONGESTION";
        }
        if (o == 5) {
            return "CALL_FAIL_INCOMPATIBILITY_DESTINATION";
        }
        if (o == 6) {
            return "CALL_FAIL_CALL_BARRED";
        }
        if (o == 7) {
            return "CALL_FAIL_FDN_BLOCKED";
        }
        if (o == 8) {
            return "CALL_FAIL_IMEI_NOT_ACCEPTED";
        }
        if (o == 9) {
            return "CALL_FAIL_EMERGENCY_TEMP_FAILURE";
        }
        if (o == 10) {
            return "CALL_FAIL_EMERGENCY_PERM_FAILURE";
        }
        if (o == 11) {
            return "CALL_FAIL_HO_NOT_FEASIBLE";
        }
        if (o == 501) {
            return "CALL_FAIL_USER_BUSY";
        }
        if (o == 502) {
            return "CALL_FAIL_USER_REJECT";
        }
        if (o == 503) {
            return "CALL_FAIL_LOW_BATTERY";
        }
        if (o == 504) {
            return "CALL_FAIL_BLACKLISTED_CALL_ID";
        }
        if (o == 505) {
            return "CALL_FAIL_CS_RETRY_REQUIRED";
        }
        if (o == 506) {
            return "CALL_FAIL_NETWORK_UNAVAILABLE";
        }
        if (o == 507) {
            return "CALL_FAIL_FEATURE_UNAVAILABLE";
        }
        if (o == 508) {
            return "CALL_FAIL_SIP_ERROR";
        }
        if (o == 509) {
            return "CALL_FAIL_MISC";
        }
        if (o == 510) {
            return "CALL_FAIL_ANSWERED_ELSEWHERE";
        }
        if (o == 511) {
            return "CALL_FAIL_PULL_OUT_OF_SYNC";
        }
        if (o == 512) {
            return "CALL_FAIL_CAUSE_CALL_PULLED";
        }
        if (o == 513) {
            return "CALL_FAIL_ACCESS_CLASS_BLOCKED";
        }
        if (o == 514) {
            return "CALL_FAIL_SIP_REDIRECTED";
        }
        if (o == 515) {
            return "CALL_FAIL_SIP_BAD_REQUEST";
        }
        if (o == 516) {
            return "CALL_FAIL_SIP_FORBIDDEN";
        }
        if (o == 517) {
            return "CALL_FAIL_SIP_NOT_FOUND";
        }
        if (o == 518) {
            return "CALL_FAIL_SIP_NOT_SUPPORTED";
        }
        if (o == 519) {
            return "CALL_FAIL_SIP_REQUEST_TIMEOUT";
        }
        if (o == 520) {
            return "CALL_FAIL_SIP_TEMPORARILY_UNAVAILABLE";
        }
        if (o == 521) {
            return "CALL_FAIL_SIP_BAD_ADDRESS";
        }
        if (o == 522) {
            return "CALL_FAIL_SIP_BUSY";
        }
        if (o == 523) {
            return "CALL_FAIL_SIP_REQUEST_CANCELLED";
        }
        if (o == 524) {
            return "CALL_FAIL_SIP_NOT_ACCEPTABLE";
        }
        if (o == 525) {
            return "CALL_FAIL_SIP_NOT_REACHABLE";
        }
        if (o == 526) {
            return "CALL_FAIL_SIP_SERVER_INTERNAL_ERROR";
        }
        if (o == 527) {
            return "CALL_FAIL_SIP_SERVER_NOT_IMPLEMENTED";
        }
        if (o == 528) {
            return "CALL_FAIL_SIP_SERVER_BAD_GATEWAY";
        }
        if (o == 529) {
            return "CALL_FAIL_SIP_SERVICE_UNAVAILABLE";
        }
        if (o == 530) {
            return "CALL_FAIL_SIP_SERVER_TIMEOUT";
        }
        if (o == 531) {
            return "CALL_FAIL_SIP_SERVER_VERSION_UNSUPPORTED";
        }
        if (o == 532) {
            return "CALL_FAIL_SIP_SERVER_MESSAGE_TOOLARGE";
        }
        if (o == 533) {
            return "CALL_FAIL_SIP_SERVER_PRECONDITION_FAILURE";
        }
        if (o == 534) {
            return "CALL_FAIL_SIP_USER_REJECTED";
        }
        if (o == 535) {
            return "CALL_FAIL_SIP_GLOBAL_ERROR";
        }
        if (o == 536) {
            return "CALL_FAIL_MEDIA_INIT_FAILED";
        }
        if (o == 537) {
            return "CALL_FAIL_MEDIA_NO_DATA";
        }
        if (o == 538) {
            return "CALL_FAIL_MEDIA_NOT_ACCEPTABLE";
        }
        if (o == 539) {
            return "CALL_FAIL_MEDIA_UNSPECIFIED_ERROR";
        }
        if (o == 540) {
            return "CALL_FAIL_HOLD_RESUME_FAILED";
        }
        if (o == 541) {
            return "CALL_FAIL_HOLD_RESUME_CANCELED";
        }
        if (o == 542) {
            return "CALL_FAIL_HOLD_REINVITE_COLLISION";
        }
        if (o == 543) {
            return "CALL_FAIL_ERROR_UNSPECIFIED";
        }
        if (o == 544) {
            return "CALL_FAIL_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("CALL_FAIL_UNOBTAINABLE_NUMBER");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("CALL_FAIL_NORMAL");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("CALL_FAIL_BUSY");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CALL_FAIL_NORMAL_UNSPECIFIED");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("CALL_FAIL_CONGESTION");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("CALL_FAIL_INCOMPATIBILITY_DESTINATION");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("CALL_FAIL_CALL_BARRED");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("CALL_FAIL_FDN_BLOCKED");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("CALL_FAIL_IMEI_NOT_ACCEPTED");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("CALL_FAIL_EMERGENCY_TEMP_FAILURE");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("CALL_FAIL_EMERGENCY_PERM_FAILURE");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("CALL_FAIL_HO_NOT_FEASIBLE");
            flipped |= 11;
        }
        if ((o & 501) == 501) {
            list.add("CALL_FAIL_USER_BUSY");
            flipped |= 501;
        }
        if ((o & 502) == 502) {
            list.add("CALL_FAIL_USER_REJECT");
            flipped |= 502;
        }
        if ((o & 503) == 503) {
            list.add("CALL_FAIL_LOW_BATTERY");
            flipped |= 503;
        }
        if ((o & 504) == 504) {
            list.add("CALL_FAIL_BLACKLISTED_CALL_ID");
            flipped |= 504;
        }
        if ((o & 505) == 505) {
            list.add("CALL_FAIL_CS_RETRY_REQUIRED");
            flipped |= 505;
        }
        if ((o & CALL_FAIL_NETWORK_UNAVAILABLE) == 506) {
            list.add("CALL_FAIL_NETWORK_UNAVAILABLE");
            flipped |= CALL_FAIL_NETWORK_UNAVAILABLE;
        }
        if ((o & CALL_FAIL_FEATURE_UNAVAILABLE) == 507) {
            list.add("CALL_FAIL_FEATURE_UNAVAILABLE");
            flipped |= CALL_FAIL_FEATURE_UNAVAILABLE;
        }
        if ((o & CALL_FAIL_SIP_ERROR) == 508) {
            list.add("CALL_FAIL_SIP_ERROR");
            flipped |= CALL_FAIL_SIP_ERROR;
        }
        if ((o & CALL_FAIL_MISC) == 509) {
            list.add("CALL_FAIL_MISC");
            flipped |= CALL_FAIL_MISC;
        }
        if ((o & CALL_FAIL_ANSWERED_ELSEWHERE) == 510) {
            list.add("CALL_FAIL_ANSWERED_ELSEWHERE");
            flipped |= CALL_FAIL_ANSWERED_ELSEWHERE;
        }
        if ((o & CALL_FAIL_PULL_OUT_OF_SYNC) == 511) {
            list.add("CALL_FAIL_PULL_OUT_OF_SYNC");
            flipped |= CALL_FAIL_PULL_OUT_OF_SYNC;
        }
        if ((o & 512) == 512) {
            list.add("CALL_FAIL_CAUSE_CALL_PULLED");
            flipped |= 512;
        }
        if ((o & 513) == 513) {
            list.add("CALL_FAIL_ACCESS_CLASS_BLOCKED");
            flipped |= 513;
        }
        if ((o & CALL_FAIL_SIP_REDIRECTED) == 514) {
            list.add("CALL_FAIL_SIP_REDIRECTED");
            flipped |= CALL_FAIL_SIP_REDIRECTED;
        }
        if ((o & CALL_FAIL_SIP_BAD_REQUEST) == 515) {
            list.add("CALL_FAIL_SIP_BAD_REQUEST");
            flipped |= CALL_FAIL_SIP_BAD_REQUEST;
        }
        if ((o & CALL_FAIL_SIP_FORBIDDEN) == 516) {
            list.add("CALL_FAIL_SIP_FORBIDDEN");
            flipped |= CALL_FAIL_SIP_FORBIDDEN;
        }
        if ((o & CALL_FAIL_SIP_NOT_FOUND) == 517) {
            list.add("CALL_FAIL_SIP_NOT_FOUND");
            flipped |= CALL_FAIL_SIP_NOT_FOUND;
        }
        if ((o & CALL_FAIL_SIP_NOT_SUPPORTED) == 518) {
            list.add("CALL_FAIL_SIP_NOT_SUPPORTED");
            flipped |= CALL_FAIL_SIP_NOT_SUPPORTED;
        }
        if ((o & CALL_FAIL_SIP_REQUEST_TIMEOUT) == 519) {
            list.add("CALL_FAIL_SIP_REQUEST_TIMEOUT");
            flipped |= CALL_FAIL_SIP_REQUEST_TIMEOUT;
        }
        if ((o & CALL_FAIL_SIP_TEMPORARILY_UNAVAILABLE) == 520) {
            list.add("CALL_FAIL_SIP_TEMPORARILY_UNAVAILABLE");
            flipped |= CALL_FAIL_SIP_TEMPORARILY_UNAVAILABLE;
        }
        if ((o & CALL_FAIL_SIP_BAD_ADDRESS) == 521) {
            list.add("CALL_FAIL_SIP_BAD_ADDRESS");
            flipped |= CALL_FAIL_SIP_BAD_ADDRESS;
        }
        if ((o & CALL_FAIL_SIP_BUSY) == 522) {
            list.add("CALL_FAIL_SIP_BUSY");
            flipped |= CALL_FAIL_SIP_BUSY;
        }
        if ((o & CALL_FAIL_SIP_REQUEST_CANCELLED) == 523) {
            list.add("CALL_FAIL_SIP_REQUEST_CANCELLED");
            flipped |= CALL_FAIL_SIP_REQUEST_CANCELLED;
        }
        if ((o & CALL_FAIL_SIP_NOT_ACCEPTABLE) == 524) {
            list.add("CALL_FAIL_SIP_NOT_ACCEPTABLE");
            flipped |= CALL_FAIL_SIP_NOT_ACCEPTABLE;
        }
        if ((o & CALL_FAIL_SIP_NOT_REACHABLE) == 525) {
            list.add("CALL_FAIL_SIP_NOT_REACHABLE");
            flipped |= CALL_FAIL_SIP_NOT_REACHABLE;
        }
        if ((o & CALL_FAIL_SIP_SERVER_INTERNAL_ERROR) == 526) {
            list.add("CALL_FAIL_SIP_SERVER_INTERNAL_ERROR");
            flipped |= CALL_FAIL_SIP_SERVER_INTERNAL_ERROR;
        }
        if ((o & CALL_FAIL_SIP_SERVER_NOT_IMPLEMENTED) == 527) {
            list.add("CALL_FAIL_SIP_SERVER_NOT_IMPLEMENTED");
            flipped |= CALL_FAIL_SIP_SERVER_NOT_IMPLEMENTED;
        }
        if ((o & CALL_FAIL_SIP_SERVER_BAD_GATEWAY) == 528) {
            list.add("CALL_FAIL_SIP_SERVER_BAD_GATEWAY");
            flipped |= CALL_FAIL_SIP_SERVER_BAD_GATEWAY;
        }
        if ((o & CALL_FAIL_SIP_SERVICE_UNAVAILABLE) == 529) {
            list.add("CALL_FAIL_SIP_SERVICE_UNAVAILABLE");
            flipped |= CALL_FAIL_SIP_SERVICE_UNAVAILABLE;
        }
        if ((o & CALL_FAIL_SIP_SERVER_TIMEOUT) == 530) {
            list.add("CALL_FAIL_SIP_SERVER_TIMEOUT");
            flipped |= CALL_FAIL_SIP_SERVER_TIMEOUT;
        }
        if ((o & CALL_FAIL_SIP_SERVER_VERSION_UNSUPPORTED) == 531) {
            list.add("CALL_FAIL_SIP_SERVER_VERSION_UNSUPPORTED");
            flipped |= CALL_FAIL_SIP_SERVER_VERSION_UNSUPPORTED;
        }
        if ((o & CALL_FAIL_SIP_SERVER_MESSAGE_TOOLARGE) == 532) {
            list.add("CALL_FAIL_SIP_SERVER_MESSAGE_TOOLARGE");
            flipped |= CALL_FAIL_SIP_SERVER_MESSAGE_TOOLARGE;
        }
        if ((o & CALL_FAIL_SIP_SERVER_PRECONDITION_FAILURE) == 533) {
            list.add("CALL_FAIL_SIP_SERVER_PRECONDITION_FAILURE");
            flipped |= CALL_FAIL_SIP_SERVER_PRECONDITION_FAILURE;
        }
        if ((o & CALL_FAIL_SIP_USER_REJECTED) == 534) {
            list.add("CALL_FAIL_SIP_USER_REJECTED");
            flipped |= CALL_FAIL_SIP_USER_REJECTED;
        }
        if ((o & CALL_FAIL_SIP_GLOBAL_ERROR) == 535) {
            list.add("CALL_FAIL_SIP_GLOBAL_ERROR");
            flipped |= CALL_FAIL_SIP_GLOBAL_ERROR;
        }
        if ((o & CALL_FAIL_MEDIA_INIT_FAILED) == 536) {
            list.add("CALL_FAIL_MEDIA_INIT_FAILED");
            flipped |= CALL_FAIL_MEDIA_INIT_FAILED;
        }
        if ((o & CALL_FAIL_MEDIA_NO_DATA) == 537) {
            list.add("CALL_FAIL_MEDIA_NO_DATA");
            flipped |= CALL_FAIL_MEDIA_NO_DATA;
        }
        if ((o & CALL_FAIL_MEDIA_NOT_ACCEPTABLE) == 538) {
            list.add("CALL_FAIL_MEDIA_NOT_ACCEPTABLE");
            flipped |= CALL_FAIL_MEDIA_NOT_ACCEPTABLE;
        }
        if ((o & CALL_FAIL_MEDIA_UNSPECIFIED_ERROR) == 539) {
            list.add("CALL_FAIL_MEDIA_UNSPECIFIED_ERROR");
            flipped |= CALL_FAIL_MEDIA_UNSPECIFIED_ERROR;
        }
        if ((o & CALL_FAIL_HOLD_RESUME_FAILED) == 540) {
            list.add("CALL_FAIL_HOLD_RESUME_FAILED");
            flipped |= CALL_FAIL_HOLD_RESUME_FAILED;
        }
        if ((o & CALL_FAIL_HOLD_RESUME_CANCELED) == 541) {
            list.add("CALL_FAIL_HOLD_RESUME_CANCELED");
            flipped |= CALL_FAIL_HOLD_RESUME_CANCELED;
        }
        if ((o & CALL_FAIL_HOLD_REINVITE_COLLISION) == 542) {
            list.add("CALL_FAIL_HOLD_REINVITE_COLLISION");
            flipped |= CALL_FAIL_HOLD_REINVITE_COLLISION;
        }
        if ((o & CALL_FAIL_ERROR_UNSPECIFIED) == 543) {
            list.add("CALL_FAIL_ERROR_UNSPECIFIED");
            flipped |= CALL_FAIL_ERROR_UNSPECIFIED;
        }
        if ((o & CALL_FAIL_INVALID) == 544) {
            list.add("CALL_FAIL_INVALID");
            flipped |= CALL_FAIL_INVALID;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
