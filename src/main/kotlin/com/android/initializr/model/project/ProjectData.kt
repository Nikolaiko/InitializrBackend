package com.android.initializr.model.project

import com.android.initializr.model.dependencies.DependencyId

data class ProjectData(
        val type: ProjectType = ProjectType.APPLICATION,
        val minSdkVersion: String = "23",
        val targetSdkVersion: String = "28",

        val compileSdkVersion: String = "28",
        val buildToolsVersion: String = "30",

        val group: String = "com.example",
        val name: String = "Demo",

        val dependencies: List<DependencyId> = emptyList()
)