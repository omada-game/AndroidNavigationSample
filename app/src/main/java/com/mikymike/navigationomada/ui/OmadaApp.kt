package com.mikymike.navigationomada.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mikymike.module1.HomeNavGraph
import com.mikymike.navigationomada.MainNavGraph
import com.mikymike.onboarding.OnBoardingIsFinished
import com.mikymike.onboarding.OnBoardingNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun OmadaApp(
    modifier: Modifier = Modifier, onBoardingIsFinished: Boolean = false
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
        })
}

//    val engine = rememberAnimatedNavHostEngine()
//    val navController = engine.rememberNavController()
//    val startRoute = if (onBoardingIsFinished) HomeNavGraph else OnBoardingNavGraph
//    val bottomSheetNavigator = rememberBottomSheetNavigator()
//    navController.navigatorProvider += bottomSheetNavigator
//
//    ModalBottomSheetLayout(
//        modifier = modifier,
//        bottomSheetNavigator = bottomSheetNavigator,
//        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
//    ) {
//        Surface(
//            modifier = modifier, color = MaterialTheme.colorScheme.background
//        ) {
//            DestinationsNavHost(modifier = modifier,
//                engine = engine,
//                navController = navController,
//                navGraph = MainNavGraph,
//                startRoute = startRoute,
//                dependenciesContainerBuilder = {
//                    dependency(OnBoardingNavGraph) {
//                        OnBoardingIsFinished { navController.navigate(HomeNavGraph) }
//                    }
//                    dependency(OnBoardingNavGraph) {
//                        OpenBottomSheet { navController.navigate(BottomSheetDestination) }
//                    }
//                })
//        }
//    }
