package com.mikymike.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikymike.profile.ui.dot.ProfileDotManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    profileDotManager: ProfileDotManager
) : ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.Default){
            profileDotManager.dotIsVisible(false)
        }
    }
}