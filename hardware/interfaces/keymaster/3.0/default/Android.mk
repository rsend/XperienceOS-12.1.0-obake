LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := android.hardware.keymaster@3.0-impl
LOCAL_PROPRIETARY_MODULE := true
LOCAL_MODULE_RELATIVE_PATH := hw
LOCAL_SRC_FILES := \
    KeymasterDevice.cpp \

LOCAL_SHARED_LIBRARIES := \
    liblog \
    libsoftkeymasterdevice \
    libcrypto \
    libkeymaster_portable \
    libkeymaster_staging \
    libhidlbase \
    libhidltransport \
    libutils \
    libhardware \
    android.hardware.keymaster@3.0

include $(BUILD_SHARED_LIBRARY)
