package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

class NavigationComponentResolver: DependencyResolver {
    override val id = DependencyId.NAVIGATION_COMPONENT_LIBRARY

    override fun needPlugins(): Boolean = false
    override fun needDependency(): Boolean = true
    override fun needClasspath(): Boolean = false

    override fun getPlugins(): List<PluginId> = emptyList()

    override fun getModuleDependency(): List<String> = listOf(
            "implementation \"androidx.navigation:navigation-fragment-ktx:2.3.0\"",
            "implementation \"androidx.navigation:navigation-ui-ktx:2.3.0\""
    )

    override fun getAppClasspath(): List<ClasspathId> = emptyList()
}