package com.zzz.dishesapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    url : String,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription : String? = null
) {

    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )

}