package com.rulhouse.redditrestfulapi.model.local.crawler_repository.domain.use_cases

import com.rulhouse.redditrestfulapi.model.local.crawler_repository.domain.repository.RedditApiRepositoryRepository
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import kotlinx.coroutines.flow.Flow

class GetRedditPostsFlow (
    private val repository: RedditApiRepositoryRepository
) {
    suspend operator fun invoke(): Flow<List<Post>> {
        return repository.getPostsFlow()
    }
}