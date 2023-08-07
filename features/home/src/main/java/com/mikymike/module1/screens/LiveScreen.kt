package com.mikymike.module1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [DeepLink(
        uriPattern = "omadagame://destination/live/{origin}"
    )]
)
@Composable
fun LiveScreen(
    origin: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Live origin: $origin !!", style = TextStyle(color = Color.White))
    }
}