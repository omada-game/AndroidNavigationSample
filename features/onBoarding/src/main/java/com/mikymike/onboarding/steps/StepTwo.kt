package com.mikymike.onboarding.steps

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import com.mikymike.onboarding.destinations.StepOneDestination
import com.mikymike.onboarding.destinations.StepThreeDestination
import com.mikymike.onboarding.navigation.ContentNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

object StepTwoTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return when (initialState.destination.route) {
            StepOneDestination.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
            StepThreeDestination.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)
            else -> fadeIn()
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return when (targetState.destination.route) {
            StepOneDestination.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)
            StepThreeDestination.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)
            else -> fadeOut()
        }
    }
}

@ContentNavGraph
@Destination(style = StepTwoTransitions::class)
@Composable
fun StepTwo(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            navigator.navigate(StepThreeDestination)
        }) {
            Text(text = "Go to third step")
        }
    }
}