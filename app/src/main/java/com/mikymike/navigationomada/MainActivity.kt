package com.mikymike.navigationomada

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.mikymike.navigationomada.ui.OmadaApp
import com.mikymike.navigationomada.ui.theme.NavigationOmadaTheme
import dagger.hilt.android.AndroidEntryPoint

private const val SHOW_ON_BOARDING = "show_on_boarding"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            NavigationOmadaTheme {
                OmadaApp(modifier = Modifier.fillMaxSize(),
                    showOnBoarding = getPreferences(MODE_PRIVATE).getBoolean(
                        SHOW_ON_BOARDING, true
                    ),
                    onBoardingIsDone = {
                        getPreferences(Context.MODE_PRIVATE)?.edit()
                            ?.putBoolean(SHOW_ON_BOARDING, false)?.apply()
                    },
                    appLoadingIsFinished = {
                        splashScreen.setKeepOnScreenCondition { false }
                    })
            }
        }
    }
}