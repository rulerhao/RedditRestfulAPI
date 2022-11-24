package com.rulhouse.redditrestfulapi.model.reddit.use_case

import java.util.concurrent.Flow

class RedditApiImpl(private val apiService: RedditApiService): RedditApiRepository {

    override val limit = 5

    override suspend fun getFirstPost(): Flow<BaseResult<>> {
        val result = apiService.getFirstPosts(limit)
        if (result.isSuccessful) {
//            result.body().data.children
            return Flow<>
        } else {

        }
        return apiService.getFirstPosts(limit)
    }

}