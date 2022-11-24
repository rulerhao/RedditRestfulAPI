package com.rulhouse.redditrestfulapi.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.RedditApiUseCases
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val redditApiUseCases: RedditApiUseCases
) : ViewModel() {

    val tag = this::class.simpleName

    init {
        Log.d("TestRetrofit", "init")
        viewModelScope.launch {
            redditApiUseCases.getFirstPosts()
                .onStart {
                    Log.d(tag, "onStart")
                }
                .catch { exception ->
                    Log.d(tag, "catch: ${exception.message}")
                }
                .collect { baseResult ->
                    when (baseResult) {
                        is BaseResult.Success -> Log.d(tag, "data: " + baseResult.data[0])
                        is BaseResult.Error -> Log.d(tag, "error: " + baseResult.rawResponse)

                    }
                }
        }
    }
}