package com.rulhouse.redditrestfulapi.model.repository.use_cases

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.repository.repository.DataRepositoryRepository
import kotlinx.coroutines.flow.Flow

class GetLocalPosts (
    private val repository: DataRepositoryRepository
) {
    suspend operator fun invoke(): Flow<List<Post>> {
        return repository.getLocalPosts()
    }
}