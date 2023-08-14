package com.mikymike.navigationomada.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mikymike.module1.navigation.HomeNavGraph
import com.mikymike.navigationomada.MainNavGraph
import com.mikymike.onboarding.OnBoardingNavGraph
import com.mikymike.onboarding.navigation.OnBoardingIsDone
import com.mikymike.onboarding.navigation.OnBoardingLoadingFinished
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun OmadaApp(
    modifier: Modifier = Modifier,
    showOnBoarding: Boolean = false,
    appLoadingIsFinished: () -> Unit = {},
    onBoardingIsDone: () -> Unit
) {
    val navController = rememberNavController()
    val startRoute = if (showOnBoarding) OnBoardingNavGraph else HomeNavGraph

    DestinationsNavHost(modifier = modifier,
        navController = navController,
        startRoute = startRoute,
        navGraph = MainNavGraph,
        dependenciesContainerBuilder = {
            dependency(OnBoardingNavGraph) {
                OnBoardingIsDone {
                    navController.navigate(HomeNavGraph) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId)
                    }
                    onBoardingIsDone()
                }
            }
            dependency(OnBoardingNavGraph) {
                OnBoardingLoadingFinished {
                    appLoadingIsFinished()
                }
            }
        })
}
