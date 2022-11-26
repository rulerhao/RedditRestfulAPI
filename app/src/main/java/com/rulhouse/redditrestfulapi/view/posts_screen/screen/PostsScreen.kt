package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rulhouse.redditrestfulapi.view.posts_screen.event.PostsScreenEvent
import com.rulhouse.redditrestfulapi.view.posts_screen.state.LayoutType
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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = viewModel.posts,

                ) { item ->
                    Post(
                        post = item,
                        layoutType = LayoutType.Overlap
                    )
                }
            }
        }
    }
}