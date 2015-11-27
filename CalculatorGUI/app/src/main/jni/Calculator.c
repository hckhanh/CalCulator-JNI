//
// Created by ohk1hc on 11/27/2015.
//

#include "Calculator.h"


JNIEXPORT jfloat JNICALL Java_com_example_ohk1hc_calculatorgui_Calculator_calculate
        (JNIEnv* env, jclass jobj, jfloat jnumber1, jfloat jnumber2, jint joperator){
    switch (joperator) {
        case SUMMATION:
            return jnumber1 + jnumber2;
        case SUBTRACTION:
            return jnumber1 - jnumber2;
        case MUTIPLICATION:
            return jnumber1 * jnumber2;
        case DIVISION:
            if (jnumber2 == 0) {
                jclass jclas = (*env)->FindClass(env, "java/lang/ArithmeticException");
                if ((*env)->ExceptionCheck(env)) {
                    (*env)->ExceptionClear(env);
                }
                (*env)->ThrowNew(env, jclas, "Cannot divide by Zero!");
            } else {
                return jnumber1 / jnumber2;
            }
    }

    LOGD("CalculatorAPI", jnumber1, jnumber2, joperator);
}
