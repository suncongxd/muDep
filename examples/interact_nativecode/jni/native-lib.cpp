#include <jni.h>
//#include "com_example_interact_nativecode_MainActivity.h"
#include  "string.h"
#include  "stdlib.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_example_interact_1nativecode_MainActivity_propagateData(JNIEnv *env, jobject thisObj,
                                                       jobject data, jobject ev, jboolean choice) {
    jclass cd = env->GetObjectClass(data);
    jfieldID fd = env->GetFieldID(cd, "s", "Ljava/lang/String;");
    jobject imei = env->GetObjectField(data, fd); //imei = data.s

    if(choice == 0){//choice=0 means choice= false
        cd = env->GetObjectClass(ev);
        fd = env->GetFieldID(cd, "s", "Ljava/lang/String;");
        env->SetObjectField(ev, fd, imei); //ev.s = imei
    }
}

