package com.rulhouse.redditrestfulapi.model.repository.use_cases

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.repository.repository.RepositoryRepository

class InsertLocalPosts (
    private val repository: RepositoryRepository
) {
    suspend operator fun invoke(posts: List<Post>) {
        repository.insertLocalPosts(posts)
    }
}