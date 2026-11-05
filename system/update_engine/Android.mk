#
# Copyright (C) 2015 The Android Open Source Project
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

LOCAL_PATH := $(my-dir)

# Default values for the USE flags. Override these USE flags from your product
# by setting BRILLO_USE_* values. Note that we define local variables like
# local_use_* to prevent leaking our default setting for other packages.
local_use_binder := $(if $(BRILLO_USE_BINDER),$(BRILLO_USE_BINDER),1)
local_use_hwid_override := \
    $(if $(BRILLO_USE_HWID_OVERRIDE),$(BRILLO_USE_HWID_OVERRIDE),0)
# "libcros" gates the LibCrosService exposed by the Chrome OS' chrome browser to
# the system layer.
local_use_libcros := $(if $(BRILLO_USE_LIBCROS),$(BRILLO_USE_LIBCROS),0)
local_use_mtd := $(if $(BRILLO_USE_MTD),$(BRILLO_USE_MTD),0)

# IoT devices use Omaha for updates.
local_use_omaha := $(if $(filter true,$(PRODUCT_IOT)),1,0)

ue_common_cflags := \
    -DUSE_BINDER=$(local_use_binder) \
    -DUSE_HWID_OVERRIDE=$(local_use_hwid_override) \
    -DUSE_LIBCROS=$(local_use_libcros) \
    -DUSE_MTD=$(local_use_mtd) \
    -DUSE_OMAHA=$(local_use_omaha) \
    -D_FILE_OFFSET_BITS=64 \
    -D_POSIX_C_SOURCE=199309L \
    -Wa,--noexecstack \
    -Wall \
    -Werror \
    -Wextra \
    -Wformat=2 \
    -Wno-psabi \
    -Wno-unused-parameter \
    -ffunction-sections \
    -fstack-protector-strong \
    -fvisibility=hidden
ue_common_cppflags := \
    -Wnon-virtual-dtor \
    -fno-strict-aliasing
ue_common_ldflags := \
    -Wl,--gc-sections
ue_common_c_includes := \
    $(LOCAL_PATH)/client_library/include \
    system
ue_common_shared_libraries := \
    libbrillo-stream \
    libbrillo \
    libchrome
ue_common_static_libraries := \
    libgtest_prod \

# update_metadata-protos (type: static_library)
# ========================================================
# Protobufs.
ue_update_metadata_protos_exported_static_libraries := \
    update_metadata-protos
ue_update_metadata_protos_exported_shared_libraries := \
    libprotobuf-cpp-lite

ue_update_metadata_protos_src_files := \
    update_metadata.proto

# Build for the host.

# Build for the target.

# libpayload_consumer (type: static_library)
# ========================================================
# The payload application component and common dependencies.
ue_libpayload_consumer_exported_static_libraries := \
    update_metadata-protos \
    libxz \
    libbz \
    libbspatch \
    $(ue_update_metadata_protos_exported_static_libraries)
ue_libpayload_consumer_exported_shared_libraries := \
    libcrypto \
    $(ue_update_metadata_protos_exported_shared_libraries)

ue_libpayload_consumer_src_files := \
    common/action_processor.cc \
    common/boot_control_stub.cc \
    common/clock.cc \
    common/constants.cc \
    common/cpu_limiter.cc \
    common/error_code_utils.cc \
    common/file_fetcher.cc \
    common/hash_calculator.cc \
    common/http_common.cc \
    common/http_fetcher.cc \
    common/hwid_override.cc \
    common/multi_range_http_fetcher.cc \
    common/platform_constants_android.cc \
    common/prefs.cc \
    common/subprocess.cc \
    common/terminator.cc \
    common/utils.cc \
    payload_consumer/bzip_extent_writer.cc \
    payload_consumer/delta_performer.cc \
    payload_consumer/download_action.cc \
    payload_consumer/extent_writer.cc \
    payload_consumer/file_descriptor.cc \
    payload_consumer/file_writer.cc \
    payload_consumer/filesystem_verifier_action.cc \
    payload_consumer/install_plan.cc \
    payload_consumer/payload_constants.cc \
    payload_consumer/payload_verifier.cc \
    payload_consumer/postinstall_runner_action.cc \
    payload_consumer/xz_extent_writer.cc

ifeq ($(HOST_OS),linux)
# Build for the host.
endif  # HOST_OS == linux

# Build for the target.

# libupdate_engine_boot_control (type: static_library)
# ========================================================
# A BootControl class implementation using Android's HIDL boot_control HAL.
ue_libupdate_engine_boot_control_exported_static_libraries := \
    update_metadata-protos \
    $(ue_update_metadata_protos_exported_static_libraries)

ue_libupdate_engine_boot_control_exported_shared_libraries := \
    libhwbinder \
    libhidlbase \
    libutils \
    android.hardware.boot@1.0 \
    $(ue_update_metadata_protos_exported_shared_libraries)

ifeq ($(local_use_omaha),1)

# libupdate_engine (type: static_library)
# ========================================================
# The main daemon static_library with all the code used to check for updates
# with Omaha and expose a DBus daemon.
ue_libupdate_engine_exported_c_includes := \
    external/cros/system_api/dbus
ue_libupdate_engine_exported_static_libraries := \
    libpayload_consumer \
    update_metadata-protos \
    libbz \
    libfs_mgr \
    libbase \
    liblog \
    $(ue_libpayload_consumer_exported_static_libraries) \
    $(ue_update_metadata_protos_exported_static_libraries) \
    libupdate_engine_boot_control \
    $(ue_libupdate_engine_boot_control_exported_static_libraries)
ue_libupdate_engine_exported_shared_libraries := \
    libmetrics \
    libexpat \
    libbrillo-policy \
    libcurl \
    libcutils \
    libssl \
    $(ue_libpayload_consumer_exported_shared_libraries) \
    $(ue_update_metadata_protos_exported_shared_libraries) \
    $(ue_libupdate_engine_boot_control_exported_shared_libraries)
ifeq ($(local_use_binder),1)
ue_libupdate_engine_exported_shared_libraries += \
    libbinder \
    libbinderwrapper \
    libbrillo-binder \
    libutils
endif  # local_use_binder == 1

else  # local_use_omaha == 1

ifneq ($(local_use_binder),1)
$(error USE_BINDER is disabled but is required in non-Brillo devices.)
endif  # local_use_binder == 1

# libupdate_engine_android (type: static_library)
# ========================================================
# The main daemon static_library used in Android (non-Brillo). This only has a
# loop to apply payloads provided by the upper layer via a Binder interface.
ue_libupdate_engine_android_exported_static_libraries := \
    libpayload_consumer \
    libfs_mgr \
    libbase \
    liblog \
    $(ue_libpayload_consumer_exported_static_libraries) \
    libupdate_engine_boot_control \
    $(ue_libupdate_engine_boot_control_exported_static_libraries)
ue_libupdate_engine_android_exported_shared_libraries := \
    $(ue_libpayload_consumer_exported_shared_libraries) \
    $(ue_libupdate_engine_boot_control_exported_shared_libraries) \
    libandroid_net \
    libbinder \
    libbinderwrapper \
    libbrillo-binder \
    libcutils \
    libcurl \
    libssl \
    libutils

endif  # local_use_omaha == 1

# update_engine (type: executable)
# ========================================================
# update_engine daemon.

# update_engine_sideload (type: executable)
# ========================================================
# A static binary equivalent to update_engine daemon that installs an update
# from a local file directly instead of running in the background.

# libupdate_engine_client (type: shared_library)
# ========================================================

# update_engine_client (type: executable)
# ========================================================
# update_engine console client.

# libpayload_generator (type: static_library)
# ========================================================
# server-side code. This is used for delta_generator and unittests but not
# for any client code.
ue_libpayload_generator_exported_static_libraries := \
    libpayload_consumer \
    update_metadata-protos \
    liblzma \
    $(ue_libpayload_consumer_exported_static_libraries) \
    $(ue_update_metadata_protos_exported_static_libraries)
ue_libpayload_generator_exported_shared_libraries := \
    libext2fs \
    $(ue_libpayload_consumer_exported_shared_libraries) \
    $(ue_update_metadata_protos_exported_shared_libraries)

ue_libpayload_generator_src_files := \
    payload_generator/ab_generator.cc \
    payload_generator/annotated_operation.cc \
    payload_generator/blob_file_writer.cc \
    payload_generator/block_mapping.cc \
    payload_generator/bzip.cc \
    payload_generator/cycle_breaker.cc \
    payload_generator/delta_diff_generator.cc \
    payload_generator/delta_diff_utils.cc \
    payload_generator/ext2_filesystem.cc \
    payload_generator/extent_ranges.cc \
    payload_generator/extent_utils.cc \
    payload_generator/full_update_generator.cc \
    payload_generator/graph_types.cc \
    payload_generator/graph_utils.cc \
    payload_generator/inplace_generator.cc \
    payload_generator/mapfile_filesystem.cc \
    payload_generator/payload_file.cc \
    payload_generator/payload_generation_config.cc \
    payload_generator/payload_signer.cc \
    payload_generator/raw_filesystem.cc \
    payload_generator/tarjan.cc \
    payload_generator/topological_sort.cc \
    payload_generator/xz_android.cc

ifeq ($(HOST_OS),linux)
# Build for the host.
endif  # HOST_OS == linux

# Build for the target.

# delta_generator (type: executable)
# ========================================================
# server-side delta generator.
ue_delta_generator_src_files := \
    payload_generator/generate_delta_main.cc

ifeq ($(HOST_OS),linux)
# Build for the host.
endif  # HOST_OS == linux

# Build for the target.

# Private and public keys for unittests.
# ========================================================
# Generate a module that installs a prebuilt private key and a module that
# installs a public key generated from the private key.
#
# $(1): The path to the private key in pem format.
define ue-unittest-keys
    $(eval include $(CLEAR_VARS)) \
    $(eval LOCAL_MODULE := ue_$(1).pem) \
    $(eval LOCAL_MODULE_CLASS := ETC) \
    $(eval $(ifeq $(BRILLO), 1, LOCAL_MODULE_TAGS := eng)) \
    $(eval LOCAL_SRC_FILES := $(1).pem) \
    $(eval LOCAL_MODULE_PATH := \
        $(TARGET_OUT_DATA_NATIVE_TESTS)/update_engine_unittests) \
    $(eval LOCAL_MODULE_STEM := $(1).pem) \
    $(eval include $(BUILD_PREBUILT)) \
    \
    $(eval include $(CLEAR_VARS)) \
    $(eval LOCAL_MODULE := ue_$(1).pub.pem) \
    $(eval LOCAL_MODULE_CLASS := ETC) \
    $(eval $(ifeq $(BRILLO), 1, LOCAL_MODULE_TAGS := eng)) \
    $(eval LOCAL_MODULE_PATH := \
        $(TARGET_OUT_DATA_NATIVE_TESTS)/update_engine_unittests) \
    $(eval LOCAL_MODULE_STEM := $(1).pub.pem) \
    $(eval include $(BUILD_SYSTEM)/base_rules.mk) \
    $(eval $(LOCAL_BUILT_MODULE) : $(LOCAL_PATH)/$(1).pem ; \
        openssl rsa -in $$< -pubout -out $$@)
endef

$(call ue-unittest-keys,unittest_key)
$(call ue-unittest-keys,unittest_key2)

# Sample images for unittests.
# ========================================================
# Generate a prebuilt module that installs a sample image from the compressed
# sample_images.tar.bz2 file used by the unittests.
#
# $(1): The filename in the sample_images.tar.bz2
define ue-unittest-sample-image
    $(eval include $(CLEAR_VARS)) \
    $(eval LOCAL_MODULE := ue_unittest_$(1)) \
    $(eval LOCAL_MODULE_CLASS := EXECUTABLES) \
    $(eval $(ifeq $(BRILLO), 1, LOCAL_MODULE_TAGS := eng)) \
    $(eval LOCAL_MODULE_PATH := \
        $(TARGET_OUT_DATA_NATIVE_TESTS)/update_engine_unittests/gen) \
    $(eval LOCAL_MODULE_STEM := $(1)) \
    $(eval include $(BUILD_SYSTEM)/base_rules.mk) \
    $(eval $(LOCAL_BUILT_MODULE) : \
        $(LOCAL_PATH)/sample_images/sample_images.tar.bz2 ; \
        tar -jxf $$< -C $$(dir $$@) $$(notdir $$@) && touch $$@)
endef

$(call ue-unittest-sample-image,disk_ext2_1k.img)
$(call ue-unittest-sample-image,disk_ext2_4k.img)
$(call ue-unittest-sample-image,disk_ext2_4k_empty.img)
$(call ue-unittest-sample-image,disk_ext2_unittest.img)

# Zlib Fingerprint
# ========================================================

# update_engine.conf
# ========================================================

# test_http_server (type: executable)
# ========================================================
# Test HTTP Server.

# bsdiff (type: executable)
# ========================================================
# We need bsdiff in the update_engine_unittests directory, so we build it here.

# test_subprocess (type: executable)
# ========================================================
# Test helper subprocess program.

# update_engine_unittests (type: executable)
# ========================================================
# Main unittest file.

# Update payload signing public key.
# ========================================================
ifeq ($(PRODUCT_IOT),true)
endif  # PRODUCT_IOT

# Brillo update payload generation script
# ========================================================
ifeq ($(HOST_OS),linux)
endif  # HOST_OS == linux
