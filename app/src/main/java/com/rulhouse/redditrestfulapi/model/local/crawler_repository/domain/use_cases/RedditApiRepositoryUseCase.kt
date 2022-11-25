package com.rulhouse.redditrestfulapi.model.local.crawler_repository.domain.use_cases

data class RedditApiRepositoryUseCase (
    val getRedditApiRepositoryFlow: GetRedditPostsFlow,
    val insertRedditPosts: InsertRedditPosts
)