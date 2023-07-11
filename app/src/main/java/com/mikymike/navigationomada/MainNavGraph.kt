package com.mikymike.navigationomada

import com.mikymike.module1.HomeNavGraph
import com.mikymike.navigationomada.ui.destinations.BottomSheetDestination
import com.mikymike.onboarding.OnBoardingNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object MainNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> =
        listOf(BottomSheetDestination).associateBy { it.route }
    override val route: String = "main"
    override val startRoute: Route = HomeNavGraph
    override val nestedNavGraphs = listOf(
        HomeNavGraph, OnBoardingNavGraph
    )
}