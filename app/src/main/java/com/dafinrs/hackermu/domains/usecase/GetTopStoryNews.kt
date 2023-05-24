package com.dafinrs.hackermu.domains.usecase

import com.dafinrs.hackermu.data.repository.StoryRepository
import com.dafinrs.hackermu.domains.models.StoryModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class GetTopStoryNews @Inject constructor(private val storyRepository: StoryRepository) :
    UsecaseMain<List<StoryModel>> {

    override suspend fun call(): List<StoryModel> = storyRepository.topStories()
}