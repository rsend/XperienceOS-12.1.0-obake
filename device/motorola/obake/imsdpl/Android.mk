LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := ims_dpl_bootstrapd
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := \
    ims_dpl_bootstrap.cpp \
    ims_initial_registration.cpp \
    ims_long_timer_bridge.cpp
LOCAL_SHARED_LIBRARIES := libcutils libdl liblog
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
LOCAL_32_BIT_ONLY := true
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_MODULE := libims_dpl_trace
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := ims_dpl_trace.cpp
LOCAL_SHARED_LIBRARIES := libdl liblog
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
LOCAL_32_BIT_ONLY := true
include $(BUILD_SHARED_LIBRARY)
