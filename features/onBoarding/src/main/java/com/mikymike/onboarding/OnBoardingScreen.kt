package com.mikymike.onboarding

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

private const val ON_BOARDING_IS_FINISHED = "onBoardingIsFinished"

@RootNavGraph(start = true)
@Destination
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = viewModel(),
    splashScreen: SplashScreen,
    onBoardingIsFinished: OnBoardingIsFinished
) {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(systemUiController) {
        systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = false)
    }

    val activity = LocalContext.current.let {
        when (it) {
            is Activity -> it
            is ContextWrapper -> it.baseContext as Activity
            else -> null
        }
    }

    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(isLoading) {
        if (!isLoading) splashScreen.setKeepOnScreenCondition { false }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red), contentAlignment = Alignment.Center
    ) {
        Column {
            Button(onClick = {
                activity?.getPreferences(Context.MODE_PRIVATE)?.edit()?.putBoolean(
                    ON_BOARDING_IS_FINISHED, true
                )?.apply()
                onBoardingIsFinished.invoke()
            }) {
                Text(text = "On Boarding")
            }
        }
    }
}