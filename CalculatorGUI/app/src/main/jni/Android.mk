LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES += LOCAL_PATH

LOCAL_LDLIBS    := -llog
LOCAL_SRC_FILES := Calculator.c
LOCAL_MODULE    := Calculator

include $(BUILD_SHARED_LIBRARY)