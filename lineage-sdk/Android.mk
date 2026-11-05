# Copyright (C) 2015 The CyanogenMod Project
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

# Image builds need the static platform APIs and resources, not SDK docs,
# API publication, AAR publishing, or standalone test packages.
lineage_platform_res := APPS/org.lineageos.platform-res_intermediates/src
lineage_sdk_src := sdk/src/java/lineageos
lineage_sdk_internal_src := sdk/src/java/org/lineageos/internal
lineage_sdk_LOCAL_JAVA_LIBRARIES := \
    android-support-annotations \
    android-support-v7-preference \
    android-support-v7-recyclerview \
    android-support-v14-preference

include $(CLEAR_VARS)

LOCAL_MODULE:= org.lineageos.platform.sdk
LOCAL_MODULE_TAGS := optional
LOCAL_REQUIRED_MODULES := services

LOCAL_SRC_FILES := \
    $(call all-java-files-under, $(lineage_sdk_src)) \
    $(call all-Iaidl-files-under, $(lineage_sdk_src))

# Included aidl files from lineageos.app namespace
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/sdk/src/java

lineage_sdk_LOCAL_INTERMEDIATE_SOURCES := \
    $(lineage_platform_res)/lineageos/platform/R.java \
    $(lineage_platform_res)/lineageos/platform/Manifest.java

LOCAL_INTERMEDIATE_SOURCES := \
    $(lineage_sdk_LOCAL_INTERMEDIATE_SOURCES)

LOCAL_JAVA_LIBRARIES := \
    $(lineage_sdk_LOCAL_JAVA_LIBRARIES)

# Make sure that R.java and Manifest.java are built before we build
# the source for this library.
lineage_framework_res_R_stamp := \
    $(call intermediates-dir-for,APPS,org.lineageos.platform-res,,COMMON)/src/R.stamp
LOCAL_ADDITIONAL_DEPENDENCIES := $(lineage_framework_res_R_stamp)
include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE:= org.lineageos.platform.internal
LOCAL_MODULE_TAGS := optional
LOCAL_REQUIRED_MODULES := services

LOCAL_SRC_FILES := \
    $(call all-java-files-under, $(lineage_sdk_src)) \
    $(call all-java-files-under, $(lineage_sdk_internal_src)) \
    $(call all-Iaidl-files-under, $(lineage_sdk_src)) \
    $(call all-Iaidl-files-under, $(lineage_sdk_internal_src))

# Included aidl files from lineageos.app namespace
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/sdk/src/java
LOCAL_AIDL_FLAGS := -n

lineage_sdk_LOCAL_INTERMEDIATE_SOURCES := \
    $(lineage_platform_res)/lineageos/platform/R.java \
    $(lineage_platform_res)/lineageos/platform/Manifest.java \
    $(lineage_platform_res)/org/lineageos/platform/internal/R.java \
    $(lineage_platform_res)/org/lineageos/platform/internal/Manifest.java

LOCAL_INTERMEDIATE_SOURCES := \
    $(lineage_sdk_LOCAL_INTERMEDIATE_SOURCES)

LOCAL_JAVA_LIBRARIES := \
    $(lineage_sdk_LOCAL_JAVA_LIBRARIES)

include $(BUILD_STATIC_JAVA_LIBRARY)

include $(call first-makefiles-under,$(LOCAL_PATH))
