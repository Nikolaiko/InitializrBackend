package com.android.initializr.model.classpath

enum class ClasspathId() {
    GOOGLE_SERVICE_CLASSPATH,
    GOOGLE_FIREBASE_CLASSPATH,
    ANDROID_TOOLS_CLASSPATH,
    KOTLIN_GRADLE_PLUGIN;

    companion object {
        fun lineFromClasspath(value: ClasspathId) = when(value) {
            GOOGLE_FIREBASE_CLASSPATH -> "classpath \"com.google.firebase:firebase-crashlytics-gradle:2.2.0\""
            GOOGLE_SERVICE_CLASSPATH -> "classpath \"com.google.gms:google-services:4.3.3\""
            ANDROID_TOOLS_CLASSPATH -> "classpath \"com.android.tools.build:gradle:4.0.0\""
            KOTLIN_GRADLE_PLUGIN -> "classpath \"org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72\""
        }
    }
}