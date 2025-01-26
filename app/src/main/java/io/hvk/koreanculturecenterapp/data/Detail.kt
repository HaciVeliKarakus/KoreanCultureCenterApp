package io.hvk.koreanculturecenterapp.data

data class Detail(
    val header: String? = null,
    val textList: List<SubDetail> = listOf(),
    val imgList: List<SubDetail> = listOf(),
    val downloadable: String? = null
)

data class SubDetail(
    val text: String? = null,
    val image: String? = null
)
