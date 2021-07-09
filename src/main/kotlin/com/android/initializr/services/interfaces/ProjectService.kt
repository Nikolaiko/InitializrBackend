package com.android.initializr.services.interfaces

import com.android.initializr.model.project.ProjectData
import java.io.File
import java.io.OutputStream

interface ProjectService {
    fun buildProject(projectData: ProjectData, outputStream: OutputStream)
    fun buildProjectZipFile(projectData: ProjectData): File
}