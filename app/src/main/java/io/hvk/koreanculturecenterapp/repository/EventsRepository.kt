package io.hvk.koreanculturecenterapp.repository

import io.hvk.koreanculturecenterapp.MAIN_URL
import io.hvk.koreanculturecenterapp.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class EventsRepository {
    suspend fun getEvents(): List<Event> = withContext(Dispatchers.IO) {
        try {
            val baseUrl = "https://tr.korean-culture.org/tr/363/board/216/"
            val doc: Document = Jsoup.connect(baseUrl + "list").get()

            val elements = doc.select("div.bbs-type-exhibit").select("li")

            elements.map { element ->
                val image = element.select("div.photo img")
                val imageUrl = MAIN_URL + image.attr("src")

                val title = element.select("div.title")
                val postDate = title.select("span:nth-of-type(1)")
                val eventDate = title.select("span:nth-of-type(2)")
                val info = element.select("div.txt")
                val link = buildString {
                    append(baseUrl)
                    append("/read/")
                    append(title.select("a").attr("seq"))
                }
                Event(
                    title = image.attr("alt"),
                    image = imageUrl,
                    postDate = postDate.text(),
                    eventDate = eventDate.text(),
                    info = info.text(),
                    link = link
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
} 