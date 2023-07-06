package com.mikymike.navigationomada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.mikymike.navigationomada.ui.screens.HomeScreen
import com.mikymike.navigationomada.ui.screens.MainScreen
import com.mikymike.navigationomada.ui.theme.NavigationOmadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NavigationOmadaTheme {
                MainScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}