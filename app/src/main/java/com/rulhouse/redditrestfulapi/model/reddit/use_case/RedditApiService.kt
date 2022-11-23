package com.rulhouse.redditrestfulapi.model.reddit.use_case

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApiService {
    @GET("/r/Taiwan/hot.json")
    fun getPosts(
        @Query("limit") limit: Int,
        @Query("after") after: String,
    ): Response<RedditPosts>
}