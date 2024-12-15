package io.hvk.koreanculturecenterapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import io.hvk.koreanculturecenterapp.R


@Composable
internal fun AsyncImage(
    url: String
) {
    SubcomposeAsyncImage(
        ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = url,
        modifier = Modifier.fillMaxWidth(),
        error = {
            if(LocalInspectionMode.current){
                Image(painterResource(R.drawable.placeholder),"")
            }
        }
    )
}