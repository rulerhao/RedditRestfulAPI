package com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.impl

import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.data.data_source.RedditApiRepositoryDao
import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.repository.RedditApiRepositoryRepository
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import kotlinx.coroutines.flow.Flow

class RedditApiRepositoryImpl (
    private val dao: RedditApiRepositoryDao
) : RedditApiRepositoryRepository {

    override suspend fun getPostsFlow(): Flow<List<Post>> {
        return dao.getPostsFlow()
    }

    override suspend fun insertPosts(posts: List<Post>) {
        dao.insertPosts(posts)
    }

}