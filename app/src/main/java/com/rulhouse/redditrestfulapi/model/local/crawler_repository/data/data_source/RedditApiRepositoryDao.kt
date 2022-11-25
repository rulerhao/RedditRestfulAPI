package com.rulhouse.redditrestfulapi.model.local.crawler_repository.data.data_source

import androidx.room.*
import com.rulhouse.redditrestfulapi.model.remote.reddit.dto.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface RedditApiRepositoryDao {

    @Query("SELECT * FROM Post")
    fun getPostsFlow(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(products: List<Post>)

}