package com.android.initializr.builders.gradleScriptBuilders.groovy

import com.android.initializr.utils.ROOT_SECTION_NAME
import com.android.initializr.builders.gradleScriptBuilders.defaultValues.*
import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.DependenciesResolver.getResolver
import com.android.initializr.builders.gradleScriptBuilders.pluginsResolvers.allPluginsList
import com.android.initializr.model.gradleScripts.GradleSection
import com.android.initializr.model.plugins.PluginId
import com.android.initializr.model.project.ProjectData

class ModuleGradleScriptBuilder {
    companion object {
        const val DEPENDENCY_SECTION_NAME: String = "dependencies"
        const val ANDROID_SECTION_NAME: String = "android"
        const val DEFAULT_CONFIG_SECTION_NAME: String = "defaultConfig"
    }

    fun buildRootSection(settings: ProjectData): GradleSection {
        val pluginsList: MutableList<String> = mutableListOf(
                allPluginsList[PluginId.ANDROID_APP] ?: "plugin wasn't found ${PluginId.ANDROID_APP}",
                allPluginsList[PluginId.ANDROID_KOTLIN] ?: "plugin wasn't found ${PluginId.ANDROID_KOTLIN}",
                allPluginsList[PluginId.ANDROID_KOTLIN_EXT] ?: "plugin wasn't found ${PluginId.ANDROID_KOTLIN_EXT}"
        )

        for (dependency in settings.dependencies) {
            val plugins = getResolver(dependency)?.getPlugins() ?: emptyList()
            plugins.forEach {
                if (!pluginsList.contains(allPluginsList[it])) {
                    pluginsList.add(allPluginsList[it] ?: "plugin wasn't found $it")
                }
            }
        }

        return GradleSection(
                ROOT_SECTION_NAME,
                pluginsList,
                listOf(
                        buildAndroidSection(settings),
                        buildDependenciesSection(settings)
                )
        )
    }

    private fun buildDependenciesSection(settings: ProjectData): GradleSection {
        val dependenciesList = mutableListOf<String>()
        dependenciesList.addAll(systemDependencies)
        dependenciesList.add("\n")

        dependenciesList.addAll(coreDependencies)
        dependenciesList.add("\n")

        for (dependency in settings.dependencies) {
            val dependencies = getResolver(dependency)?.getModuleDependency() ?: emptyList()
            dependencies.forEach {
                if (!dependenciesList.contains(it)) {
                    dependenciesList.add(it)
                }
            }
        }
        if (settings.dependencies.isNotEmpty()) {
            dependenciesList.add("\n")
        }

        dependenciesList.addAll(testDependencies)

        return GradleSection(
                DEPENDENCY_SECTION_NAME,
                dependenciesList,
                emptyList()
        )
    }

    private fun buildAndroidSection(settings: ProjectData): GradleSection {
        return GradleSection(
                ANDROID_SECTION_NAME,
                listOf(
                        "buildToolsVersion \"$DEFAULT_BUILD_TOOLS_VERSION\"",
                        "compileSdkVersion $DEFAULT_COMPILE_SDK_VERSION"
                ),
                listOf(buildDefaultConfigSection(settings))
        )
    }

    private fun buildDefaultConfigSection(settings: ProjectData): GradleSection {
        return GradleSection(
                DEFAULT_CONFIG_SECTION_NAME,
                listOf(
                        "applicationId \"${settings.group}.${settings.name.toLowerCase()}\"",
                        "minSdkVersion ${settings.minSdkVersion}",
                        "targetSdkVersion ${settings.targetSdkVersion}",
                        "versionCode 1",
                        "versionName \"1.0\"",
                        "testInstrumentationRunner \"androidx.test.runner.AndroidJUnitRunner\""
                ),
                emptyList()
        )
    }
}