package com.mikymike.module1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mikymike.cards.CardsScreen
import com.mikymike.module1.bottomBar.BottomBar
import com.mikymike.module1.destinations.LiveScreenDestination
import com.mikymike.module1.navigation.BottomNavGraph
import com.mikymike.module1.screens.BottomSheetType
import com.mikymike.module1.sheet.DailyCoins
import com.mikymike.module2.GamesNavGraph
import com.mikymike.module2.navigation.OpenCards
import com.mikymike.module2.navigation.OpenShop
import com.mikymike.module3.ShopNavGraph
import com.mikymike.profile.ProfileNavGraph
import com.mikymike.profile.navigation.OpenLive
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(systemUiController) {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
        systemUiController.setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    val navController = rememberNavController()

    var showModalBottomSheet by remember {
        mutableStateOf(true)
    }

    var bottomSheetType by remember {
        mutableStateOf(BottomSheetType.DAILY_COINS)
    }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .background(Color.Blue)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
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
                            bottomSheetType = BottomSheetType.CARDS
                            showModalBottomSheet = true
                        }
                    }
                    dependency(ProfileNavGraph) {
                        OpenLive {
                            navigator.navigate(LiveScreenDestination)
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

        if (showModalBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { showModalBottomSheet = false },
                sheetState = bottomSheetState,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                containerColor = when (bottomSheetType) {
                    BottomSheetType.CARDS -> Color.Magenta
                    else -> Color.White
                },
                windowInsets = WindowInsets(0)
            ) {
                when (bottomSheetType) {
                    BottomSheetType.DAILY_COINS -> DailyCoins(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                        onDismiss = { showModalBottomSheet = false })

                    BottomSheetType.CARDS -> CardsScreen(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f),
                        onDismiss = { showModalBottomSheet = false })

                    BottomSheetType.DEFAULT -> Spacer(modifier = Modifier)
                }
            }
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