package com.mikymike.design

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
class SheetController @Inject constructor() {
    private var scope: CoroutineScope? = null

    private val _currentContent = MutableStateFlow<SheetContent?>(null)
    val currentContent: StateFlow<SheetContent?> = _currentContent

    private var state: ModalBottomSheetState? = null

    @Composable
    fun Setup(sheetState: ModalBottomSheetState) {
        val scope = rememberCoroutineScope()
        this@SheetController.scope = scope
        this@SheetController.state = sheetState
        LaunchedEffect(sheetState.currentValue) {
            if (sheetState.currentValue == ModalBottomSheetValue.Hidden) {
                _currentContent.emit(null)
            }
        }
    }

    suspend fun show(content: SheetContent) {
        scope?.launch {
            _currentContent.emit(content)
            state?.show() ?: run { println("AAA -> Sheet state not initialized") }
        } ?: run { println("AAA -> Scope not initialized") }
    }

    suspend fun hide() {
        scope?.launch {
            state?.hide() ?: run { println("AAA -> Sheet state not initialized") }
        } ?: run { println("AAA -> Scope not initialized") }
    }
}

@Composable
fun rememberSheetController() = remember { SheetController() }

