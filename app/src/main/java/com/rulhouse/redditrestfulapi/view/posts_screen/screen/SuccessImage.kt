package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.glide.GlideImageState

@Composable
fun BoxScope.SuccessImage(
    imageState: GlideImageState.Success
) {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        bitmap = imageState.imageBitmap!!,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}