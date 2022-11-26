package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rulhouse.redditrestfulapi.R
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ApartPost(
    post: Post
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(
            text = post.title,
            modifier = Modifier
                .weight(7f)
                .padding(8.dp)
        )
        GlideImage(
            modifier = Modifier
                .weight(3f)
                .padding(8.dp)
                .background(Color.LightGray),
            imageModel = { post.thumbnailUri },
            success = {
                SuccessImage(it)
            },
            loading = {
                CircularProgressIndicator()
            },
            failure = {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }
        )
    }
}