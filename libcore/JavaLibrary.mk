# -*- mode: makefile -*-
# Copyright (C) 2007 The Android Open Source Project
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
# Definitions for building the Java library and associated tests.
#

#
# Common definitions for host and target.
#

# libcore is divided into modules.
#
# The structure of each module is:
#
#   src/
#       main/               # To be shipped on every device.
#            java/          # Java source for library code.
#            native/        # C++ source for library code.
#            resources/     # Support files.
#       test/               # Built only on demand, for testing.
#            java/          # Java source for tests.
#            native/        # C++ source for tests (rare).
#            resources/     # Support files.
#
# All subdirectories are optional (hence the "2> /dev/null"s below).

include $(LOCAL_PATH)/openjdk_java_files.mk
include $(LOCAL_PATH)/non_openjdk_java_files.mk

define all-test-java-files-under
$(foreach dir,$(1),$(patsubst ./%,%,$(shell cd $(LOCAL_PATH) && (find $(dir)/src/test/java -name "*.java" 2> /dev/null) | grep -v -f java_tests_blacklist)))
endef

define all-core-resource-dirs
$(shell cd $(LOCAL_PATH) && ls -d */src/$(1)/{java,resources} 2> /dev/null)
endef

# The Java files and their associated resources.
core_resource_dirs := \
  luni/src/main/java \
  ojluni/src/main/resources/
test_resource_dirs :=
test_src_files :=
ojtest_src_files :=
ojtest_resource_dirs :=

ifeq ($(EMMA_INSTRUMENT),true)
ifneq ($(EMMA_INSTRUMENT_STATIC),true)
    nojcore_src_files += $(call all-java-files-under, ../external/emma/core ../external/emma/pregenerated)
    core_resource_dirs += ../external/emma/core/res ../external/emma/pregenerated/res
endif
endif

local_javac_flags=-encoding UTF-8
#local_javac_flags+=-Xlint:all -Xlint:-serial,-deprecation,-unchecked
local_javac_flags+=-Xmaxwarns 9999999

# For user / userdebug builds, strip the local variable table and the local variable
# type table. This has no bearing on stack traces, but will leave less information
# available via JDWP.
#
# TODO: Should this be conditioned on a PRODUCT_ flag or should we just turn this
# on for all builds. Also, name of the flag TBD.
ifneq (,$(PRODUCT_MINIMIZE_JAVA_DEBUG_INFO))
ifneq (,$(filter userdebug user,$(TARGET_BUILD_VARIANT)))
local_javac_flags+= -g:source,lines
local_jack_flags+= -D jack.dex.debug.vars=false -D jack.dex.debug.vars.synthetic=false
endif
endif

#
# ICU4J related rules.
#
# We compile android_icu4j along with core-libart because we're implementing parts of core-libart
# in terms of android_icu4j.
android_icu4j_root := ../external/icu/android_icu4j/
android_icu4j_src_files := $(call all-java-files-under,$(android_icu4j_root)/src/main/java)
android_icu4j_resource_dirs := $(android_icu4j_root)/resources

#
# Build for the target (device).
#

include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(openjdk_java_files) $(non_openjdk_java_files) $(android_icu4j_src_files) $(openjdk_lambda_stub_files)
LOCAL_JAVA_RESOURCE_DIRS := $(core_resource_dirs) $(android_icu4j_resource_dirs)
LOCAL_NO_STANDARD_LIBRARIES := true
LOCAL_JAVACFLAGS := $(local_javac_flags)
LOCAL_JACK_FLAGS := $(local_jack_flags)
LOCAL_DX_FLAGS := --core-library
LOCAL_MODULE_TAGS := optional
LOCAL_JAVA_LANGUAGE_VERSION := 1.8
LOCAL_MODULE := core-all
LOCAL_REQUIRED_MODULES := tzdata tzlookup.xml
LOCAL_CORE_LIBRARY := true
LOCAL_UNINSTALLABLE_MODULE := true
include $(BUILD_JAVA_LIBRARY)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(openjdk_java_files)
LOCAL_JAVA_RESOURCE_DIRS := $(core_resource_dirs)
LOCAL_NO_STANDARD_LIBRARIES := true
LOCAL_JAVACFLAGS := $(local_javac_flags)
LOCAL_JACK_FLAGS := $(local_jack_flags)
LOCAL_DX_FLAGS := --core-library
LOCAL_MODULE_TAGS := optional
LOCAL_JAVA_LANGUAGE_VERSION := 1.8
LOCAL_MODULE := core-oj
LOCAL_JAVA_LIBRARIES := core-all
LOCAL_NOTICE_FILE := $(LOCAL_PATH)/ojluni/NOTICE
LOCAL_REQUIRED_MODULES := tzdata tzlookup.xml
LOCAL_CORE_LIBRARY := true
include $(BUILD_JAVA_LIBRARY)

# Definitions to make the core library.
include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(non_openjdk_java_files) $(android_icu4j_src_files)
LOCAL_JAVA_RESOURCE_DIRS := $(android_icu4j_resource_dirs)
LOCAL_NO_STANDARD_LIBRARIES := true
LOCAL_JAVACFLAGS := $(local_javac_flags)
LOCAL_JACK_FLAGS := $(local_jack_flags)
LOCAL_DX_FLAGS := --core-library
LOCAL_MODULE_TAGS := optional
LOCAL_JAVA_LANGUAGE_VERSION := 1.8
LOCAL_MODULE := core-libart
LOCAL_JAVA_LIBRARIES := core-all
ifeq ($(EMMA_INSTRUMENT),true)
ifneq ($(EMMA_INSTRUMENT_STATIC),true)
    # For instrumented build, include Jacoco classes into core-libart.
    LOCAL_STATIC_JAVA_LIBRARIES := jacocoagent
endif # EMMA_INSTRUMENT_STATIC
endif # EMMA_INSTRUMENT
LOCAL_CORE_LIBRARY := true
LOCAL_REQUIRED_MODULES := tzdata tzlookup.xml
include $(BUILD_JAVA_LIBRARY)

# A library that exists to satisfy javac when
# compiling source code that contains lambdas.
include $(CLEAR_VARS)
LOCAL_SRC_FILES := $(openjdk_lambda_stub_files) $(openjdk_lambda_duplicate_stub_files)
LOCAL_NO_STANDARD_LIBRARIES := true
LOCAL_JAVACFLAGS := $(local_javac_flags)
LOCAL_JACK_FLAGS := $(local_jack_flags)
LOCAL_DX_FLAGS := --core-library
LOCAL_MODULE_TAGS := optional
LOCAL_JAVA_LANGUAGE_VERSION := 1.8
LOCAL_MODULE := core-lambda-stubs
LOCAL_JAVA_LIBRARIES := core-all
LOCAL_NOTICE_FILE := $(LOCAL_PATH)/ojluni/NOTICE
LOCAL_CORE_LIBRARY := true
LOCAL_UNINSTALLABLE_MODULE := true
include $(BUILD_JAVA_LIBRARY)

ifeq ($(LIBCORE_SKIP_TESTS),)
# A guaranteed unstripped version of core-oj and core-libart.
# The build system may or may not strip the core-oj and core-libart jars,
# but these will not be stripped. See b/24535627.

# Build libcore test rules for target

# Build libcore test rules for host
endif

ifeq ($(LIBCORE_SKIP_TESTS),)
# Build a library just containing files from luni/src/test/filesystems for use in tests.
my_filesystemstest_jar := $(intermediates)/filesystemstest.jar
$(my_filesystemstest_jar): $(LOCAL_BUILT_MODULE)
	$(call copy-file-to-target)
endif

ifeq ($(LIBCORE_SKIP_TESTS),)
# Make the core-tests library.
endif

ifeq ($(LIBCORE_SKIP_TESTS),)
# Make the core-tests-support library.
endif

ifeq ($(LIBCORE_SKIP_TESTS),)
# Make the jsr166-tests library.
endif

# Make the core-ojtests library.
ifeq ($(LIBCORE_SKIP_TESTS),)
endif

# Make the core-ojtests-public library. Excludes any private API tests.
ifeq ($(LIBCORE_SKIP_TESTS),)
endif

#
# Build for the host.
#

ifeq ($(HOST_OS),linux)

# Definitions to make the core library.

# A library that exists to satisfy javac when
# compiling source code that contains lambdas.

# Make the core-tests-hostdex library.
ifeq ($(LIBCORE_SKIP_TESTS),)
endif

# Make the core-tests-support library.
ifeq ($(LIBCORE_SKIP_TESTS),)
endif

# Make the core-ojtests-hostdex library.
ifeq ($(LIBCORE_SKIP_TESTS),)
endif

endif # HOST_OS == linux

#
# Local droiddoc for faster libcore testing
#
#
# Run with:
#     mm -j32 libcore-docs
#
# Main output:
#     ../out/target/common/docs/libcore/reference/packages.html
#
# All text for proofreading (or running tools over):
#     ../out/target/common/docs/libcore-proofread.txt
#
# TODO list of missing javadoc, etc:
#     ../out/target/common/docs/libcore-docs-todo.html
#
# Rerun:
#     rm -rf ../out/target/common/docs/libcore-timestamp && mm -j32 libcore-docs
#

openjdk_java_files :=
non_openjdk_java_files :=
