package io.hvk.koreanculturecenterapp.screen.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.hvk.koreanculturecenterapp.data.News

@Composable
fun NewsCard(item: News) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isHot)
                MaterialTheme.colorScheme.errorContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Ziyaretçi sayısı: ${item.visitCount}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Text(
                text = item.date,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val news = listOf(
        News(
            title = "Aralık Ayı Film Gösterimi",
            date = "Dec 05, 2024",
            link = "134922",
            type = "hot",
            visitCount = "393"
        ),
        News(
            title = "Aralık Ayı Film Gösterimi",
            date = "Dec 05, 2024",
            link = "134922",
            type = "-",
            visitCount = "393"
        )
    )
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(news) { newsItem ->
            NewsCard(newsItem)
        }
    }
}