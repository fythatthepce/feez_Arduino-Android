    LOCAL_PATH := $(call my-dir)

	include $(CLEAR_VARS)

	#opencv
	OPENCVROOT:= /Users/feez/AndroidStudioProjects/OpenCV-android-sdk
	OPENCV_CAMERA_MODULES:=on
	OPENCV_INSTALL_MODULES:=on
	OPENCV_LIB_TYPE:=SHARED
	include ${OPENCVROOT}/sdk/native/jni/OpenCV.mk

	LOCAL_SRC_FILES := com_example_feez_feeopencv1_OpencvNativeClass.cpp

	LOCAL_LDLIBS += -llog
	LOCAL_MODULE := MyOpencvLibs


	include $(BUILD_SHARED_LIBRARY)