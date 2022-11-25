package com.rulhouse.redditrestfulapi.model.local.crawler_repository.domain.repository

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import kotlinx.coroutines.flow.Flow

interface RedditApiRepositoryRepository {
    suspend fun getPostsFlow(): Flow<List<Post>>

    suspend fun insertPosts(posts: List<Post>)
}