package com.rulhouse.redditrestfulapi.view.posts_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rulhouse.redditrestfulapi.view.posts_screen.event.PostsScreenEvent
import com.rulhouse.redditrestfulapi.view.posts_screen.viewmodel.MainViewModel

@Composable
fun PostsScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    Column{
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