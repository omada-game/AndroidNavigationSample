package com.mikymike.navigationomada.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.compose.rememberNavController
import com.mikymike.module1.navigation.HomeNavGraph
import com.mikymike.navigationomada.MainNavGraph
import com.mikymike.onboarding.OnBoardingIsFinished
import com.mikymike.onboarding.OnBoardingNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun OmadaApp(
    modifier: Modifier = Modifier, splashScreen: SplashScreen, onBoardingIsFinished: Boolean = false
) {
    val navController = rememberNavController()
    val startRoute = if (onBoardingIsFinished) HomeNavGraph else OnBoardingNavGraph

    DestinationsNavHost(modifier = modifier,
        navController = navController,
        startRoute = startRoute,
        navGraph = MainNavGraph,
        dependenciesContainerBuilder = {
            dependency(OnBoardingNavGraph) {
                OnBoardingIsFinished { navController.navigate(HomeNavGraph) }
            }
            dependency(splashScreen)
        })
}
