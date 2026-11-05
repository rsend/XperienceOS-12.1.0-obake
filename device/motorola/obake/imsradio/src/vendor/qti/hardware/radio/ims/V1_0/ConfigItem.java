package vendor.qti.hardware.radio.ims.V1_0;

import java.util.ArrayList;
/* loaded from: classes.dex */
public final class ConfigItem {
    public static final int CONFIG_ITEM_AMR_BANDWIDTH_EFFICIENT_PT = 53;
    public static final int CONFIG_ITEM_AMR_DEFAULT_MODE = 56;
    public static final int CONFIG_ITEM_AMR_OCTET_ALIGNED_PT = 52;
    public static final int CONFIG_ITEM_AMR_WB_BANDWIDTH_EFFICIENT_PT = 51;
    public static final int CONFIG_ITEM_AMR_WB_OCTET_ALIGNED_PT = 50;
    public static final int CONFIG_ITEM_AVAILABILITY_CACHE_EXPIRATION = 19;
    public static final int CONFIG_ITEM_CANCELLATION_TIMER = 5;
    public static final int CONFIG_ITEM_CAPABILITIES_CACHE_EXPIRATION = 18;
    public static final int CONFIG_ITEM_CAPABILITIES_POLL_INTERVAL = 20;
    public static final int CONFIG_ITEM_CAPABILITY_DISCOVERY_ENABLED = 30;
    public static final int CONFIG_ITEM_CAPAB_POLL_LIST_SUB_EXP = 23;
    public static final int CONFIG_ITEM_DOMAIN_NAME = 13;
    public static final int CONFIG_ITEM_DTMF_NB_PT = 55;
    public static final int CONFIG_ITEM_DTMF_WB_PT = 54;
    public static final int CONFIG_ITEM_EAB_SETTING_ENABLED = 25;
    public static final int CONFIG_ITEM_EMERGENCY_CALL_TIMER = 31;
    public static final int CONFIG_ITEM_GZIP_FLAG = 24;
    public static final int CONFIG_ITEM_INVALID = 73;
    public static final int CONFIG_ITEM_KEEP_ALIVE_ENABLED = 35;
    public static final int CONFIG_ITEM_LBO_PCSCF_ADDRESS = 34;
    public static final int CONFIG_ITEM_LVC_SETTING_ENABLED = 12;
    public static final int CONFIG_ITEM_MAX_NUM_ENTRIES_IN_RCL = 22;
    public static final int CONFIG_ITEM_MIN_SESSION_EXPIRY = 4;
    public static final int CONFIG_ITEM_MOBILE_DATA_ENABLED = 26;
    public static final int CONFIG_ITEM_NONE = 0;
    public static final int CONFIG_ITEM_PUBLISH_TIMER = 16;
    public static final int CONFIG_ITEM_PUBLISH_TIMER_EXTENDED = 17;
    public static final int CONFIG_ITEM_REGISTRATION_RETRY_BASE_TIME_SEC = 36;
    public static final int CONFIG_ITEM_REGISTRATION_RETRY_MAX_TIME_SEC = 37;
    public static final int CONFIG_ITEM_RTT_SETTING_ENABLED = 70;
    public static final int CONFIG_ITEM_SILENT_REDIAL_ENABLE = 7;
    public static final int CONFIG_ITEM_SIP_ACK_RECEIPT_WAIT_TIME_MSEC = 46;
    public static final int CONFIG_ITEM_SIP_ACK_RETX_WAIT_TIME_MSEC = 47;
    public static final int CONFIG_ITEM_SIP_INVITE_REQ_RETX_INTERVAL_MSEC = 40;
    public static final int CONFIG_ITEM_SIP_INVITE_RSP_RETX_INTERVAL_MSEC = 45;
    public static final int CONFIG_ITEM_SIP_INVITE_RSP_RETX_WAIT_TIME_MSEC = 42;
    public static final int CONFIG_ITEM_SIP_INVITE_RSP_WAIT_TIME_MSEC = 41;
    public static final int CONFIG_ITEM_SIP_NON_INVITE_REQ_RETX_INTERVAL_MSEC = 43;
    public static final int CONFIG_ITEM_SIP_NON_INVITE_REQ_RETX_WAIT_TIME_MSEC = 48;
    public static final int CONFIG_ITEM_SIP_NON_INVITE_RSP_RETX_WAIT_TIME_MSEC = 49;
    public static final int CONFIG_ITEM_SIP_NON_INVITE_TXN_TIMEOUT_TIMER_MSEC = 44;
    public static final int CONFIG_ITEM_SIP_SESSION_TIMER = 3;
    public static final int CONFIG_ITEM_SIP_T1_TIMER = 8;
    public static final int CONFIG_ITEM_SIP_T2_TIMER = 9;
    public static final int CONFIG_ITEM_SIP_TF_TIMER = 10;
    public static final int CONFIG_ITEM_SMS_APP = 71;
    public static final int CONFIG_ITEM_SMS_FORMAT = 14;
    public static final int CONFIG_ITEM_SMS_OVER_IP = 15;
    public static final int CONFIG_ITEM_SMS_PSI = 57;
    public static final int CONFIG_ITEM_SOURCE_THROTTLE_PUBLISH = 21;
    public static final int CONFIG_ITEM_SPEECH_END_PORT = 39;
    public static final int CONFIG_ITEM_SPEECH_START_PORT = 38;
    public static final int CONFIG_ITEM_SSAC_HYSTERESIS_TIMER = 32;
    public static final int CONFIG_ITEM_THRESHOLD_1x = 62;
    public static final int CONFIG_ITEM_THRESHOLD_LTE1 = 59;
    public static final int CONFIG_ITEM_THRESHOLD_LTE2 = 60;
    public static final int CONFIG_ITEM_THRESHOLD_LTE3 = 61;
    public static final int CONFIG_ITEM_THRESHOLD_WIFI_A = 63;
    public static final int CONFIG_ITEM_THRESHOLD_WIFI_B = 64;
    public static final int CONFIG_ITEM_T_DELAY = 6;
    public static final int CONFIG_ITEM_T_EPDG_1x = 67;
    public static final int CONFIG_ITEM_T_EPDG_LTE = 65;
    public static final int CONFIG_ITEM_T_EPDG_WIFI = 66;
    public static final int CONFIG_ITEM_VCE_SETTING_ENABLED = 69;
    public static final int CONFIG_ITEM_VIDEO_QUALITY = 58;
    public static final int CONFIG_ITEM_VLT_SETTING_ENABLED = 11;
    public static final int CONFIG_ITEM_VOCODER_AMRMODESET = 1;
    public static final int CONFIG_ITEM_VOCODER_AMRWBMODESET = 2;
    public static final int CONFIG_ITEM_VOICE_OVER_WIFI_ENABLED = 27;
    public static final int CONFIG_ITEM_VOICE_OVER_WIFI_MODE = 29;
    public static final int CONFIG_ITEM_VOICE_OVER_WIFI_ROAMING = 28;
    public static final int CONFIG_ITEM_VOLTE_USER_OPT_IN_STATUS = 33;
    public static final int CONFIG_ITEM_VVM_APP = 72;
    public static final int CONFIG_ITEM_VWF_SETTING_ENABLED = 68;

    public static final String toString(int o) {
        if (o == 0) {
            return "CONFIG_ITEM_NONE";
        }
        if (o == 1) {
            return "CONFIG_ITEM_VOCODER_AMRMODESET";
        }
        if (o == 2) {
            return "CONFIG_ITEM_VOCODER_AMRWBMODESET";
        }
        if (o == 3) {
            return "CONFIG_ITEM_SIP_SESSION_TIMER";
        }
        if (o == 4) {
            return "CONFIG_ITEM_MIN_SESSION_EXPIRY";
        }
        if (o == 5) {
            return "CONFIG_ITEM_CANCELLATION_TIMER";
        }
        if (o == 6) {
            return "CONFIG_ITEM_T_DELAY";
        }
        if (o == 7) {
            return "CONFIG_ITEM_SILENT_REDIAL_ENABLE";
        }
        if (o == 8) {
            return "CONFIG_ITEM_SIP_T1_TIMER";
        }
        if (o == 9) {
            return "CONFIG_ITEM_SIP_T2_TIMER";
        }
        if (o == 10) {
            return "CONFIG_ITEM_SIP_TF_TIMER";
        }
        if (o == 11) {
            return "CONFIG_ITEM_VLT_SETTING_ENABLED";
        }
        if (o == 12) {
            return "CONFIG_ITEM_LVC_SETTING_ENABLED";
        }
        if (o == 13) {
            return "CONFIG_ITEM_DOMAIN_NAME";
        }
        if (o == 14) {
            return "CONFIG_ITEM_SMS_FORMAT";
        }
        if (o == 15) {
            return "CONFIG_ITEM_SMS_OVER_IP";
        }
        if (o == 16) {
            return "CONFIG_ITEM_PUBLISH_TIMER";
        }
        if (o == 17) {
            return "CONFIG_ITEM_PUBLISH_TIMER_EXTENDED";
        }
        if (o == 18) {
            return "CONFIG_ITEM_CAPABILITIES_CACHE_EXPIRATION";
        }
        if (o == 19) {
            return "CONFIG_ITEM_AVAILABILITY_CACHE_EXPIRATION";
        }
        if (o == 20) {
            return "CONFIG_ITEM_CAPABILITIES_POLL_INTERVAL";
        }
        if (o == 21) {
            return "CONFIG_ITEM_SOURCE_THROTTLE_PUBLISH";
        }
        if (o == 22) {
            return "CONFIG_ITEM_MAX_NUM_ENTRIES_IN_RCL";
        }
        if (o == 23) {
            return "CONFIG_ITEM_CAPAB_POLL_LIST_SUB_EXP";
        }
        if (o == 24) {
            return "CONFIG_ITEM_GZIP_FLAG";
        }
        if (o == 25) {
            return "CONFIG_ITEM_EAB_SETTING_ENABLED";
        }
        if (o == 26) {
            return "CONFIG_ITEM_MOBILE_DATA_ENABLED";
        }
        if (o == 27) {
            return "CONFIG_ITEM_VOICE_OVER_WIFI_ENABLED";
        }
        if (o == 28) {
            return "CONFIG_ITEM_VOICE_OVER_WIFI_ROAMING";
        }
        if (o == 29) {
            return "CONFIG_ITEM_VOICE_OVER_WIFI_MODE";
        }
        if (o == 30) {
            return "CONFIG_ITEM_CAPABILITY_DISCOVERY_ENABLED";
        }
        if (o == 31) {
            return "CONFIG_ITEM_EMERGENCY_CALL_TIMER";
        }
        if (o == 32) {
            return "CONFIG_ITEM_SSAC_HYSTERESIS_TIMER";
        }
        if (o == 33) {
            return "CONFIG_ITEM_VOLTE_USER_OPT_IN_STATUS";
        }
        if (o == 34) {
            return "CONFIG_ITEM_LBO_PCSCF_ADDRESS";
        }
        if (o == 35) {
            return "CONFIG_ITEM_KEEP_ALIVE_ENABLED";
        }
        if (o == 36) {
            return "CONFIG_ITEM_REGISTRATION_RETRY_BASE_TIME_SEC";
        }
        if (o == 37) {
            return "CONFIG_ITEM_REGISTRATION_RETRY_MAX_TIME_SEC";
        }
        if (o == 38) {
            return "CONFIG_ITEM_SPEECH_START_PORT";
        }
        if (o == 39) {
            return "CONFIG_ITEM_SPEECH_END_PORT";
        }
        if (o == 40) {
            return "CONFIG_ITEM_SIP_INVITE_REQ_RETX_INTERVAL_MSEC";
        }
        if (o == 41) {
            return "CONFIG_ITEM_SIP_INVITE_RSP_WAIT_TIME_MSEC";
        }
        if (o == 42) {
            return "CONFIG_ITEM_SIP_INVITE_RSP_RETX_WAIT_TIME_MSEC";
        }
        if (o == 43) {
            return "CONFIG_ITEM_SIP_NON_INVITE_REQ_RETX_INTERVAL_MSEC";
        }
        if (o == 44) {
            return "CONFIG_ITEM_SIP_NON_INVITE_TXN_TIMEOUT_TIMER_MSEC";
        }
        if (o == 45) {
            return "CONFIG_ITEM_SIP_INVITE_RSP_RETX_INTERVAL_MSEC";
        }
        if (o == 46) {
            return "CONFIG_ITEM_SIP_ACK_RECEIPT_WAIT_TIME_MSEC";
        }
        if (o == 47) {
            return "CONFIG_ITEM_SIP_ACK_RETX_WAIT_TIME_MSEC";
        }
        if (o == 48) {
            return "CONFIG_ITEM_SIP_NON_INVITE_REQ_RETX_WAIT_TIME_MSEC";
        }
        if (o == 49) {
            return "CONFIG_ITEM_SIP_NON_INVITE_RSP_RETX_WAIT_TIME_MSEC";
        }
        if (o == 50) {
            return "CONFIG_ITEM_AMR_WB_OCTET_ALIGNED_PT";
        }
        if (o == 51) {
            return "CONFIG_ITEM_AMR_WB_BANDWIDTH_EFFICIENT_PT";
        }
        if (o == 52) {
            return "CONFIG_ITEM_AMR_OCTET_ALIGNED_PT";
        }
        if (o == 53) {
            return "CONFIG_ITEM_AMR_BANDWIDTH_EFFICIENT_PT";
        }
        if (o == 54) {
            return "CONFIG_ITEM_DTMF_WB_PT";
        }
        if (o == 55) {
            return "CONFIG_ITEM_DTMF_NB_PT";
        }
        if (o == 56) {
            return "CONFIG_ITEM_AMR_DEFAULT_MODE";
        }
        if (o == 57) {
            return "CONFIG_ITEM_SMS_PSI";
        }
        if (o == 58) {
            return "CONFIG_ITEM_VIDEO_QUALITY";
        }
        if (o == 59) {
            return "CONFIG_ITEM_THRESHOLD_LTE1";
        }
        if (o == 60) {
            return "CONFIG_ITEM_THRESHOLD_LTE2";
        }
        if (o == 61) {
            return "CONFIG_ITEM_THRESHOLD_LTE3";
        }
        if (o == 62) {
            return "CONFIG_ITEM_THRESHOLD_1x";
        }
        if (o == 63) {
            return "CONFIG_ITEM_THRESHOLD_WIFI_A";
        }
        if (o == 64) {
            return "CONFIG_ITEM_THRESHOLD_WIFI_B";
        }
        if (o == 65) {
            return "CONFIG_ITEM_T_EPDG_LTE";
        }
        if (o == 66) {
            return "CONFIG_ITEM_T_EPDG_WIFI";
        }
        if (o == 67) {
            return "CONFIG_ITEM_T_EPDG_1x";
        }
        if (o == 68) {
            return "CONFIG_ITEM_VWF_SETTING_ENABLED";
        }
        if (o == 69) {
            return "CONFIG_ITEM_VCE_SETTING_ENABLED";
        }
        if (o == 70) {
            return "CONFIG_ITEM_RTT_SETTING_ENABLED";
        }
        if (o == 71) {
            return "CONFIG_ITEM_SMS_APP";
        }
        if (o == 72) {
            return "CONFIG_ITEM_VVM_APP";
        }
        if (o == 73) {
            return "CONFIG_ITEM_INVALID";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 0) == 0) {
            list.add("CONFIG_ITEM_NONE");
            flipped = 0;
        }
        if ((o & 1) == 1) {
            list.add("CONFIG_ITEM_VOCODER_AMRMODESET");
            flipped |= 1;
        }
        if ((o & 2) == 2) {
            list.add("CONFIG_ITEM_VOCODER_AMRWBMODESET");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CONFIG_ITEM_SIP_SESSION_TIMER");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("CONFIG_ITEM_MIN_SESSION_EXPIRY");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("CONFIG_ITEM_CANCELLATION_TIMER");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("CONFIG_ITEM_T_DELAY");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("CONFIG_ITEM_SILENT_REDIAL_ENABLE");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("CONFIG_ITEM_SIP_T1_TIMER");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("CONFIG_ITEM_SIP_T2_TIMER");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("CONFIG_ITEM_SIP_TF_TIMER");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("CONFIG_ITEM_VLT_SETTING_ENABLED");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("CONFIG_ITEM_LVC_SETTING_ENABLED");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("CONFIG_ITEM_DOMAIN_NAME");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("CONFIG_ITEM_SMS_FORMAT");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("CONFIG_ITEM_SMS_OVER_IP");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("CONFIG_ITEM_PUBLISH_TIMER");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("CONFIG_ITEM_PUBLISH_TIMER_EXTENDED");
            flipped |= 17;
        }
        if ((o & 18) == 18) {
            list.add("CONFIG_ITEM_CAPABILITIES_CACHE_EXPIRATION");
            flipped |= 18;
        }
        if ((o & 19) == 19) {
            list.add("CONFIG_ITEM_AVAILABILITY_CACHE_EXPIRATION");
            flipped |= 19;
        }
        if ((o & 20) == 20) {
            list.add("CONFIG_ITEM_CAPABILITIES_POLL_INTERVAL");
            flipped |= 20;
        }
        if ((o & 21) == 21) {
            list.add("CONFIG_ITEM_SOURCE_THROTTLE_PUBLISH");
            flipped |= 21;
        }
        if ((o & 22) == 22) {
            list.add("CONFIG_ITEM_MAX_NUM_ENTRIES_IN_RCL");
            flipped |= 22;
        }
        if ((o & 23) == 23) {
            list.add("CONFIG_ITEM_CAPAB_POLL_LIST_SUB_EXP");
            flipped |= 23;
        }
        if ((o & 24) == 24) {
            list.add("CONFIG_ITEM_GZIP_FLAG");
            flipped |= 24;
        }
        if ((o & 25) == 25) {
            list.add("CONFIG_ITEM_EAB_SETTING_ENABLED");
            flipped |= 25;
        }
        if ((o & 26) == 26) {
            list.add("CONFIG_ITEM_MOBILE_DATA_ENABLED");
            flipped |= 26;
        }
        if ((o & 27) == 27) {
            list.add("CONFIG_ITEM_VOICE_OVER_WIFI_ENABLED");
            flipped |= 27;
        }
        if ((o & 28) == 28) {
            list.add("CONFIG_ITEM_VOICE_OVER_WIFI_ROAMING");
            flipped |= 28;
        }
        if ((o & 29) == 29) {
            list.add("CONFIG_ITEM_VOICE_OVER_WIFI_MODE");
            flipped |= 29;
        }
        if ((o & 30) == 30) {
            list.add("CONFIG_ITEM_CAPABILITY_DISCOVERY_ENABLED");
            flipped |= 30;
        }
        if ((o & 31) == 31) {
            list.add("CONFIG_ITEM_EMERGENCY_CALL_TIMER");
            flipped |= 31;
        }
        if ((o & 32) == 32) {
            list.add("CONFIG_ITEM_SSAC_HYSTERESIS_TIMER");
            flipped |= 32;
        }
        if ((o & 33) == 33) {
            list.add("CONFIG_ITEM_VOLTE_USER_OPT_IN_STATUS");
            flipped |= 33;
        }
        if ((o & 34) == 34) {
            list.add("CONFIG_ITEM_LBO_PCSCF_ADDRESS");
            flipped |= 34;
        }
        if ((o & 35) == 35) {
            list.add("CONFIG_ITEM_KEEP_ALIVE_ENABLED");
            flipped |= 35;
        }
        if ((o & 36) == 36) {
            list.add("CONFIG_ITEM_REGISTRATION_RETRY_BASE_TIME_SEC");
            flipped |= 36;
        }
        if ((o & 37) == 37) {
            list.add("CONFIG_ITEM_REGISTRATION_RETRY_MAX_TIME_SEC");
            flipped |= 37;
        }
        if ((o & 38) == 38) {
            list.add("CONFIG_ITEM_SPEECH_START_PORT");
            flipped |= 38;
        }
        if ((o & 39) == 39) {
            list.add("CONFIG_ITEM_SPEECH_END_PORT");
            flipped |= 39;
        }
        if ((o & 40) == 40) {
            list.add("CONFIG_ITEM_SIP_INVITE_REQ_RETX_INTERVAL_MSEC");
            flipped |= 40;
        }
        if ((o & 41) == 41) {
            list.add("CONFIG_ITEM_SIP_INVITE_RSP_WAIT_TIME_MSEC");
            flipped |= 41;
        }
        if ((o & 42) == 42) {
            list.add("CONFIG_ITEM_SIP_INVITE_RSP_RETX_WAIT_TIME_MSEC");
            flipped |= 42;
        }
        if ((o & 43) == 43) {
            list.add("CONFIG_ITEM_SIP_NON_INVITE_REQ_RETX_INTERVAL_MSEC");
            flipped |= 43;
        }
        if ((o & 44) == 44) {
            list.add("CONFIG_ITEM_SIP_NON_INVITE_TXN_TIMEOUT_TIMER_MSEC");
            flipped |= 44;
        }
        if ((o & 45) == 45) {
            list.add("CONFIG_ITEM_SIP_INVITE_RSP_RETX_INTERVAL_MSEC");
            flipped |= 45;
        }
        if ((o & 46) == 46) {
            list.add("CONFIG_ITEM_SIP_ACK_RECEIPT_WAIT_TIME_MSEC");
            flipped |= 46;
        }
        if ((o & 47) == 47) {
            list.add("CONFIG_ITEM_SIP_ACK_RETX_WAIT_TIME_MSEC");
            flipped |= 47;
        }
        if ((o & 48) == 48) {
            list.add("CONFIG_ITEM_SIP_NON_INVITE_REQ_RETX_WAIT_TIME_MSEC");
            flipped |= 48;
        }
        if ((o & 49) == 49) {
            list.add("CONFIG_ITEM_SIP_NON_INVITE_RSP_RETX_WAIT_TIME_MSEC");
            flipped |= 49;
        }
        if ((o & 50) == 50) {
            list.add("CONFIG_ITEM_AMR_WB_OCTET_ALIGNED_PT");
            flipped |= 50;
        }
        if ((o & 51) == 51) {
            list.add("CONFIG_ITEM_AMR_WB_BANDWIDTH_EFFICIENT_PT");
            flipped |= 51;
        }
        if ((o & 52) == 52) {
            list.add("CONFIG_ITEM_AMR_OCTET_ALIGNED_PT");
            flipped |= 52;
        }
        if ((o & 53) == 53) {
            list.add("CONFIG_ITEM_AMR_BANDWIDTH_EFFICIENT_PT");
            flipped |= 53;
        }
        if ((o & 54) == 54) {
            list.add("CONFIG_ITEM_DTMF_WB_PT");
            flipped |= 54;
        }
        if ((o & 55) == 55) {
            list.add("CONFIG_ITEM_DTMF_NB_PT");
            flipped |= 55;
        }
        if ((o & 56) == 56) {
            list.add("CONFIG_ITEM_AMR_DEFAULT_MODE");
            flipped |= 56;
        }
        if ((o & 57) == 57) {
            list.add("CONFIG_ITEM_SMS_PSI");
            flipped |= 57;
        }
        if ((o & 58) == 58) {
            list.add("CONFIG_ITEM_VIDEO_QUALITY");
            flipped |= 58;
        }
        if ((o & 59) == 59) {
            list.add("CONFIG_ITEM_THRESHOLD_LTE1");
            flipped |= 59;
        }
        if ((o & 60) == 60) {
            list.add("CONFIG_ITEM_THRESHOLD_LTE2");
            flipped |= 60;
        }
        if ((o & 61) == 61) {
            list.add("CONFIG_ITEM_THRESHOLD_LTE3");
            flipped |= 61;
        }
        if ((o & 62) == 62) {
            list.add("CONFIG_ITEM_THRESHOLD_1x");
            flipped |= 62;
        }
        if ((o & 63) == 63) {
            list.add("CONFIG_ITEM_THRESHOLD_WIFI_A");
            flipped |= 63;
        }
        if ((o & 64) == 64) {
            list.add("CONFIG_ITEM_THRESHOLD_WIFI_B");
            flipped |= 64;
        }
        if ((o & 65) == 65) {
            list.add("CONFIG_ITEM_T_EPDG_LTE");
            flipped |= 65;
        }
        if ((o & 66) == 66) {
            list.add("CONFIG_ITEM_T_EPDG_WIFI");
            flipped |= 66;
        }
        if ((o & 67) == 67) {
            list.add("CONFIG_ITEM_T_EPDG_1x");
            flipped |= 67;
        }
        if ((o & 68) == 68) {
            list.add("CONFIG_ITEM_VWF_SETTING_ENABLED");
            flipped |= 68;
        }
        if ((o & 69) == 69) {
            list.add("CONFIG_ITEM_VCE_SETTING_ENABLED");
            flipped |= 69;
        }
        if ((o & 70) == 70) {
            list.add("CONFIG_ITEM_RTT_SETTING_ENABLED");
            flipped |= 70;
        }
        if ((o & 71) == 71) {
            list.add("CONFIG_ITEM_SMS_APP");
            flipped |= 71;
        }
        if ((o & 72) == 72) {
            list.add("CONFIG_ITEM_VVM_APP");
            flipped |= 72;
        }
        if ((o & 73) == 73) {
            list.add("CONFIG_ITEM_INVALID");
            flipped |= 73;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
