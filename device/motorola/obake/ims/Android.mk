# Copyright (C) 2017 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# Addison's API-26 client is an Oreo android.telephony.ims.ImsService. It is
# bound dynamically by the framework and talks to the Qualcomm IMS radio HIDL
# interface supplied by the device-specific transport adapter.
LOCAL_MODULE := ims
LOCAL_MODULE_OWNER := motorola
LOCAL_PREBUILT_MODULE_FILE := vendor/motorola/addison/proprietary/app/ims/ims.apk
LOCAL_CERTIFICATE := platform
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := APPS
LOCAL_DEX_PREOPT := false
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

include $(BUILD_PREBUILT)

include $(CLEAR_VARS)

# Companion settings activity for the API-26 IMS implementation.
LOCAL_MODULE := imssettings
LOCAL_MODULE_OWNER := motorola
LOCAL_PREBUILT_MODULE_FILE := vendor/motorola/addison/proprietary/app/imssettings/imssettings.apk
LOCAL_CERTIFICATE := platform
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := APPS
LOCAL_DEX_PREOPT := false
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

include $(BUILD_PREBUILT)
