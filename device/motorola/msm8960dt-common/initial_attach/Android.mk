LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := liblegacy_initial_attach
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := legacy_initial_attach.cpp
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
LOCAL_SHARED_LIBRARIES := libdl
include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := initial_attach_apnd
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := initial_attach_apnd.cpp
LOCAL_STATIC_LIBRARIES := liblegacy_initial_attach
LOCAL_SHARED_LIBRARIES := libcutils libdl liblog
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
LOCAL_INIT_RC := initial_attach_apnd.rc
include $(BUILD_EXECUTABLE)
