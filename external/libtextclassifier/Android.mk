# Copyright (C) 2017 The Android Open Source Project
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

# Useful environment variables that can be set on the mmma command line, as
# <key>=<value> pairs:
#
# LIBTEXTCLASSIFIER_STRIP_OPTS: (optional) value for LOCAL_STRIP_MODULE (for all
#   modules we build).  NOT for prod builds.  Can be set to keep_symbols for
#   debug / treemap purposes.


LOCAL_PATH := $(call my-dir)

# Custom C/C++ compilation flags:
MY_LIBTEXTCLASSIFIER_CFLAGS := \
    -Wno-unused-parameter \
    -Wno-sign-compare \
    -Wno-missing-field-initializers \
    -Wno-ignored-qualifiers \
    -Wno-undefined-var-template \
    -fvisibility=hidden

# Only enable debug logging in userdebug/eng builds.
ifneq (,$(filter userdebug eng, $(TARGET_BUILD_VARIANT)))
  MY_LIBTEXTCLASSIFIER_CFLAGS += -DTC_DEBUG_LOGGING=1
endif

# ------------------------
# libtextclassifier_protos
# ------------------------

include $(CLEAR_VARS)

LOCAL_MODULE := libtextclassifier_protos

LOCAL_STRIP_MODULE := $(LIBTEXTCLASSIFIER_STRIP_OPTS)

LOCAL_SRC_FILES := $(call all-proto-files-under, .)
LOCAL_SHARED_LIBRARIES := libprotobuf-cpp-lite

include $(BUILD_STATIC_LIBRARY)

# -----------------
# libtextclassifier
# -----------------

include $(CLEAR_VARS)
LOCAL_MODULE := libtextclassifier

proto_sources_dir := $(generated_sources_dir)

LOCAL_CPP_EXTENSION := .cc
LOCAL_CFLAGS += $(MY_LIBTEXTCLASSIFIER_CFLAGS)
LOCAL_STRIP_MODULE := $(LIBTEXTCLASSIFIER_STRIP_OPTS)

LOCAL_SRC_FILES := $(filter-out tests/%,$(call all-subdir-cpp-files))
LOCAL_C_INCLUDES += $(proto_sources_dir)/proto/external/libtextclassifier

LOCAL_STATIC_LIBRARIES += libtextclassifier_protos
LOCAL_SHARED_LIBRARIES += libprotobuf-cpp-lite
LOCAL_SHARED_LIBRARIES += liblog
LOCAL_SHARED_LIBRARIES += libicuuc libicui18n

LOCAL_ADDITIONAL_DEPENDENCIES += $(LOCAL_PATH)/jni.lds
LOCAL_LDFLAGS += -Wl,-version-script=$(LOCAL_PATH)/jni.lds

include $(BUILD_SHARED_LIBRARY)

# -----------------------
# libtextclassifier_tests
# -----------------------

# ------------
# LangId model
# ------------

# ----------------------
# Smart Selection models
# ----------------------

# -----------------------
# Smart Selection bundles
# -----------------------
