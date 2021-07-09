package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

class DaggerResolver : DependencyResolver {
    override val id = DependencyId.DAGGER_LIBRARY

    override fun needPlugins(): Boolean = true
    override fun needDependency(): Boolean = true
    override fun needClasspath(): Boolean = false

    override fun getPlugins(): List<PluginId> = listOf(
            PluginId.ANDROID_KAPT
    )

    override fun getModuleDependency(): List<String> = listOf(
            "implementation \"com.google.dagger:dagger:2.17\"",
            "kapt \"com.google.dagger:dagger-compiler:2.17\""
    )

    override fun getAppClasspath(): List<ClasspathId> = emptyList()
}