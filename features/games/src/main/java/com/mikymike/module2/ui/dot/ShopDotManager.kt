package com.mikymike.module2.ui.dot

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesDotManager @Inject constructor() {
    val showGamesDot: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
}