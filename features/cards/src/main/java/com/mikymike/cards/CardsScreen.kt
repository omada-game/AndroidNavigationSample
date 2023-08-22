package com.mikymike.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mikymike.design.SheetContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class CardsSheetContent : SheetContent {
    override val backgroundColor: Color
        get() = Color.Magenta

    @Composable
    override fun Content(
        columnScope: ColumnScope, sheetState: ModalBottomSheetState
    ) {
        val coroutineScope = rememberCoroutineScope()
        CardsScreen(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f),
            onDismiss = { coroutineScope.launch(Dispatchers.Default) { sheetState.hide() } })

    }
}

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