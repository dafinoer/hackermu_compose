package com.dafinrs.hackermu.di

import androidx.paging.PagingSource
import com.dafinrs.hackermu.data.repository.StoryRepository
import com.dafinrs.hackermu.domains.models.StoryModel
import com.dafinrs.hackermu.domains.repository_impl.StoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStoryPaging(storyRepositoryImpl: StoryRepositoryImpl): PagingSource<Int, StoryModel>
}