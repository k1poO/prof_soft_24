package com.example.finalproject.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.finalproject.R

@Composable
fun CoilImage (
    modifier: Modifier = Modifier,
    link: String,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(link)
            .error(R.drawable.ic_error_logo)
            .placeholder(R.drawable.ic_profile)
            .build(),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = modifier
    )
}