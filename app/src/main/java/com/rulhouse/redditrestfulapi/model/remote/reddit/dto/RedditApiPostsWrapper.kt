package com.rulhouse.redditrestfulapi.model.remote.reddit.dto

data class RedditApiPostsWrapper(
    val `data`: Data,
    val kind: String
)