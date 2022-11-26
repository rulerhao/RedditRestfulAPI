package com.rulhouse.redditrestfulapi.model.repository.use_cases

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import com.rulhouse.redditrestfulapi.model.repository.repository.RepositoryRepository
import kotlinx.coroutines.flow.Flow

class GetFirstPosts (
    private val repository: RepositoryRepository
) {
    suspend operator fun invoke(): Flow<BaseResult<List<Post>, Int>> {
        return repository.getFirstPosts()
    }
}