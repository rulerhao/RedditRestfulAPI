package com.rulhouse.redditrestfulapi.model.reddit.use_case

import retrofit2.Response

class RedditApiImpl(private val apiService: RedditApiService): RedditApiRepository {

    override suspend fun getFirstPost(): Response<RedditPosts> {
        return apiService.getPosts(5, "a")
    }

}