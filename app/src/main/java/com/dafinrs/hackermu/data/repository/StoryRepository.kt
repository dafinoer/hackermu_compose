package com.dafinrs.hackermu.data.repository

import com.dafinrs.hackermu.domains.models.StoryModel


interface StoryRepository {

    suspend fun topStories(): List<StoryModel>
}