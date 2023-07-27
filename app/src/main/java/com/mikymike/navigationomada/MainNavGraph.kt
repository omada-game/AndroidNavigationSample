package com.mikymike.navigationomada

import com.mikymike.module1.navigation.HomeNavGraph
import com.mikymike.onboarding.OnBoardingNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object MainNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = emptyMap()
    override val route: String = "main"
    override val startRoute: Route = HomeNavGraph
    override val nestedNavGraphs = listOf(
        HomeNavGraph, OnBoardingNavGraph
    )
}