#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_net_gapstars_temper_utility_Constants_baseUrl(JNIEnv *env, jclass type) {
    return env->NewStringUTF("http://temper.net");
}