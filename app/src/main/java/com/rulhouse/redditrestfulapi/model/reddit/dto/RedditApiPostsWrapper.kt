package com.rulhouse.redditrestfulapi.model.reddit.dto

data class RedditApiPostsWrapper(
    val `data`: Data,
    val kind: String
)