package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.rulhouse.redditrestfulapi.view.posts_screen.event.PostsScreenEvent
import com.rulhouse.redditrestfulapi.view.posts_screen.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostsScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        itemCount += 5
        refreshing = false
    }
    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box {
        Column {
            PullRefreshIndicator(refreshing, state)
            Button(
                onClick = {
                    viewModel.onEvent(PostsScreenEvent.OnRefresh)
                }
            ) {
                Text(text = "Refresh")
            }
            Button(
                onClick = {
                    viewModel.onEvent(PostsScreenEvent.OnGetNewPosts)
                }
            ) {
                Text(text = "Add")
            }
            LazyColumn() {
                items(viewModel.posts) { item ->
                    Text(text = item.data.name)
                }
            }
        }
    }
}