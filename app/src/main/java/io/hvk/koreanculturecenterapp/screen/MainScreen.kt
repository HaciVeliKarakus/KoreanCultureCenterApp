package io.hvk.koreanculturecenterapp.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.hvk.koreanculturecenterapp.navigation.BottomNavigationBar
import io.hvk.koreanculturecenterapp.navigation.NavigationGraph
import io.hvk.koreanculturecenterapp.ui.theme.KoreanCultureCenterAppTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val shouldShowBottomBar = when (navBackStackEntry?.destination?.route) {
        "detail_screen?link={link}" -> false
        else -> true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(shouldShowBottomBar) {
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