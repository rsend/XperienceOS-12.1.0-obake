# Copyright 2006-2014 The Android Open Source Project

LOCAL_PATH := $(call my-dir)

logcatLibs := liblog libbase libcutils libpcrecpp

include $(CLEAR_VARS)

LOCAL_MODULE := logcat
LOCAL_SRC_FILES := logcat_main.cpp event.logtags
LOCAL_SHARED_LIBRARIES := liblogcat $(logcatLibs)
LOCAL_CFLAGS := -Werror

include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)

LOCAL_MODULE := liblogcat
LOCAL_SRC_FILES := logcat.cpp getopt_long.cpp logcat_system.cpp
LOCAL_SHARED_LIBRARIES := $(logcatLibs)
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_EXPORT_C_INCLUDE_DIRS := $(LOCAL_PATH)/include
LOCAL_CFLAGS := -Werror

include $(BUILD_SHARED_LIBRARY)

include $(call first-makefiles-under,$(LOCAL_PATH))
