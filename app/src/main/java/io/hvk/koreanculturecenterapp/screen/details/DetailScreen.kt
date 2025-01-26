package io.hvk.koreanculturecenterapp.screen.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.hvk.koreanculturecenterapp.components.AsyncImage
import io.hvk.koreanculturecenterapp.data.MAIN_URL
import io.hvk.koreanculturecenterapp.ui.theme.blue
import io.hvk.koreanculturecenterapp.ui.theme.red
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    link: String?,
    viewModel: DetailsViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        if (link != null) {
            viewModel.loadNews(link)
        } else {
            onBackClick()
        }
    }
    val news by viewModel.newsItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    news.header?.let { header ->
                        Text(
                            text = header,
                            color = blue,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                navigationIcon = {
                    AnimatedVisibility(news.header.isNullOrBlank().not()) {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(news.textList) { item ->
                        item.text?.let {
                            HtmlText(it)
                            it.extractImageUrls().forEach { url ->
                                AsyncImage(url)
                            }
                        }
                    }
                    item {
                        news.downloadable?.let {
                            HorizontalDivider()
                            HtmlText(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HtmlText(it: String) {
    Text(
        text = fromHtmlWithStyle(it)
//            .fromHtml(
//            htmlString = it,
//            linkStyles = TextLinkStyles(
//                style = SpanStyle(
//                    textDecoration = TextDecoration.Underline,
//                    color = red
//                )
//            )
//        )
        ,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

fun String.extractImageUrls(): List<String> {
    val document = Jsoup.parse(this)
    return document.select("img[src]").map { MAIN_URL + it.attr("src") }
}

fun String.extractText(): String {
    val document = Jsoup.parse(this)
    document.select("img").remove()
    return document.html()
}

private fun parseColor(value: String): Color {
    return try {
        Color(android.graphics.Color.parseColor(value))
    } catch (e: IllegalArgumentException) {
        Color.Unspecified
    }
}

fun fromHtmlWithStyle(html: String): AnnotatedString {
    val parsedHtml = Jsoup.parse(html)
    val builder = AnnotatedString.Builder()

    // Body içeriğini işleyelim
    parsedHtml.children().forEach { element ->
        processElementWithStyle(element, builder)
    }

    return builder.toAnnotatedString()
}

private fun processElementWithStyle(element: Element, builder: AnnotatedString.Builder) {
    when (element.tagName()) {
        "b", "strong" -> {
            builder.pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            builder.append(element.text())
            builder.pop()
        }
        "i", "em" -> {
            builder.pushStyle(SpanStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic))
            builder.append(element.text())
            builder.pop()
        }
        "u" -> {
            builder.pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            builder.append(element.text())
            builder.pop()
        }
        "span" -> {
            // Span style işleme
            val style = element.attr("style")
            val spanStyle = parseStyle(style)
            builder.pushStyle(spanStyle)
            builder.append(element.text())
            builder.pop()
        }
        else -> {
            builder.append(element.text()) // Varsayılan metin
        }
    }

    // Alt elementleri işlemek için recursive çağrı
    element.children().forEach { child ->
        processElementWithStyle(child, builder)
    }
}

// HTML style string'i SpanStyle'a çevirme
private fun parseStyle(style: String): SpanStyle {
    var spanStyle = SpanStyle()

    style.split(";").forEach { rule ->
        println("_______________$rule")
        val (property, value) = rule.split(":").map { it.trim() }
        when (property) {
            "color" -> {
                spanStyle = spanStyle.copy(color = parseColor(value))
            }
            "font-weight" -> {
                if (value == "bold") {
                    spanStyle = spanStyle.copy(fontWeight = FontWeight.Bold)
                }
            }
            "text-decoration" -> {
                if (value == "underline") {
                    spanStyle = spanStyle.copy(textDecoration = TextDecoration.Underline)
                }
            }
        }
    }

    return spanStyle
}