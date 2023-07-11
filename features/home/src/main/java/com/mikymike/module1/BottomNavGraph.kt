package com.mikymike.module1

import com.mikymike.module2.GamesNavGraph
import com.mikymike.module3.ShopNavGraph
import com.mikymike.profile.ProfileNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object BottomNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = emptyMap()
    override val route: String = "bottom"
    override val startRoute: Route = GamesNavGraph
    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        GamesNavGraph, ShopNavGraph, ProfileNavGraph
    )
}