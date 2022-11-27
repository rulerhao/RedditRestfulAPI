package com.rulhouse.redditrestfulapi.view.posts_screen.event

sealed class PostsScreenEvent {
    object OnRefresh: PostsScreenEvent()
    object OnGetNewPosts: PostsScreenEvent()
    object OnChangedLayout: PostsScreenEvent()
}
