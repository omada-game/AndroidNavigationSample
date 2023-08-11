package com.mikymike.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    init {
        viewModelScope.launch(Dispatchers.Default) {
            delay(1500)
            isLoading.emit(false)
        }
    }
}