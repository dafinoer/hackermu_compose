package com.dafinrs.hackermu.di

import com.dafinrs.hackermu.data.repository.StoryRepository
import com.dafinrs.hackermu.domains.usecase.GetTopStoryNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object DomainRepositoryModule {

    @Provides
    fun bindTopStoryRepository(storyRepository: StoryRepository): GetTopStoryNews {
        return GetTopStoryNews(storyRepository)
    }
}