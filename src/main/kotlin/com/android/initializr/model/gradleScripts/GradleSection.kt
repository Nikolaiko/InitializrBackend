package com.android.initializr.model.gradleScripts

data class GradleSection(
        val name: String,
        val lines: List<String>,
        val childSections: List<GradleSection>
)