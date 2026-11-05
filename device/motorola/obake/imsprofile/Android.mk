LOCAL_PATH := $(call my-dir)

# Supervised one-time modem provisioning tool. It is intentionally excluded
# from PRODUCT_PACKAGES and has no init service or automatic retry path.
include $(CLEAR_VARS)
LOCAL_MODULE := ims_profile_provision
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := ims_profile_provision.cpp
LOCAL_SHARED_LIBRARIES := libdl
LOCAL_CPPFLAGS := -std=gnu++11 -Wall -Wextra -Werror
LOCAL_32_BIT_ONLY := true
LOCAL_UNINSTALLABLE_MODULE := true
include $(BUILD_EXECUTABLE)
