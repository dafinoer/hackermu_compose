package com.dafinrs.hackermu.di


import com.dafinrs.hackermu.data.adapter.EventJsonStoryAdapter
import com.dafinrs.hackermu.data.remote.StoriesRemote
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()


    fun provideHttpRequest(path: String, queryParams: Map<String, String> = emptyMap()): HttpUrl =
        HttpUrl.Builder().apply {
            scheme("https")
            host("hacker-news.firebaseio.com")
            addPathSegment("v0/")
            addPathSegment(path)
            queryParams.forEach {
                addQueryParameter(it.key, it.value)
            }
        }.build()

    @Provides
    @Singleton
    fun moshiParser(): Moshi = Moshi.Builder().apply {
        add(EventJsonStoryAdapter())
        addLast(KotlinJsonAdapterFactory())
    }.build()

    @Provides
    fun binStoriesRemote(okHttpClient: OkHttpClient, moshi: Moshi): StoriesRemote =
        StoriesRemote(okHttpClient, moshi)
}