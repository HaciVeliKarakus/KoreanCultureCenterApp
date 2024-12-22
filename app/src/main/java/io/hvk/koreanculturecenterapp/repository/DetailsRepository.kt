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
            val doc: Document = Jsoup.connect(link).get()

            val newsElements = doc.select("div.view-content")
            val titleElement = newsElements.select("header h2")
            val text = newsElements.select("div.se-contents")
            val downloadable = newsElements.select("dl.attach-file")
            val tmpList: MutableList<SubDetail> = mutableListOf()
            text.map { element ->
                tmpList.add(
                    SubDetail(
                        text = element.html()
                    )
                )
            }
            Detail(
                header = titleElement.text(),
                list = tmpList,
                downloadable = downloadable.html()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Detail()
        }
    }
} 