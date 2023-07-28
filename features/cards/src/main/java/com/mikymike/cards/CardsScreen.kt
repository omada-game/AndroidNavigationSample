package com.mikymike.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier, onDismiss: () -> Unit = {}
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Button(onClick = onDismiss) {
            Text(
                text = "Cards"
            )
        }
    }
}