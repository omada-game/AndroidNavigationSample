package com.mikymike.module1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.mikymike.module2.GamesNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        DestinationsNavHost(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            navController = navController,
            navGraph = BottomNavGraph,
            startRoute = GamesNavGraph
        )

        val currentDestination by navController.currentDestinationAsState()

        BottomBar(modifier = Modifier.fillMaxWidth(),
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