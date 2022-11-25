package com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.di

import android.app.Application
import androidx.room.Room
import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.data.data_source.RedditApiRepositoryDataBase
import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.repository.RedditApiRepositoryRepository
import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.impl.RedditApiRepositoryImpl
import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RedditApiRepositoryModule {
    @Provides
    @Singleton
    fun provideRedditApiRepositoryDatabase(app: Application): RedditApiRepositoryDataBase {
        return Room.databaseBuilder(
            app,
            RedditApiRepositoryDataBase::class.java,
            RedditApiRepositoryDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRedditApiRepositoryRepository(db: RedditApiRepositoryDataBase): RedditApiRepositoryRepository {
        return RedditApiRepositoryImpl(db.redditApiRepositoryDao)
    }

    @Provides
    @Singleton
    fun provideRedditApiRepositoryUseCases(repository: RedditApiRepositoryRepository): RedditApiRepositoryUseCase {
        return RedditApiRepositoryUseCase(
            getRedditApiRepositoryFlow = GetRedditPostsFlow(repository),
            insertRedditPosts = InsertRedditPosts(repository)
        )
    }
}