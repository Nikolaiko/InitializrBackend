package com.android.initializr.utils

import com.android.initializr.model.dependencies.Dependency
import com.android.initializr.model.dependencies.DependencyId

val allAvailableDependencies = listOf(
        Dependency(DependencyId.DAGGER_LIBRARY, "Dagger"),
        Dependency(DependencyId.ROOM_LIBRARY, "Room"),
        Dependency(DependencyId.NAVIGATION_COMPONENT_LIBRARY, "Nav")
)