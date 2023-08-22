package com.mikymike.module1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiEffects {
    object ShowDailyCoins : HomeUiEffects()
}

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    val uiEffects = MutableSharedFlow<HomeUiEffects>()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            delay(2000)
            isLoading.emit(false)
            delay(1500)
            uiEffects.emit(HomeUiEffects.ShowDailyCoins)
        }
    }
}