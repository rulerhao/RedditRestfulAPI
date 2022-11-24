package com.rulhouse.redditrestfulapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rulhouse.redditrestfulapi.ui.theme.RedditRestfulAPITheme
import com.rulhouse.redditrestfulapi.view.PostsScreen
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