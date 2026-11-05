LOCAL_PATH:= $(call my-dir)

libpcap_cflags := \
  -Wno-macro-redefined \
  -Wno-pointer-arith \
  -Wno-sign-compare \
  -Wno-unused-parameter \
  -D_BSD_SOURCE \
  -D_U_="__attribute__((unused))" \
  -Werror \

include $(CLEAR_VARS)

# (Matches order in libpcap's Makefile.)
LOCAL_SRC_FILES := \
  pcap-linux.c pcap-usb-linux.c pcap-netfilter-linux-android.c \
  fad-getad.c \
  pcap.c inet.c fad-helpers.c gencode.c optimize.c nametoaddr.c \
  etherent.c savefile.c sf-pcap.c sf-pcap-ng.c pcap-common.c \
  bpf_image.c bpf_dump.c \
  scanner.c grammar.c bpf_filter.c version.c \

LOCAL_CFLAGS += $(libpcap_cflags)
LOCAL_CFLAGS += -DHAVE_CONFIG_H

LOCAL_EXPORT_C_INCLUDE_DIRS := $(LOCAL_PATH)

LOCAL_MODULE:= libpcap

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_WHOLE_STATIC_LIBRARIES := libpcap
LOCAL_MODULE := libpcap

include $(BUILD_SHARED_LIBRARY)

