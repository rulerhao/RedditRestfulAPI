package com.rulhouse.redditrestfulapi.model.reddit.use_case

import com.rulhouse.redditrestfulapi.model.reddit.dto.RedditApiPostsWrapper
import retrofit2.Response

class GetFirstPost (
    private val repository: RedditApiRepository
) {
    suspend operator fun invoke(): Response<RedditApiPostsWrapper> {
        return repository.getFirstPost()
    }
}