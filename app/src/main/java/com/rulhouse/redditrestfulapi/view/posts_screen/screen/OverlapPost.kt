package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun OverlapPost(
    post: Post
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color.LightGray)
        ) {
            GlideImage(
                imageModel = { post.thumbnailUri },
                success = {
                    SuccessImage(it)
                },
                loading = {
                    CircularProgressIndicator()
                }
            )
            Text(
                text = post.title,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}