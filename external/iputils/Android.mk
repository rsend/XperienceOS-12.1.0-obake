LOCAL_PATH:= $(call my-dir)

iputils_cflags := \
  -D_GNU_SOURCE \
  -Wno-missing-field-initializers \
  -Wno-sign-compare \
  -Wno-unused-parameter \

include $(CLEAR_VARS)
LOCAL_CFLAGS := $(iputils_cflags)
LOCAL_MODULE := ping
LOCAL_SRC_FILES := ping.c ping_common.c
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_CFLAGS := $(iputils_cflags)
LOCAL_MODULE := ping6
LOCAL_SHARED_LIBRARIES := libcrypto
LOCAL_SRC_FILES := ping6.c ping_common.c
include $(BUILD_EXECUTABLE)
