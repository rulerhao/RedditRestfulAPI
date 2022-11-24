package com.rulhouse.redditrestfulapi.model.remote.reddit.use_case

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Children
import com.rulhouse.redditrestfulapi.model.remote.reddit.repository.RedditApiRepository
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow

class GetNextPosts (
    private val repository: RedditApiRepository
) {
    suspend operator fun invoke(): Flow<BaseResult<List<Children>, Int>> {
        return repository.getNextPosts()
    }
}