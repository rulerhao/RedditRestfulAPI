package com.rulhouse.redditrestfulapi.model.repository.di

import com.rulhouse.redditrestfulapi.model.local.reddit_api_repository.domain.use_cases.RedditApiDBUseCases
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.RedditApiUseCases
import com.rulhouse.redditrestfulapi.model.repository.impl.DataRepositoryImplData
import com.rulhouse.redditrestfulapi.model.repository.repository.DataRepositoryRepository
import com.rulhouse.redditrestfulapi.model.repository.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryRepository(
        redditApiUseCases: RedditApiUseCases,
        redditApiDBUseCases: RedditApiDBUseCases
    ): DataRepositoryRepository {
        return DataRepositoryImplData(redditApiUseCases, redditApiDBUseCases)
    }

    @Provides
    @Singleton
    fun provideRedditApiRepositoryUseCases(repository: DataRepositoryRepository): DataRepositoryUseCases {
        return DataRepositoryUseCases(
            getFirstPosts = GetFirstPosts(repository),
            getNextPosts = GetNextPosts(repository),
            getLocalPosts = GetLocalPosts(repository),
            insertLocalPosts = InsertLocalPosts(repository)
        )
    }

}