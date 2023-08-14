package com.mikymike.onboarding.steps

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.mikymike.onboarding.navigation.ContentNavGraph
import com.mikymike.onboarding.navigation.OnBoardingIsDone
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle


object StepFourTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return fadeIn()
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return fadeOut()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@ContentNavGraph
@Destination(style = StepFourTransitions::class)
@Composable
fun AnimatedVisibilityScope.StepFour(
    onBoardingIsDone: OnBoardingIsDone
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .animateEnterExit(enter = fadeIn() + slideInVertically { it },
                    exit = fadeOut() + slideOutVertically { it })
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { onBoardingIsDone.invoke() }) {
                Text(text = "Finish")
            }
        }
    }
}