package com.mikymike.navigationomada.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikymike.module1.navigation.HomeLoadingFinished
import com.mikymike.module1.navigation.HomeNavGraph
import com.mikymike.navigationomada.MainNavGraph
import com.mikymike.onboarding.OnBoardingNavGraph
import com.mikymike.onboarding.navigation.OnBoardingIsDone
import com.mikymike.onboarding.navigation.OnBoardingLoadingFinished
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.rememberNavHostEngine

@Composable
fun OmadaApp(
    modifier: Modifier = Modifier,
    showOnBoarding: Boolean = false,
    appLoadingIsFinished: () -> Unit = {},
    onBoardingIsDone: () -> Unit
) {
    val engine = rememberNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
    )
    val navController = engine.rememberNavController()

    val startRoute = if (showOnBoarding) OnBoardingNavGraph else HomeNavGraph

    DestinationsNavHost(modifier = modifier,
        engine = engine,
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
            dependency(HomeNavGraph) {
                HomeLoadingFinished {
                    appLoadingIsFinished()
                }
            }
        }
    )
}
