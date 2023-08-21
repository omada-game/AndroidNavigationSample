package com.mikymike.module1.bottomBar

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BottomBarManager @Inject constructor() {

    val showGamesDot: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val showShopDot: MutableStateFlow<Boolean> = MutableStateFlow(value = true)
    val showProfileDot: MutableStateFlow<Boolean> = MutableStateFlow(value = true)

    suspend fun shopDotIsVisible(isVisible: Boolean) {
        showShopDot.emit(isVisible)
    }

    suspend fun profileDotIsVisible(isVisible: Boolean) {
        showProfileDot.emit(isVisible)
    }
}