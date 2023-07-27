package com.mikymike.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CardsScreen() {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(systemUiController) {
        systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Cards"
        )
    }
}