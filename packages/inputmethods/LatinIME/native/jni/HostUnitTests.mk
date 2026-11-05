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

# Host build is never supported in unbundled (NDK/tapas) build
ifeq (,$(TARGET_BUILD_APPS))

# HACK: Temporarily disable host tool build on Mac until the build system is ready for C++11.
LATINIME_HOST_OSNAME := $(shell uname -s)
ifneq ($(LATINIME_HOST_OSNAME), Darwin) # TODO: Remove this

LOCAL_PATH := $(call my-dir)

######################################

#################### Host native tests

include $(LOCAL_PATH)/CleanupNativeFileList.mk

endif # Darwin - TODO: Remove this

endif # TARGET_BUILD_APPS

#################### Clean up the tmp vars
LATINIME_HOST_OSNAME :=
LATIN_IME_SRC_DIR :=
LATIN_IME_TEST_SRC_DIR :=
