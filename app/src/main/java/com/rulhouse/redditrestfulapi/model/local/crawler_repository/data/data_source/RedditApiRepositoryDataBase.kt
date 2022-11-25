package com.rulhouse.redditrestfulapi.model.local.crawler_repository.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import com.rulhouse.redditrestfulapi.util.Converters

@Database(
    entities = [Post::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RedditApiRepositoryDataBase : RoomDatabase() {

    abstract val redditApiRepositoryDao: RedditApiRepositoryDao

    companion object {
        const val DATABASE_NAME = "reddit_api_repository_db"
    }
}