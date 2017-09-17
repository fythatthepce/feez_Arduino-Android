#include <com_example_feez_ndkfee1_NativeClass.h>

JNIEXPORT jstring JNICALL Java_com_example_feez_ndkfee1_NativeClass_getMessageFromJNI
        (JNIEnv *env, jclass obj){
    return env->NewStringUTF("Msg from JNI");
    }
