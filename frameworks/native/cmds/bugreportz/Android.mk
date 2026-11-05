LOCAL_PATH:= $(call my-dir)

# bugreportz
# ==========

include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
   bugreportz.cpp \
   main.cpp \

LOCAL_MODULE:= bugreportz

LOCAL_CFLAGS := -Werror -Wall

LOCAL_SHARED_LIBRARIES := \
    libbase \
    libcutils \

include $(BUILD_EXECUTABLE)

# bugreportz_test
# ===============
