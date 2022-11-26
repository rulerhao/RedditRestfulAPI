package com.rulhouse.redditrestfulapi.model.repository.use_cases

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.repository.repository.RepositoryRepository
import kotlinx.coroutines.flow.Flow

class GetLocalPosts (
    private val repository: RepositoryRepository
) {
    suspend operator fun invoke(): Flow<List<Post>> {
        return repository.getLocalPosts()
    }
}