package com.rulhouse.redditrestfulapi.model.reddit.use_case

import retrofit2.Response

class GetFirstPost (
    private val repository: RedditApiRepository
) {
    suspend operator fun invoke(): Response<RedditPosts> {
        return repository.getFirstPost()
    }
}