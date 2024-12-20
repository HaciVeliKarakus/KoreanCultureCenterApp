package io.hvk.koreanculturecenterapp.screen.news


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = viewModel(),
    onClick: (String) -> Unit
) {
    val news by viewModel.newsItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
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
                items(news) { item ->
                    NewsCard(item) {
                        onClick(item.link)
                    }
                }
            }
            if (news.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Daha sonra tekrar deneyiniz.",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

