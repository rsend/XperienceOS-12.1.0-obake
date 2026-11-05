# Motorola Obake SU6-7.7 radio/RIL userspace dependency closure.
#
# The directory is deliberately isolated from /system/lib so the legacy QMI
# libraries cannot replace Oreo platform libraries for unrelated processes.
# The IMS daemons use this same closure through their private LD_LIBRARY_PATH.

RADIO_SU6_77_ROOT := vendor/motorola/msm8960dt-common/proprietary/radio-su6-77
RADIO_SU6_77_BIN_PATH := $(RADIO_SU6_77_ROOT)/bin
RADIO_SU6_77_PATH := $(RADIO_SU6_77_ROOT)/lib

PRODUCT_COPY_FILES += \
    $(RADIO_SU6_77_BIN_PATH)/ims_rtp_daemon:system/vendor/bin/ims_rtp_daemon \
    $(RADIO_SU6_77_BIN_PATH)/imsdatadaemon:system/vendor/bin/imsdatadaemon \
    $(RADIO_SU6_77_BIN_PATH)/imsqmidaemon:system/vendor/bin/imsqmidaemon \
    $(RADIO_SU6_77_BIN_PATH)/netmgrd-su6-77:system/vendor/bin/netmgrd-su6-77 \
    $(RADIO_SU6_77_BIN_PATH)/qmi_motext_hook:system/bin/qmi_motext_hook \
    $(RADIO_SU6_77_BIN_PATH)/qmuxd-su6-77:system/vendor/bin/qmuxd-su6-77 \
    $(RADIO_SU6_77_PATH)/libadropbox.so:system/lib/radio-su6-77/libadropbox.so \
    $(RADIO_SU6_77_PATH)/lib-dplmedia.so:system/lib/radio-su6-77/lib-dplmedia.so \
    $(RADIO_SU6_77_PATH)/lib-imsdpl.so:system/lib/radio-su6-77/lib-imsdpl.so \
    $(RADIO_SU6_77_PATH)/lib-imsSDP.so:system/lib/radio-su6-77/lib-imsSDP.so \
    $(RADIO_SU6_77_PATH)/lib-imsxml.so:system/lib/radio-su6-77/lib-imsxml.so \
    $(RADIO_SU6_77_PATH)/lib-imsrcs.so:system/lib/radio-su6-77/lib-imsrcs.so \
    $(RADIO_SU6_77_PATH)/lib-imss.so:system/lib/radio-su6-77/lib-imss.so \
    $(RADIO_SU6_77_PATH)/lib-rtpcommon.so:system/lib/radio-su6-77/lib-rtpcommon.so \
    $(RADIO_SU6_77_PATH)/lib-rtpcore.so:system/lib/radio-su6-77/lib-rtpcore.so \
    $(RADIO_SU6_77_PATH)/lib-rtpdaemoninterface.so:system/lib/radio-su6-77/lib-rtpdaemoninterface.so \
    $(RADIO_SU6_77_PATH)/lib-rtpsl.so:system/lib/radio-su6-77/lib-rtpsl.so \
    $(RADIO_SU6_77_PATH)/libdiag.so:system/lib/radio-su6-77/libdiag.so \
    $(RADIO_SU6_77_PATH)/libdsi_netctrl.so:system/lib/radio-su6-77/libdsi_netctrl.so \
    $(RADIO_SU6_77_PATH)/libdsutils.so:system/lib/radio-su6-77/libdsutils.so \
    $(RADIO_SU6_77_PATH)/libidl.so:system/lib/radio-su6-77/libidl.so \
    $(RADIO_SU6_77_PATH)/libmdmcutback.so:system/lib/radio-su6-77/libmdmcutback.so \
    $(RADIO_SU6_77_PATH)/libmotext_inf.so:system/lib/radio-su6-77/libmotext_inf.so \
    $(RADIO_SU6_77_PATH)/libnetmgr.so:system/lib/radio-su6-77/libnetmgr.so \
    $(RADIO_SU6_77_PATH)/libqcci_legacy.so:system/lib/radio-su6-77/libqcci_legacy.so \
    $(RADIO_SU6_77_PATH)/libqdi.so:system/lib/radio-su6-77/libqdi.so \
    $(RADIO_SU6_77_PATH)/libqdp.so:system/lib/radio-su6-77/libqdp.so \
    $(RADIO_SU6_77_PATH)/libqmi.so:system/lib/radio-su6-77/libqmi.so \
    $(RADIO_SU6_77_PATH)/libqmi_cci.so:system/lib/radio-su6-77/libqmi_cci.so \
    $(RADIO_SU6_77_PATH)/libqmi_client_qmux.so:system/lib/radio-su6-77/libqmi_client_qmux.so \
    $(RADIO_SU6_77_PATH)/libqmi_common_so.so:system/lib/radio-su6-77/libqmi_common_so.so \
    $(RADIO_SU6_77_PATH)/libqmi_csi.so:system/lib/radio-su6-77/libqmi_csi.so \
    $(RADIO_SU6_77_PATH)/libqmi_encdec.so:system/lib/radio-su6-77/libqmi_encdec.so \
    $(RADIO_SU6_77_PATH)/libqmiservices.so:system/lib/radio-su6-77/libqmiservices.so \
    $(RADIO_SU6_77_PATH)/libqsap_sdk.so:system/lib/radio-su6-77/libqsap_sdk.so \
    $(RADIO_SU6_77_PATH)/libril-qc-qmi-1.so:system/lib/radio-su6-77/libril-qc-qmi-1.so \
    $(RADIO_SU6_77_PATH)/libril-qcril-hook-oem.so:system/lib/radio-su6-77/libril-qcril-hook-oem.so \
    $(RADIO_SU6_77_PATH)/libtime_genoff.so:system/lib/radio-su6-77/libtime_genoff.so

# lib-imsdpl uses this exact absolute path. Install only one copy so dlopen()
# cannot create a second QIMF singleton beside the private radio closure.
PRODUCT_COPY_FILES += \
    $(RADIO_SU6_77_PATH)/lib-imsqimf.so:system/vendor/lib/lib-imsqimf.so \
    $(RADIO_SU6_77_PATH)/libwpa_client.so:system/lib/radio-su6-77/libwpa_client.so
