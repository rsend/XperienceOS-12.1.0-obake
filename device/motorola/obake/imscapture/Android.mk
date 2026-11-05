LOCAL_PATH := $(call my-dir)
ifeq ($(TARGET_DEVICE):$(OBAKE_IMS_BOOT_CAPTURE),obake:true)
include $(CLEAR_VARS)
LOCAL_MODULE := ims_bootcap
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := ims_bootcap.cpp
LOCAL_SHARED_LIBRARIES := libcutils
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
include $(BUILD_EXECUTABLE)
endif
