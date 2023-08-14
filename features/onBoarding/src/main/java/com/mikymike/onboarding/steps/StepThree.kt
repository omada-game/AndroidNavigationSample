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
import com.mikymike.onboarding.destinations.StepFourDestination
import com.mikymike.onboarding.destinations.StepTwoDestination
import com.mikymike.onboarding.navigation.ContentNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

object StepThreeTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return when (initialState.destination.route) {
            StepTwoDestination.route -> slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
            else -> fadeIn()
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return when (targetState.destination.route) {
            StepTwoDestination.route -> slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)
            else -> fadeOut()
        }
    }
}

@ContentNavGraph
@Destination(style = StepThreeTransitions::class)
@Composable
fun StepThree(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { navigator.navigate(StepFourDestination) }) {
            Text(text = "Go to fourth step")
        }
    }
}
