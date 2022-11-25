package com.rulhouse.redditrestfulapi.model.local.crawler_repository.impl

import com.rulhouse.redditrestfulapi.model.local.crawler_repository.data.data_source.RedditApiRepositoryDao
import com.rulhouse.redditrestfulapi.model.local.crawler_repository.domain.repository.RedditApiRepositoryRepository
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