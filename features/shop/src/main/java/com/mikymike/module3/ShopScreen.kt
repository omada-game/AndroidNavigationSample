package com.mikymike.module3

import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mikymike.module3.destinations.ShopDialogDestination
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import com.ramcosta.composedestinations.result.getOr

@RootNavGraph(start = true)
@Destination(
    deepLinks = [DeepLink(
        uriPattern = "omadagame://destination/shop"
    )]
)
@Composable
fun ShopScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<ShopDialogDestination, Boolean>
) {
    val context = LocalContext.current

    resultRecipient.onNavResult { result ->
        Toast.makeText(
            context, "Value = ${result.getOr { false }}", Toast.LENGTH_SHORT
        ).show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Shop")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navigator.navigate(ShopDialogDestination) }) {
                Text(text = "Buy diamonds $$")
            }
        }
    }
}