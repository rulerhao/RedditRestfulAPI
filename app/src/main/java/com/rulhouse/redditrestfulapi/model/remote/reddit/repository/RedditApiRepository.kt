package com.rulhouse.redditrestfulapi.model.remote.reddit.repository

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow


interface RedditApiRepository {

    val limit: Int
    var lastName: String
//    val childrens: List<Children>
    suspend fun getFirstPost(): Flow<BaseResult<List<Post>, Int>>
    suspend fun getNextPosts(): Flow<BaseResult<List<Post>, Int>>
}