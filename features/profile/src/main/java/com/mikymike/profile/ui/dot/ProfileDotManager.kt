package com.mikymike.profile.ui.dot

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDotManager @Inject constructor() {
    val showProfileDot: MutableStateFlow<Boolean> = MutableStateFlow(value = true)

    suspend fun dotIsVisible(isVisible: Boolean) {
        showProfileDot.emit(isVisible)
    }
}