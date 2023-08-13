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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StoryNewsViewModel @Inject constructor(
    private val pagingSource: PagingSource<Int, StoryModel>
) : ViewModel() {

    val flowList = Pager(
        PagingConfig(
            pageSize = 10, enablePlaceholders = true, initialLoadSize = 10
        )
    ) {
        pagingSource
    }.flow.stateIn(
        viewModelScope,
        initialValue = PagingData.empty(),
        started = SharingStarted.WhileSubscribed(5000),
    ).cachedIn(viewModelScope)
}