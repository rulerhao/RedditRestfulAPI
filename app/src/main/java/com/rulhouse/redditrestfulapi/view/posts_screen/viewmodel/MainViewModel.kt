package com.rulhouse.redditrestfulapi.view.posts_screen.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.model.remote.response.BaseResult
import com.rulhouse.redditrestfulapi.model.repository.use_cases.DataRepositoryUseCases
import com.rulhouse.redditrestfulapi.view.posts_screen.event.PostsScreenEvent
import com.rulhouse.redditrestfulapi.view.posts_screen.state.LayoutType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataRepositoryUseCases: DataRepositoryUseCases
) : ViewModel() {

    val tag = this::class.simpleName

    private val _posts = mutableStateListOf<Post>()
    val posts: List<Post> = _posts

    private val _layoutTypes = mutableStateOf(LayoutType.Apart)
    val layoutTypes: State<LayoutType> = _layoutTypes

    private var getPostsJob: Job? = null

    init {
        viewModelScope.launch {
            getLocalPosts()
        }
        viewModelScope.launch {
            getFirstPosts()
        }
    }
//    init {
//        Log.d("TestRetrofit", "init")
//        viewModelScope.launch {
//            redditApiUseCases.getFirstPosts()
//                .onStart {
//                    Log.d(tag, "onStart")
//                }
//                .catch { exception ->
//                    Log.d(tag, "catch: ${exception.message}")
//                }
//                .collect { baseResult ->
//                    when (baseResult) {
//                        is BaseResult.Success -> {
//                            posts.clear()
//                            posts.addAll(baseResult.data)
//                        }
//                        is BaseResult.Error -> Log.d(tag, "error: " + baseResult.rawResponse)
//
//                    }
//                }
//        }
//    }

    fun onEvent(event: PostsScreenEvent) {
        when (event) {
            is PostsScreenEvent.OnRefresh -> {
                viewModelScope.launch {
                    getFirstPosts()
                }
            }
            is PostsScreenEvent.OnGetNewPosts -> {
                viewModelScope.launch {
                    getNextPosts()
                }
            }
            is PostsScreenEvent.OnChangedLayout -> {
                if (layoutTypes.value == LayoutType.Overlap) {
                    _layoutTypes.value = LayoutType.Apart
                } else {
                    _layoutTypes.value = LayoutType.Overlap
                }
            }
        }
    }

    private suspend fun getFirstPosts() {
        getPostsJob?.cancel()
        getPostsJob = dataRepositoryUseCases.getFirstPosts()
            .onStart {
                Log.d(tag, "onStart")
            }
            .catch { exception ->
                Log.d(tag, "catch: ${exception.message}")
            }
            .onEach { baseResult ->
                when (baseResult) {
                    is BaseResult.Success -> {
                        _posts.clear()
                        _posts.addAll(baseResult.data)
                    }
                    is BaseResult.Error -> Log.d(tag, "error: " + baseResult.rawResponse)

                }
            }.launchIn(viewModelScope)
    }

    private suspend fun getNextPosts() {
        getPostsJob?.cancel()
        getPostsJob = dataRepositoryUseCases.getNextPosts()
            .onStart {
                Log.d(tag, "onStart")
            }
            .catch { exception ->
                Log.d(tag, "catch: ${exception.message}")
            }
            .onEach { baseResult ->
                when (baseResult) {
                    is BaseResult.Success -> {
                        _posts.addAll(baseResult.data)
                    }
                    is BaseResult.Error -> Log.d(tag, "error: " + baseResult.rawResponse)

                }
            }.launchIn(viewModelScope)
    }

    private suspend fun getLocalPosts() {
        dataRepositoryUseCases.getLocalPosts()
            .collect {
                if (posts.isEmpty()) {
                    _posts.addAll(it)
                }
            }
    }

}