package com.rulhouse.redditrestfulapi.model.reddit.use_case

import com.rulhouse.redditrestfulapi.model.reddit.dto.RedditApiPostsWrapper
import retrofit2.Response


interface RedditApiRepository {

    val limit: Int
    suspend fun getFirstPost(): Response<RedditApiPostsWrapper>
//    suspend fun get
//    suspend fun getPosts(limit: Int, later: String): RedditPosts {}
}