package io.hvk.koreanculturecenterapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    EVENTS("Ana Sayfa", Icons.Default.Home, "home"),
    NEWS("Haberler", Icons.Default.List, "news"),
    PRESS("Hakkında", Icons.Default.Info, "about")
} 