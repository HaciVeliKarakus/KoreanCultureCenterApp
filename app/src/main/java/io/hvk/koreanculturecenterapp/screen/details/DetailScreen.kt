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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.hvk.koreanculturecenterapp.components.AsyncImage
import io.hvk.koreanculturecenterapp.data.MAIN_URL
import io.hvk.koreanculturecenterapp.ui.theme.blue
import org.jsoup.Jsoup

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
                            textAlign = TextAlign.Center,
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
                           Icon(Icons.AutoMirrored.Filled.ArrowBack,"")
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
                    items(news.list) { item ->
                        item.text?.let {
                            Text(
                                text = AnnotatedString.fromHtml(
                                    htmlString = it,
                                    linkStyles = TextLinkStyles(
                                        style = SpanStyle(
                                            textDecoration = TextDecoration.Underline,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                ),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            it.extractImageUrls().forEach { url ->
                                AsyncImage(url)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun String.extractImageUrls(): List<String> {
    val document = Jsoup.parse(this)
    return document.select("img[src]").map { MAIN_URL + it.attr("src") }
}
