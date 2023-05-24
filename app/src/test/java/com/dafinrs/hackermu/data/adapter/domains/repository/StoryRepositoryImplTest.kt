package com.dafinrs.hackermu.data.adapter.domains.repository

import com.dafinrs.hackermu.data.remote.StoriesRemote
import com.dafinrs.hackermu.di.NetworkModule
import com.dafinrs.hackermu.domains.repository_impl.StoryRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.SpyK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert
import org.junit.Rule

class StoryRepositoryImplTest {

    private val listId = listOf(
        9129911, 9129199, 9127761, 9128141, 9128264
    )

    @get:Rule
    val mockRule = MockKRule(this)

    @SpyK
    var mockStoriesRemote: StoriesRemote =
        StoriesRemote(NetworkModule.okHttpClient(), NetworkModule.moshiParser())

    @Test
    fun get_stories_hacker_news() = runTest {
        coEvery { mockStoriesRemote.getTopIdStory(any()) } returns listId
        val storyRepository = StoryRepositoryImpl(mockStoriesRemote)
        val result = storyRepository.topStories()

        Assert.assertEquals(result.size, listId.size)
        coVerify(exactly = 1) {
            mockStoriesRemote.getTopIdStory(any())
        }
    }

    @Test
    fun get_stories_with_exception() = runTest {
        coEvery { mockStoriesRemote.getTopIdStory(any()) } throws IllegalArgumentException()
        val storyRepository = StoryRepositoryImpl(mockStoriesRemote)
        try {
            storyRepository.topStories()
            Assert.fail("test failed")
        } catch (e: IllegalArgumentException) {
            println("error: $e")
        } finally {
            coVerify(exactly = 1) {
                mockStoriesRemote.getTopIdStory(any())
            }
        }
    }

    @Test
    fun get_stories_items() = runTest {
        coEvery { mockStoriesRemote.getTopIdStory(any()) } returns listId
        val storyRepo = StoryRepositoryImpl(mockStoriesRemote)
        val result = storyRepo.topStories()

        Assert.assertEquals(result.size, listId.size)
        coVerify(exactly = 1) {
            mockStoriesRemote.getTopIdStory(any())
        }

    }
}