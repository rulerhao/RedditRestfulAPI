package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.runtime.Composable
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.view.posts_screen.state.LayoutType

@Composable
fun Post(
    post: Post,
    layoutType: LayoutType
) {
    when(layoutType)  {
        LayoutType.Apart -> {
            ApartPost(post = post)
        }
        LayoutType.Overlap -> {
            OverlapPost(post = post)
        }
    }
}