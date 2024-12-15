package io.hvk.koreanculturecenterapp.screen.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.hvk.koreanculturecenterapp.components.AsyncImage
import io.hvk.koreanculturecenterapp.data.Event
import io.hvk.koreanculturecenterapp.ui.theme.blue

@Composable
fun EventCard(
    item: Event,
    onClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    AsyncImage(url = item.image)
                    Text(
                        item.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = blue
                    )
                    Text(
                        item.info,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    Row {
                        Text("Post Date: ")
                        Text(
                            item.postDate,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row {
                        Text("Event Date: ")
                        Text(
                            item.eventDate,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val events = listOf(
        Event(
            title = "Kore-Türkiye Dostluk Sergisinin Açılışı Gerçekleşti",
            image = "perpetua",
            postDate = "Dec 12, 2024",
            eventDate = "Dec 04, 2024 ~ Jan 31, 2025",
            info = "Kore Kültür Merkezi 4 Aralık 2024 (Çarşamba) ile 31 Ocak 2025 (Cuma) tarihleri arasında Kültür Merkezi sergi salonunda Kore-Türkiye Dostluk Sergisi'nin açılışını gerçekleştirdi.",
            link = "vitae"

        )
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(events) { newsItem ->
            EventCard(newsItem){}
        }
    }
}

