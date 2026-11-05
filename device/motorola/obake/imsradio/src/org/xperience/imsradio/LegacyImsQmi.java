package org.xperience.imsradio;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class LegacyImsQmi {
    public static final int ACTIVATE = 1;
    public static final int ADD = 0;
    public static final int ASSURED_TRANSFER = 1;
    public static final int BACKGROUND = 2;
    public static final int BLIND_TRANSFER = 0;
    public static final int BLOCK_REASON_HANDOVER_FAILURE = 3;
    public static final int BLOCK_REASON_OTHER_FAILURE = 4;
    public static final int BLOCK_REASON_PDP_FAILURE = 1;
    public static final int BLOCK_REASON_REGISTRATION_FAILURE = 2;
    public static final int CALL_ACTIVE = 0;
    public static final int CALL_ALERTING = 3;
    public static final int CALL_DIALING = 2;
    public static final int CALL_DOMAIN_AUTOMATIC = 3;
    public static final int CALL_DOMAIN_CS = 1;
    public static final int CALL_DOMAIN_NOT_SET = 4;
    public static final int CALL_DOMAIN_PS = 2;
    public static final int CALL_DOMAIN_UNKNOWN = 0;
    public static final int CALL_END = 6;
    public static final int CALL_FAIL_ACCESS_CLASS_BLOCKED = 1017;
    public static final int CALL_FAIL_ANSWERED_ELSEWHERE = 1014;
    public static final int CALL_FAIL_BLACKLISTED_CALL_ID = 504;
    public static final int CALL_FAIL_BUSY = 17;
    public static final int CALL_FAIL_CALL_BARRED = 240;
    public static final int CALL_FAIL_CAUSE_CALL_PULLED = 1016;
    public static final int CALL_FAIL_CONGESTION = 34;
    public static final int CALL_FAIL_CS_RETRY_REQUIRED = 505;
    public static final int CALL_FAIL_EMERGENCY_PERM_FAILURE = 326;
    public static final int CALL_FAIL_EMERGENCY_TEMP_FAILURE = 325;
    public static final int CALL_FAIL_ERROR_UNSPECIFIED = 65535;
    public static final int CALL_FAIL_FDN_BLOCKED = 241;
    public static final int CALL_FAIL_FEATURE_UNAVAILABLE = 1011;
    public static final int CALL_FAIL_HOLD_REINVITE_COLLISION = 3007;
    public static final int CALL_FAIL_HOLD_RESUME_CANCELED = 3006;
    public static final int CALL_FAIL_HOLD_RESUME_FAILED = 3005;
    public static final int CALL_FAIL_HO_NOT_FEASIBLE = 382;
    public static final int CALL_FAIL_INCOMPATIBILITY_DESTINATION = 88;
    public static final int CALL_FAIL_LOW_BATTERY = 503;
    public static final int CALL_FAIL_MEDIA_INIT_FAILED = 3001;
    public static final int CALL_FAIL_MEDIA_NOT_ACCEPTABLE = 3003;
    public static final int CALL_FAIL_MEDIA_NO_DATA = 3002;
    public static final int CALL_FAIL_MEDIA_UNSPECIFIED_ERROR = 3004;
    public static final int CALL_FAIL_MISC = 1013;
    public static final int CALL_FAIL_NETWORK_UNAVAILABLE = 1010;
    public static final int CALL_FAIL_NORMAL = 16;
    public static final int CALL_FAIL_NORMAL_UNSPECIFIED = 31;
    public static final int CALL_FAIL_PULL_OUT_OF_SYNC = 1015;
    public static final int CALL_FAIL_SIP_BAD_ADDRESS = 2008;
    public static final int CALL_FAIL_SIP_BAD_REQUEST = 2002;
    public static final int CALL_FAIL_SIP_BUSY = 2009;
    public static final int CALL_FAIL_SIP_Error = 1012;
    public static final int CALL_FAIL_SIP_FORBIDDEN = 2003;
    public static final int CALL_FAIL_SIP_GLOBAL_ERROR = 2022;
    public static final int CALL_FAIL_SIP_NOT_ACCEPTABLE = 2011;
    public static final int CALL_FAIL_SIP_NOT_FOUND = 2004;
    public static final int CALL_FAIL_SIP_NOT_REACHABLE = 2012;
    public static final int CALL_FAIL_SIP_NOT_SUPPORTED = 2005;
    public static final int CALL_FAIL_SIP_REDIRECTED = 2001;
    public static final int CALL_FAIL_SIP_REQUEST_CANCELLED = 2010;
    public static final int CALL_FAIL_SIP_REQUEST_TIMEOUT = 2006;
    public static final int CALL_FAIL_SIP_SERVER_BAD_GATEWAY = 2015;
    public static final int CALL_FAIL_SIP_SERVER_INTERNAL_ERROR = 2013;
    public static final int CALL_FAIL_SIP_SERVER_MESSAGE_TOOLARGE = 2019;
    public static final int CALL_FAIL_SIP_SERVER_NOT_IMPLEMENTED = 2014;
    public static final int CALL_FAIL_SIP_SERVER_PRECONDITION_FAILURE = 2020;
    public static final int CALL_FAIL_SIP_SERVER_TIMEOUT = 2017;
    public static final int CALL_FAIL_SIP_SERVER_VERSION_UNSUPPORTED = 2018;
    public static final int CALL_FAIL_SIP_SERVICE_UNAVAILABLE = 2016;
    public static final int CALL_FAIL_SIP_TEMPORARILY_UNAVAILABLE = 2007;
    public static final int CALL_FAIL_SIP_USER_REJECTED = 2021;
    public static final int CALL_FAIL_UNOBTAINABLE_NUMBER = 1;
    public static final int CALL_FAIL_USER_BUSY = 501;
    public static final int CALL_FAIL_USER_REJECT = 502;
    public static final int CALL_HOLDING = 1;
    public static final int CALL_INCOMING = 4;
    public static final int CALL_SUBSTATE_AUDIO_CONNECTED_SUSPENDED = 1;
    public static final int CALL_SUBSTATE_AVP_RETRY = 4;
    public static final int CALL_SUBSTATE_MEDIA_PAUSED = 8;
    public static final int CALL_SUBSTATE_NONE = 0;
    public static final int CALL_SUBSTATE_VIDEO_CONNECTED_SUSPENDED = 2;
    public static final int CALL_TYPE_CS_VS_RX = 6;
    public static final int CALL_TYPE_CS_VS_TX = 5;
    public static final int CALL_TYPE_PS_VS_RX = 8;
    public static final int CALL_TYPE_PS_VS_TX = 7;
    public static final int CALL_TYPE_SMS = 10;
    public static final int CALL_TYPE_UNKNOWN = 9;
    public static final int CALL_TYPE_UT = 11;
    public static final int CALL_TYPE_VOICE = 0;
    public static final int CALL_TYPE_VT = 3;
    public static final int CALL_TYPE_VT_NODIR = 4;
    public static final int CALL_TYPE_VT_RX = 2;
    public static final int CALL_TYPE_VT_TX = 1;
    public static final int CALL_WAITING = 5;
    public static final int CANCEL = 3;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_GENERIC_ERROR = 11;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_LIPSYNC = 10;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_LOCAL_REQ = 3;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_LOW_THRPUT = 8;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_PACKET_LOSS = 7;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_QOS = 6;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_REMOTE_REQ = 4;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_RTP_TIMEOUT = 5;
    public static final int CAUSE_CODE_SESSION_MODIFY_DOWNGRADE_THERM_MITIGATION = 9;
    public static final int CAUSE_CODE_SESSION_MODIFY_UPGRADE_LOCAL_REQ = 1;
    public static final int CAUSE_CODE_SESSION_MODIFY_UPGRADE_REMOTE_REQ = 2;
    public static final int CAUSE_CODE_UNSPECIFIED = 0;
    public static final int CELLULAR_ONLY = 4;
    public static final int CELLULAR_PREFERRED = 3;
    public static final int COMPLETE_FAIL = 2;
    public static final int COMPLETE_SUCCESS = 1;
    public static final int CONFIG_FILE_NOT_AVAILABLE = 2;
    public static final int CONFIG_IMS_NOT_READY = 1;
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
    public static final int CONFIG_NO_ERR = 0;
    public static final int CONFIG_OTHER_INTERNAL_ERR = 5;
    public static final int CONFIG_READ_FAILED = 3;
    public static final int CONFIG_WRITE_FAILED = 4;
    public static final int CONSULTATIVE_TRANSFER = 2;
    public static final int DEACTIVATE = 2;
    public static final int DISABLED = 0;
    public static final int ENABLED = 1;
    public static final int ERASURE = 5;
    public static final int E_CANCELLED = 7;
    public static final int E_FDN_CHECK_FAILURE = 34;
    public static final int E_GENERIC_FAILURE = 2;
    public static final int E_HOLD_RESUME_CANCELED = 32;
    public static final int E_HOLD_RESUME_FAILED = 31;
    public static final int E_IMS_DEREGISTERED = 29;
    public static final int E_INVALID_PARAMETER = 27;
    public static final int E_NETWORK_NOT_SUPPORTED = 30;
    public static final int E_RADIO_NOT_AVAILABLE = 1;
    public static final int E_REINVITE_COLLISION = 33;
    public static final int E_REJECTED_BY_REMOTE = 28;
    public static final int E_REQUEST_NOT_SUPPORTED = 6;
    public static final int E_SUCCESS = 0;
    public static final int E_UNUSED = 16;
    public static final int FACILITY_BAIC = 6;
    public static final int FACILITY_BAICa = 12;
    public static final int FACILITY_BAICr = 7;
    public static final int FACILITY_BAOC = 3;
    public static final int FACILITY_BAOIC = 4;
    public static final int FACILITY_BAOICxH = 5;
    public static final int FACILITY_BA_ALL = 8;
    public static final int FACILITY_BA_MO = 9;
    public static final int FACILITY_BA_MT = 10;
    public static final int FACILITY_BS_MT = 11;
    public static final int FACILITY_CLIP = 1;
    public static final int FACILITY_COLP = 2;
    public static final int FOREGROUND = 1;
    public static final int HIGH = 1;
    public static final int HO_DISABLED_ALL = 2;
    public static final int HO_ENABLED_ALL = 1;
    public static final int HO_ENABLED_WLAN_TO_WWAN_ONLY = 3;
    public static final int HO_ENABLED_WWAN_TO_WLAN_ONLY = 4;
    public static final int IP_PRESENTATION_NUM_ALLOWED = 0;
    public static final int IP_PRESENTATION_NUM_DEFAULT = 2;
    public static final int IP_PRESENTATION_NUM_RESTRICTED = 1;
    public static final int LOW = 0;
    public static final int LTE_TO_IWLAN_HO_FAIL = 1;
    public static final int MO = 0;
    public static final int MT = 1;
    public static final int MWI_MSG_FAX = 2;
    public static final int MWI_MSG_MULTIMEDIA = 4;
    public static final int MWI_MSG_NONE = -1;
    public static final int MWI_MSG_PAGER = 3;
    public static final int MWI_MSG_PRIORITY_LOW = 0;
    public static final int MWI_MSG_PRIORITY_NORMAL = 1;
    public static final int MWI_MSG_PRIORITY_UNKNOWN = -1;
    public static final int MWI_MSG_PRIORITY_URGENT = 2;
    public static final int MWI_MSG_TEXT = 5;
    public static final int MWI_MSG_VIDEO = 1;
    public static final int MWI_MSG_VOICE = 0;
    public static final int NOT_PROVISIONED = 0;
    public static final int NOT_TRIGGERED = 4;
    public static final int NOT_TRIGGERED_MOBILE_DATA_OFF = 5;
    public static final int PROVISIONED = 1;
    public static final int QUERY = 3;
    public static final int RADIO_STATE_OFF = 0;
    public static final int RADIO_STATE_ON = 10;
    public static final int RADIO_STATE_UNAVAILABLE = 1;
    public static final int RADIO_TECH_1xRTT = 6;
    public static final int RADIO_TECH_ANY = -1;
    public static final int RADIO_TECH_EDGE = 2;
    public static final int RADIO_TECH_EHRPD = 13;
    public static final int RADIO_TECH_EVDO_0 = 7;
    public static final int RADIO_TECH_EVDO_A = 8;
    public static final int RADIO_TECH_EVDO_B = 12;
    public static final int RADIO_TECH_GPRS = 1;
    public static final int RADIO_TECH_GSM = 16;
    public static final int RADIO_TECH_HSDPA = 9;
    public static final int RADIO_TECH_HSPA = 11;
    public static final int RADIO_TECH_HSPAP = 15;
    public static final int RADIO_TECH_HSUPA = 10;
    public static final int RADIO_TECH_IS95A = 4;
    public static final int RADIO_TECH_IS95B = 5;
    public static final int RADIO_TECH_IWLAN = 19;
    public static final int RADIO_TECH_LTE = 14;
    public static final int RADIO_TECH_TD_SCDMA = 17;
    public static final int RADIO_TECH_UMTS = 3;
    public static final int RADIO_TECH_UNKNOWN = 0;
    public static final int RADIO_TECH_WIFI = 18;
    public static final int REGISTER = 4;
    public static final int REG_FAILURE_REASON_TYPE_3GPP_SPEC_DEFINED = 5;
    public static final int REG_FAILURE_REASON_TYPE_CALL_MANAGER_DEFINED = 4;
    public static final int REG_FAILURE_REASON_TYPE_EHRPD = 7;
    public static final int REG_FAILURE_REASON_TYPE_HANDOFF = 10;
    public static final int REG_FAILURE_REASON_TYPE_INTERNAL = 3;
    public static final int REG_FAILURE_REASON_TYPE_IPV6 = 8;
    public static final int REG_FAILURE_REASON_TYPE_IWLAN = 9;
    public static final int REG_FAILURE_REASON_TYPE_MOBILE_IP = 2;
    public static final int REG_FAILURE_REASON_TYPE_PPP = 6;
    public static final int REG_FAILURE_REASON_TYPE_UNSPECIFIED = 1;
    public static final int REMOVE = 1;
    public static final int REQUEST = 1;
    public static final int REQUEST_ADD_PARTICIPANT = 28;
    public static final int REQUEST_ANSWER = 3;
    public static final int REQUEST_CANCEL_MODIFY_CALL = 56;
    public static final int REQUEST_CONFERENCE = 10;
    public static final int REQUEST_DEFLECT_CALL = 32;
    public static final int REQUEST_DIAL = 2;
    public static final int REQUEST_DTMF = 13;
    public static final int REQUEST_DTMF_START = 14;
    public static final int REQUEST_DTMF_STOP = 15;
    public static final int REQUEST_EXIT_EMERGENCY_CALLBACK_MODE = 11;
    public static final int REQUEST_EXPLICIT_CALL_TRANSFER = 12;
    public static final int REQUEST_GET_CLIR = 20;
    public static final int REQUEST_GET_COLR = 33;
    public static final int REQUEST_GET_CURRENT_CALLS = 6;
    public static final int REQUEST_GET_HANDOVER_CONFIG = 52;
    public static final int REQUEST_GET_IMS_CONFIG = 45;
    public static final int REQUEST_GET_IMS_SUB_CONFIG = 54;
    public static final int REQUEST_GET_RTP_ERROR_STATISTICS = 41;
    public static final int REQUEST_GET_RTP_STATISTICS = 40;
    public static final int REQUEST_GET_SSAC_INFO = 49;
    public static final int REQUEST_GET_VOLTE_PREF = 51;
    public static final int REQUEST_GET_VOPS_INFO = 48;
    public static final int REQUEST_GET_VOWIFI_CALL_QUALITY = 47;
    public static final int REQUEST_GET_WIFI_CALLING_STATUS = 42;
    public static final int REQUEST_HANGUP = 4;
    public static final int REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND = 8;
    public static final int REQUEST_HANGUP_WAITING_OR_BACKGROUND = 7;
    public static final int REQUEST_HOLD = 37;
    public static final int REQUEST_IMS_REGISTRATION_STATE = 1;
    public static final int REQUEST_IMS_REG_STATE_CHANGE = 26;
    public static final int REQUEST_LAST_CALL_FAIL_CAUSE = 5;
    public static final int REQUEST_MODIFY_CALL_CONFIRM = 18;
    public static final int REQUEST_MODIFY_CALL_INITIATE = 17;
    public static final int REQUEST_QUERY_CALL_FORWARD_STATUS = 22;
    public static final int REQUEST_QUERY_CALL_WAITING = 24;
    public static final int REQUEST_QUERY_CLIP = 19;
    public static final int REQUEST_QUERY_SERVICE_STATUS = 29;
    public static final int REQUEST_QUERY_VT_CALL_QUALITY = 35;
    public static final int REQUEST_RESUME = 38;
    public static final int REQUEST_SEND_GEOLOCATION_INFO = 46;
    public static final int REQUEST_SEND_RTT_MSG = 55;
    public static final int REQUEST_SEND_UI_TTY_MODE = 39;
    public static final int REQUEST_SET_CALL_FORWARD_STATUS = 23;
    public static final int REQUEST_SET_CALL_WAITING = 25;
    public static final int REQUEST_SET_CLIR = 21;
    public static final int REQUEST_SET_COLR = 34;
    public static final int REQUEST_SET_HANDOVER_CONFIG = 53;
    public static final int REQUEST_SET_IMS_CONFIG = 44;
    public static final int REQUEST_SET_SERVICE_STATUS = 30;
    public static final int REQUEST_SET_SUPP_SVC_NOTIFICATION = 27;
    public static final int REQUEST_SET_VOLTE_PREF = 50;
    public static final int REQUEST_SET_VT_CALL_QUALITY = 36;
    public static final int REQUEST_SET_WIFI_CALLING_STATUS = 43;
    public static final int REQUEST_SUPP_SVC_STATUS = 31;
    public static final int REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE = 9;
    public static final int REQUEST_UDUB = 16;
    public static final int RESPONSE = 2;
    public static final int RINGING = 0;
    public static final int RTT_MODE_DISABLED = 0;
    public static final int RTT_MODE_FULL = 1;
    public static final int SMS = 1;
    public static final int START = 0;
    public static final int STATUS_DISABLED = 0;
    public static final int STATUS_ENABLED = 2;
    public static final int STATUS_NOT_SUPPORTED = 3;
    public static final int STATUS_PARTIALLY_ENABLED = 1;
    public static final int STATUS_UNKNOWN = 2;
    public static final int TTY_MODE_FULL = 1;
    public static final int TTY_MODE_HCO = 2;
    public static final int TTY_MODE_OFF = 0;
    public static final int TTY_MODE_VCO = 3;
    public static final int UNKNOWN = 0;
    public static final int UNKNOWN_REQ = 0;
    public static final int UNSOL_CALL_RING = 202;
    public static final int UNSOL_ENTER_EMERGENCY_CALLBACK_MODE = 205;
    public static final int UNSOL_EXIT_EMERGENCY_CALLBACK_MODE = 206;
    public static final int UNSOL_IMS_SUB_CONFIG_CHANGED = 221;
    public static final int UNSOL_MODIFY_CALL = 207;
    public static final int UNSOL_MWI = 214;
    public static final int UNSOL_PARTICIPANT_STATUS_INFO = 220;
    public static final int UNSOL_RADIO_STATE_CHANGED = 213;
    public static final int UNSOL_REFRESH_CONF_INFO = 209;
    public static final int UNSOL_REFRESH_VICE_INFO = 215;
    public static final int UNSOL_REQUEST_GEOLOCATION = 216;
    public static final int UNSOL_RESPONSE = 3;
    public static final int UNSOL_RESPONSE_CALL_STATE_CHANGED = 201;
    public static final int UNSOL_RESPONSE_HANDOVER = 208;
    public static final int UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED = 204;
    public static final int UNSOL_RESPONSE_REGISTRATION_BLOCK_STATUS = 222;
    public static final int UNSOL_RESPONSE_RTT_MSG_RECEIVED = 223;
    public static final int UNSOL_RINGBACK_TONE = 203;
    public static final int UNSOL_RSP_BASE = 200;
    public static final int UNSOL_SRV_STATUS_UPDATE = 210;
    public static final int UNSOL_SSAC_CHANGED = 219;
    public static final int UNSOL_SUPP_SVC_NOTIFICATION = 211;
    public static final int UNSOL_TTY_NOTIFICATION = 212;
    public static final int UNSOL_VOPS_CHANGED = 218;
    public static final int UNSOL_VOWIFI_CALL_QUALITY = 217;
    public static final int VOIP = 2;
    public static final int VOLTE_PREF_DISABLED = 0;
    public static final int VOLTE_PREF_ENABLED = 1;
    public static final int VOLTE_PREF_UNKNOWN = 2;
    public static final int VOWIFI_QUALITY_BAD = 4;
    public static final int VOWIFI_QUALITY_EXCELLENT = 1;
    public static final int VOWIFI_QUALITY_FAIR = 2;
    public static final int VOWIFI_QUALITY_NONE = 0;
    public static final int VT = 3;
    public static final int WIFI_NOT_SUPPORTED = 0;
    public static final int WIFI_ONLY = 2;
    public static final int WIFI_PREFERRED = 1;
    public static final int WIFI_PREF_NONE = 0;
    public static final int WIFI_STATUS_OFF = 2;
    public static final int WIFI_STATUS_ON = 1;

    private LegacyImsQmi() {
    }

    /* loaded from: classes.dex */
    public static final class MsgTag extends MessageMicro {
        public static final int ERROR_FIELD_NUMBER = 4;
        public static final int ID_FIELD_NUMBER = 3;
        public static final int TOKEN_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 2;
        private boolean hasError;
        private boolean hasId;
        private boolean hasToken;
        private boolean hasType;
        private int token_ = 0;
        private int type_ = 0;
        private int id_ = 0;
        private int error_ = 0;
        private int cachedSize = -1;

        public int getToken() {
            return this.token_;
        }

        public boolean hasToken() {
            return this.hasToken;
        }

        public MsgTag setToken(int value) {
            this.hasToken = true;
            this.token_ = value;
            return this;
        }

        public MsgTag clearToken() {
            this.hasToken = false;
            this.token_ = 0;
            return this;
        }

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public MsgTag setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public MsgTag clearType() {
            this.hasType = false;
            this.type_ = 0;
            return this;
        }

        public boolean hasId() {
            return this.hasId;
        }

        public int getId() {
            return this.id_;
        }

        public MsgTag setId(int value) {
            this.hasId = true;
            this.id_ = value;
            return this;
        }

        public MsgTag clearId() {
            this.hasId = false;
            this.id_ = 0;
            return this;
        }

        public boolean hasError() {
            return this.hasError;
        }

        public int getError() {
            return this.error_;
        }

        public MsgTag setError(int value) {
            this.hasError = true;
            this.error_ = value;
            return this;
        }

        public MsgTag clearError() {
            this.hasError = false;
            this.error_ = 0;
            return this;
        }

        public final MsgTag clear() {
            clearToken();
            clearType();
            clearId();
            clearError();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasToken && this.hasType && this.hasId && this.hasError;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasToken()) {
                output.writeFixed32(1, getToken());
            }
            if (hasType()) {
                output.writeInt32(2, getType());
            }
            if (hasId()) {
                output.writeInt32(3, getId());
            }
            if (hasError()) {
                output.writeInt32(4, getError());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasToken()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getToken()) + 0;
            }
            if (hasType()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getType());
            }
            if (hasId()) {
                size += CodedOutputStreamMicro.computeInt32Size(3, getId());
            }
            if (hasError()) {
                size += CodedOutputStreamMicro.computeInt32Size(4, getError());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public MsgTag mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setToken(input.readFixed32());
                        break;
                    case 16:
                        setType(input.readInt32());
                        break;
                    case 24:
                        setId(input.readInt32());
                        break;
                    case 32:
                        setError(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static MsgTag parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (MsgTag) new MsgTag().mergeFrom(data);
        }

        public static MsgTag parseFrom(CodedInputStreamMicro input) throws IOException {
            return new MsgTag().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallFailCauseResponse extends MessageMicro {
        public static final int ERRORDETAILS_FIELD_NUMBER = 4;
        public static final int ERRORINFO_FIELD_NUMBER = 2;
        public static final int FAILCAUSE_FIELD_NUMBER = 1;
        public static final int NETWORKERRORSTRING_FIELD_NUMBER = 3;
        private boolean hasErrorDetails;
        private boolean hasErrorinfo;
        private boolean hasFailcause;
        private boolean hasNetworkErrorString;
        private int failcause_ = 1;
        private ByteStringMicro errorinfo_ = ByteStringMicro.EMPTY;
        private String networkErrorString_ = "";
        private SipErrorInfo errorDetails_ = null;
        private int cachedSize = -1;

        public boolean hasFailcause() {
            return this.hasFailcause;
        }

        public int getFailcause() {
            return this.failcause_;
        }

        public CallFailCauseResponse setFailcause(int value) {
            this.hasFailcause = true;
            this.failcause_ = value;
            return this;
        }

        public CallFailCauseResponse clearFailcause() {
            this.hasFailcause = false;
            this.failcause_ = 1;
            return this;
        }

        public ByteStringMicro getErrorinfo() {
            return this.errorinfo_;
        }

        public boolean hasErrorinfo() {
            return this.hasErrorinfo;
        }

        public CallFailCauseResponse setErrorinfo(ByteStringMicro value) {
            this.hasErrorinfo = true;
            this.errorinfo_ = value;
            return this;
        }

        public CallFailCauseResponse clearErrorinfo() {
            this.hasErrorinfo = false;
            this.errorinfo_ = ByteStringMicro.EMPTY;
            return this;
        }

        public String getNetworkErrorString() {
            return this.networkErrorString_;
        }

        public boolean hasNetworkErrorString() {
            return this.hasNetworkErrorString;
        }

        public CallFailCauseResponse setNetworkErrorString(String value) {
            this.hasNetworkErrorString = true;
            this.networkErrorString_ = value;
            return this;
        }

        public CallFailCauseResponse clearNetworkErrorString() {
            this.hasNetworkErrorString = false;
            this.networkErrorString_ = "";
            return this;
        }

        public boolean hasErrorDetails() {
            return this.hasErrorDetails;
        }

        public SipErrorInfo getErrorDetails() {
            return this.errorDetails_;
        }

        public CallFailCauseResponse setErrorDetails(SipErrorInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasErrorDetails = true;
            this.errorDetails_ = value;
            return this;
        }

        public CallFailCauseResponse clearErrorDetails() {
            this.hasErrorDetails = false;
            this.errorDetails_ = null;
            return this;
        }

        public final CallFailCauseResponse clear() {
            clearFailcause();
            clearErrorinfo();
            clearNetworkErrorString();
            clearErrorDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasFailcause()) {
                output.writeInt32(1, getFailcause());
            }
            if (hasErrorinfo()) {
                output.writeBytes(2, getErrorinfo());
            }
            if (hasNetworkErrorString()) {
                output.writeString(3, getNetworkErrorString());
            }
            if (hasErrorDetails()) {
                output.writeMessage(4, getErrorDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasFailcause()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getFailcause()) + 0;
            }
            if (hasErrorinfo()) {
                size += CodedOutputStreamMicro.computeBytesSize(2, getErrorinfo());
            }
            if (hasNetworkErrorString()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getNetworkErrorString());
            }
            if (hasErrorDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(4, getErrorDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallFailCauseResponse mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setFailcause(input.readInt32());
                        break;
                    case 18:
                        setErrorinfo(input.readBytes());
                        break;
                    case 26:
                        setNetworkErrorString(input.readString());
                        break;
                    case 34:
                        SipErrorInfo value = new SipErrorInfo();
                        input.readMessage(value);
                        setErrorDetails(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallFailCauseResponse parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallFailCauseResponse) new CallFailCauseResponse().mergeFrom(data);
        }

        public static CallFailCauseResponse parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallFailCauseResponse().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class StatusForAccessTech extends MessageMicro {
        public static final int NETWORKMODE_FIELD_NUMBER = 1;
        public static final int REGISTERED_FIELD_NUMBER = 4;
        public static final int RESTRICTIONCAUSE_FIELD_NUMBER = 3;
        public static final int STATUS_FIELD_NUMBER = 2;
        private boolean hasNetworkMode;
        private boolean hasRegistered;
        private boolean hasRestrictionCause;
        private boolean hasStatus;
        private int networkMode_ = -1;
        private int status_ = 0;
        private int restrictionCause_ = 0;
        private Registration registered_ = null;
        private int cachedSize = -1;

        public boolean hasNetworkMode() {
            return this.hasNetworkMode;
        }

        public int getNetworkMode() {
            return this.networkMode_;
        }

        public StatusForAccessTech setNetworkMode(int value) {
            this.hasNetworkMode = true;
            this.networkMode_ = value;
            return this;
        }

        public StatusForAccessTech clearNetworkMode() {
            this.hasNetworkMode = false;
            this.networkMode_ = -1;
            return this;
        }

        public boolean hasStatus() {
            return this.hasStatus;
        }

        public int getStatus() {
            return this.status_;
        }

        public StatusForAccessTech setStatus(int value) {
            this.hasStatus = true;
            this.status_ = value;
            return this;
        }

        public StatusForAccessTech clearStatus() {
            this.hasStatus = false;
            this.status_ = 0;
            return this;
        }

        public int getRestrictionCause() {
            return this.restrictionCause_;
        }

        public boolean hasRestrictionCause() {
            return this.hasRestrictionCause;
        }

        public StatusForAccessTech setRestrictionCause(int value) {
            this.hasRestrictionCause = true;
            this.restrictionCause_ = value;
            return this;
        }

        public StatusForAccessTech clearRestrictionCause() {
            this.hasRestrictionCause = false;
            this.restrictionCause_ = 0;
            return this;
        }

        public boolean hasRegistered() {
            return this.hasRegistered;
        }

        public Registration getRegistered() {
            return this.registered_;
        }

        public StatusForAccessTech setRegistered(Registration value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasRegistered = true;
            this.registered_ = value;
            return this;
        }

        public StatusForAccessTech clearRegistered() {
            this.hasRegistered = false;
            this.registered_ = null;
            return this;
        }

        public final StatusForAccessTech clear() {
            clearNetworkMode();
            clearStatus();
            clearRestrictionCause();
            clearRegistered();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasNetworkMode()) {
                output.writeInt32(1, getNetworkMode());
            }
            if (hasStatus()) {
                output.writeInt32(2, getStatus());
            }
            if (hasRestrictionCause()) {
                output.writeFixed32(3, getRestrictionCause());
            }
            if (hasRegistered()) {
                output.writeMessage(4, getRegistered());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasNetworkMode()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getNetworkMode()) + 0;
            }
            if (hasStatus()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getStatus());
            }
            if (hasRestrictionCause()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getRestrictionCause());
            }
            if (hasRegistered()) {
                size += CodedOutputStreamMicro.computeMessageSize(4, getRegistered());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public StatusForAccessTech mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setNetworkMode(input.readInt32());
                        break;
                    case 16:
                        setStatus(input.readInt32());
                        break;
                    case 29:
                        setRestrictionCause(input.readFixed32());
                        break;
                    case 34:
                        Registration value = new Registration();
                        input.readMessage(value);
                        setRegistered(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static StatusForAccessTech parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (StatusForAccessTech) new StatusForAccessTech().mergeFrom(data);
        }

        public static StatusForAccessTech parseFrom(CodedInputStreamMicro input) throws IOException {
            return new StatusForAccessTech().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Info extends MessageMicro {
        public static final int ACCTECHSTATUS_FIELD_NUMBER = 7;
        public static final int CALLTYPE_FIELD_NUMBER = 3;
        public static final int ISVALID_FIELD_NUMBER = 1;
        public static final int RESTRICTCAUSE_FIELD_NUMBER = 6;
        public static final int RTTMODE_FIELD_NUMBER = 8;
        public static final int STATUS_FIELD_NUMBER = 4;
        public static final int TYPE_FIELD_NUMBER = 2;
        public static final int USERDATA_FIELD_NUMBER = 5;
        private boolean hasCallType;
        private boolean hasIsValid;
        private boolean hasRestrictCause;
        private boolean hasRttMode;
        private boolean hasStatus;
        private boolean hasType;
        private boolean hasUserdata;
        private boolean isValid_ = false;
        private int type_ = 1;
        private int callType_ = 0;
        private int status_ = 0;
        private ByteStringMicro userdata_ = ByteStringMicro.EMPTY;
        private int restrictCause_ = 0;
        private List<StatusForAccessTech> accTechStatus_ = Collections.emptyList();
        private int rttMode_ = 0;
        private int cachedSize = -1;

        public boolean getIsValid() {
            return this.isValid_;
        }

        public boolean hasIsValid() {
            return this.hasIsValid;
        }

        public Info setIsValid(boolean value) {
            this.hasIsValid = true;
            this.isValid_ = value;
            return this;
        }

        public Info clearIsValid() {
            this.hasIsValid = false;
            this.isValid_ = false;
            return this;
        }

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public Info setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public Info clearType() {
            this.hasType = false;
            this.type_ = 1;
            return this;
        }

        public boolean hasCallType() {
            return this.hasCallType;
        }

        public int getCallType() {
            return this.callType_;
        }

        public Info setCallType(int value) {
            this.hasCallType = true;
            this.callType_ = value;
            return this;
        }

        public Info clearCallType() {
            this.hasCallType = false;
            this.callType_ = 0;
            return this;
        }

        public boolean hasStatus() {
            return this.hasStatus;
        }

        public int getStatus() {
            return this.status_;
        }

        public Info setStatus(int value) {
            this.hasStatus = true;
            this.status_ = value;
            return this;
        }

        public Info clearStatus() {
            this.hasStatus = false;
            this.status_ = 0;
            return this;
        }

        public ByteStringMicro getUserdata() {
            return this.userdata_;
        }

        public boolean hasUserdata() {
            return this.hasUserdata;
        }

        public Info setUserdata(ByteStringMicro value) {
            this.hasUserdata = true;
            this.userdata_ = value;
            return this;
        }

        public Info clearUserdata() {
            this.hasUserdata = false;
            this.userdata_ = ByteStringMicro.EMPTY;
            return this;
        }

        public int getRestrictCause() {
            return this.restrictCause_;
        }

        public boolean hasRestrictCause() {
            return this.hasRestrictCause;
        }

        public Info setRestrictCause(int value) {
            this.hasRestrictCause = true;
            this.restrictCause_ = value;
            return this;
        }

        public Info clearRestrictCause() {
            this.hasRestrictCause = false;
            this.restrictCause_ = 0;
            return this;
        }

        public List<StatusForAccessTech> getAccTechStatusList() {
            return this.accTechStatus_;
        }

        public int getAccTechStatusCount() {
            return this.accTechStatus_.size();
        }

        public StatusForAccessTech getAccTechStatus(int index) {
            return this.accTechStatus_.get(index);
        }

        public Info setAccTechStatus(int index, StatusForAccessTech value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.accTechStatus_.set(index, value);
            return this;
        }

        public Info addAccTechStatus(StatusForAccessTech value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.accTechStatus_.isEmpty()) {
                this.accTechStatus_ = new ArrayList();
            }
            this.accTechStatus_.add(value);
            return this;
        }

        public Info clearAccTechStatus() {
            this.accTechStatus_ = Collections.emptyList();
            return this;
        }

        public boolean hasRttMode() {
            return this.hasRttMode;
        }

        public int getRttMode() {
            return this.rttMode_;
        }

        public Info setRttMode(int value) {
            this.hasRttMode = true;
            this.rttMode_ = value;
            return this;
        }

        public Info clearRttMode() {
            this.hasRttMode = false;
            this.rttMode_ = 0;
            return this;
        }

        public final Info clear() {
            clearIsValid();
            clearType();
            clearCallType();
            clearStatus();
            clearUserdata();
            clearRestrictCause();
            clearAccTechStatus();
            clearRttMode();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasIsValid()) {
                output.writeBool(1, getIsValid());
            }
            if (hasType()) {
                output.writeInt32(2, getType());
            }
            if (hasCallType()) {
                output.writeInt32(3, getCallType());
            }
            if (hasStatus()) {
                output.writeInt32(4, getStatus());
            }
            if (hasUserdata()) {
                output.writeBytes(5, getUserdata());
            }
            if (hasRestrictCause()) {
                output.writeFixed32(6, getRestrictCause());
            }
            for (StatusForAccessTech element : getAccTechStatusList()) {
                output.writeMessage(7, element);
            }
            if (hasRttMode()) {
                output.writeInt32(8, getRttMode());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasIsValid()) {
                size = CodedOutputStreamMicro.computeBoolSize(1, getIsValid()) + 0;
            }
            if (hasType()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getType());
            }
            if (hasCallType()) {
                size += CodedOutputStreamMicro.computeInt32Size(3, getCallType());
            }
            if (hasStatus()) {
                size += CodedOutputStreamMicro.computeInt32Size(4, getStatus());
            }
            if (hasUserdata()) {
                size += CodedOutputStreamMicro.computeBytesSize(5, getUserdata());
            }
            if (hasRestrictCause()) {
                size += CodedOutputStreamMicro.computeFixed32Size(6, getRestrictCause());
            }
            for (StatusForAccessTech element : getAccTechStatusList()) {
                size += CodedOutputStreamMicro.computeMessageSize(7, element);
            }
            if (hasRttMode()) {
                size += CodedOutputStreamMicro.computeInt32Size(8, getRttMode());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Info mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setIsValid(input.readBool());
                        break;
                    case 16:
                        setType(input.readInt32());
                        break;
                    case 24:
                        setCallType(input.readInt32());
                        break;
                    case 32:
                        setStatus(input.readInt32());
                        break;
                    case 42:
                        setUserdata(input.readBytes());
                        break;
                    case 53:
                        setRestrictCause(input.readFixed32());
                        break;
                    case 58:
                        StatusForAccessTech value = new StatusForAccessTech();
                        input.readMessage(value);
                        addAccTechStatus(value);
                        break;
                    case 64:
                        setRttMode(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Info parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Info) new Info().mergeFrom(data);
        }

        public static Info parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Info().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SrvStatusList extends MessageMicro {
        public static final int SRVSTATUSINFO_FIELD_NUMBER = 2;
        private List<Info> srvStatusInfo_ = Collections.emptyList();
        private int cachedSize = -1;

        public List<Info> getSrvStatusInfoList() {
            return this.srvStatusInfo_;
        }

        public int getSrvStatusInfoCount() {
            return this.srvStatusInfo_.size();
        }

        public Info getSrvStatusInfo(int index) {
            return this.srvStatusInfo_.get(index);
        }

        public SrvStatusList setSrvStatusInfo(int index, Info value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.srvStatusInfo_.set(index, value);
            return this;
        }

        public SrvStatusList addSrvStatusInfo(Info value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.srvStatusInfo_.isEmpty()) {
                this.srvStatusInfo_ = new ArrayList();
            }
            this.srvStatusInfo_.add(value);
            return this;
        }

        public SrvStatusList clearSrvStatusInfo() {
            this.srvStatusInfo_ = Collections.emptyList();
            return this;
        }

        public final SrvStatusList clear() {
            clearSrvStatusInfo();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            for (Info element : getSrvStatusInfoList()) {
                output.writeMessage(2, element);
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            for (Info element : getSrvStatusInfoList()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, element);
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SrvStatusList mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 18:
                        Info value = new Info();
                        input.readMessage(value);
                        addSrvStatusInfo(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SrvStatusList parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SrvStatusList) new SrvStatusList().mergeFrom(data);
        }

        public static SrvStatusList parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SrvStatusList().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallDetails extends MessageMicro {
        public static final int CALLDOMAIN_FIELD_NUMBER = 2;
        public static final int CALLSUBSTATE_FIELD_NUMBER = 7;
        public static final int CALLTYPE_FIELD_NUMBER = 1;
        public static final int CAUSECODE_FIELD_NUMBER = 9;
        public static final int EXTRASLENGTH_FIELD_NUMBER = 3;
        public static final int EXTRAS_FIELD_NUMBER = 4;
        public static final int LOCALABILITY_FIELD_NUMBER = 5;
        public static final int MEDIAID_FIELD_NUMBER = 8;
        public static final int PEERABILITY_FIELD_NUMBER = 6;
        public static final int RTTMODE_FIELD_NUMBER = 10;
        public static final int SIPALTERNATEURI_FIELD_NUMBER = 11;
        private boolean hasCallDomain;
        private boolean hasCallSubstate;
        private boolean hasCallType;
        private boolean hasCauseCode;
        private boolean hasExtrasLength;
        private boolean hasLocalAbility;
        private boolean hasMediaId;
        private boolean hasPeerAbility;
        private boolean hasRttMode;
        private boolean hasSipAlternateUri;
        private int callType_ = 0;
        private int callDomain_ = 0;
        private int extrasLength_ = 0;
        private List<String> extras_ = Collections.emptyList();
        private SrvStatusList localAbility_ = null;
        private SrvStatusList peerAbility_ = null;
        private int callSubstate_ = 0;
        private int mediaId_ = -1;
        private int causeCode_ = 0;
        private int rttMode_ = 0;
        private String sipAlternateUri_ = "";
        private int cachedSize = -1;

        public boolean hasCallType() {
            return this.hasCallType;
        }

        public int getCallType() {
            return this.callType_;
        }

        public CallDetails setCallType(int value) {
            this.hasCallType = true;
            this.callType_ = value;
            return this;
        }

        public CallDetails clearCallType() {
            this.hasCallType = false;
            this.callType_ = 0;
            return this;
        }

        public boolean hasCallDomain() {
            return this.hasCallDomain;
        }

        public int getCallDomain() {
            return this.callDomain_;
        }

        public CallDetails setCallDomain(int value) {
            this.hasCallDomain = true;
            this.callDomain_ = value;
            return this;
        }

        public CallDetails clearCallDomain() {
            this.hasCallDomain = false;
            this.callDomain_ = 0;
            return this;
        }

        public int getExtrasLength() {
            return this.extrasLength_;
        }

        public boolean hasExtrasLength() {
            return this.hasExtrasLength;
        }

        public CallDetails setExtrasLength(int value) {
            this.hasExtrasLength = true;
            this.extrasLength_ = value;
            return this;
        }

        public CallDetails clearExtrasLength() {
            this.hasExtrasLength = false;
            this.extrasLength_ = 0;
            return this;
        }

        public List<String> getExtrasList() {
            return this.extras_;
        }

        public int getExtrasCount() {
            return this.extras_.size();
        }

        public String getExtras(int index) {
            return this.extras_.get(index);
        }

        public CallDetails setExtras(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.extras_.set(index, value);
            return this;
        }

        public CallDetails addExtras(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.extras_.isEmpty()) {
                this.extras_ = new ArrayList();
            }
            this.extras_.add(value);
            return this;
        }

        public CallDetails clearExtras() {
            this.extras_ = Collections.emptyList();
            return this;
        }

        public boolean hasLocalAbility() {
            return this.hasLocalAbility;
        }

        public SrvStatusList getLocalAbility() {
            return this.localAbility_;
        }

        public CallDetails setLocalAbility(SrvStatusList value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasLocalAbility = true;
            this.localAbility_ = value;
            return this;
        }

        public CallDetails clearLocalAbility() {
            this.hasLocalAbility = false;
            this.localAbility_ = null;
            return this;
        }

        public boolean hasPeerAbility() {
            return this.hasPeerAbility;
        }

        public SrvStatusList getPeerAbility() {
            return this.peerAbility_;
        }

        public CallDetails setPeerAbility(SrvStatusList value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasPeerAbility = true;
            this.peerAbility_ = value;
            return this;
        }

        public CallDetails clearPeerAbility() {
            this.hasPeerAbility = false;
            this.peerAbility_ = null;
            return this;
        }

        public boolean hasCallSubstate() {
            return this.hasCallSubstate;
        }

        public int getCallSubstate() {
            return this.callSubstate_;
        }

        public CallDetails setCallSubstate(int value) {
            this.hasCallSubstate = true;
            this.callSubstate_ = value;
            return this;
        }

        public CallDetails clearCallSubstate() {
            this.hasCallSubstate = false;
            this.callSubstate_ = 0;
            return this;
        }

        public int getMediaId() {
            return this.mediaId_;
        }

        public boolean hasMediaId() {
            return this.hasMediaId;
        }

        public CallDetails setMediaId(int value) {
            this.hasMediaId = true;
            this.mediaId_ = value;
            return this;
        }

        public CallDetails clearMediaId() {
            this.hasMediaId = false;
            this.mediaId_ = -1;
            return this;
        }

        public boolean hasCauseCode() {
            return this.hasCauseCode;
        }

        public int getCauseCode() {
            return this.causeCode_;
        }

        public CallDetails setCauseCode(int value) {
            this.hasCauseCode = true;
            this.causeCode_ = value;
            return this;
        }

        public CallDetails clearCauseCode() {
            this.hasCauseCode = false;
            this.causeCode_ = 0;
            return this;
        }

        public boolean hasRttMode() {
            return this.hasRttMode;
        }

        public int getRttMode() {
            return this.rttMode_;
        }

        public CallDetails setRttMode(int value) {
            this.hasRttMode = true;
            this.rttMode_ = value;
            return this;
        }

        public CallDetails clearRttMode() {
            this.hasRttMode = false;
            this.rttMode_ = 0;
            return this;
        }

        public String getSipAlternateUri() {
            return this.sipAlternateUri_;
        }

        public boolean hasSipAlternateUri() {
            return this.hasSipAlternateUri;
        }

        public CallDetails setSipAlternateUri(String value) {
            this.hasSipAlternateUri = true;
            this.sipAlternateUri_ = value;
            return this;
        }

        public CallDetails clearSipAlternateUri() {
            this.hasSipAlternateUri = false;
            this.sipAlternateUri_ = "";
            return this;
        }

        public final CallDetails clear() {
            clearCallType();
            clearCallDomain();
            clearExtrasLength();
            clearExtras();
            clearLocalAbility();
            clearPeerAbility();
            clearCallSubstate();
            clearMediaId();
            clearCauseCode();
            clearRttMode();
            clearSipAlternateUri();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallType()) {
                output.writeInt32(1, getCallType());
            }
            if (hasCallDomain()) {
                output.writeInt32(2, getCallDomain());
            }
            if (hasExtrasLength()) {
                output.writeFixed32(3, getExtrasLength());
            }
            for (String element : getExtrasList()) {
                output.writeString(4, element);
            }
            if (hasLocalAbility()) {
                output.writeMessage(5, getLocalAbility());
            }
            if (hasPeerAbility()) {
                output.writeMessage(6, getPeerAbility());
            }
            if (hasCallSubstate()) {
                output.writeInt32(7, getCallSubstate());
            }
            if (hasMediaId()) {
                output.writeSFixed32(8, getMediaId());
            }
            if (hasCauseCode()) {
                output.writeInt32(9, getCauseCode());
            }
            if (hasRttMode()) {
                output.writeInt32(10, getRttMode());
            }
            if (hasSipAlternateUri()) {
                output.writeString(11, getSipAlternateUri());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getCallType()) + 0;
            }
            if (hasCallDomain()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getCallDomain());
            }
            if (hasExtrasLength()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getExtrasLength());
            }
            int dataSize = 0;
            for (String element : getExtrasList()) {
                dataSize += CodedOutputStreamMicro.computeStringSizeNoTag(element);
            }
            int size2 = size + dataSize + (getExtrasList().size() * 1);
            if (hasLocalAbility()) {
                size2 += CodedOutputStreamMicro.computeMessageSize(5, getLocalAbility());
            }
            if (hasPeerAbility()) {
                size2 += CodedOutputStreamMicro.computeMessageSize(6, getPeerAbility());
            }
            if (hasCallSubstate()) {
                size2 += CodedOutputStreamMicro.computeInt32Size(7, getCallSubstate());
            }
            if (hasMediaId()) {
                size2 += CodedOutputStreamMicro.computeSFixed32Size(8, getMediaId());
            }
            if (hasCauseCode()) {
                size2 += CodedOutputStreamMicro.computeInt32Size(9, getCauseCode());
            }
            if (hasRttMode()) {
                size2 += CodedOutputStreamMicro.computeInt32Size(10, getRttMode());
            }
            if (hasSipAlternateUri()) {
                size2 += CodedOutputStreamMicro.computeStringSize(11, getSipAlternateUri());
            }
            this.cachedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallDetails mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setCallType(input.readInt32());
                        break;
                    case 16:
                        setCallDomain(input.readInt32());
                        break;
                    case 29:
                        setExtrasLength(input.readFixed32());
                        break;
                    case 34:
                        addExtras(input.readString());
                        break;
                    case 42:
                        SrvStatusList value = new SrvStatusList();
                        input.readMessage(value);
                        setLocalAbility(value);
                        break;
                    case 50:
                        SrvStatusList value2 = new SrvStatusList();
                        input.readMessage(value2);
                        setPeerAbility(value2);
                        break;
                    case 56:
                        setCallSubstate(input.readInt32());
                        break;
                    case 69:
                        setMediaId(input.readSFixed32());
                        break;
                    case 72:
                        setCauseCode(input.readInt32());
                        break;
                    case 80:
                        setRttMode(input.readInt32());
                        break;
                    case 90:
                        setSipAlternateUri(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallDetails parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallDetails) new CallDetails().mergeFrom(data);
        }

        public static CallDetails parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallDetails().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallModify extends MessageMicro {
        public static final int CALLDETAILS_FIELD_NUMBER = 3;
        public static final int CALLINDEX_FIELD_NUMBER = 2;
        public static final int ERROR_FIELD_NUMBER = 4;
        private boolean hasCallDetails;
        private boolean hasCallIndex;
        private boolean hasError;
        private int callIndex_ = 0;
        private CallDetails callDetails_ = null;
        private int error_ = 0;
        private int cachedSize = -1;

        public int getCallIndex() {
            return this.callIndex_;
        }

        public boolean hasCallIndex() {
            return this.hasCallIndex;
        }

        public CallModify setCallIndex(int value) {
            this.hasCallIndex = true;
            this.callIndex_ = value;
            return this;
        }

        public CallModify clearCallIndex() {
            this.hasCallIndex = false;
            this.callIndex_ = 0;
            return this;
        }

        public boolean hasCallDetails() {
            return this.hasCallDetails;
        }

        public CallDetails getCallDetails() {
            return this.callDetails_;
        }

        public CallModify setCallDetails(CallDetails value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasCallDetails = true;
            this.callDetails_ = value;
            return this;
        }

        public CallModify clearCallDetails() {
            this.hasCallDetails = false;
            this.callDetails_ = null;
            return this;
        }

        public boolean hasError() {
            return this.hasError;
        }

        public int getError() {
            return this.error_;
        }

        public CallModify setError(int value) {
            this.hasError = true;
            this.error_ = value;
            return this;
        }

        public CallModify clearError() {
            this.hasError = false;
            this.error_ = 0;
            return this;
        }

        public final CallModify clear() {
            clearCallIndex();
            clearCallDetails();
            clearError();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallIndex()) {
                output.writeFixed32(2, getCallIndex());
            }
            if (hasCallDetails()) {
                output.writeMessage(3, getCallDetails());
            }
            if (hasError()) {
                output.writeInt32(4, getError());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallIndex()) {
                size = CodedOutputStreamMicro.computeFixed32Size(2, getCallIndex()) + 0;
            }
            if (hasCallDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, getCallDetails());
            }
            if (hasError()) {
                size += CodedOutputStreamMicro.computeInt32Size(4, getError());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallModify mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 21:
                        setCallIndex(input.readFixed32());
                        break;
                    case 26:
                        CallDetails value = new CallDetails();
                        input.readMessage(value);
                        setCallDetails(value);
                        break;
                    case 32:
                        setError(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallModify parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallModify) new CallModify().mergeFrom(data);
        }

        public static CallModify parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallModify().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ConfSupportInfo extends MessageMicro {
        public static final int ISVIDEOCONFSUPPORTED_FIELD_NUMBER = 1;
        private boolean hasIsVideoConfSupported;
        private boolean isVideoConfSupported_ = false;
        private int cachedSize = -1;

        public boolean getIsVideoConfSupported() {
            return this.isVideoConfSupported_;
        }

        public boolean hasIsVideoConfSupported() {
            return this.hasIsVideoConfSupported;
        }

        public ConfSupportInfo setIsVideoConfSupported(boolean value) {
            this.hasIsVideoConfSupported = true;
            this.isVideoConfSupported_ = value;
            return this;
        }

        public ConfSupportInfo clearIsVideoConfSupported() {
            this.hasIsVideoConfSupported = false;
            this.isVideoConfSupported_ = false;
            return this;
        }

        public final ConfSupportInfo clear() {
            clearIsVideoConfSupported();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasIsVideoConfSupported()) {
                output.writeBool(1, getIsVideoConfSupported());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasIsVideoConfSupported()) {
                size = CodedOutputStreamMicro.computeBoolSize(1, getIsVideoConfSupported()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ConfSupportInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setIsVideoConfSupported(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ConfSupportInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ConfSupportInfo) new ConfSupportInfo().mergeFrom(data);
        }

        public static ConfSupportInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ConfSupportInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallList extends MessageMicro {
        public static final int CALLATTRIBUTES_FIELD_NUMBER = 2;
        private List<Call> callAttributes_ = Collections.emptyList();
        private int cachedSize = -1;

        /* loaded from: classes.dex */
        public static final class Call extends MessageMicro {
            public static final int ALS_FIELD_NUMBER = 6;
            public static final int CALLDETAILS_FIELD_NUMBER = 13;
            public static final int FAILCAUSE_FIELD_NUMBER = 14;
            public static final int HISTORYINFO_FIELD_NUMBER = 17;
            public static final int INDEX_FIELD_NUMBER = 2;
            public static final int ISCALLEDPARTYRINGING_FIELD_NUMBER = 16;
            public static final int ISENCRYPTED_FIELD_NUMBER = 15;
            public static final int ISMPTY_FIELD_NUMBER = 4;
            public static final int ISMT_FIELD_NUMBER = 5;
            public static final int ISVOICEPRIVACY_FIELD_NUMBER = 8;
            public static final int ISVOICE_FIELD_NUMBER = 7;
            public static final int NAMEPRESENTATION_FIELD_NUMBER = 12;
            public static final int NAME_FIELD_NUMBER = 11;
            public static final int NUMBERPRESENTATION_FIELD_NUMBER = 10;
            public static final int NUMBER_FIELD_NUMBER = 9;
            public static final int PEERCONFABILITY_FIELD_NUMBER = 18;
            public static final int STATE_FIELD_NUMBER = 1;
            public static final int TOA_FIELD_NUMBER = 3;
            private boolean hasAls;
            private boolean hasCallDetails;
            private boolean hasFailCause;
            private boolean hasHistoryInfo;
            private boolean hasIndex;
            private boolean hasIsCalledPartyRinging;
            private boolean hasIsEncrypted;
            private boolean hasIsMT;
            private boolean hasIsMpty;
            private boolean hasIsVoice;
            private boolean hasIsVoicePrivacy;
            private boolean hasName;
            private boolean hasNamePresentation;
            private boolean hasNumber;
            private boolean hasNumberPresentation;
            private boolean hasPeerConfAbility;
            private boolean hasState;
            private boolean hasToa;
            private int state_ = 0;
            private int index_ = 0;
            private int toa_ = 0;
            private boolean isMpty_ = false;
            private boolean isMT_ = false;
            private int als_ = 0;
            private boolean isVoice_ = false;
            private boolean isVoicePrivacy_ = false;
            private String number_ = "";
            private int numberPresentation_ = 0;
            private String name_ = "";
            private int namePresentation_ = 0;
            private CallDetails callDetails_ = null;
            private CallFailCauseResponse failCause_ = null;
            private boolean isEncrypted_ = false;
            private boolean isCalledPartyRinging_ = false;
            private String historyInfo_ = "";
            private ConfSupportInfo peerConfAbility_ = null;
            private int cachedSize = -1;

            public boolean hasState() {
                return this.hasState;
            }

            public int getState() {
                return this.state_;
            }

            public Call setState(int value) {
                this.hasState = true;
                this.state_ = value;
                return this;
            }

            public Call clearState() {
                this.hasState = false;
                this.state_ = 0;
                return this;
            }

            public int getIndex() {
                return this.index_;
            }

            public boolean hasIndex() {
                return this.hasIndex;
            }

            public Call setIndex(int value) {
                this.hasIndex = true;
                this.index_ = value;
                return this;
            }

            public Call clearIndex() {
                this.hasIndex = false;
                this.index_ = 0;
                return this;
            }

            public int getToa() {
                return this.toa_;
            }

            public boolean hasToa() {
                return this.hasToa;
            }

            public Call setToa(int value) {
                this.hasToa = true;
                this.toa_ = value;
                return this;
            }

            public Call clearToa() {
                this.hasToa = false;
                this.toa_ = 0;
                return this;
            }

            public boolean getIsMpty() {
                return this.isMpty_;
            }

            public boolean hasIsMpty() {
                return this.hasIsMpty;
            }

            public Call setIsMpty(boolean value) {
                this.hasIsMpty = true;
                this.isMpty_ = value;
                return this;
            }

            public Call clearIsMpty() {
                this.hasIsMpty = false;
                this.isMpty_ = false;
                return this;
            }

            public boolean getIsMT() {
                return this.isMT_;
            }

            public boolean hasIsMT() {
                return this.hasIsMT;
            }

            public Call setIsMT(boolean value) {
                this.hasIsMT = true;
                this.isMT_ = value;
                return this;
            }

            public Call clearIsMT() {
                this.hasIsMT = false;
                this.isMT_ = false;
                return this;
            }

            public int getAls() {
                return this.als_;
            }

            public boolean hasAls() {
                return this.hasAls;
            }

            public Call setAls(int value) {
                this.hasAls = true;
                this.als_ = value;
                return this;
            }

            public Call clearAls() {
                this.hasAls = false;
                this.als_ = 0;
                return this;
            }

            public boolean getIsVoice() {
                return this.isVoice_;
            }

            public boolean hasIsVoice() {
                return this.hasIsVoice;
            }

            public Call setIsVoice(boolean value) {
                this.hasIsVoice = true;
                this.isVoice_ = value;
                return this;
            }

            public Call clearIsVoice() {
                this.hasIsVoice = false;
                this.isVoice_ = false;
                return this;
            }

            public boolean getIsVoicePrivacy() {
                return this.isVoicePrivacy_;
            }

            public boolean hasIsVoicePrivacy() {
                return this.hasIsVoicePrivacy;
            }

            public Call setIsVoicePrivacy(boolean value) {
                this.hasIsVoicePrivacy = true;
                this.isVoicePrivacy_ = value;
                return this;
            }

            public Call clearIsVoicePrivacy() {
                this.hasIsVoicePrivacy = false;
                this.isVoicePrivacy_ = false;
                return this;
            }

            public String getNumber() {
                return this.number_;
            }

            public boolean hasNumber() {
                return this.hasNumber;
            }

            public Call setNumber(String value) {
                this.hasNumber = true;
                this.number_ = value;
                return this;
            }

            public Call clearNumber() {
                this.hasNumber = false;
                this.number_ = "";
                return this;
            }

            public int getNumberPresentation() {
                return this.numberPresentation_;
            }

            public boolean hasNumberPresentation() {
                return this.hasNumberPresentation;
            }

            public Call setNumberPresentation(int value) {
                this.hasNumberPresentation = true;
                this.numberPresentation_ = value;
                return this;
            }

            public Call clearNumberPresentation() {
                this.hasNumberPresentation = false;
                this.numberPresentation_ = 0;
                return this;
            }

            public String getName() {
                return this.name_;
            }

            public boolean hasName() {
                return this.hasName;
            }

            public Call setName(String value) {
                this.hasName = true;
                this.name_ = value;
                return this;
            }

            public Call clearName() {
                this.hasName = false;
                this.name_ = "";
                return this;
            }

            public int getNamePresentation() {
                return this.namePresentation_;
            }

            public boolean hasNamePresentation() {
                return this.hasNamePresentation;
            }

            public Call setNamePresentation(int value) {
                this.hasNamePresentation = true;
                this.namePresentation_ = value;
                return this;
            }

            public Call clearNamePresentation() {
                this.hasNamePresentation = false;
                this.namePresentation_ = 0;
                return this;
            }

            public boolean hasCallDetails() {
                return this.hasCallDetails;
            }

            public CallDetails getCallDetails() {
                return this.callDetails_;
            }

            public Call setCallDetails(CallDetails value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.hasCallDetails = true;
                this.callDetails_ = value;
                return this;
            }

            public Call clearCallDetails() {
                this.hasCallDetails = false;
                this.callDetails_ = null;
                return this;
            }

            public boolean hasFailCause() {
                return this.hasFailCause;
            }

            public CallFailCauseResponse getFailCause() {
                return this.failCause_;
            }

            public Call setFailCause(CallFailCauseResponse value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.hasFailCause = true;
                this.failCause_ = value;
                return this;
            }

            public Call clearFailCause() {
                this.hasFailCause = false;
                this.failCause_ = null;
                return this;
            }

            public boolean getIsEncrypted() {
                return this.isEncrypted_;
            }

            public boolean hasIsEncrypted() {
                return this.hasIsEncrypted;
            }

            public Call setIsEncrypted(boolean value) {
                this.hasIsEncrypted = true;
                this.isEncrypted_ = value;
                return this;
            }

            public Call clearIsEncrypted() {
                this.hasIsEncrypted = false;
                this.isEncrypted_ = false;
                return this;
            }

            public boolean getIsCalledPartyRinging() {
                return this.isCalledPartyRinging_;
            }

            public boolean hasIsCalledPartyRinging() {
                return this.hasIsCalledPartyRinging;
            }

            public Call setIsCalledPartyRinging(boolean value) {
                this.hasIsCalledPartyRinging = true;
                this.isCalledPartyRinging_ = value;
                return this;
            }

            public Call clearIsCalledPartyRinging() {
                this.hasIsCalledPartyRinging = false;
                this.isCalledPartyRinging_ = false;
                return this;
            }

            public String getHistoryInfo() {
                return this.historyInfo_;
            }

            public boolean hasHistoryInfo() {
                return this.hasHistoryInfo;
            }

            public Call setHistoryInfo(String value) {
                this.hasHistoryInfo = true;
                this.historyInfo_ = value;
                return this;
            }

            public Call clearHistoryInfo() {
                this.hasHistoryInfo = false;
                this.historyInfo_ = "";
                return this;
            }

            public boolean hasPeerConfAbility() {
                return this.hasPeerConfAbility;
            }

            public ConfSupportInfo getPeerConfAbility() {
                return this.peerConfAbility_;
            }

            public Call setPeerConfAbility(ConfSupportInfo value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.hasPeerConfAbility = true;
                this.peerConfAbility_ = value;
                return this;
            }

            public Call clearPeerConfAbility() {
                this.hasPeerConfAbility = false;
                this.peerConfAbility_ = null;
                return this;
            }

            public final Call clear() {
                clearState();
                clearIndex();
                clearToa();
                clearIsMpty();
                clearIsMT();
                clearAls();
                clearIsVoice();
                clearIsVoicePrivacy();
                clearNumber();
                clearNumberPresentation();
                clearName();
                clearNamePresentation();
                clearCallDetails();
                clearFailCause();
                clearIsEncrypted();
                clearIsCalledPartyRinging();
                clearHistoryInfo();
                clearPeerConfAbility();
                this.cachedSize = -1;
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public void writeTo(CodedOutputStreamMicro output) throws IOException {
                if (hasState()) {
                    output.writeInt32(1, getState());
                }
                if (hasIndex()) {
                    output.writeFixed32(2, getIndex());
                }
                if (hasToa()) {
                    output.writeFixed32(3, getToa());
                }
                if (hasIsMpty()) {
                    output.writeBool(4, getIsMpty());
                }
                if (hasIsMT()) {
                    output.writeBool(5, getIsMT());
                }
                if (hasAls()) {
                    output.writeFixed32(6, getAls());
                }
                if (hasIsVoice()) {
                    output.writeBool(7, getIsVoice());
                }
                if (hasIsVoicePrivacy()) {
                    output.writeBool(8, getIsVoicePrivacy());
                }
                if (hasNumber()) {
                    output.writeString(9, getNumber());
                }
                if (hasNumberPresentation()) {
                    output.writeFixed32(10, getNumberPresentation());
                }
                if (hasName()) {
                    output.writeString(11, getName());
                }
                if (hasNamePresentation()) {
                    output.writeFixed32(12, getNamePresentation());
                }
                if (hasCallDetails()) {
                    output.writeMessage(13, getCallDetails());
                }
                if (hasFailCause()) {
                    output.writeMessage(14, getFailCause());
                }
                if (hasIsEncrypted()) {
                    output.writeBool(15, getIsEncrypted());
                }
                if (hasIsCalledPartyRinging()) {
                    output.writeBool(16, getIsCalledPartyRinging());
                }
                if (hasHistoryInfo()) {
                    output.writeString(17, getHistoryInfo());
                }
                if (hasPeerConfAbility()) {
                    output.writeMessage(18, getPeerConfAbility());
                }
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public int getCachedSize() {
                if (this.cachedSize < 0) {
                    getSerializedSize();
                }
                return this.cachedSize;
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public int getSerializedSize() {
                int size = 0;
                if (hasState()) {
                    size = CodedOutputStreamMicro.computeInt32Size(1, getState()) + 0;
                }
                if (hasIndex()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(2, getIndex());
                }
                if (hasToa()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(3, getToa());
                }
                if (hasIsMpty()) {
                    size += CodedOutputStreamMicro.computeBoolSize(4, getIsMpty());
                }
                if (hasIsMT()) {
                    size += CodedOutputStreamMicro.computeBoolSize(5, getIsMT());
                }
                if (hasAls()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(6, getAls());
                }
                if (hasIsVoice()) {
                    size += CodedOutputStreamMicro.computeBoolSize(7, getIsVoice());
                }
                if (hasIsVoicePrivacy()) {
                    size += CodedOutputStreamMicro.computeBoolSize(8, getIsVoicePrivacy());
                }
                if (hasNumber()) {
                    size += CodedOutputStreamMicro.computeStringSize(9, getNumber());
                }
                if (hasNumberPresentation()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(10, getNumberPresentation());
                }
                if (hasName()) {
                    size += CodedOutputStreamMicro.computeStringSize(11, getName());
                }
                if (hasNamePresentation()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(12, getNamePresentation());
                }
                if (hasCallDetails()) {
                    size += CodedOutputStreamMicro.computeMessageSize(13, getCallDetails());
                }
                if (hasFailCause()) {
                    size += CodedOutputStreamMicro.computeMessageSize(14, getFailCause());
                }
                if (hasIsEncrypted()) {
                    size += CodedOutputStreamMicro.computeBoolSize(15, getIsEncrypted());
                }
                if (hasIsCalledPartyRinging()) {
                    size += CodedOutputStreamMicro.computeBoolSize(16, getIsCalledPartyRinging());
                }
                if (hasHistoryInfo()) {
                    size += CodedOutputStreamMicro.computeStringSize(17, getHistoryInfo());
                }
                if (hasPeerConfAbility()) {
                    size += CodedOutputStreamMicro.computeMessageSize(18, getPeerConfAbility());
                }
                this.cachedSize = size;
                return size;
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public Call mergeFrom(CodedInputStreamMicro input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            return this;
                        case 8:
                            setState(input.readInt32());
                            break;
                        case 21:
                            setIndex(input.readFixed32());
                            break;
                        case 29:
                            setToa(input.readFixed32());
                            break;
                        case 32:
                            setIsMpty(input.readBool());
                            break;
                        case 40:
                            setIsMT(input.readBool());
                            break;
                        case 53:
                            setAls(input.readFixed32());
                            break;
                        case 56:
                            setIsVoice(input.readBool());
                            break;
                        case 64:
                            setIsVoicePrivacy(input.readBool());
                            break;
                        case 74:
                            setNumber(input.readString());
                            break;
                        case 85:
                            setNumberPresentation(input.readFixed32());
                            break;
                        case 90:
                            setName(input.readString());
                            break;
                        case 101:
                            setNamePresentation(input.readFixed32());
                            break;
                        case 106:
                            CallDetails value = new CallDetails();
                            input.readMessage(value);
                            setCallDetails(value);
                            break;
                        case 114:
                            CallFailCauseResponse value2 = new CallFailCauseResponse();
                            input.readMessage(value2);
                            setFailCause(value2);
                            break;
                        case 120:
                            setIsEncrypted(input.readBool());
                            break;
                        case 128:
                            setIsCalledPartyRinging(input.readBool());
                            break;
                        case 138:
                            setHistoryInfo(input.readString());
                            break;
                        case 146:
                            ConfSupportInfo value3 = new ConfSupportInfo();
                            input.readMessage(value3);
                            setPeerConfAbility(value3);
                            break;
                        default:
                            if (parseUnknownField(input, tag)) {
                                break;
                            } else {
                                return this;
                            }
                    }
                }
            }

            public static Call parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
                return (Call) new Call().mergeFrom(data);
            }

            public static Call parseFrom(CodedInputStreamMicro input) throws IOException {
                return new Call().mergeFrom(input);
            }
        }

        public List<Call> getCallAttributesList() {
            return this.callAttributes_;
        }

        public int getCallAttributesCount() {
            return this.callAttributes_.size();
        }

        public Call getCallAttributes(int index) {
            return this.callAttributes_.get(index);
        }

        public CallList setCallAttributes(int index, Call value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.callAttributes_.set(index, value);
            return this;
        }

        public CallList addCallAttributes(Call value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.callAttributes_.isEmpty()) {
                this.callAttributes_ = new ArrayList();
            }
            this.callAttributes_.add(value);
            return this;
        }

        public CallList clearCallAttributes() {
            this.callAttributes_ = Collections.emptyList();
            return this;
        }

        public final CallList clear() {
            clearCallAttributes();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            for (Call element : getCallAttributesList()) {
                output.writeMessage(2, element);
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            for (Call element : getCallAttributesList()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, element);
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallList mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 18:
                        Call value = new Call();
                        input.readMessage(value);
                        addCallAttributes(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallList parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallList) new CallList().mergeFrom(data);
        }

        public static CallList parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallList().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Colr extends MessageMicro {
        public static final int ERRORDETAILS_FIELD_NUMBER = 2;
        public static final int PRESENTATION_FIELD_NUMBER = 1;
        private boolean hasErrorDetails;
        private boolean hasPresentation;
        private int presentation_ = 0;
        private SipErrorInfo errorDetails_ = null;
        private int cachedSize = -1;

        public boolean hasPresentation() {
            return this.hasPresentation;
        }

        public int getPresentation() {
            return this.presentation_;
        }

        public Colr setPresentation(int value) {
            this.hasPresentation = true;
            this.presentation_ = value;
            return this;
        }

        public Colr clearPresentation() {
            this.hasPresentation = false;
            this.presentation_ = 0;
            return this;
        }

        public boolean hasErrorDetails() {
            return this.hasErrorDetails;
        }

        public SipErrorInfo getErrorDetails() {
            return this.errorDetails_;
        }

        public Colr setErrorDetails(SipErrorInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasErrorDetails = true;
            this.errorDetails_ = value;
            return this;
        }

        public Colr clearErrorDetails() {
            this.hasErrorDetails = false;
            this.errorDetails_ = null;
            return this;
        }

        public final Colr clear() {
            clearPresentation();
            clearErrorDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasPresentation()) {
                output.writeInt32(1, getPresentation());
            }
            if (hasErrorDetails()) {
                output.writeMessage(2, getErrorDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasPresentation()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getPresentation()) + 0;
            }
            if (hasErrorDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, getErrorDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Colr mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setPresentation(input.readInt32());
                        break;
                    case 18:
                        SipErrorInfo value = new SipErrorInfo();
                        input.readMessage(value);
                        setErrorDetails(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Colr parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Colr) new Colr().mergeFrom(data);
        }

        public static Colr parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Colr().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Dial extends MessageMicro {
        public static final int ADDRESS_FIELD_NUMBER = 1;
        public static final int CALLDETAILS_FIELD_NUMBER = 3;
        public static final int CLIR_FIELD_NUMBER = 2;
        public static final int ISCALLPULL_FIELD_NUMBER = 6;
        public static final int ISCONFERENCEURI_FIELD_NUMBER = 5;
        public static final int ISENCRYPTED_FIELD_NUMBER = 7;
        public static final int PRESENTATION_FIELD_NUMBER = 4;
        private boolean hasAddress;
        private boolean hasCallDetails;
        private boolean hasClir;
        private boolean hasIsCallPull;
        private boolean hasIsConferenceUri;
        private boolean hasIsEncrypted;
        private boolean hasPresentation;
        private String address_ = "";
        private int clir_ = 0;
        private CallDetails callDetails_ = null;
        private int presentation_ = 0;
        private boolean isConferenceUri_ = false;
        private boolean isCallPull_ = false;
        private boolean isEncrypted_ = false;
        private int cachedSize = -1;

        public String getAddress() {
            return this.address_;
        }

        public boolean hasAddress() {
            return this.hasAddress;
        }

        public Dial setAddress(String value) {
            this.hasAddress = true;
            this.address_ = value;
            return this;
        }

        public Dial clearAddress() {
            this.hasAddress = false;
            this.address_ = "";
            return this;
        }

        public int getClir() {
            return this.clir_;
        }

        public boolean hasClir() {
            return this.hasClir;
        }

        public Dial setClir(int value) {
            this.hasClir = true;
            this.clir_ = value;
            return this;
        }

        public Dial clearClir() {
            this.hasClir = false;
            this.clir_ = 0;
            return this;
        }

        public boolean hasCallDetails() {
            return this.hasCallDetails;
        }

        public CallDetails getCallDetails() {
            return this.callDetails_;
        }

        public Dial setCallDetails(CallDetails value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasCallDetails = true;
            this.callDetails_ = value;
            return this;
        }

        public Dial clearCallDetails() {
            this.hasCallDetails = false;
            this.callDetails_ = null;
            return this;
        }

        public boolean hasPresentation() {
            return this.hasPresentation;
        }

        public int getPresentation() {
            return this.presentation_;
        }

        public Dial setPresentation(int value) {
            this.hasPresentation = true;
            this.presentation_ = value;
            return this;
        }

        public Dial clearPresentation() {
            this.hasPresentation = false;
            this.presentation_ = 0;
            return this;
        }

        public boolean getIsConferenceUri() {
            return this.isConferenceUri_;
        }

        public boolean hasIsConferenceUri() {
            return this.hasIsConferenceUri;
        }

        public Dial setIsConferenceUri(boolean value) {
            this.hasIsConferenceUri = true;
            this.isConferenceUri_ = value;
            return this;
        }

        public Dial clearIsConferenceUri() {
            this.hasIsConferenceUri = false;
            this.isConferenceUri_ = false;
            return this;
        }

        public boolean getIsCallPull() {
            return this.isCallPull_;
        }

        public boolean hasIsCallPull() {
            return this.hasIsCallPull;
        }

        public Dial setIsCallPull(boolean value) {
            this.hasIsCallPull = true;
            this.isCallPull_ = value;
            return this;
        }

        public Dial clearIsCallPull() {
            this.hasIsCallPull = false;
            this.isCallPull_ = false;
            return this;
        }

        public boolean getIsEncrypted() {
            return this.isEncrypted_;
        }

        public boolean hasIsEncrypted() {
            return this.hasIsEncrypted;
        }

        public Dial setIsEncrypted(boolean value) {
            this.hasIsEncrypted = true;
            this.isEncrypted_ = value;
            return this;
        }

        public Dial clearIsEncrypted() {
            this.hasIsEncrypted = false;
            this.isEncrypted_ = false;
            return this;
        }

        public final Dial clear() {
            clearAddress();
            clearClir();
            clearCallDetails();
            clearPresentation();
            clearIsConferenceUri();
            clearIsCallPull();
            clearIsEncrypted();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasAddress()) {
                output.writeString(1, getAddress());
            }
            if (hasClir()) {
                output.writeFixed32(2, getClir());
            }
            if (hasCallDetails()) {
                output.writeMessage(3, getCallDetails());
            }
            if (hasPresentation()) {
                output.writeInt32(4, getPresentation());
            }
            if (hasIsConferenceUri()) {
                output.writeBool(5, getIsConferenceUri());
            }
            if (hasIsCallPull()) {
                output.writeBool(6, getIsCallPull());
            }
            if (hasIsEncrypted()) {
                output.writeBool(7, getIsEncrypted());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasAddress()) {
                size = CodedOutputStreamMicro.computeStringSize(1, getAddress()) + 0;
            }
            if (hasClir()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getClir());
            }
            if (hasCallDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, getCallDetails());
            }
            if (hasPresentation()) {
                size += CodedOutputStreamMicro.computeInt32Size(4, getPresentation());
            }
            if (hasIsConferenceUri()) {
                size += CodedOutputStreamMicro.computeBoolSize(5, getIsConferenceUri());
            }
            if (hasIsCallPull()) {
                size += CodedOutputStreamMicro.computeBoolSize(6, getIsCallPull());
            }
            if (hasIsEncrypted()) {
                size += CodedOutputStreamMicro.computeBoolSize(7, getIsEncrypted());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Dial mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setAddress(input.readString());
                        break;
                    case 21:
                        setClir(input.readFixed32());
                        break;
                    case 26:
                        CallDetails value = new CallDetails();
                        input.readMessage(value);
                        setCallDetails(value);
                        break;
                    case 32:
                        setPresentation(input.readInt32());
                        break;
                    case 40:
                        setIsConferenceUri(input.readBool());
                        break;
                    case 48:
                        setIsCallPull(input.readBool());
                        break;
                    case 56:
                        setIsEncrypted(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Dial parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Dial) new Dial().mergeFrom(data);
        }

        public static Dial parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Dial().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Hangup extends MessageMicro {
        public static final int CONF_ID_FIELD_NUMBER = 4;
        public static final int CONN_INDEX_FIELD_NUMBER = 1;
        public static final int CONN_URI_FIELD_NUMBER = 3;
        public static final int FAILCAUSERESPONSE_FIELD_NUMBER = 5;
        public static final int MULTI_PARTY_FIELD_NUMBER = 2;
        private boolean hasConfId;
        private boolean hasConnIndex;
        private boolean hasConnUri;
        private boolean hasFailCauseResponse;
        private boolean hasMultiParty;
        private int connIndex_ = 0;
        private boolean multiParty_ = false;
        private String connUri_ = "";
        private int confId_ = 0;
        private CallFailCauseResponse failCauseResponse_ = null;
        private int cachedSize = -1;

        public int getConnIndex() {
            return this.connIndex_;
        }

        public boolean hasConnIndex() {
            return this.hasConnIndex;
        }

        public Hangup setConnIndex(int value) {
            this.hasConnIndex = true;
            this.connIndex_ = value;
            return this;
        }

        public Hangup clearConnIndex() {
            this.hasConnIndex = false;
            this.connIndex_ = 0;
            return this;
        }

        public boolean getMultiParty() {
            return this.multiParty_;
        }

        public boolean hasMultiParty() {
            return this.hasMultiParty;
        }

        public Hangup setMultiParty(boolean value) {
            this.hasMultiParty = true;
            this.multiParty_ = value;
            return this;
        }

        public Hangup clearMultiParty() {
            this.hasMultiParty = false;
            this.multiParty_ = false;
            return this;
        }

        public String getConnUri() {
            return this.connUri_;
        }

        public boolean hasConnUri() {
            return this.hasConnUri;
        }

        public Hangup setConnUri(String value) {
            this.hasConnUri = true;
            this.connUri_ = value;
            return this;
        }

        public Hangup clearConnUri() {
            this.hasConnUri = false;
            this.connUri_ = "";
            return this;
        }

        public int getConfId() {
            return this.confId_;
        }

        public boolean hasConfId() {
            return this.hasConfId;
        }

        public Hangup setConfId(int value) {
            this.hasConfId = true;
            this.confId_ = value;
            return this;
        }

        public Hangup clearConfId() {
            this.hasConfId = false;
            this.confId_ = 0;
            return this;
        }

        public boolean hasFailCauseResponse() {
            return this.hasFailCauseResponse;
        }

        public CallFailCauseResponse getFailCauseResponse() {
            return this.failCauseResponse_;
        }

        public Hangup setFailCauseResponse(CallFailCauseResponse value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasFailCauseResponse = true;
            this.failCauseResponse_ = value;
            return this;
        }

        public Hangup clearFailCauseResponse() {
            this.hasFailCauseResponse = false;
            this.failCauseResponse_ = null;
            return this;
        }

        public final Hangup clear() {
            clearConnIndex();
            clearMultiParty();
            clearConnUri();
            clearConfId();
            clearFailCauseResponse();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasConnIndex()) {
                output.writeFixed32(1, getConnIndex());
            }
            if (hasMultiParty()) {
                output.writeBool(2, getMultiParty());
            }
            if (hasConnUri()) {
                output.writeString(3, getConnUri());
            }
            if (hasConfId()) {
                output.writeFixed32(4, getConfId());
            }
            if (hasFailCauseResponse()) {
                output.writeMessage(5, getFailCauseResponse());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasConnIndex()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getConnIndex()) + 0;
            }
            if (hasMultiParty()) {
                size += CodedOutputStreamMicro.computeBoolSize(2, getMultiParty());
            }
            if (hasConnUri()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getConnUri());
            }
            if (hasConfId()) {
                size += CodedOutputStreamMicro.computeFixed32Size(4, getConfId());
            }
            if (hasFailCauseResponse()) {
                size += CodedOutputStreamMicro.computeMessageSize(5, getFailCauseResponse());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Hangup mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setConnIndex(input.readFixed32());
                        break;
                    case 16:
                        setMultiParty(input.readBool());
                        break;
                    case 26:
                        setConnUri(input.readString());
                        break;
                    case 37:
                        setConfId(input.readFixed32());
                        break;
                    case 42:
                        CallFailCauseResponse value = new CallFailCauseResponse();
                        input.readMessage(value);
                        setFailCauseResponse(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Hangup parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Hangup) new Hangup().mergeFrom(data);
        }

        public static Hangup parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Hangup().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class DeflectCall extends MessageMicro {
        public static final int NUMBER_FIELD_NUMBER = 2;
        private boolean hasConnIndex;
        private boolean hasNumber;
        private int connIndex_ = 0;
        private String number_ = "";
        private int cachedSize = -1;

        public int getConnIndex() {
            return this.connIndex_;
        }

        public boolean hasConnIndex() {
            return this.hasConnIndex;
        }

        public DeflectCall setConnIndex(int value) {
            this.hasConnIndex = true;
            this.connIndex_ = value;
            return this;
        }

        public DeflectCall clearConnIndex() {
            this.hasConnIndex = false;
            this.connIndex_ = 0;
            return this;
        }

        public String getNumber() {
            return this.number_;
        }

        public boolean hasNumber() {
            return this.hasNumber;
        }

        public DeflectCall setNumber(String value) {
            this.hasNumber = true;
            this.number_ = value;
            return this;
        }

        public DeflectCall clearNumber() {
            this.hasNumber = false;
            this.number_ = "";
            return this;
        }

        public final DeflectCall clear() {
            clearConnIndex();
            clearNumber();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasConnIndex()) {
                output.writeFixed32(1, getConnIndex());
            }
            if (hasNumber()) {
                output.writeString(2, getNumber());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasConnIndex()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getConnIndex()) + 0;
            }
            if (hasNumber()) {
                size += CodedOutputStreamMicro.computeStringSize(2, getNumber());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public DeflectCall mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setConnIndex(input.readFixed32());
                        break;
                    case 18:
                        setNumber(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static DeflectCall parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (DeflectCall) new DeflectCall().mergeFrom(data);
        }

        public static DeflectCall parseFrom(CodedInputStreamMicro input) throws IOException {
            return new DeflectCall().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Clir extends MessageMicro {
        public static final int PARAM_M_FIELD_NUMBER = 1;
        public static final int PARAM_N_FIELD_NUMBER = 2;
        private boolean hasParamM;
        private boolean hasParamN;
        private int paramM_ = 0;
        private int paramN_ = 0;
        private int cachedSize = -1;

        public int getParamM() {
            return this.paramM_;
        }

        public boolean hasParamM() {
            return this.hasParamM;
        }

        public Clir setParamM(int value) {
            this.hasParamM = true;
            this.paramM_ = value;
            return this;
        }

        public Clir clearParamM() {
            this.hasParamM = false;
            this.paramM_ = 0;
            return this;
        }

        public int getParamN() {
            return this.paramN_;
        }

        public boolean hasParamN() {
            return this.hasParamN;
        }

        public Clir setParamN(int value) {
            this.hasParamN = true;
            this.paramN_ = value;
            return this;
        }

        public Clir clearParamN() {
            this.hasParamN = false;
            this.paramN_ = 0;
            return this;
        }

        public final Clir clear() {
            clearParamM();
            clearParamN();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasParamM()) {
                output.writeFixed32(1, getParamM());
            }
            if (hasParamN()) {
                output.writeFixed32(2, getParamN());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasParamM()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getParamM()) + 0;
            }
            if (hasParamN()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getParamN());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Clir mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setParamM(input.readFixed32());
                        break;
                    case 21:
                        setParamN(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Clir parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Clir) new Clir().mergeFrom(data);
        }

        public static Clir parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Clir().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Answer extends MessageMicro {
        public static final int CALL_TYPE_FIELD_NUMBER = 1;
        public static final int PRESENTATION_FIELD_NUMBER = 2;
        public static final int RTTMODE_FIELD_NUMBER = 3;
        private boolean hasCallType;
        private boolean hasPresentation;
        private boolean hasRttMode;
        private int callType_ = 0;
        private int presentation_ = 0;
        private int rttMode_ = 0;
        private int cachedSize = -1;

        public boolean hasCallType() {
            return this.hasCallType;
        }

        public int getCallType() {
            return this.callType_;
        }

        public Answer setCallType(int value) {
            this.hasCallType = true;
            this.callType_ = value;
            return this;
        }

        public Answer clearCallType() {
            this.hasCallType = false;
            this.callType_ = 0;
            return this;
        }

        public boolean hasPresentation() {
            return this.hasPresentation;
        }

        public int getPresentation() {
            return this.presentation_;
        }

        public Answer setPresentation(int value) {
            this.hasPresentation = true;
            this.presentation_ = value;
            return this;
        }

        public Answer clearPresentation() {
            this.hasPresentation = false;
            this.presentation_ = 0;
            return this;
        }

        public boolean hasRttMode() {
            return this.hasRttMode;
        }

        public int getRttMode() {
            return this.rttMode_;
        }

        public Answer setRttMode(int value) {
            this.hasRttMode = true;
            this.rttMode_ = value;
            return this;
        }

        public Answer clearRttMode() {
            this.hasRttMode = false;
            this.rttMode_ = 0;
            return this;
        }

        public final Answer clear() {
            clearCallType();
            clearPresentation();
            clearRttMode();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallType()) {
                output.writeInt32(1, getCallType());
            }
            if (hasPresentation()) {
                output.writeInt32(2, getPresentation());
            }
            if (hasRttMode()) {
                output.writeInt32(3, getRttMode());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getCallType()) + 0;
            }
            if (hasPresentation()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getPresentation());
            }
            if (hasRttMode()) {
                size += CodedOutputStreamMicro.computeInt32Size(3, getRttMode());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Answer mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setCallType(input.readInt32());
                        break;
                    case 16:
                        setPresentation(input.readInt32());
                        break;
                    case 24:
                        setRttMode(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Answer parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Answer) new Answer().mergeFrom(data);
        }

        public static Answer parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Answer().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SwitchWaitingOrHoldingAndActive extends MessageMicro {
        public static final int CALL_TYPE_FIELD_NUMBER = 1;
        private boolean hasCallType;
        private int callType_ = 0;
        private int cachedSize = -1;

        public boolean hasCallType() {
            return this.hasCallType;
        }

        public int getCallType() {
            return this.callType_;
        }

        public SwitchWaitingOrHoldingAndActive setCallType(int value) {
            this.hasCallType = true;
            this.callType_ = value;
            return this;
        }

        public SwitchWaitingOrHoldingAndActive clearCallType() {
            this.hasCallType = false;
            this.callType_ = 0;
            return this;
        }

        public final SwitchWaitingOrHoldingAndActive clear() {
            clearCallType();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallType()) {
                output.writeInt32(1, getCallType());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getCallType()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SwitchWaitingOrHoldingAndActive mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setCallType(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SwitchWaitingOrHoldingAndActive parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SwitchWaitingOrHoldingAndActive) new SwitchWaitingOrHoldingAndActive().mergeFrom(data);
        }

        public static SwitchWaitingOrHoldingAndActive parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SwitchWaitingOrHoldingAndActive().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Mute extends MessageMicro {
        public static final int MUTE_FLAG_FIELD_NUMBER = 1;
        private boolean hasMuteFlag;
        private boolean muteFlag_ = false;
        private int cachedSize = -1;

        public boolean getMuteFlag() {
            return this.muteFlag_;
        }

        public boolean hasMuteFlag() {
            return this.hasMuteFlag;
        }

        public Mute setMuteFlag(boolean value) {
            this.hasMuteFlag = true;
            this.muteFlag_ = value;
            return this;
        }

        public Mute clearMuteFlag() {
            this.hasMuteFlag = false;
            this.muteFlag_ = false;
            return this;
        }

        public final Mute clear() {
            clearMuteFlag();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasMuteFlag()) {
                output.writeBool(1, getMuteFlag());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasMuteFlag()) {
                size = CodedOutputStreamMicro.computeBoolSize(1, getMuteFlag()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Mute mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setMuteFlag(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Mute parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Mute) new Mute().mergeFrom(data);
        }

        public static Mute parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Mute().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Dtmf extends MessageMicro {
        public static final int DTMF_FIELD_NUMBER = 1;
        private boolean hasDtmf;
        private String dtmf_ = "";
        private int cachedSize = -1;

        public String getDtmf() {
            return this.dtmf_;
        }

        public boolean hasDtmf() {
            return this.hasDtmf;
        }

        public Dtmf setDtmf(String value) {
            this.hasDtmf = true;
            this.dtmf_ = value;
            return this;
        }

        public Dtmf clearDtmf() {
            this.hasDtmf = false;
            this.dtmf_ = "";
            return this;
        }

        public final Dtmf clear() {
            clearDtmf();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasDtmf()) {
                output.writeString(1, getDtmf());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasDtmf()) {
                size = CodedOutputStreamMicro.computeStringSize(1, getDtmf()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Dtmf mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setDtmf(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Dtmf parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Dtmf) new Dtmf().mergeFrom(data);
        }

        public static Dtmf parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Dtmf().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Registration extends MessageMicro {
        public static final int ERRORCODE_FIELD_NUMBER = 2;
        public static final int ERRORMESSAGE_FIELD_NUMBER = 3;
        public static final int NOT_REGISTERED = 2;
        public static final int PASSOCIATEDURIS_FIELD_NUMBER = 5;
        public static final int RADIOTECH_FIELD_NUMBER = 4;
        public static final int REGISTERED = 1;
        public static final int REGISTERING = 3;
        public static final int STATE_FIELD_NUMBER = 1;
        private boolean hasErrorCode;
        private boolean hasErrorMessage;
        private boolean hasPAssociatedUris;
        private boolean hasRadioTech;
        private boolean hasState;
        private int state_ = 1;
        private int errorCode_ = 0;
        private String errorMessage_ = "";
        private int radioTech_ = -1;
        private String pAssociatedUris_ = "";
        private int cachedSize = -1;

        public boolean hasState() {
            return this.hasState;
        }

        public int getState() {
            return this.state_;
        }

        public Registration setState(int value) {
            this.hasState = true;
            this.state_ = value;
            return this;
        }

        public Registration clearState() {
            this.hasState = false;
            this.state_ = 1;
            return this;
        }

        public int getErrorCode() {
            return this.errorCode_;
        }

        public boolean hasErrorCode() {
            return this.hasErrorCode;
        }

        public Registration setErrorCode(int value) {
            this.hasErrorCode = true;
            this.errorCode_ = value;
            return this;
        }

        public Registration clearErrorCode() {
            this.hasErrorCode = false;
            this.errorCode_ = 0;
            return this;
        }

        public String getErrorMessage() {
            return this.errorMessage_;
        }

        public boolean hasErrorMessage() {
            return this.hasErrorMessage;
        }

        public Registration setErrorMessage(String value) {
            this.hasErrorMessage = true;
            this.errorMessage_ = value;
            return this;
        }

        public Registration clearErrorMessage() {
            this.hasErrorMessage = false;
            this.errorMessage_ = "";
            return this;
        }

        public boolean hasRadioTech() {
            return this.hasRadioTech;
        }

        public int getRadioTech() {
            return this.radioTech_;
        }

        public Registration setRadioTech(int value) {
            this.hasRadioTech = true;
            this.radioTech_ = value;
            return this;
        }

        public Registration clearRadioTech() {
            this.hasRadioTech = false;
            this.radioTech_ = -1;
            return this;
        }

        public String getPAssociatedUris() {
            return this.pAssociatedUris_;
        }

        public boolean hasPAssociatedUris() {
            return this.hasPAssociatedUris;
        }

        public Registration setPAssociatedUris(String value) {
            this.hasPAssociatedUris = true;
            this.pAssociatedUris_ = value;
            return this;
        }

        public Registration clearPAssociatedUris() {
            this.hasPAssociatedUris = false;
            this.pAssociatedUris_ = "";
            return this;
        }

        public final Registration clear() {
            clearState();
            clearErrorCode();
            clearErrorMessage();
            clearRadioTech();
            clearPAssociatedUris();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasState()) {
                output.writeInt32(1, getState());
            }
            if (hasErrorCode()) {
                output.writeFixed32(2, getErrorCode());
            }
            if (hasErrorMessage()) {
                output.writeString(3, getErrorMessage());
            }
            if (hasRadioTech()) {
                output.writeInt32(4, getRadioTech());
            }
            if (hasPAssociatedUris()) {
                output.writeString(5, getPAssociatedUris());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasState()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getState()) + 0;
            }
            if (hasErrorCode()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getErrorCode());
            }
            if (hasErrorMessage()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getErrorMessage());
            }
            if (hasRadioTech()) {
                size += CodedOutputStreamMicro.computeInt32Size(4, getRadioTech());
            }
            if (hasPAssociatedUris()) {
                size += CodedOutputStreamMicro.computeStringSize(5, getPAssociatedUris());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Registration mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setState(input.readInt32());
                        break;
                    case 21:
                        setErrorCode(input.readFixed32());
                        break;
                    case 26:
                        setErrorMessage(input.readString());
                        break;
                    case 32:
                        setRadioTech(input.readInt32());
                        break;
                    case 42:
                        setPAssociatedUris(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Registration parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Registration) new Registration().mergeFrom(data);
        }

        public static Registration parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Registration().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class RingBackTone extends MessageMicro {
        public static final int FLAG_FIELD_NUMBER = 1;
        public static final int START = 1;
        public static final int STOP = 0;
        private boolean hasFlag;
        private int flag_ = 0;
        private int cachedSize = -1;

        public boolean hasFlag() {
            return this.hasFlag;
        }

        public int getFlag() {
            return this.flag_;
        }

        public RingBackTone setFlag(int value) {
            this.hasFlag = true;
            this.flag_ = value;
            return this;
        }

        public RingBackTone clearFlag() {
            this.hasFlag = false;
            this.flag_ = 0;
            return this;
        }

        public final RingBackTone clear() {
            clearFlag();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasFlag()) {
                output.writeInt32(1, getFlag());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasFlag()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getFlag()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RingBackTone mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setFlag(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static RingBackTone parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RingBackTone) new RingBackTone().mergeFrom(data);
        }

        public static RingBackTone parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RingBackTone().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class IFConnected extends MessageMicro {
        public static final int IF_VERSION_FIELD_NUMBER = 1;
        public static final int VERSION_0 = 0;
        private boolean hasIfVersion;
        private int ifVersion_ = 0;
        private int cachedSize = -1;

        public boolean hasIfVersion() {
            return this.hasIfVersion;
        }

        public int getIfVersion() {
            return this.ifVersion_;
        }

        public IFConnected setIfVersion(int value) {
            this.hasIfVersion = true;
            this.ifVersion_ = value;
            return this;
        }

        public IFConnected clearIfVersion() {
            this.hasIfVersion = false;
            this.ifVersion_ = 0;
            return this;
        }

        public final IFConnected clear() {
            clearIfVersion();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasIfVersion()) {
                output.writeInt32(1, getIfVersion());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasIfVersion()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getIfVersion()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public IFConnected mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setIfVersion(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static IFConnected parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (IFConnected) new IFConnected().mergeFrom(data);
        }

        public static IFConnected parseFrom(CodedInputStreamMicro input) throws IOException {
            return new IFConnected().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class LastFailCause extends MessageMicro {
        public static final int CAUSE_FIELD_NUMBER = 1;
        private boolean hasCause;
        private int cause_ = 1;
        private int cachedSize = -1;

        public boolean hasCause() {
            return this.hasCause;
        }

        public int getCause() {
            return this.cause_;
        }

        public LastFailCause setCause(int value) {
            this.hasCause = true;
            this.cause_ = value;
            return this;
        }

        public LastFailCause clearCause() {
            this.hasCause = false;
            this.cause_ = 1;
            return this;
        }

        public final LastFailCause clear() {
            clearCause();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCause()) {
                output.writeInt32(1, getCause());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCause()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getCause()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public LastFailCause mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setCause(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static LastFailCause parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (LastFailCause) new LastFailCause().mergeFrom(data);
        }

        public static LastFailCause parseFrom(CodedInputStreamMicro input) throws IOException {
            return new LastFailCause().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Extra extends MessageMicro {
        public static final int EXTRAINFO_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private boolean hasExtraInfo;
        private boolean hasType;
        private int type_ = 1;
        private ByteStringMicro extraInfo_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public Extra setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public Extra clearType() {
            this.hasType = false;
            this.type_ = 1;
            return this;
        }

        public ByteStringMicro getExtraInfo() {
            return this.extraInfo_;
        }

        public boolean hasExtraInfo() {
            return this.hasExtraInfo;
        }

        public Extra setExtraInfo(ByteStringMicro value) {
            this.hasExtraInfo = true;
            this.extraInfo_ = value;
            return this;
        }

        public Extra clearExtraInfo() {
            this.hasExtraInfo = false;
            this.extraInfo_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final Extra clear() {
            clearType();
            clearExtraInfo();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasType()) {
                output.writeInt32(1, getType());
            }
            if (hasExtraInfo()) {
                output.writeBytes(2, getExtraInfo());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getType()) + 0;
            }
            if (hasExtraInfo()) {
                size += CodedOutputStreamMicro.computeBytesSize(2, getExtraInfo());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Extra mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setType(input.readInt32());
                        break;
                    case 18:
                        setExtraInfo(input.readBytes());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Extra parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Extra) new Extra().mergeFrom(data);
        }

        public static Extra parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Extra().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Handover extends MessageMicro {
        public static final int ERRORCODE_FIELD_NUMBER = 5;
        public static final int ERRORMESSAGE_FIELD_NUMBER = 6;
        public static final int HOEXTRA_FIELD_NUMBER = 4;
        public static final int SRCTECH_FIELD_NUMBER = 2;
        public static final int TARGETTECH_FIELD_NUMBER = 3;
        public static final int TYPE_FIELD_NUMBER = 1;
        private boolean hasErrorCode;
        private boolean hasErrorMessage;
        private boolean hasHoExtra;
        private boolean hasSrcTech;
        private boolean hasTargetTech;
        private boolean hasType;
        private int type_ = 0;
        private int srcTech_ = -1;
        private int targetTech_ = -1;
        private Extra hoExtra_ = null;
        private String errorCode_ = "";
        private String errorMessage_ = "";
        private int cachedSize = -1;

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public Handover setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public Handover clearType() {
            this.hasType = false;
            this.type_ = 0;
            return this;
        }

        public boolean hasSrcTech() {
            return this.hasSrcTech;
        }

        public int getSrcTech() {
            return this.srcTech_;
        }

        public Handover setSrcTech(int value) {
            this.hasSrcTech = true;
            this.srcTech_ = value;
            return this;
        }

        public Handover clearSrcTech() {
            this.hasSrcTech = false;
            this.srcTech_ = -1;
            return this;
        }

        public boolean hasTargetTech() {
            return this.hasTargetTech;
        }

        public int getTargetTech() {
            return this.targetTech_;
        }

        public Handover setTargetTech(int value) {
            this.hasTargetTech = true;
            this.targetTech_ = value;
            return this;
        }

        public Handover clearTargetTech() {
            this.hasTargetTech = false;
            this.targetTech_ = -1;
            return this;
        }

        public boolean hasHoExtra() {
            return this.hasHoExtra;
        }

        public Extra getHoExtra() {
            return this.hoExtra_;
        }

        public Handover setHoExtra(Extra value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasHoExtra = true;
            this.hoExtra_ = value;
            return this;
        }

        public Handover clearHoExtra() {
            this.hasHoExtra = false;
            this.hoExtra_ = null;
            return this;
        }

        public String getErrorCode() {
            return this.errorCode_;
        }

        public boolean hasErrorCode() {
            return this.hasErrorCode;
        }

        public Handover setErrorCode(String value) {
            this.hasErrorCode = true;
            this.errorCode_ = value;
            return this;
        }

        public Handover clearErrorCode() {
            this.hasErrorCode = false;
            this.errorCode_ = "";
            return this;
        }

        public String getErrorMessage() {
            return this.errorMessage_;
        }

        public boolean hasErrorMessage() {
            return this.hasErrorMessage;
        }

        public Handover setErrorMessage(String value) {
            this.hasErrorMessage = true;
            this.errorMessage_ = value;
            return this;
        }

        public Handover clearErrorMessage() {
            this.hasErrorMessage = false;
            this.errorMessage_ = "";
            return this;
        }

        public final Handover clear() {
            clearType();
            clearSrcTech();
            clearTargetTech();
            clearHoExtra();
            clearErrorCode();
            clearErrorMessage();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasType()) {
                output.writeInt32(1, getType());
            }
            if (hasSrcTech()) {
                output.writeInt32(2, getSrcTech());
            }
            if (hasTargetTech()) {
                output.writeInt32(3, getTargetTech());
            }
            if (hasHoExtra()) {
                output.writeMessage(4, getHoExtra());
            }
            if (hasErrorCode()) {
                output.writeString(5, getErrorCode());
            }
            if (hasErrorMessage()) {
                output.writeString(6, getErrorMessage());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getType()) + 0;
            }
            if (hasSrcTech()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getSrcTech());
            }
            if (hasTargetTech()) {
                size += CodedOutputStreamMicro.computeInt32Size(3, getTargetTech());
            }
            if (hasHoExtra()) {
                size += CodedOutputStreamMicro.computeMessageSize(4, getHoExtra());
            }
            if (hasErrorCode()) {
                size += CodedOutputStreamMicro.computeStringSize(5, getErrorCode());
            }
            if (hasErrorMessage()) {
                size += CodedOutputStreamMicro.computeStringSize(6, getErrorMessage());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Handover mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setType(input.readInt32());
                        break;
                    case 16:
                        setSrcTech(input.readInt32());
                        break;
                    case 24:
                        setTargetTech(input.readInt32());
                        break;
                    case 34:
                        Extra value = new Extra();
                        input.readMessage(value);
                        setHoExtra(value);
                        break;
                    case 42:
                        setErrorCode(input.readString());
                        break;
                    case 50:
                        setErrorMessage(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Handover parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Handover) new Handover().mergeFrom(data);
        }

        public static Handover parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Handover().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class TtyNotify extends MessageMicro {
        public static final int MODE_FIELD_NUMBER = 1;
        public static final int USERDATA_FIELD_NUMBER = 2;
        private boolean hasMode;
        private boolean hasUserData;
        private int mode_ = 0;
        private ByteStringMicro userData_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public boolean hasMode() {
            return this.hasMode;
        }

        public int getMode() {
            return this.mode_;
        }

        public TtyNotify setMode(int value) {
            this.hasMode = true;
            this.mode_ = value;
            return this;
        }

        public TtyNotify clearMode() {
            this.hasMode = false;
            this.mode_ = 0;
            return this;
        }

        public ByteStringMicro getUserData() {
            return this.userData_;
        }

        public boolean hasUserData() {
            return this.hasUserData;
        }

        public TtyNotify setUserData(ByteStringMicro value) {
            this.hasUserData = true;
            this.userData_ = value;
            return this;
        }

        public TtyNotify clearUserData() {
            this.hasUserData = false;
            this.userData_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final TtyNotify clear() {
            clearMode();
            clearUserData();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasMode()) {
                output.writeInt32(1, getMode());
            }
            if (hasUserData()) {
                output.writeBytes(2, getUserData());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasMode()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getMode()) + 0;
            }
            if (hasUserData()) {
                size += CodedOutputStreamMicro.computeBytesSize(2, getUserData());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public TtyNotify mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setMode(input.readInt32());
                        break;
                    case 18:
                        setUserData(input.readBytes());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static TtyNotify parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (TtyNotify) new TtyNotify().mergeFrom(data);
        }

        public static TtyNotify parseFrom(CodedInputStreamMicro input) throws IOException {
            return new TtyNotify().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class RadioStateChanged extends MessageMicro {
        public static final int STATE_FIELD_NUMBER = 1;
        private boolean hasState;
        private int state_ = 0;
        private int cachedSize = -1;

        public boolean hasState() {
            return this.hasState;
        }

        public int getState() {
            return this.state_;
        }

        public RadioStateChanged setState(int value) {
            this.hasState = true;
            this.state_ = value;
            return this;
        }

        public RadioStateChanged clearState() {
            this.hasState = false;
            this.state_ = 0;
            return this;
        }

        public final RadioStateChanged clear() {
            clearState();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasState()) {
                output.writeInt32(1, getState());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasState()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getState()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RadioStateChanged mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setState(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static RadioStateChanged parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RadioStateChanged) new RadioStateChanged().mergeFrom(data);
        }

        public static RadioStateChanged parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RadioStateChanged().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ClipProvisionStatus extends MessageMicro {
        public static final int CLIP_STATUS_FIELD_NUMBER = 1;
        public static final int ERRORDETAILS_FIELD_NUMBER = 2;
        private boolean hasClipStatus;
        private boolean hasErrorDetails;
        private int clipStatus_ = 0;
        private SipErrorInfo errorDetails_ = null;
        private int cachedSize = -1;

        public boolean hasClipStatus() {
            return this.hasClipStatus;
        }

        public int getClipStatus() {
            return this.clipStatus_;
        }

        public ClipProvisionStatus setClipStatus(int value) {
            this.hasClipStatus = true;
            this.clipStatus_ = value;
            return this;
        }

        public ClipProvisionStatus clearClipStatus() {
            this.hasClipStatus = false;
            this.clipStatus_ = 0;
            return this;
        }

        public boolean hasErrorDetails() {
            return this.hasErrorDetails;
        }

        public SipErrorInfo getErrorDetails() {
            return this.errorDetails_;
        }

        public ClipProvisionStatus setErrorDetails(SipErrorInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasErrorDetails = true;
            this.errorDetails_ = value;
            return this;
        }

        public ClipProvisionStatus clearErrorDetails() {
            this.hasErrorDetails = false;
            this.errorDetails_ = null;
            return this;
        }

        public final ClipProvisionStatus clear() {
            clearClipStatus();
            clearErrorDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasClipStatus()) {
                output.writeInt32(1, getClipStatus());
            }
            if (hasErrorDetails()) {
                output.writeMessage(2, getErrorDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasClipStatus()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getClipStatus()) + 0;
            }
            if (hasErrorDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, getErrorDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ClipProvisionStatus mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setClipStatus(input.readInt32());
                        break;
                    case 18:
                        SipErrorInfo value = new SipErrorInfo();
                        input.readMessage(value);
                        setErrorDetails(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ClipProvisionStatus parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ClipProvisionStatus) new ClipProvisionStatus().mergeFrom(data);
        }

        public static ClipProvisionStatus parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ClipProvisionStatus().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ServiceClass extends MessageMicro {
        public static final int SERVICE_CLASS_FIELD_NUMBER = 1;
        private boolean hasServiceClass;
        private int serviceClass_ = 0;
        private int cachedSize = -1;

        public int getServiceClass() {
            return this.serviceClass_;
        }

        public boolean hasServiceClass() {
            return this.hasServiceClass;
        }

        public ServiceClass setServiceClass(int value) {
            this.hasServiceClass = true;
            this.serviceClass_ = value;
            return this;
        }

        public ServiceClass clearServiceClass() {
            this.hasServiceClass = false;
            this.serviceClass_ = 0;
            return this;
        }

        public final ServiceClass clear() {
            clearServiceClass();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasServiceClass()) {
                output.writeFixed32(1, getServiceClass());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasServiceClass()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getServiceClass()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ServiceClass mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setServiceClass(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ServiceClass parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ServiceClass) new ServiceClass().mergeFrom(data);
        }

        public static ServiceClass parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ServiceClass().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CbNumList extends MessageMicro {
        public static final int NUMBER_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 1;
        private boolean hasNumber;
        private boolean hasStatus;
        private int status_ = 0;
        private String number_ = "";
        private int cachedSize = -1;

        public boolean hasStatus() {
            return this.hasStatus;
        }

        public int getStatus() {
            return this.status_;
        }

        public CbNumList setStatus(int value) {
            this.hasStatus = true;
            this.status_ = value;
            return this;
        }

        public CbNumList clearStatus() {
            this.hasStatus = false;
            this.status_ = 0;
            return this;
        }

        public String getNumber() {
            return this.number_;
        }

        public boolean hasNumber() {
            return this.hasNumber;
        }

        public CbNumList setNumber(String value) {
            this.hasNumber = true;
            this.number_ = value;
            return this;
        }

        public CbNumList clearNumber() {
            this.hasNumber = false;
            this.number_ = "";
            return this;
        }

        public final CbNumList clear() {
            clearStatus();
            clearNumber();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasStatus()) {
                output.writeInt32(1, getStatus());
            }
            if (hasNumber()) {
                output.writeString(2, getNumber());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasStatus()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getStatus()) + 0;
            }
            if (hasNumber()) {
                size += CodedOutputStreamMicro.computeStringSize(2, getNumber());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CbNumList mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setStatus(input.readInt32());
                        break;
                    case 18:
                        setNumber(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CbNumList parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CbNumList) new CbNumList().mergeFrom(data);
        }

        public static CbNumList parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CbNumList().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CbNumListType extends MessageMicro {
        public static final int CB_NUM_LIST_FIELD_NUMBER = 2;
        public static final int SERVICECLASS_FIELD_NUMBER = 1;
        private boolean hasServiceClass;
        private ServiceClass serviceClass_ = null;
        private List<CbNumList> cbNumList_ = Collections.emptyList();
        private int cachedSize = -1;

        public boolean hasServiceClass() {
            return this.hasServiceClass;
        }

        public ServiceClass getServiceClass() {
            return this.serviceClass_;
        }

        public CbNumListType setServiceClass(ServiceClass value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasServiceClass = true;
            this.serviceClass_ = value;
            return this;
        }

        public CbNumListType clearServiceClass() {
            this.hasServiceClass = false;
            this.serviceClass_ = null;
            return this;
        }

        public List<CbNumList> getCbNumListList() {
            return this.cbNumList_;
        }

        public int getCbNumListCount() {
            return this.cbNumList_.size();
        }

        public CbNumList getCbNumList(int index) {
            return this.cbNumList_.get(index);
        }

        public CbNumListType setCbNumList(int index, CbNumList value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.cbNumList_.set(index, value);
            return this;
        }

        public CbNumListType addCbNumList(CbNumList value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.cbNumList_.isEmpty()) {
                this.cbNumList_ = new ArrayList();
            }
            this.cbNumList_.add(value);
            return this;
        }

        public CbNumListType clearCbNumList() {
            this.cbNumList_ = Collections.emptyList();
            return this;
        }

        public final CbNumListType clear() {
            clearServiceClass();
            clearCbNumList();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasServiceClass()) {
                output.writeMessage(1, getServiceClass());
            }
            for (CbNumList element : getCbNumListList()) {
                output.writeMessage(2, element);
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasServiceClass()) {
                size = CodedOutputStreamMicro.computeMessageSize(1, getServiceClass()) + 0;
            }
            for (CbNumList element : getCbNumListList()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, element);
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CbNumListType mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        ServiceClass value = new ServiceClass();
                        input.readMessage(value);
                        setServiceClass(value);
                        break;
                    case 18:
                        CbNumList value2 = new CbNumList();
                        input.readMessage(value2);
                        addCbNumList(value2);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CbNumListType parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CbNumListType) new CbNumListType().mergeFrom(data);
        }

        public static CbNumListType parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CbNumListType().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallWaitingInfo extends MessageMicro {
        public static final int ERRORDETAILS_FIELD_NUMBER = 3;
        public static final int SERVICE_CLASS_FIELD_NUMBER = 2;
        public static final int SERVICE_STATUS_FIELD_NUMBER = 1;
        private boolean hasErrorDetails;
        private boolean hasServiceClass;
        private boolean hasServiceStatus;
        private int serviceStatus_ = 0;
        private ServiceClass serviceClass_ = null;
        private SipErrorInfo errorDetails_ = null;
        private int cachedSize = -1;

        public boolean hasServiceStatus() {
            return this.hasServiceStatus;
        }

        public int getServiceStatus() {
            return this.serviceStatus_;
        }

        public CallWaitingInfo setServiceStatus(int value) {
            this.hasServiceStatus = true;
            this.serviceStatus_ = value;
            return this;
        }

        public CallWaitingInfo clearServiceStatus() {
            this.hasServiceStatus = false;
            this.serviceStatus_ = 0;
            return this;
        }

        public boolean hasServiceClass() {
            return this.hasServiceClass;
        }

        public ServiceClass getServiceClass() {
            return this.serviceClass_;
        }

        public CallWaitingInfo setServiceClass(ServiceClass value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasServiceClass = true;
            this.serviceClass_ = value;
            return this;
        }

        public CallWaitingInfo clearServiceClass() {
            this.hasServiceClass = false;
            this.serviceClass_ = null;
            return this;
        }

        public boolean hasErrorDetails() {
            return this.hasErrorDetails;
        }

        public SipErrorInfo getErrorDetails() {
            return this.errorDetails_;
        }

        public CallWaitingInfo setErrorDetails(SipErrorInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasErrorDetails = true;
            this.errorDetails_ = value;
            return this;
        }

        public CallWaitingInfo clearErrorDetails() {
            this.hasErrorDetails = false;
            this.errorDetails_ = null;
            return this;
        }

        public final CallWaitingInfo clear() {
            clearServiceStatus();
            clearServiceClass();
            clearErrorDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasServiceStatus()) {
                output.writeInt32(1, getServiceStatus());
            }
            if (hasServiceClass()) {
                output.writeMessage(2, getServiceClass());
            }
            if (hasErrorDetails()) {
                output.writeMessage(3, getErrorDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasServiceStatus()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getServiceStatus()) + 0;
            }
            if (hasServiceClass()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, getServiceClass());
            }
            if (hasErrorDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, getErrorDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallWaitingInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setServiceStatus(input.readInt32());
                        break;
                    case 18:
                        ServiceClass value = new ServiceClass();
                        input.readMessage(value);
                        setServiceClass(value);
                        break;
                    case 26:
                        SipErrorInfo value2 = new SipErrorInfo();
                        input.readMessage(value2);
                        setErrorDetails(value2);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallWaitingInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallWaitingInfo) new CallWaitingInfo().mergeFrom(data);
        }

        public static CallWaitingInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallWaitingInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallForwardInfoList extends MessageMicro {
        public static final int ERRORDETAILS_FIELD_NUMBER = 3;
        public static final int INFO_FIELD_NUMBER = 2;
        private boolean hasErrorDetails;
        private List<CallForwardInfo> info_ = Collections.emptyList();
        private SipErrorInfo errorDetails_ = null;
        private int cachedSize = -1;

        /* loaded from: classes.dex */
        public static final class CallForwardInfo extends MessageMicro {
            public static final int CALLFWDTIMEREND_FIELD_NUMBER = 8;
            public static final int CALLFWDTIMERSTART_FIELD_NUMBER = 7;
            public static final int NUMBER_FIELD_NUMBER = 5;
            public static final int REASON_FIELD_NUMBER = 2;
            public static final int SERVICE_CLASS_FIELD_NUMBER = 3;
            public static final int STATUS_FIELD_NUMBER = 1;
            public static final int TIME_SECONDS_FIELD_NUMBER = 6;
            public static final int TOA_FIELD_NUMBER = 4;
            private boolean hasCallFwdTimerEnd;
            private boolean hasCallFwdTimerStart;
            private boolean hasNumber;
            private boolean hasReason;
            private boolean hasServiceClass;
            private boolean hasStatus;
            private boolean hasTimeSeconds;
            private boolean hasToa;
            private int status_ = 0;
            private int reason_ = 0;
            private int serviceClass_ = 0;
            private int toa_ = 0;
            private String number_ = "";
            private int timeSeconds_ = 0;
            private CallFwdTimerInfo callFwdTimerStart_ = null;
            private CallFwdTimerInfo callFwdTimerEnd_ = null;
            private int cachedSize = -1;

            public int getStatus() {
                return this.status_;
            }

            public boolean hasStatus() {
                return this.hasStatus;
            }

            public CallForwardInfo setStatus(int value) {
                this.hasStatus = true;
                this.status_ = value;
                return this;
            }

            public CallForwardInfo clearStatus() {
                this.hasStatus = false;
                this.status_ = 0;
                return this;
            }

            public int getReason() {
                return this.reason_;
            }

            public boolean hasReason() {
                return this.hasReason;
            }

            public CallForwardInfo setReason(int value) {
                this.hasReason = true;
                this.reason_ = value;
                return this;
            }

            public CallForwardInfo clearReason() {
                this.hasReason = false;
                this.reason_ = 0;
                return this;
            }

            public int getServiceClass() {
                return this.serviceClass_;
            }

            public boolean hasServiceClass() {
                return this.hasServiceClass;
            }

            public CallForwardInfo setServiceClass(int value) {
                this.hasServiceClass = true;
                this.serviceClass_ = value;
                return this;
            }

            public CallForwardInfo clearServiceClass() {
                this.hasServiceClass = false;
                this.serviceClass_ = 0;
                return this;
            }

            public int getToa() {
                return this.toa_;
            }

            public boolean hasToa() {
                return this.hasToa;
            }

            public CallForwardInfo setToa(int value) {
                this.hasToa = true;
                this.toa_ = value;
                return this;
            }

            public CallForwardInfo clearToa() {
                this.hasToa = false;
                this.toa_ = 0;
                return this;
            }

            public String getNumber() {
                return this.number_;
            }

            public boolean hasNumber() {
                return this.hasNumber;
            }

            public CallForwardInfo setNumber(String value) {
                this.hasNumber = true;
                this.number_ = value;
                return this;
            }

            public CallForwardInfo clearNumber() {
                this.hasNumber = false;
                this.number_ = "";
                return this;
            }

            public int getTimeSeconds() {
                return this.timeSeconds_;
            }

            public boolean hasTimeSeconds() {
                return this.hasTimeSeconds;
            }

            public CallForwardInfo setTimeSeconds(int value) {
                this.hasTimeSeconds = true;
                this.timeSeconds_ = value;
                return this;
            }

            public CallForwardInfo clearTimeSeconds() {
                this.hasTimeSeconds = false;
                this.timeSeconds_ = 0;
                return this;
            }

            public boolean hasCallFwdTimerStart() {
                return this.hasCallFwdTimerStart;
            }

            public CallFwdTimerInfo getCallFwdTimerStart() {
                return this.callFwdTimerStart_;
            }

            public CallForwardInfo setCallFwdTimerStart(CallFwdTimerInfo value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.hasCallFwdTimerStart = true;
                this.callFwdTimerStart_ = value;
                return this;
            }

            public CallForwardInfo clearCallFwdTimerStart() {
                this.hasCallFwdTimerStart = false;
                this.callFwdTimerStart_ = null;
                return this;
            }

            public boolean hasCallFwdTimerEnd() {
                return this.hasCallFwdTimerEnd;
            }

            public CallFwdTimerInfo getCallFwdTimerEnd() {
                return this.callFwdTimerEnd_;
            }

            public CallForwardInfo setCallFwdTimerEnd(CallFwdTimerInfo value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.hasCallFwdTimerEnd = true;
                this.callFwdTimerEnd_ = value;
                return this;
            }

            public CallForwardInfo clearCallFwdTimerEnd() {
                this.hasCallFwdTimerEnd = false;
                this.callFwdTimerEnd_ = null;
                return this;
            }

            public final CallForwardInfo clear() {
                clearStatus();
                clearReason();
                clearServiceClass();
                clearToa();
                clearNumber();
                clearTimeSeconds();
                clearCallFwdTimerStart();
                clearCallFwdTimerEnd();
                this.cachedSize = -1;
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public void writeTo(CodedOutputStreamMicro output) throws IOException {
                if (hasStatus()) {
                    output.writeFixed32(1, getStatus());
                }
                if (hasReason()) {
                    output.writeFixed32(2, getReason());
                }
                if (hasServiceClass()) {
                    output.writeFixed32(3, getServiceClass());
                }
                if (hasToa()) {
                    output.writeFixed32(4, getToa());
                }
                if (hasNumber()) {
                    output.writeString(5, getNumber());
                }
                if (hasTimeSeconds()) {
                    output.writeFixed32(6, getTimeSeconds());
                }
                if (hasCallFwdTimerStart()) {
                    output.writeMessage(7, getCallFwdTimerStart());
                }
                if (hasCallFwdTimerEnd()) {
                    output.writeMessage(8, getCallFwdTimerEnd());
                }
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public int getCachedSize() {
                if (this.cachedSize < 0) {
                    getSerializedSize();
                }
                return this.cachedSize;
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public int getSerializedSize() {
                int size = 0;
                if (hasStatus()) {
                    size = CodedOutputStreamMicro.computeFixed32Size(1, getStatus()) + 0;
                }
                if (hasReason()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(2, getReason());
                }
                if (hasServiceClass()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(3, getServiceClass());
                }
                if (hasToa()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(4, getToa());
                }
                if (hasNumber()) {
                    size += CodedOutputStreamMicro.computeStringSize(5, getNumber());
                }
                if (hasTimeSeconds()) {
                    size += CodedOutputStreamMicro.computeFixed32Size(6, getTimeSeconds());
                }
                if (hasCallFwdTimerStart()) {
                    size += CodedOutputStreamMicro.computeMessageSize(7, getCallFwdTimerStart());
                }
                if (hasCallFwdTimerEnd()) {
                    size += CodedOutputStreamMicro.computeMessageSize(8, getCallFwdTimerEnd());
                }
                this.cachedSize = size;
                return size;
            }

            @Override // com.google.protobuf.micro.MessageMicro
            public CallForwardInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            return this;
                        case 13:
                            setStatus(input.readFixed32());
                            break;
                        case 21:
                            setReason(input.readFixed32());
                            break;
                        case 29:
                            setServiceClass(input.readFixed32());
                            break;
                        case 37:
                            setToa(input.readFixed32());
                            break;
                        case 42:
                            setNumber(input.readString());
                            break;
                        case 53:
                            setTimeSeconds(input.readFixed32());
                            break;
                        case 58:
                            CallFwdTimerInfo value = new CallFwdTimerInfo();
                            input.readMessage(value);
                            setCallFwdTimerStart(value);
                            break;
                        case 66:
                            CallFwdTimerInfo value2 = new CallFwdTimerInfo();
                            input.readMessage(value2);
                            setCallFwdTimerEnd(value2);
                            break;
                        default:
                            if (parseUnknownField(input, tag)) {
                                break;
                            } else {
                                return this;
                            }
                    }
                }
            }

            public static CallForwardInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
                return (CallForwardInfo) new CallForwardInfo().mergeFrom(data);
            }

            public static CallForwardInfo parseFrom(CodedInputStreamMicro input) throws IOException {
                return new CallForwardInfo().mergeFrom(input);
            }
        }

        public List<CallForwardInfo> getInfoList() {
            return this.info_;
        }

        public int getInfoCount() {
            return this.info_.size();
        }

        public CallForwardInfo getInfo(int index) {
            return this.info_.get(index);
        }

        public CallForwardInfoList setInfo(int index, CallForwardInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.info_.set(index, value);
            return this;
        }

        public CallForwardInfoList addInfo(CallForwardInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.info_.isEmpty()) {
                this.info_ = new ArrayList();
            }
            this.info_.add(value);
            return this;
        }

        public CallForwardInfoList clearInfo() {
            this.info_ = Collections.emptyList();
            return this;
        }

        public boolean hasErrorDetails() {
            return this.hasErrorDetails;
        }

        public SipErrorInfo getErrorDetails() {
            return this.errorDetails_;
        }

        public CallForwardInfoList setErrorDetails(SipErrorInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasErrorDetails = true;
            this.errorDetails_ = value;
            return this;
        }

        public CallForwardInfoList clearErrorDetails() {
            this.hasErrorDetails = false;
            this.errorDetails_ = null;
            return this;
        }

        public final CallForwardInfoList clear() {
            clearInfo();
            clearErrorDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            for (CallForwardInfo element : getInfoList()) {
                output.writeMessage(2, element);
            }
            if (hasErrorDetails()) {
                output.writeMessage(3, getErrorDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            for (CallForwardInfo element : getInfoList()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, element);
            }
            if (hasErrorDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, getErrorDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallForwardInfoList mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 18:
                        CallForwardInfo value = new CallForwardInfo();
                        input.readMessage(value);
                        addInfo(value);
                        break;
                    case 26:
                        SipErrorInfo value2 = new SipErrorInfo();
                        input.readMessage(value2);
                        setErrorDetails(value2);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallForwardInfoList parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallForwardInfoList) new CallForwardInfoList().mergeFrom(data);
        }

        public static CallForwardInfoList parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallForwardInfoList().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CallFwdTimerInfo extends MessageMicro {
        public static final int DAY_FIELD_NUMBER = 3;
        public static final int HOUR_FIELD_NUMBER = 4;
        public static final int MINUTE_FIELD_NUMBER = 5;
        public static final int MONTH_FIELD_NUMBER = 2;
        public static final int SECOND_FIELD_NUMBER = 6;
        public static final int TIMEZONE_FIELD_NUMBER = 7;
        public static final int YEAR_FIELD_NUMBER = 1;
        private boolean hasDay;
        private boolean hasHour;
        private boolean hasMinute;
        private boolean hasMonth;
        private boolean hasSecond;
        private boolean hasTimezone;
        private boolean hasYear;
        private int year_ = 0;
        private int month_ = 0;
        private int day_ = 0;
        private int hour_ = 0;
        private int minute_ = 0;
        private int second_ = 0;
        private int timezone_ = 0;
        private int cachedSize = -1;

        public int getYear() {
            return this.year_;
        }

        public boolean hasYear() {
            return this.hasYear;
        }

        public CallFwdTimerInfo setYear(int value) {
            this.hasYear = true;
            this.year_ = value;
            return this;
        }

        public CallFwdTimerInfo clearYear() {
            this.hasYear = false;
            this.year_ = 0;
            return this;
        }

        public int getMonth() {
            return this.month_;
        }

        public boolean hasMonth() {
            return this.hasMonth;
        }

        public CallFwdTimerInfo setMonth(int value) {
            this.hasMonth = true;
            this.month_ = value;
            return this;
        }

        public CallFwdTimerInfo clearMonth() {
            this.hasMonth = false;
            this.month_ = 0;
            return this;
        }

        public int getDay() {
            return this.day_;
        }

        public boolean hasDay() {
            return this.hasDay;
        }

        public CallFwdTimerInfo setDay(int value) {
            this.hasDay = true;
            this.day_ = value;
            return this;
        }

        public CallFwdTimerInfo clearDay() {
            this.hasDay = false;
            this.day_ = 0;
            return this;
        }

        public int getHour() {
            return this.hour_;
        }

        public boolean hasHour() {
            return this.hasHour;
        }

        public CallFwdTimerInfo setHour(int value) {
            this.hasHour = true;
            this.hour_ = value;
            return this;
        }

        public CallFwdTimerInfo clearHour() {
            this.hasHour = false;
            this.hour_ = 0;
            return this;
        }

        public int getMinute() {
            return this.minute_;
        }

        public boolean hasMinute() {
            return this.hasMinute;
        }

        public CallFwdTimerInfo setMinute(int value) {
            this.hasMinute = true;
            this.minute_ = value;
            return this;
        }

        public CallFwdTimerInfo clearMinute() {
            this.hasMinute = false;
            this.minute_ = 0;
            return this;
        }

        public int getSecond() {
            return this.second_;
        }

        public boolean hasSecond() {
            return this.hasSecond;
        }

        public CallFwdTimerInfo setSecond(int value) {
            this.hasSecond = true;
            this.second_ = value;
            return this;
        }

        public CallFwdTimerInfo clearSecond() {
            this.hasSecond = false;
            this.second_ = 0;
            return this;
        }

        public int getTimezone() {
            return this.timezone_;
        }

        public boolean hasTimezone() {
            return this.hasTimezone;
        }

        public CallFwdTimerInfo setTimezone(int value) {
            this.hasTimezone = true;
            this.timezone_ = value;
            return this;
        }

        public CallFwdTimerInfo clearTimezone() {
            this.hasTimezone = false;
            this.timezone_ = 0;
            return this;
        }

        public final CallFwdTimerInfo clear() {
            clearYear();
            clearMonth();
            clearDay();
            clearHour();
            clearMinute();
            clearSecond();
            clearTimezone();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasYear()) {
                output.writeFixed32(1, getYear());
            }
            if (hasMonth()) {
                output.writeFixed32(2, getMonth());
            }
            if (hasDay()) {
                output.writeFixed32(3, getDay());
            }
            if (hasHour()) {
                output.writeFixed32(4, getHour());
            }
            if (hasMinute()) {
                output.writeFixed32(5, getMinute());
            }
            if (hasSecond()) {
                output.writeFixed32(6, getSecond());
            }
            if (hasTimezone()) {
                output.writeFixed32(7, getTimezone());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasYear()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getYear()) + 0;
            }
            if (hasMonth()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getMonth());
            }
            if (hasDay()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getDay());
            }
            if (hasHour()) {
                size += CodedOutputStreamMicro.computeFixed32Size(4, getHour());
            }
            if (hasMinute()) {
                size += CodedOutputStreamMicro.computeFixed32Size(5, getMinute());
            }
            if (hasSecond()) {
                size += CodedOutputStreamMicro.computeFixed32Size(6, getSecond());
            }
            if (hasTimezone()) {
                size += CodedOutputStreamMicro.computeFixed32Size(7, getTimezone());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CallFwdTimerInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setYear(input.readFixed32());
                        break;
                    case 21:
                        setMonth(input.readFixed32());
                        break;
                    case 29:
                        setDay(input.readFixed32());
                        break;
                    case 37:
                        setHour(input.readFixed32());
                        break;
                    case 45:
                        setMinute(input.readFixed32());
                        break;
                    case 53:
                        setSecond(input.readFixed32());
                        break;
                    case 61:
                        setTimezone(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CallFwdTimerInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CallFwdTimerInfo) new CallFwdTimerInfo().mergeFrom(data);
        }

        public static CallFwdTimerInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CallFwdTimerInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ConfInfo extends MessageMicro {
        public static final int CONFCALLSTATE_FIELD_NUMBER = 2;
        public static final int CONF_INFO_URI_FIELD_NUMBER = 1;
        private boolean hasConfCallState;
        private boolean hasConfInfoUri;
        private ByteStringMicro confInfoUri_ = ByteStringMicro.EMPTY;
        private int confCallState_ = 0;
        private int cachedSize = -1;

        public ByteStringMicro getConfInfoUri() {
            return this.confInfoUri_;
        }

        public boolean hasConfInfoUri() {
            return this.hasConfInfoUri;
        }

        public ConfInfo setConfInfoUri(ByteStringMicro value) {
            this.hasConfInfoUri = true;
            this.confInfoUri_ = value;
            return this;
        }

        public ConfInfo clearConfInfoUri() {
            this.hasConfInfoUri = false;
            this.confInfoUri_ = ByteStringMicro.EMPTY;
            return this;
        }

        public boolean hasConfCallState() {
            return this.hasConfCallState;
        }

        public int getConfCallState() {
            return this.confCallState_;
        }

        public ConfInfo setConfCallState(int value) {
            this.hasConfCallState = true;
            this.confCallState_ = value;
            return this;
        }

        public ConfInfo clearConfCallState() {
            this.hasConfCallState = false;
            this.confCallState_ = 0;
            return this;
        }

        public final ConfInfo clear() {
            clearConfInfoUri();
            clearConfCallState();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasConfInfoUri()) {
                output.writeBytes(1, getConfInfoUri());
            }
            if (hasConfCallState()) {
                output.writeInt32(2, getConfCallState());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasConfInfoUri()) {
                size = CodedOutputStreamMicro.computeBytesSize(1, getConfInfoUri()) + 0;
            }
            if (hasConfCallState()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getConfCallState());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ConfInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setConfInfoUri(input.readBytes());
                        break;
                    case 16:
                        setConfCallState(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ConfInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ConfInfo) new ConfInfo().mergeFrom(data);
        }

        public static ConfInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ConfInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SuppSvcNotification extends MessageMicro {
        public static final int CODE_FIELD_NUMBER = 2;
        public static final int CONNID_FIELD_NUMBER = 6;
        public static final int HISTORY_INFO_FIELD_NUMBER = 7;
        public static final int HOLD_TONE_FIELD_NUMBER = 8;
        public static final int INDEX_FIELD_NUMBER = 3;
        public static final int NOTIFICATIONTYPE_FIELD_NUMBER = 1;
        public static final int NUMBER_FIELD_NUMBER = 5;
        public static final int TYPE_FIELD_NUMBER = 4;
        private boolean hasCode;
        private boolean hasConnId;
        private boolean hasHistoryInfo;
        private boolean hasHoldTone;
        private boolean hasIndex;
        private boolean hasNotificationType;
        private boolean hasNumber;
        private boolean hasType;
        private int notificationType_ = 0;
        private int code_ = 0;
        private int index_ = 0;
        private int type_ = 0;
        private String number_ = "";
        private int connId_ = 0;
        private String historyInfo_ = "";
        private boolean holdTone_ = false;
        private int cachedSize = -1;

        public boolean hasNotificationType() {
            return this.hasNotificationType;
        }

        public int getNotificationType() {
            return this.notificationType_;
        }

        public SuppSvcNotification setNotificationType(int value) {
            this.hasNotificationType = true;
            this.notificationType_ = value;
            return this;
        }

        public SuppSvcNotification clearNotificationType() {
            this.hasNotificationType = false;
            this.notificationType_ = 0;
            return this;
        }

        public int getCode() {
            return this.code_;
        }

        public boolean hasCode() {
            return this.hasCode;
        }

        public SuppSvcNotification setCode(int value) {
            this.hasCode = true;
            this.code_ = value;
            return this;
        }

        public SuppSvcNotification clearCode() {
            this.hasCode = false;
            this.code_ = 0;
            return this;
        }

        public int getIndex() {
            return this.index_;
        }

        public boolean hasIndex() {
            return this.hasIndex;
        }

        public SuppSvcNotification setIndex(int value) {
            this.hasIndex = true;
            this.index_ = value;
            return this;
        }

        public SuppSvcNotification clearIndex() {
            this.hasIndex = false;
            this.index_ = 0;
            return this;
        }

        public int getType() {
            return this.type_;
        }

        public boolean hasType() {
            return this.hasType;
        }

        public SuppSvcNotification setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public SuppSvcNotification clearType() {
            this.hasType = false;
            this.type_ = 0;
            return this;
        }

        public String getNumber() {
            return this.number_;
        }

        public boolean hasNumber() {
            return this.hasNumber;
        }

        public SuppSvcNotification setNumber(String value) {
            this.hasNumber = true;
            this.number_ = value;
            return this;
        }

        public SuppSvcNotification clearNumber() {
            this.hasNumber = false;
            this.number_ = "";
            return this;
        }

        public int getConnId() {
            return this.connId_;
        }

        public boolean hasConnId() {
            return this.hasConnId;
        }

        public SuppSvcNotification setConnId(int value) {
            this.hasConnId = true;
            this.connId_ = value;
            return this;
        }

        public SuppSvcNotification clearConnId() {
            this.hasConnId = false;
            this.connId_ = 0;
            return this;
        }

        public String getHistoryInfo() {
            return this.historyInfo_;
        }

        public boolean hasHistoryInfo() {
            return this.hasHistoryInfo;
        }

        public SuppSvcNotification setHistoryInfo(String value) {
            this.hasHistoryInfo = true;
            this.historyInfo_ = value;
            return this;
        }

        public SuppSvcNotification clearHistoryInfo() {
            this.hasHistoryInfo = false;
            this.historyInfo_ = "";
            return this;
        }

        public boolean getHoldTone() {
            return this.holdTone_;
        }

        public boolean hasHoldTone() {
            return this.hasHoldTone;
        }

        public SuppSvcNotification setHoldTone(boolean value) {
            this.hasHoldTone = true;
            this.holdTone_ = value;
            return this;
        }

        public SuppSvcNotification clearHoldTone() {
            this.hasHoldTone = false;
            this.holdTone_ = false;
            return this;
        }

        public final SuppSvcNotification clear() {
            clearNotificationType();
            clearCode();
            clearIndex();
            clearType();
            clearNumber();
            clearConnId();
            clearHistoryInfo();
            clearHoldTone();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasNotificationType()) {
                output.writeInt32(1, getNotificationType());
            }
            if (hasCode()) {
                output.writeFixed32(2, getCode());
            }
            if (hasIndex()) {
                output.writeFixed32(3, getIndex());
            }
            if (hasType()) {
                output.writeFixed32(4, getType());
            }
            if (hasNumber()) {
                output.writeString(5, getNumber());
            }
            if (hasConnId()) {
                output.writeFixed32(6, getConnId());
            }
            if (hasHistoryInfo()) {
                output.writeString(7, getHistoryInfo());
            }
            if (hasHoldTone()) {
                output.writeBool(8, getHoldTone());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasNotificationType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getNotificationType()) + 0;
            }
            if (hasCode()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getCode());
            }
            if (hasIndex()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getIndex());
            }
            if (hasType()) {
                size += CodedOutputStreamMicro.computeFixed32Size(4, getType());
            }
            if (hasNumber()) {
                size += CodedOutputStreamMicro.computeStringSize(5, getNumber());
            }
            if (hasConnId()) {
                size += CodedOutputStreamMicro.computeFixed32Size(6, getConnId());
            }
            if (hasHistoryInfo()) {
                size += CodedOutputStreamMicro.computeStringSize(7, getHistoryInfo());
            }
            if (hasHoldTone()) {
                size += CodedOutputStreamMicro.computeBoolSize(8, getHoldTone());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SuppSvcNotification mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setNotificationType(input.readInt32());
                        break;
                    case 21:
                        setCode(input.readFixed32());
                        break;
                    case 29:
                        setIndex(input.readFixed32());
                        break;
                    case 37:
                        setType(input.readFixed32());
                        break;
                    case 42:
                        setNumber(input.readString());
                        break;
                    case 53:
                        setConnId(input.readFixed32());
                        break;
                    case 58:
                        setHistoryInfo(input.readString());
                        break;
                    case 64:
                        setHoldTone(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SuppSvcNotification parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SuppSvcNotification) new SuppSvcNotification().mergeFrom(data);
        }

        public static SuppSvcNotification parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SuppSvcNotification().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SuppSvcStatus extends MessageMicro {
        public static final int STATUS_FIELD_NUMBER = 1;
        private boolean hasStatus;
        private int status_ = 0;
        private int cachedSize = -1;

        public boolean hasStatus() {
            return this.hasStatus;
        }

        public int getStatus() {
            return this.status_;
        }

        public SuppSvcStatus setStatus(int value) {
            this.hasStatus = true;
            this.status_ = value;
            return this;
        }

        public SuppSvcStatus clearStatus() {
            this.hasStatus = false;
            this.status_ = 0;
            return this;
        }

        public final SuppSvcStatus clear() {
            clearStatus();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasStatus()) {
                output.writeInt32(1, getStatus());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasStatus()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getStatus()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SuppSvcStatus mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setStatus(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SuppSvcStatus parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SuppSvcStatus) new SuppSvcStatus().mergeFrom(data);
        }

        public static SuppSvcStatus parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SuppSvcStatus().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SuppSvcRequest extends MessageMicro {
        public static final int CBNUMLISTTYPE_FIELD_NUMBER = 3;
        public static final int FACILITYTYPE_FIELD_NUMBER = 2;
        public static final int OPERATIONTYPE_FIELD_NUMBER = 1;
        private boolean hasCbNumListType;
        private boolean hasFacilityType;
        private boolean hasOperationType;
        private int operationType_ = 0;
        private int facilityType_ = 0;
        private CbNumListType cbNumListType_ = null;
        private int cachedSize = -1;

        public int getOperationType() {
            return this.operationType_;
        }

        public boolean hasOperationType() {
            return this.hasOperationType;
        }

        public SuppSvcRequest setOperationType(int value) {
            this.hasOperationType = true;
            this.operationType_ = value;
            return this;
        }

        public SuppSvcRequest clearOperationType() {
            this.hasOperationType = false;
            this.operationType_ = 0;
            return this;
        }

        public int getFacilityType() {
            return this.facilityType_;
        }

        public boolean hasFacilityType() {
            return this.hasFacilityType;
        }

        public SuppSvcRequest setFacilityType(int value) {
            this.hasFacilityType = true;
            this.facilityType_ = value;
            return this;
        }

        public SuppSvcRequest clearFacilityType() {
            this.hasFacilityType = false;
            this.facilityType_ = 0;
            return this;
        }

        public boolean hasCbNumListType() {
            return this.hasCbNumListType;
        }

        public CbNumListType getCbNumListType() {
            return this.cbNumListType_;
        }

        public SuppSvcRequest setCbNumListType(CbNumListType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasCbNumListType = true;
            this.cbNumListType_ = value;
            return this;
        }

        public SuppSvcRequest clearCbNumListType() {
            this.hasCbNumListType = false;
            this.cbNumListType_ = null;
            return this;
        }

        public final SuppSvcRequest clear() {
            clearOperationType();
            clearFacilityType();
            clearCbNumListType();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasOperationType()) {
                output.writeFixed32(1, getOperationType());
            }
            if (hasFacilityType()) {
                output.writeFixed32(2, getFacilityType());
            }
            if (hasCbNumListType()) {
                output.writeMessage(3, getCbNumListType());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasOperationType()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getOperationType()) + 0;
            }
            if (hasFacilityType()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getFacilityType());
            }
            if (hasCbNumListType()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, getCbNumListType());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SuppSvcRequest mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setOperationType(input.readFixed32());
                        break;
                    case 21:
                        setFacilityType(input.readFixed32());
                        break;
                    case 26:
                        CbNumListType value = new CbNumListType();
                        input.readMessage(value);
                        setCbNumListType(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SuppSvcRequest parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SuppSvcRequest) new SuppSvcRequest().mergeFrom(data);
        }

        public static SuppSvcRequest parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SuppSvcRequest().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SuppSvcResponse extends MessageMicro {
        public static final int CBNUMLISTTYPE_FIELD_NUMBER = 4;
        public static final int ERRORDETAILS_FIELD_NUMBER = 5;
        public static final int FACILITYTYPE_FIELD_NUMBER = 2;
        public static final int FAILURECAUSE_FIELD_NUMBER = 3;
        public static final int STATUS_FIELD_NUMBER = 1;
        private boolean hasErrorDetails;
        private boolean hasFacilityType;
        private boolean hasFailureCause;
        private boolean hasStatus;
        private int status_ = 0;
        private int facilityType_ = 0;
        private String failureCause_ = "";
        private List<CbNumListType> cbNumListType_ = Collections.emptyList();
        private SipErrorInfo errorDetails_ = null;
        private int cachedSize = -1;

        public boolean hasStatus() {
            return this.hasStatus;
        }

        public int getStatus() {
            return this.status_;
        }

        public SuppSvcResponse setStatus(int value) {
            this.hasStatus = true;
            this.status_ = value;
            return this;
        }

        public SuppSvcResponse clearStatus() {
            this.hasStatus = false;
            this.status_ = 0;
            return this;
        }

        public int getFacilityType() {
            return this.facilityType_;
        }

        public boolean hasFacilityType() {
            return this.hasFacilityType;
        }

        public SuppSvcResponse setFacilityType(int value) {
            this.hasFacilityType = true;
            this.facilityType_ = value;
            return this;
        }

        public SuppSvcResponse clearFacilityType() {
            this.hasFacilityType = false;
            this.facilityType_ = 0;
            return this;
        }

        public String getFailureCause() {
            return this.failureCause_;
        }

        public boolean hasFailureCause() {
            return this.hasFailureCause;
        }

        public SuppSvcResponse setFailureCause(String value) {
            this.hasFailureCause = true;
            this.failureCause_ = value;
            return this;
        }

        public SuppSvcResponse clearFailureCause() {
            this.hasFailureCause = false;
            this.failureCause_ = "";
            return this;
        }

        public List<CbNumListType> getCbNumListTypeList() {
            return this.cbNumListType_;
        }

        public int getCbNumListTypeCount() {
            return this.cbNumListType_.size();
        }

        public CbNumListType getCbNumListType(int index) {
            return this.cbNumListType_.get(index);
        }

        public SuppSvcResponse setCbNumListType(int index, CbNumListType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.cbNumListType_.set(index, value);
            return this;
        }

        public SuppSvcResponse addCbNumListType(CbNumListType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.cbNumListType_.isEmpty()) {
                this.cbNumListType_ = new ArrayList();
            }
            this.cbNumListType_.add(value);
            return this;
        }

        public SuppSvcResponse clearCbNumListType() {
            this.cbNumListType_ = Collections.emptyList();
            return this;
        }

        public boolean hasErrorDetails() {
            return this.hasErrorDetails;
        }

        public SipErrorInfo getErrorDetails() {
            return this.errorDetails_;
        }

        public SuppSvcResponse setErrorDetails(SipErrorInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasErrorDetails = true;
            this.errorDetails_ = value;
            return this;
        }

        public SuppSvcResponse clearErrorDetails() {
            this.hasErrorDetails = false;
            this.errorDetails_ = null;
            return this;
        }

        public final SuppSvcResponse clear() {
            clearStatus();
            clearFacilityType();
            clearFailureCause();
            clearCbNumListType();
            clearErrorDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasStatus()) {
                output.writeInt32(1, getStatus());
            }
            if (hasFacilityType()) {
                output.writeFixed32(2, getFacilityType());
            }
            if (hasFailureCause()) {
                output.writeString(3, getFailureCause());
            }
            for (CbNumListType element : getCbNumListTypeList()) {
                output.writeMessage(4, element);
            }
            if (hasErrorDetails()) {
                output.writeMessage(5, getErrorDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasStatus()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getStatus()) + 0;
            }
            if (hasFacilityType()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getFacilityType());
            }
            if (hasFailureCause()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getFailureCause());
            }
            for (CbNumListType element : getCbNumListTypeList()) {
                size += CodedOutputStreamMicro.computeMessageSize(4, element);
            }
            if (hasErrorDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(5, getErrorDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SuppSvcResponse mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setStatus(input.readInt32());
                        break;
                    case 21:
                        setFacilityType(input.readFixed32());
                        break;
                    case 26:
                        setFailureCause(input.readString());
                        break;
                    case 34:
                        CbNumListType value = new CbNumListType();
                        input.readMessage(value);
                        addCbNumListType(value);
                        break;
                    case 42:
                        SipErrorInfo value2 = new SipErrorInfo();
                        input.readMessage(value2);
                        setErrorDetails(value2);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SuppSvcResponse parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SuppSvcResponse) new SuppSvcResponse().mergeFrom(data);
        }

        public static SuppSvcResponse parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SuppSvcResponse().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class VideoCallQuality extends MessageMicro {
        public static final int QUALITY_FIELD_NUMBER = 1;
        private boolean hasQuality;
        private int quality_ = 0;
        private int cachedSize = -1;

        public boolean hasQuality() {
            return this.hasQuality;
        }

        public int getQuality() {
            return this.quality_;
        }

        public VideoCallQuality setQuality(int value) {
            this.hasQuality = true;
            this.quality_ = value;
            return this;
        }

        public VideoCallQuality clearQuality() {
            this.hasQuality = false;
            this.quality_ = 0;
            return this;
        }

        public final VideoCallQuality clear() {
            clearQuality();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasQuality()) {
                output.writeInt32(1, getQuality());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasQuality()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getQuality()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public VideoCallQuality mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setQuality(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static VideoCallQuality parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (VideoCallQuality) new VideoCallQuality().mergeFrom(data);
        }

        public static VideoCallQuality parseFrom(CodedInputStreamMicro input) throws IOException {
            return new VideoCallQuality().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class MwiMessageSummary extends MessageMicro {
        public static final int MESSAGETYPE_FIELD_NUMBER = 1;
        public static final int NEWMESSAGE_FIELD_NUMBER = 2;
        public static final int NEWURGENT_FIELD_NUMBER = 4;
        public static final int OLDMESSAGE_FIELD_NUMBER = 3;
        public static final int OLDURGENT_FIELD_NUMBER = 5;
        private boolean hasMessageType;
        private boolean hasNewMessage;
        private boolean hasNewUrgent;
        private boolean hasOldMessage;
        private boolean hasOldUrgent;
        private int messageType_ = -1;
        private int newMessage_ = 0;
        private int oldMessage_ = 0;
        private int newUrgent_ = 0;
        private int oldUrgent_ = 0;
        private int cachedSize = -1;

        public boolean hasMessageType() {
            return this.hasMessageType;
        }

        public int getMessageType() {
            return this.messageType_;
        }

        public MwiMessageSummary setMessageType(int value) {
            this.hasMessageType = true;
            this.messageType_ = value;
            return this;
        }

        public MwiMessageSummary clearMessageType() {
            this.hasMessageType = false;
            this.messageType_ = -1;
            return this;
        }

        public int getNewMessage() {
            return this.newMessage_;
        }

        public boolean hasNewMessage() {
            return this.hasNewMessage;
        }

        public MwiMessageSummary setNewMessage(int value) {
            this.hasNewMessage = true;
            this.newMessage_ = value;
            return this;
        }

        public MwiMessageSummary clearNewMessage() {
            this.hasNewMessage = false;
            this.newMessage_ = 0;
            return this;
        }

        public int getOldMessage() {
            return this.oldMessage_;
        }

        public boolean hasOldMessage() {
            return this.hasOldMessage;
        }

        public MwiMessageSummary setOldMessage(int value) {
            this.hasOldMessage = true;
            this.oldMessage_ = value;
            return this;
        }

        public MwiMessageSummary clearOldMessage() {
            this.hasOldMessage = false;
            this.oldMessage_ = 0;
            return this;
        }

        public int getNewUrgent() {
            return this.newUrgent_;
        }

        public boolean hasNewUrgent() {
            return this.hasNewUrgent;
        }

        public MwiMessageSummary setNewUrgent(int value) {
            this.hasNewUrgent = true;
            this.newUrgent_ = value;
            return this;
        }

        public MwiMessageSummary clearNewUrgent() {
            this.hasNewUrgent = false;
            this.newUrgent_ = 0;
            return this;
        }

        public int getOldUrgent() {
            return this.oldUrgent_;
        }

        public boolean hasOldUrgent() {
            return this.hasOldUrgent;
        }

        public MwiMessageSummary setOldUrgent(int value) {
            this.hasOldUrgent = true;
            this.oldUrgent_ = value;
            return this;
        }

        public MwiMessageSummary clearOldUrgent() {
            this.hasOldUrgent = false;
            this.oldUrgent_ = 0;
            return this;
        }

        public final MwiMessageSummary clear() {
            clearMessageType();
            clearNewMessage();
            clearOldMessage();
            clearNewUrgent();
            clearOldUrgent();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasMessageType()) {
                output.writeInt32(1, getMessageType());
            }
            if (hasNewMessage()) {
                output.writeFixed32(2, getNewMessage());
            }
            if (hasOldMessage()) {
                output.writeFixed32(3, getOldMessage());
            }
            if (hasNewUrgent()) {
                output.writeFixed32(4, getNewUrgent());
            }
            if (hasOldUrgent()) {
                output.writeFixed32(5, getOldUrgent());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasMessageType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getMessageType()) + 0;
            }
            if (hasNewMessage()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getNewMessage());
            }
            if (hasOldMessage()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getOldMessage());
            }
            if (hasNewUrgent()) {
                size += CodedOutputStreamMicro.computeFixed32Size(4, getNewUrgent());
            }
            if (hasOldUrgent()) {
                size += CodedOutputStreamMicro.computeFixed32Size(5, getOldUrgent());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public MwiMessageSummary mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setMessageType(input.readInt32());
                        break;
                    case 21:
                        setNewMessage(input.readFixed32());
                        break;
                    case 29:
                        setOldMessage(input.readFixed32());
                        break;
                    case 37:
                        setNewUrgent(input.readFixed32());
                        break;
                    case 45:
                        setOldUrgent(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static MwiMessageSummary parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (MwiMessageSummary) new MwiMessageSummary().mergeFrom(data);
        }

        public static MwiMessageSummary parseFrom(CodedInputStreamMicro input) throws IOException {
            return new MwiMessageSummary().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class MwiMessageDetails extends MessageMicro {
        public static final int DATE_FIELD_NUMBER = 4;
        public static final int FROMADDRESS_FIELD_NUMBER = 2;
        public static final int MESSAGEID_FIELD_NUMBER = 6;
        public static final int MESSAGETYPE_FIELD_NUMBER = 7;
        public static final int PRIORITY_FIELD_NUMBER = 5;
        public static final int SUBJECT_FIELD_NUMBER = 3;
        public static final int TOADDRESS_FIELD_NUMBER = 1;
        private boolean hasDate;
        private boolean hasFromAddress;
        private boolean hasMessageId;
        private boolean hasMessageType;
        private boolean hasPriority;
        private boolean hasSubject;
        private boolean hasToAddress;
        private String toAddress_ = "";
        private String fromAddress_ = "";
        private String subject_ = "";
        private String date_ = "";
        private int priority_ = -1;
        private String messageId_ = "";
        private int messageType_ = -1;
        private int cachedSize = -1;

        public String getToAddress() {
            return this.toAddress_;
        }

        public boolean hasToAddress() {
            return this.hasToAddress;
        }

        public MwiMessageDetails setToAddress(String value) {
            this.hasToAddress = true;
            this.toAddress_ = value;
            return this;
        }

        public MwiMessageDetails clearToAddress() {
            this.hasToAddress = false;
            this.toAddress_ = "";
            return this;
        }

        public String getFromAddress() {
            return this.fromAddress_;
        }

        public boolean hasFromAddress() {
            return this.hasFromAddress;
        }

        public MwiMessageDetails setFromAddress(String value) {
            this.hasFromAddress = true;
            this.fromAddress_ = value;
            return this;
        }

        public MwiMessageDetails clearFromAddress() {
            this.hasFromAddress = false;
            this.fromAddress_ = "";
            return this;
        }

        public String getSubject() {
            return this.subject_;
        }

        public boolean hasSubject() {
            return this.hasSubject;
        }

        public MwiMessageDetails setSubject(String value) {
            this.hasSubject = true;
            this.subject_ = value;
            return this;
        }

        public MwiMessageDetails clearSubject() {
            this.hasSubject = false;
            this.subject_ = "";
            return this;
        }

        public String getDate() {
            return this.date_;
        }

        public boolean hasDate() {
            return this.hasDate;
        }

        public MwiMessageDetails setDate(String value) {
            this.hasDate = true;
            this.date_ = value;
            return this;
        }

        public MwiMessageDetails clearDate() {
            this.hasDate = false;
            this.date_ = "";
            return this;
        }

        public boolean hasPriority() {
            return this.hasPriority;
        }

        public int getPriority() {
            return this.priority_;
        }

        public MwiMessageDetails setPriority(int value) {
            this.hasPriority = true;
            this.priority_ = value;
            return this;
        }

        public MwiMessageDetails clearPriority() {
            this.hasPriority = false;
            this.priority_ = -1;
            return this;
        }

        public String getMessageId() {
            return this.messageId_;
        }

        public boolean hasMessageId() {
            return this.hasMessageId;
        }

        public MwiMessageDetails setMessageId(String value) {
            this.hasMessageId = true;
            this.messageId_ = value;
            return this;
        }

        public MwiMessageDetails clearMessageId() {
            this.hasMessageId = false;
            this.messageId_ = "";
            return this;
        }

        public boolean hasMessageType() {
            return this.hasMessageType;
        }

        public int getMessageType() {
            return this.messageType_;
        }

        public MwiMessageDetails setMessageType(int value) {
            this.hasMessageType = true;
            this.messageType_ = value;
            return this;
        }

        public MwiMessageDetails clearMessageType() {
            this.hasMessageType = false;
            this.messageType_ = -1;
            return this;
        }

        public final MwiMessageDetails clear() {
            clearToAddress();
            clearFromAddress();
            clearSubject();
            clearDate();
            clearPriority();
            clearMessageId();
            clearMessageType();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasToAddress()) {
                output.writeString(1, getToAddress());
            }
            if (hasFromAddress()) {
                output.writeString(2, getFromAddress());
            }
            if (hasSubject()) {
                output.writeString(3, getSubject());
            }
            if (hasDate()) {
                output.writeString(4, getDate());
            }
            if (hasPriority()) {
                output.writeInt32(5, getPriority());
            }
            if (hasMessageId()) {
                output.writeString(6, getMessageId());
            }
            if (hasMessageType()) {
                output.writeInt32(7, getMessageType());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasToAddress()) {
                size = CodedOutputStreamMicro.computeStringSize(1, getToAddress()) + 0;
            }
            if (hasFromAddress()) {
                size += CodedOutputStreamMicro.computeStringSize(2, getFromAddress());
            }
            if (hasSubject()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getSubject());
            }
            if (hasDate()) {
                size += CodedOutputStreamMicro.computeStringSize(4, getDate());
            }
            if (hasPriority()) {
                size += CodedOutputStreamMicro.computeInt32Size(5, getPriority());
            }
            if (hasMessageId()) {
                size += CodedOutputStreamMicro.computeStringSize(6, getMessageId());
            }
            if (hasMessageType()) {
                size += CodedOutputStreamMicro.computeInt32Size(7, getMessageType());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public MwiMessageDetails mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setToAddress(input.readString());
                        break;
                    case 18:
                        setFromAddress(input.readString());
                        break;
                    case 26:
                        setSubject(input.readString());
                        break;
                    case 34:
                        setDate(input.readString());
                        break;
                    case 40:
                        setPriority(input.readInt32());
                        break;
                    case 50:
                        setMessageId(input.readString());
                        break;
                    case 56:
                        setMessageType(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static MwiMessageDetails parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (MwiMessageDetails) new MwiMessageDetails().mergeFrom(data);
        }

        public static MwiMessageDetails parseFrom(CodedInputStreamMicro input) throws IOException {
            return new MwiMessageDetails().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Mwi extends MessageMicro {
        public static final int MWIMSGDETAIL_FIELD_NUMBER = 3;
        public static final int MWIMSGSUMMARY_FIELD_NUMBER = 1;
        public static final int UEADDRESS_FIELD_NUMBER = 2;
        private boolean hasUeAddress;
        private List<MwiMessageSummary> mwiMsgSummary_ = Collections.emptyList();
        private String ueAddress_ = "";
        private List<MwiMessageDetails> mwiMsgDetail_ = Collections.emptyList();
        private int cachedSize = -1;

        public List<MwiMessageSummary> getMwiMsgSummaryList() {
            return this.mwiMsgSummary_;
        }

        public int getMwiMsgSummaryCount() {
            return this.mwiMsgSummary_.size();
        }

        public MwiMessageSummary getMwiMsgSummary(int index) {
            return this.mwiMsgSummary_.get(index);
        }

        public Mwi setMwiMsgSummary(int index, MwiMessageSummary value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.mwiMsgSummary_.set(index, value);
            return this;
        }

        public Mwi addMwiMsgSummary(MwiMessageSummary value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.mwiMsgSummary_.isEmpty()) {
                this.mwiMsgSummary_ = new ArrayList();
            }
            this.mwiMsgSummary_.add(value);
            return this;
        }

        public Mwi clearMwiMsgSummary() {
            this.mwiMsgSummary_ = Collections.emptyList();
            return this;
        }

        public String getUeAddress() {
            return this.ueAddress_;
        }

        public boolean hasUeAddress() {
            return this.hasUeAddress;
        }

        public Mwi setUeAddress(String value) {
            this.hasUeAddress = true;
            this.ueAddress_ = value;
            return this;
        }

        public Mwi clearUeAddress() {
            this.hasUeAddress = false;
            this.ueAddress_ = "";
            return this;
        }

        public List<MwiMessageDetails> getMwiMsgDetailList() {
            return this.mwiMsgDetail_;
        }

        public int getMwiMsgDetailCount() {
            return this.mwiMsgDetail_.size();
        }

        public MwiMessageDetails getMwiMsgDetail(int index) {
            return this.mwiMsgDetail_.get(index);
        }

        public Mwi setMwiMsgDetail(int index, MwiMessageDetails value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.mwiMsgDetail_.set(index, value);
            return this;
        }

        public Mwi addMwiMsgDetail(MwiMessageDetails value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (this.mwiMsgDetail_.isEmpty()) {
                this.mwiMsgDetail_ = new ArrayList();
            }
            this.mwiMsgDetail_.add(value);
            return this;
        }

        public Mwi clearMwiMsgDetail() {
            this.mwiMsgDetail_ = Collections.emptyList();
            return this;
        }

        public final Mwi clear() {
            clearMwiMsgSummary();
            clearUeAddress();
            clearMwiMsgDetail();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            for (MwiMessageSummary element : getMwiMsgSummaryList()) {
                output.writeMessage(1, element);
            }
            if (hasUeAddress()) {
                output.writeString(2, getUeAddress());
            }
            for (MwiMessageDetails element2 : getMwiMsgDetailList()) {
                output.writeMessage(3, element2);
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            for (MwiMessageSummary element : getMwiMsgSummaryList()) {
                size += CodedOutputStreamMicro.computeMessageSize(1, element);
            }
            if (hasUeAddress()) {
                size += CodedOutputStreamMicro.computeStringSize(2, getUeAddress());
            }
            for (MwiMessageDetails element2 : getMwiMsgDetailList()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, element2);
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Mwi mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        MwiMessageSummary value = new MwiMessageSummary();
                        input.readMessage(value);
                        addMwiMsgSummary(value);
                        break;
                    case 18:
                        setUeAddress(input.readString());
                        break;
                    case 26:
                        MwiMessageDetails value2 = new MwiMessageDetails();
                        input.readMessage(value2);
                        addMwiMsgDetail(value2);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Mwi parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Mwi) new Mwi().mergeFrom(data);
        }

        public static Mwi parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Mwi().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Hold extends MessageMicro {
        public static final int CALLID_FIELD_NUMBER = 1;
        private boolean hasCallId;
        private int callId_ = 0;
        private int cachedSize = -1;

        public int getCallId() {
            return this.callId_;
        }

        public boolean hasCallId() {
            return this.hasCallId;
        }

        public Hold setCallId(int value) {
            this.hasCallId = true;
            this.callId_ = value;
            return this;
        }

        public Hold clearCallId() {
            this.hasCallId = false;
            this.callId_ = 0;
            return this;
        }

        public final Hold clear() {
            clearCallId();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallId()) {
                output.writeFixed32(1, getCallId());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallId()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getCallId()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Hold mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setCallId(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Hold parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Hold) new Hold().mergeFrom(data);
        }

        public static Hold parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Hold().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class Resume extends MessageMicro {
        public static final int CALLID_FIELD_NUMBER = 1;
        private boolean hasCallId;
        private int callId_ = 0;
        private int cachedSize = -1;

        public int getCallId() {
            return this.callId_;
        }

        public boolean hasCallId() {
            return this.hasCallId;
        }

        public Resume setCallId(int value) {
            this.hasCallId = true;
            this.callId_ = value;
            return this;
        }

        public Resume clearCallId() {
            this.hasCallId = false;
            this.callId_ = 0;
            return this;
        }

        public final Resume clear() {
            clearCallId();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallId()) {
                output.writeFixed32(1, getCallId());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallId()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getCallId()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public Resume mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setCallId(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static Resume parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (Resume) new Resume().mergeFrom(data);
        }

        public static Resume parseFrom(CodedInputStreamMicro input) throws IOException {
            return new Resume().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class RtpStatisticsData extends MessageMicro {
        public static final int COUNT_FIELD_NUMBER = 1;
        private boolean hasCount;
        private long count_ = 0;
        private int cachedSize = -1;

        public long getCount() {
            return this.count_;
        }

        public boolean hasCount() {
            return this.hasCount;
        }

        public RtpStatisticsData setCount(long value) {
            this.hasCount = true;
            this.count_ = value;
            return this;
        }

        public RtpStatisticsData clearCount() {
            this.hasCount = false;
            this.count_ = 0L;
            return this;
        }

        public final RtpStatisticsData clear() {
            clearCount();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCount()) {
                output.writeFixed64(1, getCount());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCount()) {
                size = CodedOutputStreamMicro.computeFixed64Size(1, getCount()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RtpStatisticsData mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 9:
                        setCount(input.readFixed64());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static RtpStatisticsData parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RtpStatisticsData) new RtpStatisticsData().mergeFrom(data);
        }

        public static RtpStatisticsData parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RtpStatisticsData().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class WifiCallingInfo extends MessageMicro {
        public static final int PREFERENCE_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 1;
        private boolean hasPreference;
        private boolean hasStatus;
        private int status_ = 0;
        private int preference_ = 0;
        private int cachedSize = -1;

        public boolean hasStatus() {
            return this.hasStatus;
        }

        public int getStatus() {
            return this.status_;
        }

        public WifiCallingInfo setStatus(int value) {
            this.hasStatus = true;
            this.status_ = value;
            return this;
        }

        public WifiCallingInfo clearStatus() {
            this.hasStatus = false;
            this.status_ = 0;
            return this;
        }

        public boolean hasPreference() {
            return this.hasPreference;
        }

        public int getPreference() {
            return this.preference_;
        }

        public WifiCallingInfo setPreference(int value) {
            this.hasPreference = true;
            this.preference_ = value;
            return this;
        }

        public WifiCallingInfo clearPreference() {
            this.hasPreference = false;
            this.preference_ = 0;
            return this;
        }

        public final WifiCallingInfo clear() {
            clearStatus();
            clearPreference();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasStatus()) {
                output.writeInt32(1, getStatus());
            }
            if (hasPreference()) {
                output.writeInt32(2, getPreference());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasStatus()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getStatus()) + 0;
            }
            if (hasPreference()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getPreference());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public WifiCallingInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setStatus(input.readInt32());
                        break;
                    case 16:
                        setPreference(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static WifiCallingInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (WifiCallingInfo) new WifiCallingInfo().mergeFrom(data);
        }

        public static WifiCallingInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new WifiCallingInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ConfigMsg extends MessageMicro {
        public static final int BOOLVALUE_FIELD_NUMBER = 2;
        public static final int ERRORCAUSE_FIELD_NUMBER = 5;
        public static final int INTVALUE_FIELD_NUMBER = 3;
        public static final int ITEM_FIELD_NUMBER = 1;
        public static final int STRINGVALUE_FIELD_NUMBER = 4;
        private boolean hasBoolValue;
        private boolean hasErrorCause;
        private boolean hasIntValue;
        private boolean hasItem;
        private boolean hasStringValue;
        private int item_ = 0;
        private boolean boolValue_ = false;
        private int intValue_ = 0;
        private String stringValue_ = "";
        private int errorCause_ = 0;
        private int cachedSize = -1;

        public boolean hasItem() {
            return this.hasItem;
        }

        public int getItem() {
            return this.item_;
        }

        public ConfigMsg setItem(int value) {
            this.hasItem = true;
            this.item_ = value;
            return this;
        }

        public ConfigMsg clearItem() {
            this.hasItem = false;
            this.item_ = 0;
            return this;
        }

        public boolean getBoolValue() {
            return this.boolValue_;
        }

        public boolean hasBoolValue() {
            return this.hasBoolValue;
        }

        public ConfigMsg setBoolValue(boolean value) {
            this.hasBoolValue = true;
            this.boolValue_ = value;
            return this;
        }

        public ConfigMsg clearBoolValue() {
            this.hasBoolValue = false;
            this.boolValue_ = false;
            return this;
        }

        public int getIntValue() {
            return this.intValue_;
        }

        public boolean hasIntValue() {
            return this.hasIntValue;
        }

        public ConfigMsg setIntValue(int value) {
            this.hasIntValue = true;
            this.intValue_ = value;
            return this;
        }

        public ConfigMsg clearIntValue() {
            this.hasIntValue = false;
            this.intValue_ = 0;
            return this;
        }

        public String getStringValue() {
            return this.stringValue_;
        }

        public boolean hasStringValue() {
            return this.hasStringValue;
        }

        public ConfigMsg setStringValue(String value) {
            this.hasStringValue = true;
            this.stringValue_ = value;
            return this;
        }

        public ConfigMsg clearStringValue() {
            this.hasStringValue = false;
            this.stringValue_ = "";
            return this;
        }

        public boolean hasErrorCause() {
            return this.hasErrorCause;
        }

        public int getErrorCause() {
            return this.errorCause_;
        }

        public ConfigMsg setErrorCause(int value) {
            this.hasErrorCause = true;
            this.errorCause_ = value;
            return this;
        }

        public ConfigMsg clearErrorCause() {
            this.hasErrorCause = false;
            this.errorCause_ = 0;
            return this;
        }

        public final ConfigMsg clear() {
            clearItem();
            clearBoolValue();
            clearIntValue();
            clearStringValue();
            clearErrorCause();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasItem()) {
                output.writeInt32(1, getItem());
            }
            if (hasBoolValue()) {
                output.writeBool(2, getBoolValue());
            }
            if (hasIntValue()) {
                output.writeFixed32(3, getIntValue());
            }
            if (hasStringValue()) {
                output.writeString(4, getStringValue());
            }
            if (hasErrorCause()) {
                output.writeInt32(5, getErrorCause());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasItem()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getItem()) + 0;
            }
            if (hasBoolValue()) {
                size += CodedOutputStreamMicro.computeBoolSize(2, getBoolValue());
            }
            if (hasIntValue()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getIntValue());
            }
            if (hasStringValue()) {
                size += CodedOutputStreamMicro.computeStringSize(4, getStringValue());
            }
            if (hasErrorCause()) {
                size += CodedOutputStreamMicro.computeInt32Size(5, getErrorCause());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ConfigMsg mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setItem(input.readInt32());
                        break;
                    case 16:
                        setBoolValue(input.readBool());
                        break;
                    case 29:
                        setIntValue(input.readFixed32());
                        break;
                    case 34:
                        setStringValue(input.readString());
                        break;
                    case 40:
                        setErrorCause(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ConfigMsg parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ConfigMsg) new ConfigMsg().mergeFrom(data);
        }

        public static ConfigMsg parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ConfigMsg().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ViceInfo extends MessageMicro {
        public static final int VICE_INFO_URI_FIELD_NUMBER = 1;
        private boolean hasViceInfoUri;
        private ByteStringMicro viceInfoUri_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public ByteStringMicro getViceInfoUri() {
            return this.viceInfoUri_;
        }

        public boolean hasViceInfoUri() {
            return this.hasViceInfoUri;
        }

        public ViceInfo setViceInfoUri(ByteStringMicro value) {
            this.hasViceInfoUri = true;
            this.viceInfoUri_ = value;
            return this;
        }

        public ViceInfo clearViceInfoUri() {
            this.hasViceInfoUri = false;
            this.viceInfoUri_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final ViceInfo clear() {
            clearViceInfoUri();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasViceInfoUri()) {
                output.writeBytes(1, getViceInfoUri());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasViceInfoUri()) {
                size = CodedOutputStreamMicro.computeBytesSize(1, getViceInfoUri()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ViceInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setViceInfoUri(input.readBytes());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ViceInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ViceInfo) new ViceInfo().mergeFrom(data);
        }

        public static ViceInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ViceInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class GeoLocationInfo extends MessageMicro {
        public static final int ADDRESSINFO_FIELD_NUMBER = 3;
        public static final int LAT_FIELD_NUMBER = 1;
        public static final int LON_FIELD_NUMBER = 2;
        private boolean hasAddressInfo;
        private boolean hasLat;
        private boolean hasLon;
        private double lat_ = 0.0d;
        private double lon_ = 0.0d;
        private AddressInfo addressInfo_ = null;
        private int cachedSize = -1;

        public double getLat() {
            return this.lat_;
        }

        public boolean hasLat() {
            return this.hasLat;
        }

        public GeoLocationInfo setLat(double value) {
            this.hasLat = true;
            this.lat_ = value;
            return this;
        }

        public GeoLocationInfo clearLat() {
            this.hasLat = false;
            this.lat_ = 0.0d;
            return this;
        }

        public double getLon() {
            return this.lon_;
        }

        public boolean hasLon() {
            return this.hasLon;
        }

        public GeoLocationInfo setLon(double value) {
            this.hasLon = true;
            this.lon_ = value;
            return this;
        }

        public GeoLocationInfo clearLon() {
            this.hasLon = false;
            this.lon_ = 0.0d;
            return this;
        }

        public boolean hasAddressInfo() {
            return this.hasAddressInfo;
        }

        public AddressInfo getAddressInfo() {
            return this.addressInfo_;
        }

        public GeoLocationInfo setAddressInfo(AddressInfo value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasAddressInfo = true;
            this.addressInfo_ = value;
            return this;
        }

        public GeoLocationInfo clearAddressInfo() {
            this.hasAddressInfo = false;
            this.addressInfo_ = null;
            return this;
        }

        public final GeoLocationInfo clear() {
            clearLat();
            clearLon();
            clearAddressInfo();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasLat()) {
                output.writeDouble(1, getLat());
            }
            if (hasLon()) {
                output.writeDouble(2, getLon());
            }
            if (hasAddressInfo()) {
                output.writeMessage(3, getAddressInfo());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasLat()) {
                size = CodedOutputStreamMicro.computeDoubleSize(1, getLat()) + 0;
            }
            if (hasLon()) {
                size += CodedOutputStreamMicro.computeDoubleSize(2, getLon());
            }
            if (hasAddressInfo()) {
                size += CodedOutputStreamMicro.computeMessageSize(3, getAddressInfo());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public GeoLocationInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 9:
                        setLat(input.readDouble());
                        break;
                    case 17:
                        setLon(input.readDouble());
                        break;
                    case 26:
                        AddressInfo value = new AddressInfo();
                        input.readMessage(value);
                        setAddressInfo(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static GeoLocationInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (GeoLocationInfo) new GeoLocationInfo().mergeFrom(data);
        }

        public static GeoLocationInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new GeoLocationInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class AddressInfo extends MessageMicro {
        public static final int CITY_FIELD_NUMBER = 1;
        public static final int COUNTRYCODE_FIELD_NUMBER = 5;
        public static final int COUNTRY_FIELD_NUMBER = 3;
        public static final int POSTALCODE_FIELD_NUMBER = 4;
        public static final int STATE_FIELD_NUMBER = 2;
        private boolean hasCity;
        private boolean hasCountry;
        private boolean hasCountryCode;
        private boolean hasPostalCode;
        private boolean hasState;
        private String city_ = "";
        private String state_ = "";
        private String country_ = "";
        private String postalCode_ = "";
        private String countryCode_ = "";
        private int cachedSize = -1;

        public String getCity() {
            return this.city_;
        }

        public boolean hasCity() {
            return this.hasCity;
        }

        public AddressInfo setCity(String value) {
            this.hasCity = true;
            this.city_ = value;
            return this;
        }

        public AddressInfo clearCity() {
            this.hasCity = false;
            this.city_ = "";
            return this;
        }

        public String getState() {
            return this.state_;
        }

        public boolean hasState() {
            return this.hasState;
        }

        public AddressInfo setState(String value) {
            this.hasState = true;
            this.state_ = value;
            return this;
        }

        public AddressInfo clearState() {
            this.hasState = false;
            this.state_ = "";
            return this;
        }

        public String getCountry() {
            return this.country_;
        }

        public boolean hasCountry() {
            return this.hasCountry;
        }

        public AddressInfo setCountry(String value) {
            this.hasCountry = true;
            this.country_ = value;
            return this;
        }

        public AddressInfo clearCountry() {
            this.hasCountry = false;
            this.country_ = "";
            return this;
        }

        public String getPostalCode() {
            return this.postalCode_;
        }

        public boolean hasPostalCode() {
            return this.hasPostalCode;
        }

        public AddressInfo setPostalCode(String value) {
            this.hasPostalCode = true;
            this.postalCode_ = value;
            return this;
        }

        public AddressInfo clearPostalCode() {
            this.hasPostalCode = false;
            this.postalCode_ = "";
            return this;
        }

        public String getCountryCode() {
            return this.countryCode_;
        }

        public boolean hasCountryCode() {
            return this.hasCountryCode;
        }

        public AddressInfo setCountryCode(String value) {
            this.hasCountryCode = true;
            this.countryCode_ = value;
            return this;
        }

        public AddressInfo clearCountryCode() {
            this.hasCountryCode = false;
            this.countryCode_ = "";
            return this;
        }

        public final AddressInfo clear() {
            clearCity();
            clearState();
            clearCountry();
            clearPostalCode();
            clearCountryCode();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCity()) {
                output.writeString(1, getCity());
            }
            if (hasState()) {
                output.writeString(2, getState());
            }
            if (hasCountry()) {
                output.writeString(3, getCountry());
            }
            if (hasPostalCode()) {
                output.writeString(4, getPostalCode());
            }
            if (hasCountryCode()) {
                output.writeString(5, getCountryCode());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCity()) {
                size = CodedOutputStreamMicro.computeStringSize(1, getCity()) + 0;
            }
            if (hasState()) {
                size += CodedOutputStreamMicro.computeStringSize(2, getState());
            }
            if (hasCountry()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getCountry());
            }
            if (hasPostalCode()) {
                size += CodedOutputStreamMicro.computeStringSize(4, getPostalCode());
            }
            if (hasCountryCode()) {
                size += CodedOutputStreamMicro.computeStringSize(5, getCountryCode());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public AddressInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setCity(input.readString());
                        break;
                    case 18:
                        setState(input.readString());
                        break;
                    case 26:
                        setCountry(input.readString());
                        break;
                    case 34:
                        setPostalCode(input.readString());
                        break;
                    case 42:
                        setCountryCode(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static AddressInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (AddressInfo) new AddressInfo().mergeFrom(data);
        }

        public static AddressInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new AddressInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ExplicitCallTransfer extends MessageMicro {
        public static final int CALLID_FIELD_NUMBER = 1;
        public static final int ECTTYPE_FIELD_NUMBER = 2;
        public static final int TARGETADDRESS_FIELD_NUMBER = 3;
        public static final int TARGETCALLID_FIELD_NUMBER = 4;
        private boolean hasCallId;
        private boolean hasEctType;
        private boolean hasTargetAddress;
        private boolean hasTargetCallId;
        private int callId_ = 0;
        private int ectType_ = 0;
        private String targetAddress_ = "";
        private int targetCallId_ = 0;
        private int cachedSize = -1;

        public int getCallId() {
            return this.callId_;
        }

        public boolean hasCallId() {
            return this.hasCallId;
        }

        public ExplicitCallTransfer setCallId(int value) {
            this.hasCallId = true;
            this.callId_ = value;
            return this;
        }

        public ExplicitCallTransfer clearCallId() {
            this.hasCallId = false;
            this.callId_ = 0;
            return this;
        }

        public boolean hasEctType() {
            return this.hasEctType;
        }

        public int getEctType() {
            return this.ectType_;
        }

        public ExplicitCallTransfer setEctType(int value) {
            this.hasEctType = true;
            this.ectType_ = value;
            return this;
        }

        public ExplicitCallTransfer clearEctType() {
            this.hasEctType = false;
            this.ectType_ = 0;
            return this;
        }

        public String getTargetAddress() {
            return this.targetAddress_;
        }

        public boolean hasTargetAddress() {
            return this.hasTargetAddress;
        }

        public ExplicitCallTransfer setTargetAddress(String value) {
            this.hasTargetAddress = true;
            this.targetAddress_ = value;
            return this;
        }

        public ExplicitCallTransfer clearTargetAddress() {
            this.hasTargetAddress = false;
            this.targetAddress_ = "";
            return this;
        }

        public int getTargetCallId() {
            return this.targetCallId_;
        }

        public boolean hasTargetCallId() {
            return this.hasTargetCallId;
        }

        public ExplicitCallTransfer setTargetCallId(int value) {
            this.hasTargetCallId = true;
            this.targetCallId_ = value;
            return this;
        }

        public ExplicitCallTransfer clearTargetCallId() {
            this.hasTargetCallId = false;
            this.targetCallId_ = 0;
            return this;
        }

        public final ExplicitCallTransfer clear() {
            clearCallId();
            clearEctType();
            clearTargetAddress();
            clearTargetCallId();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallId()) {
                output.writeFixed32(1, getCallId());
            }
            if (hasEctType()) {
                output.writeInt32(2, getEctType());
            }
            if (hasTargetAddress()) {
                output.writeString(3, getTargetAddress());
            }
            if (hasTargetCallId()) {
                output.writeFixed32(4, getTargetCallId());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallId()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getCallId()) + 0;
            }
            if (hasEctType()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getEctType());
            }
            if (hasTargetAddress()) {
                size += CodedOutputStreamMicro.computeStringSize(3, getTargetAddress());
            }
            if (hasTargetCallId()) {
                size += CodedOutputStreamMicro.computeFixed32Size(4, getTargetCallId());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ExplicitCallTransfer mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setCallId(input.readFixed32());
                        break;
                    case 16:
                        setEctType(input.readInt32());
                        break;
                    case 26:
                        setTargetAddress(input.readString());
                        break;
                    case 37:
                        setTargetCallId(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ExplicitCallTransfer parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ExplicitCallTransfer) new ExplicitCallTransfer().mergeFrom(data);
        }

        public static ExplicitCallTransfer parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ExplicitCallTransfer().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class VoWiFiCallQuality extends MessageMicro {
        public static final int QUALITY_FIELD_NUMBER = 1;
        private boolean hasQuality;
        private int quality_ = 0;
        private int cachedSize = -1;

        public boolean hasQuality() {
            return this.hasQuality;
        }

        public int getQuality() {
            return this.quality_;
        }

        public VoWiFiCallQuality setQuality(int value) {
            this.hasQuality = true;
            this.quality_ = value;
            return this;
        }

        public VoWiFiCallQuality clearQuality() {
            this.hasQuality = false;
            this.quality_ = 0;
            return this;
        }

        public final VoWiFiCallQuality clear() {
            clearQuality();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasQuality()) {
                output.writeInt32(1, getQuality());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasQuality()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getQuality()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public VoWiFiCallQuality mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setQuality(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static VoWiFiCallQuality parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (VoWiFiCallQuality) new VoWiFiCallQuality().mergeFrom(data);
        }

        public static VoWiFiCallQuality parseFrom(CodedInputStreamMicro input) throws IOException {
            return new VoWiFiCallQuality().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SsacInfo extends MessageMicro {
        public static final int BARRINGFACTORVIDEOSIB_FIELD_NUMBER = 7;
        public static final int BARRINGFACTORVIDEO_FIELD_NUMBER = 3;
        public static final int BARRINGFACTORVOICESIB_FIELD_NUMBER = 5;
        public static final int BARRINGFACTORVOICE_FIELD_NUMBER = 1;
        public static final int BARRINGTIMEVIDEOSIB_FIELD_NUMBER = 8;
        public static final int BARRINGTIMEVIDEO_FIELD_NUMBER = 4;
        public static final int BARRINGTIMEVOICESIB_FIELD_NUMBER = 6;
        public static final int BARRINGTIMEVOICE_FIELD_NUMBER = 2;
        private boolean hasBarringFactorVideo;
        private boolean hasBarringFactorVideoSib;
        private boolean hasBarringFactorVoice;
        private boolean hasBarringFactorVoiceSib;
        private boolean hasBarringTimeVideo;
        private boolean hasBarringTimeVideoSib;
        private boolean hasBarringTimeVoice;
        private boolean hasBarringTimeVoiceSib;
        private int barringFactorVoice_ = 0;
        private int barringTimeVoice_ = 0;
        private int barringFactorVideo_ = 0;
        private int barringTimeVideo_ = 0;
        private int barringFactorVoiceSib_ = 0;
        private int barringTimeVoiceSib_ = 0;
        private int barringFactorVideoSib_ = 0;
        private int barringTimeVideoSib_ = 0;
        private int cachedSize = -1;

        public int getBarringFactorVoice() {
            return this.barringFactorVoice_;
        }

        public boolean hasBarringFactorVoice() {
            return this.hasBarringFactorVoice;
        }

        public SsacInfo setBarringFactorVoice(int value) {
            this.hasBarringFactorVoice = true;
            this.barringFactorVoice_ = value;
            return this;
        }

        public SsacInfo clearBarringFactorVoice() {
            this.hasBarringFactorVoice = false;
            this.barringFactorVoice_ = 0;
            return this;
        }

        public int getBarringTimeVoice() {
            return this.barringTimeVoice_;
        }

        public boolean hasBarringTimeVoice() {
            return this.hasBarringTimeVoice;
        }

        public SsacInfo setBarringTimeVoice(int value) {
            this.hasBarringTimeVoice = true;
            this.barringTimeVoice_ = value;
            return this;
        }

        public SsacInfo clearBarringTimeVoice() {
            this.hasBarringTimeVoice = false;
            this.barringTimeVoice_ = 0;
            return this;
        }

        public int getBarringFactorVideo() {
            return this.barringFactorVideo_;
        }

        public boolean hasBarringFactorVideo() {
            return this.hasBarringFactorVideo;
        }

        public SsacInfo setBarringFactorVideo(int value) {
            this.hasBarringFactorVideo = true;
            this.barringFactorVideo_ = value;
            return this;
        }

        public SsacInfo clearBarringFactorVideo() {
            this.hasBarringFactorVideo = false;
            this.barringFactorVideo_ = 0;
            return this;
        }

        public int getBarringTimeVideo() {
            return this.barringTimeVideo_;
        }

        public boolean hasBarringTimeVideo() {
            return this.hasBarringTimeVideo;
        }

        public SsacInfo setBarringTimeVideo(int value) {
            this.hasBarringTimeVideo = true;
            this.barringTimeVideo_ = value;
            return this;
        }

        public SsacInfo clearBarringTimeVideo() {
            this.hasBarringTimeVideo = false;
            this.barringTimeVideo_ = 0;
            return this;
        }

        public int getBarringFactorVoiceSib() {
            return this.barringFactorVoiceSib_;
        }

        public boolean hasBarringFactorVoiceSib() {
            return this.hasBarringFactorVoiceSib;
        }

        public SsacInfo setBarringFactorVoiceSib(int value) {
            this.hasBarringFactorVoiceSib = true;
            this.barringFactorVoiceSib_ = value;
            return this;
        }

        public SsacInfo clearBarringFactorVoiceSib() {
            this.hasBarringFactorVoiceSib = false;
            this.barringFactorVoiceSib_ = 0;
            return this;
        }

        public int getBarringTimeVoiceSib() {
            return this.barringTimeVoiceSib_;
        }

        public boolean hasBarringTimeVoiceSib() {
            return this.hasBarringTimeVoiceSib;
        }

        public SsacInfo setBarringTimeVoiceSib(int value) {
            this.hasBarringTimeVoiceSib = true;
            this.barringTimeVoiceSib_ = value;
            return this;
        }

        public SsacInfo clearBarringTimeVoiceSib() {
            this.hasBarringTimeVoiceSib = false;
            this.barringTimeVoiceSib_ = 0;
            return this;
        }

        public int getBarringFactorVideoSib() {
            return this.barringFactorVideoSib_;
        }

        public boolean hasBarringFactorVideoSib() {
            return this.hasBarringFactorVideoSib;
        }

        public SsacInfo setBarringFactorVideoSib(int value) {
            this.hasBarringFactorVideoSib = true;
            this.barringFactorVideoSib_ = value;
            return this;
        }

        public SsacInfo clearBarringFactorVideoSib() {
            this.hasBarringFactorVideoSib = false;
            this.barringFactorVideoSib_ = 0;
            return this;
        }

        public int getBarringTimeVideoSib() {
            return this.barringTimeVideoSib_;
        }

        public boolean hasBarringTimeVideoSib() {
            return this.hasBarringTimeVideoSib;
        }

        public SsacInfo setBarringTimeVideoSib(int value) {
            this.hasBarringTimeVideoSib = true;
            this.barringTimeVideoSib_ = value;
            return this;
        }

        public SsacInfo clearBarringTimeVideoSib() {
            this.hasBarringTimeVideoSib = false;
            this.barringTimeVideoSib_ = 0;
            return this;
        }

        public final SsacInfo clear() {
            clearBarringFactorVoice();
            clearBarringTimeVoice();
            clearBarringFactorVideo();
            clearBarringTimeVideo();
            clearBarringFactorVoiceSib();
            clearBarringTimeVoiceSib();
            clearBarringFactorVideoSib();
            clearBarringTimeVideoSib();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasBarringFactorVoice()) {
                output.writeFixed32(1, getBarringFactorVoice());
            }
            if (hasBarringTimeVoice()) {
                output.writeFixed32(2, getBarringTimeVoice());
            }
            if (hasBarringFactorVideo()) {
                output.writeFixed32(3, getBarringFactorVideo());
            }
            if (hasBarringTimeVideo()) {
                output.writeFixed32(4, getBarringTimeVideo());
            }
            if (hasBarringFactorVoiceSib()) {
                output.writeFixed32(5, getBarringFactorVoiceSib());
            }
            if (hasBarringTimeVoiceSib()) {
                output.writeFixed32(6, getBarringTimeVoiceSib());
            }
            if (hasBarringFactorVideoSib()) {
                output.writeFixed32(7, getBarringFactorVideoSib());
            }
            if (hasBarringTimeVideoSib()) {
                output.writeFixed32(8, getBarringTimeVideoSib());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasBarringFactorVoice()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getBarringFactorVoice()) + 0;
            }
            if (hasBarringTimeVoice()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getBarringTimeVoice());
            }
            if (hasBarringFactorVideo()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getBarringFactorVideo());
            }
            if (hasBarringTimeVideo()) {
                size += CodedOutputStreamMicro.computeFixed32Size(4, getBarringTimeVideo());
            }
            if (hasBarringFactorVoiceSib()) {
                size += CodedOutputStreamMicro.computeFixed32Size(5, getBarringFactorVoiceSib());
            }
            if (hasBarringTimeVoiceSib()) {
                size += CodedOutputStreamMicro.computeFixed32Size(6, getBarringTimeVoiceSib());
            }
            if (hasBarringFactorVideoSib()) {
                size += CodedOutputStreamMicro.computeFixed32Size(7, getBarringFactorVideoSib());
            }
            if (hasBarringTimeVideoSib()) {
                size += CodedOutputStreamMicro.computeFixed32Size(8, getBarringTimeVideoSib());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SsacInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setBarringFactorVoice(input.readFixed32());
                        break;
                    case 21:
                        setBarringTimeVoice(input.readFixed32());
                        break;
                    case 29:
                        setBarringFactorVideo(input.readFixed32());
                        break;
                    case 37:
                        setBarringTimeVideo(input.readFixed32());
                        break;
                    case 45:
                        setBarringFactorVoiceSib(input.readFixed32());
                        break;
                    case 53:
                        setBarringTimeVoiceSib(input.readFixed32());
                        break;
                    case 61:
                        setBarringFactorVideoSib(input.readFixed32());
                        break;
                    case 69:
                        setBarringTimeVideoSib(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SsacInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SsacInfo) new SsacInfo().mergeFrom(data);
        }

        public static SsacInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SsacInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class VopsInfo extends MessageMicro {
        public static final int ISVOPSENABLED_FIELD_NUMBER = 1;
        private boolean hasIsVopsEnabled;
        private boolean isVopsEnabled_ = false;
        private int cachedSize = -1;

        public boolean getIsVopsEnabled() {
            return this.isVopsEnabled_;
        }

        public boolean hasIsVopsEnabled() {
            return this.hasIsVopsEnabled;
        }

        public VopsInfo setIsVopsEnabled(boolean value) {
            this.hasIsVopsEnabled = true;
            this.isVopsEnabled_ = value;
            return this;
        }

        public VopsInfo clearIsVopsEnabled() {
            this.hasIsVopsEnabled = false;
            this.isVopsEnabled_ = false;
            return this;
        }

        public final VopsInfo clear() {
            clearIsVopsEnabled();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasIsVopsEnabled()) {
                output.writeBool(1, getIsVopsEnabled());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasIsVopsEnabled()) {
                size = CodedOutputStreamMicro.computeBoolSize(1, getIsVopsEnabled()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public VopsInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setIsVopsEnabled(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static VopsInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (VopsInfo) new VopsInfo().mergeFrom(data);
        }

        public static VopsInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new VopsInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ParticipantStatusInfo extends MessageMicro {
        public static final int CALLID_FIELD_NUMBER = 1;
        public static final int ISECT_FIELD_NUMBER = 5;
        public static final int OPERATION_FIELD_NUMBER = 2;
        public static final int PARTICIPANTURI_FIELD_NUMBER = 4;
        public static final int SIPSTATUS_FIELD_NUMBER = 3;
        private boolean hasCallId;
        private boolean hasIsEct;
        private boolean hasOperation;
        private boolean hasParticipantUri;
        private boolean hasSipStatus;
        private int callId_ = 0;
        private int operation_ = 0;
        private int sipStatus_ = 0;
        private String participantUri_ = "";
        private boolean isEct_ = false;
        private int cachedSize = -1;

        public int getCallId() {
            return this.callId_;
        }

        public boolean hasCallId() {
            return this.hasCallId;
        }

        public ParticipantStatusInfo setCallId(int value) {
            this.hasCallId = true;
            this.callId_ = value;
            return this;
        }

        public ParticipantStatusInfo clearCallId() {
            this.hasCallId = false;
            this.callId_ = 0;
            return this;
        }

        public boolean hasOperation() {
            return this.hasOperation;
        }

        public int getOperation() {
            return this.operation_;
        }

        public ParticipantStatusInfo setOperation(int value) {
            this.hasOperation = true;
            this.operation_ = value;
            return this;
        }

        public ParticipantStatusInfo clearOperation() {
            this.hasOperation = false;
            this.operation_ = 0;
            return this;
        }

        public int getSipStatus() {
            return this.sipStatus_;
        }

        public boolean hasSipStatus() {
            return this.hasSipStatus;
        }

        public ParticipantStatusInfo setSipStatus(int value) {
            this.hasSipStatus = true;
            this.sipStatus_ = value;
            return this;
        }

        public ParticipantStatusInfo clearSipStatus() {
            this.hasSipStatus = false;
            this.sipStatus_ = 0;
            return this;
        }

        public String getParticipantUri() {
            return this.participantUri_;
        }

        public boolean hasParticipantUri() {
            return this.hasParticipantUri;
        }

        public ParticipantStatusInfo setParticipantUri(String value) {
            this.hasParticipantUri = true;
            this.participantUri_ = value;
            return this;
        }

        public ParticipantStatusInfo clearParticipantUri() {
            this.hasParticipantUri = false;
            this.participantUri_ = "";
            return this;
        }

        public boolean getIsEct() {
            return this.isEct_;
        }

        public boolean hasIsEct() {
            return this.hasIsEct;
        }

        public ParticipantStatusInfo setIsEct(boolean value) {
            this.hasIsEct = true;
            this.isEct_ = value;
            return this;
        }

        public ParticipantStatusInfo clearIsEct() {
            this.hasIsEct = false;
            this.isEct_ = false;
            return this;
        }

        public final ParticipantStatusInfo clear() {
            clearCallId();
            clearOperation();
            clearSipStatus();
            clearParticipantUri();
            clearIsEct();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallId()) {
                output.writeFixed32(1, getCallId());
            }
            if (hasOperation()) {
                output.writeInt32(2, getOperation());
            }
            if (hasSipStatus()) {
                output.writeFixed32(3, getSipStatus());
            }
            if (hasParticipantUri()) {
                output.writeString(4, getParticipantUri());
            }
            if (hasIsEct()) {
                output.writeBool(5, getIsEct());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallId()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getCallId()) + 0;
            }
            if (hasOperation()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getOperation());
            }
            if (hasSipStatus()) {
                size += CodedOutputStreamMicro.computeFixed32Size(3, getSipStatus());
            }
            if (hasParticipantUri()) {
                size += CodedOutputStreamMicro.computeStringSize(4, getParticipantUri());
            }
            if (hasIsEct()) {
                size += CodedOutputStreamMicro.computeBoolSize(5, getIsEct());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ParticipantStatusInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setCallId(input.readFixed32());
                        break;
                    case 16:
                        setOperation(input.readInt32());
                        break;
                    case 29:
                        setSipStatus(input.readFixed32());
                        break;
                    case 34:
                        setParticipantUri(input.readString());
                        break;
                    case 40:
                        setIsEct(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ParticipantStatusInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ParticipantStatusInfo) new ParticipantStatusInfo().mergeFrom(data);
        }

        public static ParticipantStatusInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ParticipantStatusInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class VolteUserPref extends MessageMicro {
        public static final int MODE_FIELD_NUMBER = 1;
        private boolean hasMode;
        private int mode_ = 0;
        private int cachedSize = -1;

        public boolean hasMode() {
            return this.hasMode;
        }

        public int getMode() {
            return this.mode_;
        }

        public VolteUserPref setMode(int value) {
            this.hasMode = true;
            this.mode_ = value;
            return this;
        }

        public VolteUserPref clearMode() {
            this.hasMode = false;
            this.mode_ = 0;
            return this;
        }

        public final VolteUserPref clear() {
            clearMode();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasMode()) {
                output.writeInt32(1, getMode());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasMode()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getMode()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public VolteUserPref mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setMode(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static VolteUserPref parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (VolteUserPref) new VolteUserPref().mergeFrom(data);
        }

        public static VolteUserPref parseFrom(CodedInputStreamMicro input) throws IOException {
            return new VolteUserPref().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class HandoverConfigMsg extends MessageMicro {
        public static final int HOCONFIG_FIELD_NUMBER = 1;
        private boolean hasHoConfig;
        private int hoConfig_ = 1;
        private int cachedSize = -1;

        public boolean hasHoConfig() {
            return this.hasHoConfig;
        }

        public int getHoConfig() {
            return this.hoConfig_;
        }

        public HandoverConfigMsg setHoConfig(int value) {
            this.hasHoConfig = true;
            this.hoConfig_ = value;
            return this;
        }

        public HandoverConfigMsg clearHoConfig() {
            this.hasHoConfig = false;
            this.hoConfig_ = 1;
            return this;
        }

        public final HandoverConfigMsg clear() {
            clearHoConfig();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasHoConfig()) {
                output.writeInt32(1, getHoConfig());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasHoConfig()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getHoConfig()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public HandoverConfigMsg mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setHoConfig(input.readInt32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static HandoverConfigMsg parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (HandoverConfigMsg) new HandoverConfigMsg().mergeFrom(data);
        }

        public static HandoverConfigMsg parseFrom(CodedInputStreamMicro input) throws IOException {
            return new HandoverConfigMsg().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class SipErrorInfo extends MessageMicro {
        public static final int SIPERRORCODE_FIELD_NUMBER = 1;
        public static final int SIPERRORSTRING_FIELD_NUMBER = 2;
        private boolean hasSipErrorCode;
        private boolean hasSipErrorString;
        private int sipErrorCode_ = 0;
        private String sipErrorString_ = "";
        private int cachedSize = -1;

        public int getSipErrorCode() {
            return this.sipErrorCode_;
        }

        public boolean hasSipErrorCode() {
            return this.hasSipErrorCode;
        }

        public SipErrorInfo setSipErrorCode(int value) {
            this.hasSipErrorCode = true;
            this.sipErrorCode_ = value;
            return this;
        }

        public SipErrorInfo clearSipErrorCode() {
            this.hasSipErrorCode = false;
            this.sipErrorCode_ = 0;
            return this;
        }

        public String getSipErrorString() {
            return this.sipErrorString_;
        }

        public boolean hasSipErrorString() {
            return this.hasSipErrorString;
        }

        public SipErrorInfo setSipErrorString(String value) {
            this.hasSipErrorString = true;
            this.sipErrorString_ = value;
            return this;
        }

        public SipErrorInfo clearSipErrorString() {
            this.hasSipErrorString = false;
            this.sipErrorString_ = "";
            return this;
        }

        public final SipErrorInfo clear() {
            clearSipErrorCode();
            clearSipErrorString();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasSipErrorCode()) {
                output.writeFixed32(1, getSipErrorCode());
            }
            if (hasSipErrorString()) {
                output.writeString(2, getSipErrorString());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasSipErrorCode()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getSipErrorCode()) + 0;
            }
            if (hasSipErrorString()) {
                size += CodedOutputStreamMicro.computeStringSize(2, getSipErrorString());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public SipErrorInfo mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setSipErrorCode(input.readFixed32());
                        break;
                    case 18:
                        setSipErrorString(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static SipErrorInfo parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (SipErrorInfo) new SipErrorInfo().mergeFrom(data);
        }

        public static SipErrorInfo parseFrom(CodedInputStreamMicro input) throws IOException {
            return new SipErrorInfo().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class ImsSubConfig extends MessageMicro {
        public static final int IMSSTACKENABLED_FIELD_NUMBER = 2;
        public static final int SIMULTSTACKCOUNT_FIELD_NUMBER = 1;
        private boolean hasSimultStackCount;
        private int simultStackCount_ = 0;
        private List<Boolean> imsStackEnabled_ = Collections.emptyList();
        private int cachedSize = -1;

        public int getSimultStackCount() {
            return this.simultStackCount_;
        }

        public boolean hasSimultStackCount() {
            return this.hasSimultStackCount;
        }

        public ImsSubConfig setSimultStackCount(int value) {
            this.hasSimultStackCount = true;
            this.simultStackCount_ = value;
            return this;
        }

        public ImsSubConfig clearSimultStackCount() {
            this.hasSimultStackCount = false;
            this.simultStackCount_ = 0;
            return this;
        }

        public List<Boolean> getImsStackEnabledList() {
            return this.imsStackEnabled_;
        }

        public int getImsStackEnabledCount() {
            return this.imsStackEnabled_.size();
        }

        public boolean getImsStackEnabled(int index) {
            return this.imsStackEnabled_.get(index).booleanValue();
        }

        public ImsSubConfig setImsStackEnabled(int index, boolean value) {
            this.imsStackEnabled_.set(index, Boolean.valueOf(value));
            return this;
        }

        public ImsSubConfig addImsStackEnabled(boolean value) {
            if (this.imsStackEnabled_.isEmpty()) {
                this.imsStackEnabled_ = new ArrayList();
            }
            this.imsStackEnabled_.add(Boolean.valueOf(value));
            return this;
        }

        public ImsSubConfig clearImsStackEnabled() {
            this.imsStackEnabled_ = Collections.emptyList();
            return this;
        }

        public final ImsSubConfig clear() {
            clearSimultStackCount();
            clearImsStackEnabled();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasSimultStackCount()) {
                output.writeFixed32(1, getSimultStackCount());
            }
            for (Boolean bool : getImsStackEnabledList()) {
                boolean element = bool.booleanValue();
                output.writeBool(2, element);
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasSimultStackCount()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getSimultStackCount()) + 0;
            }
            int dataSize = getImsStackEnabledList().size() * 1;
            int size2 = size + dataSize + (getImsStackEnabledList().size() * 1);
            this.cachedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public ImsSubConfig mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setSimultStackCount(input.readFixed32());
                        break;
                    case 16:
                        addImsStackEnabled(input.readBool());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static ImsSubConfig parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (ImsSubConfig) new ImsSubConfig().mergeFrom(data);
        }

        public static ImsSubConfig parseFrom(CodedInputStreamMicro input) throws IOException {
            return new ImsSubConfig().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class RegistrationBlockStatus extends MessageMicro {
        public static final int IMSBLOCKSTATUSONWLAN_FIELD_NUMBER = 2;
        public static final int IMSBLOCKSTATUSONWWAN_FIELD_NUMBER = 1;
        private boolean hasImsBlockStatusOnWlan;
        private boolean hasImsBlockStatusOnWwan;
        private BlockStatus imsBlockStatusOnWwan_ = null;
        private BlockStatus imsBlockStatusOnWlan_ = null;
        private int cachedSize = -1;

        public boolean hasImsBlockStatusOnWwan() {
            return this.hasImsBlockStatusOnWwan;
        }

        public BlockStatus getImsBlockStatusOnWwan() {
            return this.imsBlockStatusOnWwan_;
        }

        public RegistrationBlockStatus setImsBlockStatusOnWwan(BlockStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasImsBlockStatusOnWwan = true;
            this.imsBlockStatusOnWwan_ = value;
            return this;
        }

        public RegistrationBlockStatus clearImsBlockStatusOnWwan() {
            this.hasImsBlockStatusOnWwan = false;
            this.imsBlockStatusOnWwan_ = null;
            return this;
        }

        public boolean hasImsBlockStatusOnWlan() {
            return this.hasImsBlockStatusOnWlan;
        }

        public BlockStatus getImsBlockStatusOnWlan() {
            return this.imsBlockStatusOnWlan_;
        }

        public RegistrationBlockStatus setImsBlockStatusOnWlan(BlockStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasImsBlockStatusOnWlan = true;
            this.imsBlockStatusOnWlan_ = value;
            return this;
        }

        public RegistrationBlockStatus clearImsBlockStatusOnWlan() {
            this.hasImsBlockStatusOnWlan = false;
            this.imsBlockStatusOnWlan_ = null;
            return this;
        }

        public final RegistrationBlockStatus clear() {
            clearImsBlockStatusOnWwan();
            clearImsBlockStatusOnWlan();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasImsBlockStatusOnWwan()) {
                output.writeMessage(1, getImsBlockStatusOnWwan());
            }
            if (hasImsBlockStatusOnWlan()) {
                output.writeMessage(2, getImsBlockStatusOnWlan());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasImsBlockStatusOnWwan()) {
                size = CodedOutputStreamMicro.computeMessageSize(1, getImsBlockStatusOnWwan()) + 0;
            }
            if (hasImsBlockStatusOnWlan()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, getImsBlockStatusOnWlan());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RegistrationBlockStatus mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        BlockStatus value = new BlockStatus();
                        input.readMessage(value);
                        setImsBlockStatusOnWwan(value);
                        break;
                    case 18:
                        BlockStatus value2 = new BlockStatus();
                        input.readMessage(value2);
                        setImsBlockStatusOnWlan(value2);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static RegistrationBlockStatus parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RegistrationBlockStatus) new RegistrationBlockStatus().mergeFrom(data);
        }

        public static RegistrationBlockStatus parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RegistrationBlockStatus().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class BlockStatus extends MessageMicro {
        public static final int BLOCKREASONDETAILS_FIELD_NUMBER = 2;
        public static final int BLOCKREASON_FIELD_NUMBER = 1;
        private boolean hasBlockReason;
        private boolean hasBlockReasonDetails;
        private int blockReason_ = 1;
        private BlockReasonDetails blockReasonDetails_ = null;
        private int cachedSize = -1;

        public boolean hasBlockReason() {
            return this.hasBlockReason;
        }

        public int getBlockReason() {
            return this.blockReason_;
        }

        public BlockStatus setBlockReason(int value) {
            this.hasBlockReason = true;
            this.blockReason_ = value;
            return this;
        }

        public BlockStatus clearBlockReason() {
            this.hasBlockReason = false;
            this.blockReason_ = 1;
            return this;
        }

        public boolean hasBlockReasonDetails() {
            return this.hasBlockReasonDetails;
        }

        public BlockReasonDetails getBlockReasonDetails() {
            return this.blockReasonDetails_;
        }

        public BlockStatus setBlockReasonDetails(BlockReasonDetails value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.hasBlockReasonDetails = true;
            this.blockReasonDetails_ = value;
            return this;
        }

        public BlockStatus clearBlockReasonDetails() {
            this.hasBlockReasonDetails = false;
            this.blockReasonDetails_ = null;
            return this;
        }

        public final BlockStatus clear() {
            clearBlockReason();
            clearBlockReasonDetails();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasBlockReason()) {
                output.writeInt32(1, getBlockReason());
            }
            if (hasBlockReasonDetails()) {
                output.writeMessage(2, getBlockReasonDetails());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasBlockReason()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getBlockReason()) + 0;
            }
            if (hasBlockReasonDetails()) {
                size += CodedOutputStreamMicro.computeMessageSize(2, getBlockReasonDetails());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public BlockStatus mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setBlockReason(input.readInt32());
                        break;
                    case 18:
                        BlockReasonDetails value = new BlockReasonDetails();
                        input.readMessage(value);
                        setBlockReasonDetails(value);
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static BlockStatus parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (BlockStatus) new BlockStatus().mergeFrom(data);
        }

        public static BlockStatus parseFrom(CodedInputStreamMicro input) throws IOException {
            return new BlockStatus().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class BlockReasonDetails extends MessageMicro {
        public static final int REGFAILUREREASONTYPE_FIELD_NUMBER = 1;
        public static final int REGFAILUREREASON_FIELD_NUMBER = 2;
        private boolean hasRegFailureReason;
        private boolean hasRegFailureReasonType;
        private int regFailureReasonType_ = 1;
        private int regFailureReason_ = 0;
        private int cachedSize = -1;

        public boolean hasRegFailureReasonType() {
            return this.hasRegFailureReasonType;
        }

        public int getRegFailureReasonType() {
            return this.regFailureReasonType_;
        }

        public BlockReasonDetails setRegFailureReasonType(int value) {
            this.hasRegFailureReasonType = true;
            this.regFailureReasonType_ = value;
            return this;
        }

        public BlockReasonDetails clearRegFailureReasonType() {
            this.hasRegFailureReasonType = false;
            this.regFailureReasonType_ = 1;
            return this;
        }

        public int getRegFailureReason() {
            return this.regFailureReason_;
        }

        public boolean hasRegFailureReason() {
            return this.hasRegFailureReason;
        }

        public BlockReasonDetails setRegFailureReason(int value) {
            this.hasRegFailureReason = true;
            this.regFailureReason_ = value;
            return this;
        }

        public BlockReasonDetails clearRegFailureReason() {
            this.hasRegFailureReason = false;
            this.regFailureReason_ = 0;
            return this;
        }

        public final BlockReasonDetails clear() {
            clearRegFailureReasonType();
            clearRegFailureReason();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasRegFailureReasonType()) {
                output.writeInt32(1, getRegFailureReasonType());
            }
            if (hasRegFailureReason()) {
                output.writeFixed32(2, getRegFailureReason());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasRegFailureReasonType()) {
                size = CodedOutputStreamMicro.computeInt32Size(1, getRegFailureReasonType()) + 0;
            }
            if (hasRegFailureReason()) {
                size += CodedOutputStreamMicro.computeFixed32Size(2, getRegFailureReason());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public BlockReasonDetails mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        setRegFailureReasonType(input.readInt32());
                        break;
                    case 21:
                        setRegFailureReason(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static BlockReasonDetails parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (BlockReasonDetails) new BlockReasonDetails().mergeFrom(data);
        }

        public static BlockReasonDetails parseFrom(CodedInputStreamMicro input) throws IOException {
            return new BlockReasonDetails().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class RttMessage extends MessageMicro {
        public static final int RTTMESSAGE_FIELD_NUMBER = 1;
        private boolean hasRttMessage;
        private String rttMessage_ = "";
        private int cachedSize = -1;

        public String getRttMessage() {
            return this.rttMessage_;
        }

        public boolean hasRttMessage() {
            return this.hasRttMessage;
        }

        public RttMessage setRttMessage(String value) {
            this.hasRttMessage = true;
            this.rttMessage_ = value;
            return this;
        }

        public RttMessage clearRttMessage() {
            this.hasRttMessage = false;
            this.rttMessage_ = "";
            return this;
        }

        public final RttMessage clear() {
            clearRttMessage();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasRttMessage()) {
                output.writeString(1, getRttMessage());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasRttMessage()) {
                size = CodedOutputStreamMicro.computeStringSize(1, getRttMessage()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RttMessage mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        setRttMessage(input.readString());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static RttMessage parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RttMessage) new RttMessage().mergeFrom(data);
        }

        public static RttMessage parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RttMessage().mergeFrom(input);
        }
    }

    /* loaded from: classes.dex */
    public static final class CancelModifyCall extends MessageMicro {
        public static final int CALLID_FIELD_NUMBER = 1;
        private boolean hasCallId;
        private int callId_ = 0;
        private int cachedSize = -1;

        public int getCallId() {
            return this.callId_;
        }

        public boolean hasCallId() {
            return this.hasCallId;
        }

        public CancelModifyCall setCallId(int value) {
            this.hasCallId = true;
            this.callId_ = value;
            return this;
        }

        public CancelModifyCall clearCallId() {
            this.hasCallId = false;
            this.callId_ = 0;
            return this;
        }

        public final CancelModifyCall clear() {
            clearCallId();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasCallId()) {
                output.writeFixed32(1, getCallId());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = 0;
            if (hasCallId()) {
                size = CodedOutputStreamMicro.computeFixed32Size(1, getCallId()) + 0;
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public CancelModifyCall mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 13:
                        setCallId(input.readFixed32());
                        break;
                    default:
                        if (parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static CancelModifyCall parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (CancelModifyCall) new CancelModifyCall().mergeFrom(data);
        }

        public static CancelModifyCall parseFrom(CodedInputStreamMicro input) throws IOException {
            return new CancelModifyCall().mergeFrom(input);
        }
    }
}
