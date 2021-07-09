package com.android.initializr.builders.gradleScriptBuilders.pluginsResolvers

import com.android.initializr.model.plugins.PluginId

val allPluginsList: Map<PluginId, String> = mapOf(
        Pair<PluginId, String>(PluginId.ANDROID_APP, "apply plugin: 'com.android.application'"),
        Pair<PluginId, String>(PluginId.ANDROID_KOTLIN, "apply plugin: 'kotlin-android'"),
        Pair<PluginId, String>(PluginId.ANDROID_KOTLIN_EXT, "apply plugin: 'kotlin-android-extensions'"),
        Pair<PluginId, String>(PluginId.FIREBASE_CRASHLYTICS, "apply plugin: 'com.google.firebase.crashlytics'"),
        Pair<PluginId, String>(PluginId.GOOGLE_SERVICES, "apply plugin: 'com.google.gms.google-services'"),
        Pair<PluginId, String>(PluginId.ANDROID_KAPT, "apply plugin: 'kotlin-kapt'")

)