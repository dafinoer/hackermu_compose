package com.dafinrs.hackermu.domains.repository_impl


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dafinrs.hackermu.data.remote.StoriesRemote
import com.dafinrs.hackermu.domains.models.StoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject
import kotlinx.coroutines.withContext


class StoryRepositoryImpl @Inject constructor(
    private val storiesRemote: StoriesRemote
) : PagingSource<Int, StoryModel>() {

    private val ids = mutableListOf<Int>()

    companion object {
        const val TOP_STORIES_PATH = "/topstories.json"
    }

    override fun getRefreshKey(state: PagingState<Int, StoryModel>): Int? =
        state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryModel> =
        withContext(Dispatchers.IO) {
            try {
                val nextPage = params.key ?: 0
                val loadSize = params.loadSize
                var stories = listOf<StoryModel>()
                addIds()

                supervisorScope {
                    val listStories = listTenItem(nextPage, loadSize).map {
                        async { storiesRemote.getStory(it) }
                    }.toList()
                    stories = listStories.map { it.await() }.mapNotNull { it }
                        .filter { it.url?.isNotBlank() ?: false }.toList()
                }

                return@withContext LoadResult.Page(
                    data = stories,
                    prevKey = null,
                    nextKey = if (nextPage < ids.size) nextPage.plus(loadSize) else null,
                )
            } catch (error: Exception) {
                return@withContext LoadResult.Error(error)
            }
        }

    private fun addIds() {
        if (ids.isEmpty()) {
            val responseIds = storiesRemote.getTopIdStory(TOP_STORIES_PATH, limit = 60)
            ids.addAll(responseIds)
        }
    }

    private fun listTenItem(startFrom: Int, size: Int): List<Int> {
        val idsStory = mutableListOf<Int>()
        val total = startFrom + size
        for (index in startFrom until total) {
            if (index < ids.size) {
                idsStory.add(ids[index])
            } else {
                break
            }
        }

        return idsStory
    }

}