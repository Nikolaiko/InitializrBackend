package com.android.initializr.builders.fileBuilders

import com.android.initializr.utils.ROOT_SECTION_NAME
import com.android.initializr.model.gradleScripts.GradleSection
import java.lang.Integer.max
import java.lang.StringBuilder

class GradleFileBuilder {
    private val builder: StringBuilder = StringBuilder()

    fun buildFile(rootSection: GradleSection): String {
        builder.clear()

        buildCurrentSection(0, rootSection)
        return builder.toString()
    }

    private fun buildCurrentSection(tabCount: Int, section: GradleSection) {
        if (section.name != ROOT_SECTION_NAME) {
            appendTabs(max(tabCount - 1, 0))
            builder.append("${section.name} {\n")
        }

        for (sectionLine in section.lines) {
            appendTabs(tabCount)
            builder.append("$sectionLine \n")
        }

        if (section.childSections.isNotEmpty() && section.lines.isNotEmpty()) {
            builder.append("\n")
        }

        for (childSection in section.childSections) {
            buildCurrentSection(tabCount + 1, childSection)
            if (childSection != section.childSections.last()) {
                builder.append("\n")
            }
        }

        if (section.name != ROOT_SECTION_NAME) {
            appendTabs(max(tabCount - 1, 0))
            builder.append("}\n")
        }
    }

    private fun appendTabs(tabCount: Int) {
        if (tabCount > 0) {
            for (i in 0..tabCount) {
                builder.append("\t")
            }
        }
    }
}