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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

private const val ON_BOARDING_IS_FINISHED = "onBoardingIsFinished"

@RootNavGraph(start = true)
@Destination
@Composable
fun OnBoardingScreen(
    onBoardingIsFinished: OnBoardingIsFinished, openBottomSheet: OpenBottomSheet
) {
    val activity = LocalContext.current.let {
        when (it) {
            is Activity -> it
            is ContextWrapper -> it.baseContext as Activity
            else -> null
        }
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
            Button(onClick = {
                openBottomSheet.invoke()
            }) {
                Text(text = "BottomSheet")
            }
        }

    }
}