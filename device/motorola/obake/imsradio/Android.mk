LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_PACKAGE_NAME := ObakeImsRadioAdapter
LOCAL_MODULE_TAGS := optional
LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true
LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_STATIC_JAVA_LIBRARIES := libprotobuf-java-micro
LOCAL_PROGUARD_ENABLED := disabled
include $(BUILD_PACKAGE)

include $(CLEAR_VARS)
LOCAL_MODULE := ims_qipcall_policy
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := ims_qipcall_policy.cpp
LOCAL_SHARED_LIBRARIES := libdl
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)
LOCAL_MODULE := ims_lte_guard
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := ims_lte_guard.cpp
LOCAL_SHARED_LIBRARIES := libcutils libdl liblog
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
include $(BUILD_EXECUTABLE)
