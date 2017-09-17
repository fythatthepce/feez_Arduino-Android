LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := com_example_feez_ndkfee1_NativeClass.cpp

LOCAL_LDLIBS += -llog
LOCAL_MODULE := MyLibs


include $(BUILD_SHARED_LIBRARY)
