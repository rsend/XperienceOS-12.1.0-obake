#
# Copyright (C) 2014 The Android Open Source Project
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
#

LOCAL_PATH := $(call my-dir)

# User-supplied locale service providers (using the java.text.spi or
# java.util.spi mechanisms) are not supported in Android:
#
# http://developer.android.com/reference/java/util/Locale.html

icu4j_src_files := \
    $(filter-out main/classes/localespi/%, \
    $(call all-java-files-under,main/classes))


# Not all src dirs contain resources, some instead contain other random files
# that should not be included as resources. The ones that should be included
# can be identifed by the fact that they contain particular subdir trees.

define all-subdir-with-subdir
$(patsubst $(LOCAL_PATH)/%/$(2),%,$(wildcard $(LOCAL_PATH)/$(1)/$(2)))
endef

icu4j_resource_dirs := \
    $(filter-out main/classes/localespi/%, \
    $(call all-subdir-with-subdir,main/classes/*/src,com/ibm/icu))


include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(icu4j_src_files)
LOCAL_JAVA_RESOURCE_DIRS := $(icu4j_resource_dirs)
LOCAL_MODULE := icu4j
include $(BUILD_STATIC_JAVA_LIBRARY)

# Path to the ICU4C data files in the Android device file system:
icu4c_data := /system/usr/icu
icu4j_config_root := $(LOCAL_PATH)/main/classes/core/src
include external/icu/icu4j/adjust_icudt_path.mk

ifeq ($(HOST_OS),linux)
endif  # HOST_OS == linux


#==========================================================
# build ICU tests for host for testing purposes
#
# Run the tests using the ICU4J test framework with the following command:
#   java -cp ${ANDROID_BUILD_TOP}/out/host/linux-x86/framework/icu4j-host.jar:${ANDROID_BUILD_TOP}/out/host/linux-x86/framework/icu4j-tests-host.jar com.ibm.icu.dev.test.TestAll
#==========================================================


ifeq ($(HOST_OS),linux)


endif  # HOST_OS == linux

# LayoutLib (frameworks/base/tools/layoutlib) needs JarJar'd versions of the
# icudata and icutzdata, so add rules for it.
