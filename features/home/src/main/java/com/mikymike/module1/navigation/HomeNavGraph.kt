package com.mikymike.module1.navigation

import com.mikymike.cards.destinations.CardsScreenDestination
import com.mikymike.module1.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object HomeNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> =
        listOf(HomeScreenDestination, CardsScreenDestination).associateBy { it.route }
    override val route: String = "home"
    override val startRoute: Route = HomeScreenDestination
    override val nestedNavGraphs: List<NavGraphSpec> = emptyList()
}