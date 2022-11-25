package com.rulhouse.redditrestfulapi.model.remote.reddit.di

import com.rulhouse.redditrestfulapi.model.remote.reddit.impl.RedditApiImpl
import com.rulhouse.redditrestfulapi.model.remote.reddit.repository.RedditApiRepository
import com.rulhouse.redditrestfulapi.model.remote.reddit.service.RedditApiService
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.GetFirstPosts
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.GetNextPosts
import com.rulhouse.redditrestfulapi.model.remote.reddit.use_case.RedditApiUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RedditApiModule {
    private const val BASE_URL = "https://www.reddit.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideRedditApiService(retrofit: Retrofit): RedditApiService {
        return retrofit.create(RedditApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(redditApiService: RedditApiService): RedditApiRepository {
        return RedditApiImpl(redditApiService)
    }

    @Singleton
    @Provides
    fun provideRedditApiUseCases(repository: RedditApiRepository): RedditApiUseCases {
        return RedditApiUseCases(
            getFirstPosts = GetFirstPosts(repository),
            getNextPosts = GetNextPosts(repository)
        )
    }
}