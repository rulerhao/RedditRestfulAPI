package com.rulhouse.redditrestfulapi.model.remote.reddit.impl

import android.util.Log
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Children
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.GetFirstPostsRequest
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.GetNextPostsRequest
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.RedditApiPostsWrapper
import com.rulhouse.redditrestfulapi.model.remote.reddit.repository.RedditApiRepository
import com.rulhouse.redditrestfulapi.model.remote.reddit.service.RedditApiService
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RedditApiImpl(private val apiService: RedditApiService): RedditApiRepository {

    override val limit = 5
    override var lastName: String = ""

    override suspend fun getFirstPostCall(): Flow<BaseResult<List<Children>, Int>> {
//        apiService.getFirstPostsCall(limit = limit).enqueue(
//            object : Callback<RedditApiPostsWrapper?> {
//                override fun onResponse(
//                    call: Call<RedditApiPostsWrapper?>,
//                    response: Response<RedditApiPostsWrapper?>
//                ) {
//                    // 連線成功，透過 getter 取得特定欄位資料
//                    Log.d(tag, "id: " + response.body()?.id)
//                    Log.d(tag, "title: " + response.body()?.title)
//                    Log.d(tag, "body: " + response.body()?.body)
//                    Log.d(tag, "userId: " + response.body()?.userId)
//                }
//
//                override fun onFailure(call: Call<JsonplaceholderPost?>, t: Throwable) {
//                    // 連線失敗，印出錯誤訊息
//                    Log.d(tag, "response: $t")
//                }
//            }
//        )
//        response.enqueue(
//            object : Callback<RedditApiPostsWrapper?> {
//                override fun onResponse(
//                    call: Call<RedditApiPostsWrapper?>,
//                    response: Response<RedditApiPostsWrapper?>
//                ) {
//                    // 連線成功，透過 getter 取得特定欄位資料
//                    Log.d(tag, "id: " + response.body()?.id)
//                    Log.d(tag, "title: " + response.body()?.title)
//                    Log.d(tag, "body: " + response.body()?.body)
//                    Log.d(tag, "userId: " + response.body()?.userId)
//                }
//
//                override fun onFailure(call: Call<JsonplaceholderPost?>, t: Throwable) {
//                    // 連線失敗，印出錯誤訊息
//                    Log.d(tag, "response: $t")
//                }
//            }
//        )
        return flow {}
    }

    override suspend fun getFirstPost(): Flow<BaseResult<List<Children>, Int>> {
        val response = apiService.getFirstPosts(limit = limit)
        return returnFlow(response)
    }

    override suspend fun getNextPosts(): Flow<BaseResult<List<Children>, Int>> {
        val response = apiService.getNextPosts(limit = limit, after = lastName)
        return returnFlow(response)
    }

    private suspend fun returnFlow(response: Response<RedditApiPostsWrapper>): Flow<BaseResult<List<Children>, Int>> {
        return flow {
            if (response.isSuccessful) {
                val children = response.body()?.data!!.children
                lastName = children.last().data.name
                emit(BaseResult.Success(children))
            } else {
                val code = response.code()
                emit(BaseResult.Error(code))
            }
        }
    }
}