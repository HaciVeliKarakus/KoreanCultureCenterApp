package io.hvk.koreanculturecenterapp.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.hvk.koreanculturecenterapp.navigation.BottomNavigationBar
import io.hvk.koreanculturecenterapp.navigation.NavigationGraph
import io.hvk.koreanculturecenterapp.navigation.NavigationItem
import io.hvk.koreanculturecenterapp.screen.events.AboutScreen
import io.hvk.koreanculturecenterapp.screen.news.NewsScreen
import io.hvk.koreanculturecenterapp.screen.press.PressScreen
import io.hvk.koreanculturecenterapp.ui.theme.KoreanCultureCenterAppTheme
import io.hvk.koreanculturecenterapp.ui.theme.Red

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val shouldShowBottomBar = when (navBackStackEntry?.destination?.route) {
        "lesson_detail/{lessonNumber}" -> false
        "quiz_detail/{quizId}" -> false  // Hide bottom bar during quiz
        else -> true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(
                bottom = if (shouldShowBottomBar) padding.calculateBottomPadding() else 0.dp,
                top = padding.calculateTopPadding(),
                start = padding.calculateStartPadding(LayoutDirection.Ltr),
                end = padding.calculateEndPadding(LayoutDirection.Ltr)
            )
        ) {
            NavigationGraph(navController)
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    KoreanCultureCenterAppTheme {
        MainScreen()
    }
}