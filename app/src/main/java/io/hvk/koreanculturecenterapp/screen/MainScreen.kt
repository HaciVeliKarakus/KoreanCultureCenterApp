package io.hvk.koreanculturecenterapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.hvk.koreanculturecenterapp.navigation.NavigationItem
import io.hvk.koreanculturecenterapp.screen.events.AboutScreen
import io.hvk.koreanculturecenterapp.screen.news.NewsScreen
import io.hvk.koreanculturecenterapp.screen.press.PressScreen
import io.hvk.koreanculturecenterapp.ui.theme.KoreanCultureCenterAppTheme

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(NavigationItem.NEWS) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationItem.entries.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedTab == item,
                        onClick = { selectedTab = item },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.secondary,
                            selectedTextColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            NavigationItem.NEWS -> NewsScreen(modifier = Modifier.padding(innerPadding))
            NavigationItem.PRESS -> PressScreen(modifier = Modifier.padding(innerPadding))
            NavigationItem.EVENTS -> AboutScreen(modifier = Modifier.padding(innerPadding))
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