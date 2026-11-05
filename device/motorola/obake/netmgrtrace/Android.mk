LOCAL_PATH := $(call my-dir)

# Temporary netmgrd DAC diagnostic; remove using the packaged rollback patch.
include $(CLEAR_VARS)
LOCAL_MODULE := libobake_netmgr_trace
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := netmgr_trace.cpp
LOCAL_SHARED_LIBRARIES := libdl liblog
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
LOCAL_32_BIT_ONLY := true
include $(BUILD_SHARED_LIBRARY)
