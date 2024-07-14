cmake_minimum_required(VERSION 3.4.1)
project("com.example.light")



include_directories(src/main/jni)

# compile frequency domain example to a native library
add_library(
        FrequencyDomainExample
        SHARED
        FrequencyDomain.cpp
        /Users/snow/lightnroll/app/src/main/jni/SuperpoweredAndroidAudioIO.cpp
)

# link the native library against the following libraries
target_link_libraries(
        FrequencyDomainExample
        log
        android
        OpenSLES
        /Users/snow/SuperPowered/libSuperpoweredAndroid${ANDROID_ABI}.a
)

