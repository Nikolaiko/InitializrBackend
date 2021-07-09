package com.android.initializr.ui

import com.android.initializr.model.dependencies.Dependency
import com.android.initializr.model.project.ProjectData
import com.android.initializr.services.interfaces.ProjectService
import com.android.initializr.utils.allAvailableDependencies
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.checkbox.Checkbox
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.html.Anchor
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.radiobutton.RadioButtonGroup
import com.vaadin.flow.component.splitlayout.SplitLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.InputStreamFactory
import com.vaadin.flow.server.StreamResource
import com.vaadin.ui.CheckBoxGroup
import org.springframework.beans.factory.annotation.Autowired
import java.io.FileInputStream


@Route(MAIN_SCREEN_PATH)
class MainScreen(
        @Autowired private val projectBuilder: ProjectService
) : SplitLayout() {

    private var projectData = ProjectData()

    init {
        addToPrimary(constructMainLayout())
        addToSecondary(constructDependenciesLayout())

        setPrimaryStyle("minWidth", "50%")
        setPrimaryStyle("maxWidth", "50%")

        setSecondaryStyle("minWidth", "50%")
        setSecondaryStyle("maxWidth", "50%")


    }

    private fun constructMainLayout(): VerticalLayout {
        val verticalLayout = VerticalLayout()

        val gradleDSLGroup = RadioButtonGroup<String>()
        gradleDSLGroup.label = "Gradle DSL"
        gradleDSLGroup.setItems("Kotlin", "Groovy")
        gradleDSLGroup.value = "Groovy"
        verticalLayout.add(gradleDSLGroup)

        val groupText = TextField("Group", "com.example", "project group")
        verticalLayout.add(groupText)

        val artifactText = TextField("Artifact", "demo", "artifact name")
        verticalLayout.add(artifactText)

        val nameText = TextField("Name", "demo", "project name")
        verticalLayout.add(nameText)

        val packageText = TextField("Package", "com.example.demo", "project package")
        verticalLayout.add(packageText)

        val button = Button("Accessible")
        verticalLayout.add(button)

        button.addClickListener {
            val s = projectBuilder.buildProjectZipFile(projectData)
            val resss = StreamResource("fgfggf.zip", InputStreamFactory {
                FileInputStream(s)
            })

            val downloadLink = Anchor(resss, "dsddsds")
            downloadLink.getElement().setAttribute("download", true)
            verticalLayout.add(downloadLink)
        }

        return verticalLayout
    }

    private fun constructDependenciesLayout(): VerticalLayout {
        val dependenciesLayout = VerticalLayout()
        val addDependencyButton = Button("Add Dependency")
        addDependencyButton.addClickListener {
            addDependency()
        }

        dependenciesLayout.add(addDependencyButton)

        return dependenciesLayout
    }

    private fun addDependency() {
        val dependencyDialog = Dialog()
        for (dependency in allAvailableDependencies) {
            val checkBoxGroup = Checkbox(dependency.id.name)
            checkBoxGroup.addValueChangeListener {
                println("${dependency.id} is ${it.value}")
            }
            dependencyDialog.add(checkBoxGroup)
        }
        dependencyDialog.open()
    }
}