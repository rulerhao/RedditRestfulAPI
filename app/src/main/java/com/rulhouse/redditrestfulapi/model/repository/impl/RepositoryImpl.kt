package com.rulhouse.redditrestfulapi.model.repository.impl

import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.use_cases.RedditApiRepositoryUseCase
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.RedditApiUseCases
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl {

    @Inject
    lateinit var apiUseCases: RedditApiUseCases

    @Inject
    lateinit var repositoryUseCases: RedditApiRepositoryUseCase

    private var posts: MutableList<Post> = mutableListOf()

    suspend fun getFirstPosts(): Flow<BaseResult<List<Post>, Int>> {
        return apiUseCases.getFirstPosts().apply {
                collect { baseResult ->
                    when (baseResult) {
                        is BaseResult.Success -> {
                            posts = baseResult.data.toMutableList()
                            repositoryUseCases.insertRedditPosts(posts = posts)
                        }
                        else -> {}
                    }
                }
        }
    }

    suspend fun getNextPosts(): Flow<BaseResult<List<Post>, Int>> {
        return apiUseCases.getNextPosts().apply {
            collect { baseResult ->
                when (baseResult) {
                    is BaseResult.Success -> {
                        posts.addAll(baseResult.data)
                        repositoryUseCases.insertRedditPosts(posts = posts)
                    }
                    else -> {}
                }
            }
        }
    }

    suspend fun getLocalPosts(): Flow<List<Post>> {
        return repositoryUseCases.getRedditApiRepositoryFlow().apply {
            collect { localPosts ->
                posts = localPosts.toMutableList()
            }
        }
    }

    suspend fun insertLocalPosts(posts: List<Post> ) {
        repositoryUseCases.insertRedditPosts(posts = posts)
    }

}