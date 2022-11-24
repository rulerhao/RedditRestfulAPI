package com.rulhouse.redditrestfulapi.model.remote.reddit.use_case

data class RedditApiUseCases (
    val getFirstPosts: GetFirstPosts,
    val getNextPosts: GetNextPosts
)