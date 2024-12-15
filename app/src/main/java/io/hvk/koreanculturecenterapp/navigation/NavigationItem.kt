package io.hvk.koreanculturecenterapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import io.hvk.koreanculturecenterapp.icon.Event_note
import io.hvk.koreanculturecenterapp.icon.Newspaper
import io.hvk.koreanculturecenterapp.icon.Spatial_audio
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationItem(val title: String, val icon: ImageVector, val route: String) {
    data object news : NavigationItem("Duyurular", Spatial_audio, "news")
    data object event : NavigationItem("Etkinlikler", Event_note, "about")
    data object press : NavigationItem("Haberler", Newspaper, "home")
}
