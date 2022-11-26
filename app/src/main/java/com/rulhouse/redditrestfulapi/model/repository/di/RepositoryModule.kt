package com.rulhouse.redditrestfulapi.model.repository.di

import com.rulhouse.redditrestfulapi.model.repository.impl.RepositoryImpl
import com.rulhouse.redditrestfulapi.model.repository.repository.RepositoryRepository
import com.rulhouse.redditrestfulapi.model.repository.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryRepository(): RepositoryRepository {
        return RepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideRedditApiRepositoryUseCases(repository: RepositoryRepository): RepositoryUseCases {
        return RepositoryUseCases(
            getFirstPosts = GetFirstPosts(repository),
            getNextPosts = GetNextPosts(repository),
            getLocalPosts = GetLocalPosts(repository),
            insertLocalPosts = InsertLocalPosts(repository)
        )
    }

}