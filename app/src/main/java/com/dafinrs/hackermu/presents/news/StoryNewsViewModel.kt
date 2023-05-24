package com.dafinrs.hackermu.presents.news

import android.util.Log
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.dafinrs.hackermu.domains.models.StoryModel
import com.dafinrs.hackermu.domains.usecase.GetTopStoryNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryNewsViewModel @Inject constructor(
    private val pagingSource: PagingSource<Int, StoryModel>
) : ViewModel() {

    fun flowList(): Flow<PagingData<StoryModel>> = Pager(
        PagingConfig(
            pageSize = 10, enablePlaceholders = true, initialLoadSize = 10
        )
    ) {
        pagingSource
    }.flow.stateIn(
        viewModelScope, initialValue = PagingData.empty(), started = SharingStarted.Lazily,
    )
}