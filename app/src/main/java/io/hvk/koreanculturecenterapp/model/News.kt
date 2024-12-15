package io.hvk.koreanculturecenterapp.model

data class News(
    val title: String,
    val date: String,
    val link: String,
    val type: String,
    val visitCount:String
) {
    val isHot:Boolean
        get() = type == "hot"
}