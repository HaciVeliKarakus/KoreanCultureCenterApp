package io.hvk.koreanculturecenterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.hvk.koreanculturecenterapp.screen.MainScreen
import io.hvk.koreanculturecenterapp.ui.theme.KoreanCultureCenterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            val uiController = rememberSystemUiController()

            SideEffect {
                uiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = true
                )
            }
            KoreanCultureCenterAppTheme {
                MainScreen()
            }
        }
    }
}





