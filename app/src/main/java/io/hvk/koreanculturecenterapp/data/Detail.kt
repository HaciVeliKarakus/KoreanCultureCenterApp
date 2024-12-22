package io.hvk.koreanculturecenterapp.data

data class Detail(
    val header: String? = null,
    val list: List<SubDetail> = listOf(),
    val downloadable: String? = null
)

data class SubDetail(
    val text: String? = null,
    val image: String? = null
)
