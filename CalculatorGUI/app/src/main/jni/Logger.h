#pragma once
#ifndef _DSE_LOGGER_H_
#define  _DSE_LOGGER_H_

#if defined _MSC_BUILD || defined __APPLE__

#include <iostream>

#define LOGI(...)   printf("[INFO] ");printf("["); printf(__func__); printf("] "); printf(__VA_ARGS__); printf("\n")
#define LOGE(...)   printf("[ERROR] ");printf("["); printf(__func__); printf("] "); printf(__VA_ARGS__); printf("\n")
#define LOGW(...)   printf("[WARNING] ");printf("["); printf(__func__); printf("] "); printf(__VA_ARGS__); printf("\n")
#define LOGD(...)   printf("[DEBUG] ");printf("["); printf(__func__); printf("] "); printf(__VA_ARGS__); printf("\n")

#else

#include <android\log.h>

#define LOGI(...)   __android_log_print(ANDROID_LOG_INFO, __FUNCTION__, __VA_ARGS__)
#define LOGE(...)   __android_log_print(ANDROID_LOG_ERROR, __FUNCTION__, __VA_ARGS__)
#define LOGW(...)   __android_log_print(ANDROID_LOG_WARN, __FUNCTION__, __VA_ARGS__)
#define LOGD(...)   __android_log_print(ANDROID_LOG_DEBUG, __FUNCTION__, __VA_ARGS__)

#endif

#define __STRING(ARGS) #ARGS
#define STRING(ARGS) __STRING(ARGS)

#endif
