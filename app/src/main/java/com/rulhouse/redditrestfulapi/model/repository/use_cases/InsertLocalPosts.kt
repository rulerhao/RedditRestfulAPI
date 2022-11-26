package com.rulhouse.redditrestfulapi.model.repository.use_cases

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.repository.repository.DataRepositoryRepository

class InsertLocalPosts (
    private val repository: DataRepositoryRepository
) {
    suspend operator fun invoke(posts: List<Post>) {
        repository.insertLocalPosts(posts)
    }
}