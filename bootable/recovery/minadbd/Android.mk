# Copyright 2005 The Android Open Source Project

LOCAL_PATH:= $(call my-dir)

minadbd_cflags := \
    -Wall -Werror \
    -Wno-unused-parameter \
    -Wno-missing-field-initializers \
    -DADB_HOST=0 \

include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
    fuse_adb_provider.cpp \
    minadbd.cpp \
    minadbd_services.cpp \

LOCAL_CLANG := true
LOCAL_MODULE := libminadbd
LOCAL_CFLAGS := $(minadbd_cflags)
LOCAL_CONLY_FLAGS := -Wimplicit-function-declaration
LOCAL_C_INCLUDES := bootable/recovery system/core/adb
LOCAL_WHOLE_STATIC_LIBRARIES := libadbd
LOCAL_STATIC_LIBRARIES := libcrypto libbase

include $(BUILD_STATIC_LIBRARY)
