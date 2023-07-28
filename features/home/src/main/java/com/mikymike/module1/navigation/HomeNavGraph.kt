package com.mikymike.module1.navigation

import com.mikymike.module1.destinations.HomeScreenDestination
import com.mikymike.module1.destinations.LiveScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object HomeNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
        HomeScreenDestination, LiveScreenDestination
    ).associateBy { it.route }
    override val route: String = "home"
    override val startRoute: Route = HomeScreenDestination
    override val nestedNavGraphs: List<NavGraphSpec> = emptyList()
}