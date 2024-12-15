package io.hvk.koreanculturecenterapp.data

data class News(
    val title: String,
    val date: String,
    val link: String,
    val type: String,
    val visitCount: String
) {
    val isHot: Boolean
        get() = type == "hot"
}