package com.rulhouse.redditrestfulapi.model.reddit.use_case

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object RedditApiModule {
    private const val BASE_URL = "https://www.reddit.com/r/Taiwan/hot.json/"

    @ActivityScoped
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @ActivityScoped
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @ActivityScoped
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @ActivityScoped
    @Provides
    fun provideRedditApiService(retrofit: Retrofit): RedditApiService {
        return retrofit.create(RedditApiService::class.java)
    }

    @ActivityScoped
    @Provides
    fun providesRepository(redditApiService: RedditApiService): RedditApiRepository {
        return RedditApiImpl(redditApiService)
    }

    @Provides
    @ActivityScoped
    fun provideRedditApiUseCases(repository: RedditApiRepository): RedditApiUseCases {
        return RedditApiUseCases(
            getFirstPost = GetFirstPost(repository)
        )
    }
}