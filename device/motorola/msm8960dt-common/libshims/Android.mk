LOCAL_PATH := $(call my-dir)

# liblog
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
    moto_log.c
LOCAL_MODULE := libshim_log
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)

# The SU6-7.7 lib-imsdpl binary imports the historical non-_ex OpenSSL final
# functions. Oreo's BoringSSL retains the equivalent _ex implementations but
# no longer exports those two aliases.
include $(CLEAR_VARS)
LOCAL_SRC_FILES := ims_openssl_legacy.c
LOCAL_SHARED_LIBRARIES := libcrypto
LOCAL_MODULE := libshim_ims_openssl_legacy
LOCAL_MODULE_TAGS := optional
LOCAL_32_BIT_ONLY := true
include $(BUILD_SHARED_LIBRARY)

# libmdmcutback
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
    moto_mdmcutback.c
LOCAL_MODULE := libshim_mdmcutback
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)

# libqc-opt
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
    icu53.c \
    qcopt.cpp
LOCAL_C_INCLUDES := \
    external/skia/include/core
LOCAL_SHARED_LIBRARIES := libicuuc libicui18n libskia
LOCAL_MODULE := libshim_qcopt
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)

# libril
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
    moto_ril.c
LOCAL_SHARED_LIBRARIES := libbinder
LOCAL_MODULE := libshim_ril
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := thermal.c
LOCAL_MODULE := libshims_thermal
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := GraphicBuffer.cpp
LOCAL_MODULE := libshims_graphicbuffer
LOCAL_MODULE_TAGS := optional
LOCAL_SHARED_LIBRARIES := libui libutils
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
    sensorlistener/ISensorServer.cpp \
    sensorlistener/SensorManager.cpp
LOCAL_MODULE := libshim_sensorlistener
LOCAL_MODULE_TAGS := optional
LOCAL_SHARED_LIBRARIES := \
    libnativeloader \
    libbinder \
    libcutils \
    libEGL \
    libGLESv2 \
    libsync \
    libui \
    libutils \
    liblog \
    libbase \
    libsensor
include $(BUILD_SHARED_LIBRARY)
