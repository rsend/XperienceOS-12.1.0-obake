#
# This policy configuration will be used by all products that
# inherit from Lineage
#

BOARD_SEPOLICY_DIRS += \
    device/xperience/sepolicy/qcom \
    device/xperience/sepolicy/qcom/$(TARGET_BOARD_PLATFORM)
