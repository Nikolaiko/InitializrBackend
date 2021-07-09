package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.plugins.PluginId

class ToothpickResolver: DependencyResolver {
    override val id = DependencyId.TOOTHPICK

    override fun needPlugins(): Boolean = true
    override fun needDependency(): Boolean = true
    override fun needClasspath(): Boolean = false

    override fun getPlugins(): List<PluginId> = listOf(PluginId.ANDROID_KAPT)

    override fun getModuleDependency(): List<String> = listOf(
            "implementation \"com.github.stephanenicolas.toothpick:ktp:3.1.0\"",
            "kapt \"com.github.stephanenicolas.toothpick:toothpick-compiler:3.1.0\"",
            "testImplementation \"com.github.stephanenicolas.toothpick:toothpick-testing-junit5:3.1.0\""
    )

    override fun getAppClasspath(): List<ClasspathId> = emptyList()
}