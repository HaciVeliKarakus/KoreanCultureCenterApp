package io.hvk.koreanculturecenterapp.repository

import io.hvk.koreanculturecenterapp.data.Press
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PressRepository {
    suspend fun getData(): List<Press> = withContext(Dispatchers.IO) {
        try {
            val baseUrl = "https://tr.korean-culture.org/tr/1632/board/1312/"
            val doc: Document = Jsoup.connect(baseUrl + "list").get()

            val elements = doc.select("table.bbs").select("tr.bbsList")
            elements.map { element ->
                val title = element.select("td.subject")

                val postDate = element.select("td:nth-child(3)")
                val visitCount = element.select("td:nth-child(4)")
                val link = buildString {
                    append(baseUrl)
                    append("read/")
                    append(title.select("a").attr("seq"))
                }
                Press(
                    title = title.text(),
                    date = postDate.text(),
                    link = link,
                    visitCount = visitCount.text()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
} 