package com.rulhouse.redditrestfulapi.model.repository.repository

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow

interface RepositoryRepository {

    suspend fun getFirstPosts(): Flow<BaseResult<List<Post>, Int>>

    suspend fun getNextPosts(): Flow<BaseResult<List<Post>, Int>>

    suspend fun getLocalPosts(): Flow<List<Post>>

    suspend fun insertLocalPosts(posts: List<Post>)

}