package com.mikymike.module3.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@Destination(style = DestinationStyle.Dialog::class)
@Composable
fun ShopDialog(
    resultNavigator: ResultBackNavigator<Boolean>
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Are you sure bro ?")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { resultNavigator.navigateBack(result = false) }) {
                Text(text = "C'est non!")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { resultNavigator.navigateBack(result = true) }) {
                Text(text = "Si papii")
            }
        }
    }
}