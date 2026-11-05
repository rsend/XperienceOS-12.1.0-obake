#
# Copyright (C) 2012 The Android Open Source Project
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

okhttp_common_src_files := $(call all-java-files-under,okhttp/src/main/java)
okhttp_common_src_files += $(call all-java-files-under,okhttp-urlconnection/src/main/java)
okhttp_common_src_files += $(call all-java-files-under,okhttp-android-support/src/main/java)
okhttp_common_src_files += $(call all-java-files-under,okio/okio/src/main/java)

okhttp_system_src_files := $(filter-out %/Platform.java, $(okhttp_common_src_files))
okhttp_system_src_files += $(call all-java-files-under, android/main/java)


# Exclude test Android currently has problems with due to @Parameterized (requires JUnit 4.11).


include $(CLEAR_VARS)
LOCAL_MODULE := okhttp
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := $(okhttp_system_src_files)
LOCAL_JARJAR_RULES := $(LOCAL_PATH)/jarjar-rules.txt
LOCAL_JAVA_LIBRARIES := core-oj core-libart
LOCAL_NO_STANDARD_LIBRARIES := true
LOCAL_ADDITIONAL_DEPENDENCIES := $(LOCAL_PATH)/Android.mk
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_JAVA_LIBRARY)

# A guaranteed unstripped version of okhttp.
# The build system may or may not strip the okhttp jar, but this one will
# not be stripped. See b/24535627.

# non-jarjar'd version of okhttp to compile the tests against

ifeq ($(HOST_OS),linux)
endif  # ($(HOST_OS),linux)
