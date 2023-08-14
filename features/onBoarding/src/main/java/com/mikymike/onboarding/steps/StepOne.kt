package com.mikymike.onboarding.steps

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import com.mikymike.onboarding.destinations.StepTwoDestination
import com.mikymike.onboarding.navigation.ContentNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

object StepOneTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)

    }
}

@ContentNavGraph(start = true)
@Destination(style = StepOneTransitions::class)
@Composable
fun StepOne(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            navigator.navigate(StepTwoDestination)
        }) {
            Text(text = "Go to second step")
        }
    }
}
