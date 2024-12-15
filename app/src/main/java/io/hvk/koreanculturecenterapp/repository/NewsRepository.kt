package io.hvk.koreanculturecenterapp.repository

import io.hvk.koreanculturecenterapp.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class NewsRepository {
    suspend fun getNews(): List<News> = withContext(Dispatchers.IO) {
        try {
            val baseUrl = "https://tr.korean-culture.org/tr/362/board/215/"
            val doc: Document = Jsoup.connect(baseUrl + "list").get()

            val newsElements = doc.select("table.bbs").select("tr:not(:first-child)")
            newsElements.map { element ->
                val titleElement = element.select("td.subject a")
                val dateElement = element.select("td:nth-child(3)")
                val visitCount = element.select("td:nth-child(4)")


                News(
                    title = titleElement.text(),
                    date = dateElement.text(),
                    link = baseUrl + "read/" + titleElement.attr("seq"),
                    type = element.className(),
                    visitCount = visitCount.text()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
} 