package com.mikymike.design

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
interface SheetContent {

    val backgroundColor: Color

    @Composable
    fun Content(columnScope: ColumnScope, sheetState: ModalBottomSheetState)
}