package com.rulhouse.redditrestfulapi.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.redditrestfulapi.model.reddit.use_case.RedditApiUseCases
import com.rulhouse.redditrestfulapi.model.reddit.use_case.RedditPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val redditApiUseCases: RedditApiUseCases
) : ViewModel() {

    val tag = this::class.simpleName

    init {
        Log.d("TestRetrofit", "init")
        viewModelScope.launch {
            val response = redditApiUseCases.getFirstPost()
            response.enqueue(
                object : Callback<RedditPosts?> {
                    override fun onResponse(
                        call: Call<RedditPosts?>,
                        response: Response<RedditPosts?>
                    ) {
                        // 連線成功，透過 getter 取得特定欄位資料
                        Log.d(tag, "data: " + response.body()?.data)
                        Log.d(tag, "kind: " + response.body()?.kind)
                    }

                    override fun onFailure(call: Call<RedditPosts?>, t: Throwable) {
                        // 連線失敗，印出錯誤訊息
                        Log.d(tag, "response: $t")
                    }
                }
            )
//            response.let {
//                Log.d("TestRetrofit", it.)
//            }
        }
    }
}