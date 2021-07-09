package com.android.initializr.builders.gradleScriptBuilders.groovy

import com.android.initializr.utils.ROOT_SECTION_NAME
import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.DependenciesResolver
import com.android.initializr.model.classpath.ClasspathId
import com.android.initializr.model.gradleScripts.GradleSection
import com.android.initializr.model.project.ProjectData

class AppGradleScriptBuilder {
    companion object {
        const val DEPENDENCY_SECTION_NAME: String = "dependencies"
        const val REPOSITORIES_SECTION_NAME: String = "repositories"
        const val ALL_PROJECTS_SECTION_NAME: String = "allprojects"
        const val BUILD_SCRIPT_SECTION_NAME: String = "buildscript"
    }

    fun buildRootSection(settings: ProjectData): GradleSection {
        return GradleSection(
                ROOT_SECTION_NAME,
                emptyList(),
                listOf(
                        buildBuildScriptSection(settings),
                        buildAllProjectsSection(settings)
                )
        )
    }

    private fun buildBuildScriptSection(settings: ProjectData): GradleSection {
        return GradleSection(
                BUILD_SCRIPT_SECTION_NAME,
                emptyList(),
                listOf(
                        buildRepositoriesSection(settings),
                        buildDependenciesSection(settings)
                )
        )
    }

    private fun buildAllProjectsSection(settings: ProjectData): GradleSection {
        return GradleSection(
                ALL_PROJECTS_SECTION_NAME,
                emptyList(),
                listOf(buildRepositoriesSection(settings))
        )
    }

    private fun buildRepositoriesSection(settings: ProjectData): GradleSection {
        return GradleSection(
                REPOSITORIES_SECTION_NAME,
                listOf(
                        "google()",
                        "jcenter()",
                        "mavenCentral()"
                ),
                emptyList()
        )
    }

    private fun buildDependenciesSection(settings: ProjectData): GradleSection {
        val dependenciesListId = mutableListOf(
                ClasspathId.ANDROID_TOOLS_CLASSPATH,
                ClasspathId.KOTLIN_GRADLE_PLUGIN
        )

        for (dependency in settings.dependencies) {
            val dependencies = DependenciesResolver.getResolver(dependency)?.getAppClasspath() ?: emptyList()
            dependencies.forEach {
                if (!dependenciesListId.contains(it)) {
                    dependenciesListId.add(it)
                }
            }
        }

        val dependenciesLines = dependenciesListId.map {
            ClasspathId.lineFromClasspath(it)
        }

        return GradleSection(
                DEPENDENCY_SECTION_NAME,
                dependenciesLines,
                emptyList()
        )
    }
}