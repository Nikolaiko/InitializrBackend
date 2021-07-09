package com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers

import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.implementations.*
import com.android.initializr.builders.gradleScriptBuilders.dependencyResolvers.interfaces.DependencyResolver
import com.android.initializr.model.dependencies.DependencyId

object DependenciesResolver {
    private val resolvers: Map<DependencyId, DependencyResolver> = mapOf(
            Pair<DependencyId, DependencyResolver>(DependencyId.DAGGER_LIBRARY, DaggerResolver()),
            Pair<DependencyId, DependencyResolver>(DependencyId.NAVIGATION_COMPONENT_LIBRARY, NavigationComponentResolver()),
            Pair<DependencyId, DependencyResolver>(DependencyId.ROOM_LIBRARY, RoomResolver()),
            Pair<DependencyId, DependencyResolver>(DependencyId.CRASHLYTICS, CrashlyticsResolver()),
            Pair<DependencyId, DependencyResolver>(DependencyId.GOOGLE_MAPS, GoogleMapsResolver()),
            Pair<DependencyId, DependencyResolver>(DependencyId.TOOTHPICK, ToothpickResolver())
    )

    fun getResolver(dependencyName: DependencyId): DependencyResolver? = resolvers[dependencyName]
}