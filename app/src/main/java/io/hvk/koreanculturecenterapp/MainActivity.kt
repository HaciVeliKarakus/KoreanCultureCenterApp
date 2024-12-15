package io.hvk.koreanculturecenterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.hvk.koreanculturecenterapp.screen.MainScreen
import io.hvk.koreanculturecenterapp.ui.theme.KoreanCultureCenterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoreanCultureCenterAppTheme {
                MainScreen()
            }
        }
    }
}





