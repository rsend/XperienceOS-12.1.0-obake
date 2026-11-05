#
# Copyright (C) 2014 The CyanogenMod Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# inherit from common
-include device/motorola/msm8960dt-common/BoardConfigCommon.mk

# inherit from the proprietary version
-include vendor/motorola/obake/BoardConfigVendor.mk

LOCAL_PATH := device/motorola/obake

ifeq ($(OBAKE_IMS_BOOT_CAPTURE),true)
BOARD_SEPOLICY_DIRS += device/motorola/obake/imscapture/sepolicy
endif

# Assert
TARGET_OTA_ASSERT_DEVICE := xt1030,obakem,obakem_verizon,xt1080,obake,obake_verizon,xt1080m,obake-maxx,obake-maxx_verizon

# Bluetooth includes
BOARD_BLUETOOTH_BDROID_BUILDCFG_INCLUDE_DIR := $(LOCAL_PATH)/bluetooth

# Init
TARGET_INIT_VENDOR_LIB := libinit_obake
TARGET_RECOVERY_DEVICE_MODULES := libinit_obake
TARGET_NR_SVC_SUPP_GIDS := 28
TARGET_UNIFIED_DEVICE := true

# Reserve the vmalloc space required by the Obake display/ION memory layout.
# Keep this device-specific so the working Ghost 8.1 board remains unchanged.
BOARD_KERNEL_CMDLINE += vmalloc=400M

# Keep Ghost unchanged; Obake defaults to enforcing. A permissive comparison
# requires debug init: Oreo user init ignores the permissive boot flag.
BOARD_KERNEL_CMDLINE := $(filter-out androidboot.selinux=permissive androidboot.selinux=enforcing,$(BOARD_KERNEL_CMDLINE))
ifeq ($(OBAKE_SELINUX_PERMISSIVE),true)
ifeq ($(filter userdebug eng,$(TARGET_BUILD_VARIANT)),)
$(error OBAKE_SELINUX_PERMISSIVE=true requires a userdebug or eng build)
endif
BOARD_KERNEL_CMDLINE += androidboot.selinux=permissive
else
BOARD_KERNEL_CMDLINE += androidboot.selinux=enforcing
endif

# An enforcing system_server cannot execute framework jars from /data.
# Preopt its jars and the boot classpath on user builds.
ifeq ($(TARGET_BUILD_VARIANT),user)
WITH_DEXPREOPT := true
WITH_DEXPREOPT_BOOT_IMG_AND_SYSTEM_SERVER_ONLY := true
endif

# Lights
TARGET_PROVIDES_LIBLIGHT := true

# Partitions (set for 16 GB)
BOARD_FLASH_BLOCK_SIZE := 131072
BOARD_HAS_LARGE_FILESYSTEM := true
BOARD_BOOTIMAGE_PARTITION_SIZE := 0x00A00000
BOARD_RECOVERYIMAGE_PARTITION_SIZE := 0x00A00000
BOARD_SYSTEMIMAGE_PARTITION_SIZE := 1560281088
BOARD_USERDATAIMAGE_PARTITION_SIZE := 12884885504
BOARD_CACHEIMAGE_FILE_SYSTEM_TYPE := ext4
BOARD_CACHEIMAGE_PARTITION_SIZE := 1001000000
