package com.rulhouse.redditrestfulapi.model.reddit.use_case

import retrofit2.Response


interface RedditApiRepository {


    suspend fun getFirstPost(): Response<RedditPosts>
//    suspend fun get
//    suspend fun getPosts(limit: Int, later: String): RedditPosts {}
}