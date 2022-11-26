package com.rulhouse.redditrestfulapi.model.repository.di

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
    fun provideRepositoryRepository(): DataRepositoryRepository {
        return DataRepositoryImplData()
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