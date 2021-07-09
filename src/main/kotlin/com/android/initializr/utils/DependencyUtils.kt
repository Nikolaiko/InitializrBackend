package com.android.initializr.utils

import com.android.initializr.model.dependencies.DependencyId

val dependencyMap = mapOf(
        "dagger" to DependencyId.DAGGER_LIBRARY,
        "navcomponent" to DependencyId.NAVIGATION_COMPONENT_LIBRARY,
        "room" to DependencyId.ROOM_LIBRARY,
        "toothpick" to DependencyId.TOOTHPICK,
        "crashlytics" to DependencyId.CRASHLYTICS,
        "googlemaps" to DependencyId.GOOGLE_MAPS
)