package com.rulhouse.redditrestfulapi.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rulhouse.redditrestfulapi.ui.theme.RedditRestfulAPITheme
import com.rulhouse.redditrestfulapi.view.posts_screen.screen.PostsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RedditRestfulAPITheme {
                PostsScreen()
            }
        }
    }
}