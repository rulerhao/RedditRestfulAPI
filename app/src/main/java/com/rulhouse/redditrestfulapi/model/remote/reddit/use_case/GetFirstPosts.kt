package com.rulhouse.redditrestfulapi.model.remote.reddit.use_case

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.reddit.repository.RedditApiRepository
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow

class GetFirstPosts (
    private val repository: RedditApiRepository
) {
    suspend operator fun invoke(): Flow<BaseResult<List<Post>, Int>> {
        return repository.getFirstPost()
    }
}