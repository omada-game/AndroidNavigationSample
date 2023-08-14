package com.mikymike.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mikymike.onboarding.destinations.StepFourDestination
import com.mikymike.onboarding.navigation.OnBoardingIsDone
import com.mikymike.onboarding.navigation.OnBoardingLoadingFinished
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine

@RootNavGraph(start = true)
@Destination
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = viewModel(),
    onBoardingIsDone: OnBoardingIsDone,
    loadingIsFinished: OnBoardingLoadingFinished
) {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(systemUiController) {
        systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = true)
    }

    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(isLoading) {
        if (!isLoading) loadingIsFinished.invoke()
    }

    OnBoardingScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .windowInsetsPadding(WindowInsets.systemBars), onBoardingIsDone = onBoardingIsDone
    )
}

@Composable
private fun OnBoardingScreen(
    modifier: Modifier = Modifier, onBoardingIsDone: OnBoardingIsDone
) {

    val engine = rememberNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
    )

    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Steps", style = TextStyle(
                    fontSize = 32.sp
                )
            )
            DestinationsNavHost(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                navGraph = ContentNavGraph,
                engine = engine,
                dependenciesContainerBuilder = {
                    dependency(StepFourDestination) {
                        onBoardingIsDone
                    }
                })
        }
    }
}


