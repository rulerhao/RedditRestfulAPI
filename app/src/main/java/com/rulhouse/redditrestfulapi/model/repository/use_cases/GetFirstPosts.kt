package com.rulhouse.redditrestfulapi.model.repository.use_cases

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import com.rulhouse.redditrestfulapi.model.repository.repository.DataRepositoryRepository
import kotlinx.coroutines.flow.Flow

class GetFirstPosts (
    private val repository: DataRepositoryRepository
) {
    suspend operator fun invoke(): Flow<BaseResult<List<Post>, Int>> {
        return repository.getFirstPosts()
    }
}