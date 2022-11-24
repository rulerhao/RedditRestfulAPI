package com.rulhouse.redditrestfulapi.model.remote.reddit.service

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.RedditApiPostsWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApiService {

    @GET("r/Taiwan/hot.json")
    fun getFirstPosts(
        @Query("limit") limit: Int
    ): Response<RedditApiPostsWrapper>

    @GET("r/Taiwan/hot.json")
    fun getNextPosts(
        @Query("limit") limit: Int,
        @Query("after") after: String,
    ): Response<RedditApiPostsWrapper>
}