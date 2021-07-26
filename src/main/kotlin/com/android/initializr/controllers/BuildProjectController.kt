package com.android.initializr.controllers

import com.android.initializr.builders.gradleScriptBuilders.defaultValues.DEFAULT_BUILD_TOOLS_VERSION
import com.android.initializr.builders.gradleScriptBuilders.defaultValues.DEFAULT_COMPILE_SDK_VERSION
import com.android.initializr.model.dependencies.DependencyId
import com.android.initializr.model.project.ProjectData
import com.android.initializr.model.project.ProjectType
import com.android.initializr.services.interfaces.ProjectService
import com.android.initializr.utils.dependencyMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/build")
class BuildProjectController @Autowired constructor(
        private val service: ProjectService
) {
    @CrossOrigin(
            origins = [
                "*"
            ]
    )
    @GetMapping(value = ["/project"])
    fun generateProject(
            @RequestParam("type") dsl: String,
            @RequestParam("groupId") groupId: String,
            @RequestParam("name") name: String,
            @RequestParam("dependencies", required = false) dependencies: String?,
            response: HttpServletResponse
    ) {
        val dep = mutableListOf<DependencyId>()
        if (dependencies != null) {
            val dependenciesList = dependencies.split(",")
            for (dependency in dependenciesList) {
                val id = dependencyMap[dependency]
                if (id != null) {
                    dep.add(id)
                }
            }
        }

        println("Request received")

        val prg = ProjectData(
                ProjectType.APPLICATION,
                "23",
                "28",
                DEFAULT_COMPILE_SDK_VERSION,
                DEFAULT_BUILD_TOOLS_VERSION,
                groupId,
                name,
                dep
        )

        service.buildProject(prg, response.outputStream)
        response.status = HttpStatus.OK.value()
    }
}