package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

class GoogleMapsResolver : DependencyResolver {
    override val id: DependencyId = DependencyId.CRASHLYTICS

    override fun needPlugins(): Boolean = true
    override fun needDependency(): Boolean = true
    override fun needClasspath(): Boolean = true

    override fun getPlugins(): List<PluginId> = listOf(
            PluginId.GOOGLE_SERVICES
    )

    override fun getModuleDependency(): List<String> = listOf(
            "implementation \"com.google.maps:google-maps-services:0.2.4\"",
            "implementation \"com.google.android.gms:play-services-maps:17.0.0\"",
            "implementation \"org.slf4j:slf4j-nop:1.7.25\""
    )

    override fun getAppClasspath(): List<ClasspathId> = listOf(
            ClasspathId.GOOGLE_SERVICE_CLASSPATH
    )
}