package com.mikymike.module1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle

//@Destination(style = DestinationStyle.BottomSheet::class)
@Composable
fun BottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "BottomSheet")
    }
}
