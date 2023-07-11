package com.mikymike.navigationomada.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.mikymike.module1.HomeNavGraph
import com.mikymike.navigationomada.MainNavGraph
import com.mikymike.navigationomada.ui.destinations.BottomSheetDestination
import com.mikymike.onboarding.OnBoardingIsFinished
import com.mikymike.onboarding.OnBoardingNavGraph
import com.mikymike.onboarding.OpenBottomSheet
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun OmadaApp(
    modifier: Modifier = Modifier, onBoardingIsFinished: Boolean = false
) {
    val engine = rememberAnimatedNavHostEngine()
    val navController = engine.rememberNavController()
    val startRoute = if (onBoardingIsFinished) HomeNavGraph else OnBoardingNavGraph
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    ModalBottomSheetLayout(
        modifier = modifier,
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Surface(
            modifier = modifier, color = MaterialTheme.colorScheme.background
        ) {
            DestinationsNavHost(modifier = modifier,
                engine = engine,
                navController = navController,
                navGraph = MainNavGraph,
                startRoute = startRoute,
                dependenciesContainerBuilder = {
                    dependency(OnBoardingNavGraph) {
                        OnBoardingIsFinished { navController.navigate(HomeNavGraph) }
                    }
                    dependency(OnBoardingNavGraph) {
                        OpenBottomSheet { navController.navigate(BottomSheetDestination) }
                    }
                })
        }
    }
}