package io.hvk.koreanculturecenterapp.repository

import io.hvk.koreanculturecenterapp.data.Detail
import io.hvk.koreanculturecenterapp.data.SubDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class DetailsRepository {
    suspend fun getData(
        link: String
    ): Detail = withContext(Dispatchers.IO) {
        try {
//            val baseUrl = "https://tr.korean-culture.org/tr/362/board/215/"
            val doc: Document = Jsoup.connect(link).get()

            val newsElements = doc.select("div.view-content")
            val titleElement = newsElements.select("header h2")
            val text = newsElements.select("div.txt")
            val tmpList: MutableList<SubDetail> = mutableListOf()
            println("______${text.html()}")
            text.map { element ->
                println("____${element.html()}")
                tmpList.add(
                    SubDetail(
                        text = element.html()
                    )
                )
            }
            Detail(
                header = titleElement.text(),
                list = tmpList
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Detail()
        }
    }
} 