package com.mikymike.module1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mikymike.cards.CardsSheetContent
import com.mikymike.design.SheetController
import com.mikymike.design.rememberSheetController
import com.mikymike.module1.bottomBar.BottomBar
import com.mikymike.module1.destinations.LiveScreenDestination
import com.mikymike.module1.navigation.BottomNavGraph
import com.mikymike.module1.navigation.HomeLoadingFinished
import com.mikymike.module1.sheet.DailyCoinsSheetContent
import com.mikymike.module2.navigation.OpenCards
import com.mikymike.module2.navigation.OpenShop
import com.mikymike.module2.ui.GamesNavGraph
import com.mikymike.module3.ShopNavGraph
import com.mikymike.profile.navigation.OpenLive
import com.mikymike.profile.ui.ProfileNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    sheetController: SheetController = rememberSheetController(),
    homeLoadingFinished: HomeLoadingFinished,
    navigator: DestinationsNavigator
) {
    val coroutineScope = rememberCoroutineScope()

    // System's bar colors
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(systemUiController) {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
        systemUiController.setNavigationBarColor(color = Color.Transparent, darkIcons = true)
    }

    // Navigation
    val navController = rememberNavController()


    // Sheet : TODO: Using sheetController behavior for bottomSheet disable entry animation. Change it(maybe with material3 modal)!!
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    var isResumed by remember { mutableStateOf(false) }
    LaunchedEffect(lifeCycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                isResumed = true
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                isResumed = false
            }
        }
        lifeCycle.addObserver(observer)
    }
    if (isResumed) {
        sheetController.Setup(sheetState = sheetState)
    }
    val currentSheetContent by sheetController.currentContent.collectAsState()


    // Loading
    val isLoading by viewModel.isLoading.collectAsState()
    LaunchedEffect(isLoading) {
        if (!isLoading) homeLoadingFinished.invoke()
    }

    // UI Effects
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEffects.onEach {
            when (it) {
                HomeUiEffects.ShowDailyCoins -> {
                    sheetController.show(DailyCoinsSheetContent())
                }
            }
        }.launchIn(this)
    }

    // UI
    ModalBottomSheetLayout(modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetElevation = 4.dp,
        sheetBackgroundColor = currentSheetContent?.backgroundColor ?: Color.White,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            currentSheetContent?.Content(
                columnScope = this, sheetState = sheetState
            )
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            DestinationsNavHost(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                navController = navController,
                navGraph = BottomNavGraph,
                startRoute = GamesNavGraph,
                dependenciesContainerBuilder = {
                    dependency(GamesNavGraph) {
                        OpenShop {
                            navController.navigate(ShopNavGraph, fun NavOptionsBuilder.() {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            })
                        }
                    }
                    dependency(GamesNavGraph) {
                        OpenCards {
                            coroutineScope.launch {
                                sheetController.show(CardsSheetContent())
                            }
                        }
                    }
                    dependency(ProfileNavGraph) {
                        OpenLive {
                            navigator.navigate(LiveScreenDestination(origin = "Profile"))
                        }
                    }
                })

            val currentDestination by navController.currentDestinationAsState()

            BottomBar(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                selectedNavigation = currentDestination,
                onNavigationSelected = { selected ->
                    navController.navigate(selected, fun NavOptionsBuilder.() {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    })
                })
        }
    }
}

@Stable
@Composable
private fun NavController.currentDestinationAsState(): State<NavGraphSpec> {
    val selectedItem: MutableState<NavGraphSpec> = remember { mutableStateOf(GamesNavGraph) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = destination.navGraph()
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        BottomNavGraph.nestedNavGraphs.forEach {
            if (destination.route == it.route) {
                return it
            }
        }
    }

    throw RuntimeException("Unknown nav graph for destination $route")
}