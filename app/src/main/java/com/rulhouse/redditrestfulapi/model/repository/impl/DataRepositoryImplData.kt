package com.rulhouse.redditrestfulapi.model.repository.impl

import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.use_cases.RedditApiDBUseCases
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.RedditApiUseCases
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import com.rulhouse.redditrestfulapi.model.repository.repository.DataRepositoryRepository
import kotlinx.coroutines.flow.Flow

class DataRepositoryImplData(
    private val redditApiUseCases: RedditApiUseCases,
    private val redditApiDBUseCases: RedditApiDBUseCases
): DataRepositoryRepository {

    private var posts: MutableList<Post> = mutableListOf()

    override suspend fun getFirstPosts(): Flow<BaseResult<List<Post>, Int>> {
        return redditApiUseCases.getFirstPosts().apply {
                collect { baseResult ->
                    when (baseResult) {
                        is BaseResult.Success -> {
                            posts = baseResult.data.toMutableList()
                            redditApiDBUseCases.insertRedditPosts(posts = posts)
                        }
                        else -> {
                        }
                    }
                }
        }
    }

    override suspend fun getNextPosts(): Flow<BaseResult<List<Post>, Int>> {
        return redditApiUseCases.getNextPosts().apply {
            collect { baseResult ->
                when (baseResult) {
                    is BaseResult.Success -> {
                        posts.addAll(baseResult.data)
                        redditApiDBUseCases.insertRedditPosts(posts = posts)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    override suspend fun getLocalPosts(): Flow<List<Post>> {
        return redditApiDBUseCases.getRedditApiRepositoryFlow().apply {
            collect { localPosts ->
                posts = localPosts.toMutableList()
            }
        }
    }

    override suspend fun insertLocalPosts(posts: List<Post>) {
        redditApiDBUseCases.insertRedditPosts(posts = posts)
    }

}