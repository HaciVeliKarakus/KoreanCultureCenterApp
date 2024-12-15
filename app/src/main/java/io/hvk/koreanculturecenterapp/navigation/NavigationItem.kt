package io.hvk.koreanculturecenterapp.navigation

import io.hvk.koreanculturecenterapp.R
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationItem(val title: String, val icon: Int, val route: String) {
    @Serializable
    data object news : NavigationItem(
        title = "Duyurular",
        icon = R.drawable.baseline_record_voice_over_24,
        route = "news"
    )

    @Serializable
    data object event : NavigationItem(
        title = "Etkinlikler",
        icon = R.drawable.baseline_event_24,
        route = "about"
    )

    @Serializable
    data object press : NavigationItem(
        title = "Haberler",
        icon = R.drawable.baseline_newspaper_24,
        route = "home"
    )
}
