package com.rulhouse.redditrestfulapi.model.remote.reddit.impl

import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.RedditApiPostsWrapper
import com.rulhouse.redditrestfulapi.model.remote.reddit.repository.RedditApiRepository
import com.rulhouse.redditrestfulapi.model.remote.reddit.service.RedditApiService
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RedditApiImpl(private val apiService: RedditApiService): RedditApiRepository {

    override val limit = 5
    override var lastName: String = ""
//    override val childrens: List<Children> = Array
    override suspend fun getFirstPost(): Flow<BaseResult<List<Post>, Int>> {
        val response = apiService.getFirstPosts(limit = limit)
        return returnFlow(response)
    }

    override suspend fun getNextPosts(): Flow<BaseResult<List<Post>, Int>> {
        val response = apiService.getNextPosts(limit = limit, after = lastName)
        return returnFlow(response)
    }

    private suspend fun returnFlow(response: Response<RedditApiPostsWrapper>): Flow<BaseResult<List<Post>, Int>> {
        return flow {

            if (response.isSuccessful) {
                val children = response.body()?.data!!.children
                lastName = children.last().data.name
                emit(BaseResult.Success(children.map {
                    Post(
                        id = it.data.id,
                        thumbnailUri = it.data.thumbnail,
                        title = it.data.title,
                        thumbnail_width = it.data.thumbnail_width,
                        thumbnail_height = it.data.thumbnail_height,
                    )
                }))
            } else {
                val code = response.code()
                emit(BaseResult.Error(code))
            }
        }
    }
}