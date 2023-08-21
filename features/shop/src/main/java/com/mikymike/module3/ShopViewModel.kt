package com.mikymike.module3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikymike.module3.dot.ShopDotManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    shopDotManager: ShopDotManager
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.Default) {
            shopDotManager.dotIsVisible(false)
        }
    }
}