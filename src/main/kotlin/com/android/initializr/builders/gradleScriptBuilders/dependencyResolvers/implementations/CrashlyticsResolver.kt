package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

class CrashlyticsResolver : DependencyResolver {
    override val id: DependencyId = DependencyId.CRASHLYTICS

    override fun needPlugins(): Boolean = true
    override fun needDependency(): Boolean = true
    override fun needClasspath(): Boolean = true

    override fun getPlugins(): List<PluginId> = listOf(
            PluginId.FIREBASE_CRASHLYTICS,
            PluginId.GOOGLE_SERVICES
    )

    override fun getModuleDependency(): List<String> = listOf(
            "implementation \"com.google.firebase:firebase-analytics-ktx:17.4.4\"",
            "implementation \"com.google.firebase:firebase-crashlytics:17.1.1\""
    )

    override fun getAppClasspath(): List<ClasspathId> = listOf(
            ClasspathId.GOOGLE_SERVICE_CLASSPATH,
            ClasspathId.GOOGLE_FIREBASE_CLASSPATH
    )
}