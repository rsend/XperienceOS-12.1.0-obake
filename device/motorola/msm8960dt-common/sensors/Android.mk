LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := sensors.msm8960
LOCAL_MODULE_RELATIVE_PATH := hw
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := SensorsCompat.cpp
LOCAL_CFLAGS := -Wall -Werror
LOCAL_SHARED_LIBRARIES := \
    libdl \
    libhardware \
    liblog
include $(BUILD_SHARED_LIBRARY)
