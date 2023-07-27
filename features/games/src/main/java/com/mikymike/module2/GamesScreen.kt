package com.mikymike.module2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mikymike.module2.navigation.OpenCards
import com.mikymike.module2.navigation.OpenShop
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun GamesScreen(
    openShop: OpenShop, openCards: OpenCards
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(
            modifier = Modifier.align(BiasAlignment(horizontalBias = 0f, verticalBias = -0.75f)),
            text = "Games"
        )
        Column(modifier = Modifier.align(Alignment.Center)) {
            Button(onClick = {
                openShop.invoke()
            }) {
                Text(text = "OpenShop")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                openCards.invoke()
            }) {
                Text(text = "OpenCards")
            }
        }
    }
}