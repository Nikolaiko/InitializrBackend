package com.android.initializr.services

import com.android.initializr.builders.fileBuilders.GradleFileBuilder
import com.android.initializr.builders.gradleScriptBuilders.groovy.AppGradleScriptBuilder
import com.android.initializr.builders.gradleScriptBuilders.groovy.ModuleGradleScriptBuilder
import com.android.initializr.model.project.ProjectData
import com.android.initializr.services.interfaces.ProjectService
import com.android.initializr.utils.generateTempFolderName
import com.android.initializr.utils.parsePackageNameToFolders
import org.springframework.stereotype.Service
import org.zeroturnaround.zip.ZipUtil
import org.zeroturnaround.zip.commons.FileUtils
import java.io.File
import java.io.FileWriter
import java.io.OutputStream
import java.lang.Exception
import java.nio.file.Files

@Service
class BuildProjectService: ProjectService {
    companion object {
        const val PACKAGE_LINE = "PACKAGE_LINE"
        const val IMPORT_LAYOUT_PACKAGE_LINE = "IMPORT_LAYOUT_LINE"
        const val PROJECT_NAME = "PROJECT_NAME"
    }

    private var destFolderName: String = ""
    private var projectParameters: ProjectData? = null

    override fun buildProject(projectData: ProjectData, outputStream: OutputStream) {
        val folderTemplate = File("template")

        projectParameters = projectData
        destFolderName = generateTempFolderName()

        val destFolder = File(destFolderName)
        try {
            if (!destFolder.exists()) {
                destFolder.mkdirs()
            }
            FileUtils.copyDirectory(folderTemplate, destFolder)

            buildManifestFile()
            buildGradleScriptFiles()
            buildMainActivityFile()
            buildSettingsFile()
            buildStringsResourceFile()


            ZipUtil.pack(destFolder, outputStream)
            outputStream.close()

        } finally {
            if (destFolder.exists()) {
                FileUtils.deleteDirectory(destFolder)
            }
        }
    }

    private fun buildGradleScriptFiles() {
        val gradleFileBuilder = GradleFileBuilder()
        val appGradleScriptBuilder = AppGradleScriptBuilder()
        val moduleGradleScriptBuilder = ModuleGradleScriptBuilder()

        val appSections = appGradleScriptBuilder.buildRootSection(projectParameters!!)
        val moduleSections = moduleGradleScriptBuilder.buildRootSection(projectParameters!!)

        val appGradleScript = gradleFileBuilder.buildFile(appSections)
        val moduleGradleScript = gradleFileBuilder.buildFile(moduleSections)

        val appGradleFile = FileWriter("${destFolderName}/build.gradle", false)
        appGradleFile.write(appGradleScript)
        appGradleFile.close()

        val moduleGradleFile = FileWriter("${destFolderName}/app/build.gradle", false)
        moduleGradleFile.write(moduleGradleScript)
        moduleGradleFile.close()
    }

    private fun buildMainActivityFile() {
        val groupPath = parsePackageNameToFolders(projectParameters!!.group)
        val fullPackagePath = "$destFolderName/app/src/main/java$groupPath"

        val packagesFolder = File(fullPackagePath)
        if (!packagesFolder.exists()) {
            packagesFolder.mkdirs()
        }

        val mainActivityFile = File("$fullPackagePath/MainActivity.kt")
        val writer = FileWriter(mainActivityFile, false)

        val mainActivityTemplate = File("templateFile/MainActivity.kt")
        var templateContent = Files.readString(mainActivityTemplate.toPath())

        templateContent = templateContent.replace(
                PACKAGE_LINE,
                "package ${projectParameters!!.group}"
        )
        writer.write(templateContent)
        writer.close()
    }

    private fun buildSettingsFile() {
        val settingsFile = File("$destFolderName/settings.gradle")
        val writer = FileWriter(settingsFile, true)
        writer.write("\nrootProject.name = \"${projectParameters!!.name}\"")
        writer.close()
    }

    private fun buildManifestFile() {
        val manifestFilePath = "$destFolderName/app/src/main"
        val mainManifestFile = File("$manifestFilePath/AndroidManifest.xml")
        val writer = FileWriter(mainManifestFile, false)

        val mainManifestTemplate = File("templateFile/AndroidManifest.xml")
        var templateContent = Files.readString(mainManifestTemplate.toPath())

        templateContent = templateContent.replace(
                PACKAGE_LINE, projectParameters!!.group
        )
        writer.write(templateContent)
        writer.close()
    }

    private fun buildStringsResourceFile() {
        val stringsResourcePath = "$destFolderName/app/src/main/res/values"
        val resourceFile = File("$stringsResourcePath/strings.xml")
        val writer = FileWriter(resourceFile, false)

        val resourceTemplate = File("templateFile/strings.xml")
        var templateContent = Files.readString(resourceTemplate.toPath())

        templateContent = templateContent.replace(
                PROJECT_NAME, projectParameters!!.name
        )
        writer.write(templateContent)
        writer.close()
    }

    override fun buildProjectZipFile(projectData: ProjectData): File {
        val sourceTemplate = File("template")

        val destFolderName = generateTempFolderName()
        val destFolder = File(destFolderName)

        val tt = File("mine.zip")
        if (tt.exists()) {
            tt.delete()
        }
        tt.createNewFile()

        try {
            if (!destFolder.exists()) {
                destFolder.mkdirs()
            }
            FileUtils.copyDirectory(sourceTemplate, destFolder)

            val gradleFileBuilder = GradleFileBuilder()
            val appGradleScriptBuilder = AppGradleScriptBuilder()
            val moduleGradleScriptBuilder = ModuleGradleScriptBuilder()

            val appSections = appGradleScriptBuilder.buildRootSection(projectData)
            val moduleSections = moduleGradleScriptBuilder.buildRootSection(projectData)

            val appGradleScript = gradleFileBuilder.buildFile(appSections)
            val moduleGradleScript = gradleFileBuilder.buildFile(moduleSections)

            val appGradleFile = FileWriter("${destFolderName}/build.gradle", false)
            appGradleFile.write(appGradleScript)
            appGradleFile.close()

            val moduleGradleFile = FileWriter("${destFolderName}/app/build.gradle", false)
            moduleGradleFile.write(moduleGradleScript)
            moduleGradleFile.close()


            ZipUtil.pack(destFolder, tt)
        } catch (tttt: Exception) {
            if (destFolder.exists()) {
                destFolder.delete()
            }
        }
        return tt
    }
}