package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post

@Composable
fun OverlapPost(
    post: Post
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
//        GlideImage(
//            model = post.thumbnailUri,
//            contentDescription = ""
//        )
    }
}