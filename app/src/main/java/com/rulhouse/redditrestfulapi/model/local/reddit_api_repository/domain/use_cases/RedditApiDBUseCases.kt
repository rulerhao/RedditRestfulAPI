package com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.use_cases

data class RedditApiDBUseCases (
    val getRedditApiRepositoryFlow: GetRedditPostsFlow,
    val insertRedditPosts: InsertRedditPosts
)