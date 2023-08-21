package com.mikymike.module3.dot

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopDotManager @Inject constructor() {
    val showShopDot: MutableStateFlow<Boolean> = MutableStateFlow(value = true)

    suspend fun dotIsVisible(isVisible: Boolean) {
        showShopDot.emit(isVisible)
    }
}