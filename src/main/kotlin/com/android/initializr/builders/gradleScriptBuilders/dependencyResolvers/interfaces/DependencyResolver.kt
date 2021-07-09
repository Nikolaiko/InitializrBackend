package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces

import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

interface DependencyResolver {
    val id: DependencyId

    fun needPlugins(): Boolean
    fun needDependency(): Boolean
    fun needClasspath(): Boolean

    fun getPlugins(): List<PluginId>
    fun getModuleDependency(): List<String>
    fun getAppClasspath(): List<ClasspathId>
}