package io.hvk.koreanculturecenterapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import io.hvk.koreanculturecenterapp.R


@Composable
internal fun AsyncImage(
    url: String
) {
    println("___1___ $url")
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
//            .logger(DebugLogger())
//            .setHeader("User-Agent", "Mozilla/5.0")
            .build(),
        contentDescription = url,
        modifier = Modifier.fillMaxWidth(),
        placeholder = painterResource(R.drawable.placeholder),
        contentScale = ContentScale.FillWidth
    )
}