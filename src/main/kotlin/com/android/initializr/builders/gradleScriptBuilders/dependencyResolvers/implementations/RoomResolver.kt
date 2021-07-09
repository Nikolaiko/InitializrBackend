package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

class RoomResolver: DependencyResolver {
    override val id = DependencyId.ROOM_LIBRARY

    override fun needPlugins(): Boolean = true
    override fun needDependency(): Boolean = true
    override fun needClasspath(): Boolean = false

    override fun getPlugins(): List<PluginId> = listOf(
            PluginId.ANDROID_KAPT
    )

    override fun getModuleDependency(): List<String> = listOf(
            "implementation \"androidx.room:room-runtime:2.2.5\"",
            "kapt \"androidx.room:room-compiler:2.2.5\""
    )

    override fun getAppClasspath(): List<ClasspathId> = emptyList()
}