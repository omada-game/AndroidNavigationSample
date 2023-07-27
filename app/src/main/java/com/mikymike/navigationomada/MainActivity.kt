package com.mikymike.navigationomada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.mikymike.navigationomada.ui.OmadaApp
import com.mikymike.navigationomada.ui.theme.NavigationOmadaTheme

private const val ON_BOARDING_IS_FINISHED = "onBoardingIsFinished"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NavigationOmadaTheme {
                Surface {
                    OmadaApp(
                        modifier = Modifier.fillMaxSize(),
                        onBoardingIsFinished = getPreferences(MODE_PRIVATE).getBoolean(
                            ON_BOARDING_IS_FINISHED, false
                        )
                    )
                }
            }
        }
    }
}