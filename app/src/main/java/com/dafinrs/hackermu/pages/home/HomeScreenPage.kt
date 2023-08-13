package com.dafinrs.hackermu.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dafinrs.hackermu.R
import com.dafinrs.hackermu.composes.TopAppBarHacker
import com.dafinrs.hackermu.domains.models.StoryModel
import com.dafinrs.hackermu.pages.detail.DETAIL_SCREEN_PAGE_ROUTE
import com.dafinrs.hackermu.presents.news.StoryNewsViewModel
import com.dafinrs.hackermu.ui.theme.HackermuTheme


const val HOME_SCREEN_PAGE = "/home"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage(
    onNavigateDetail: (String) -> Unit,
) {
    val storyNewsViewModel = hiltViewModel<StoryNewsViewModel>()

    Scaffold(
        topBar = {
            TopAppBarHacker(
                titleName = stringResource(id = R.string.app_name),
            )
        },
    ) {
        val lazyListFlow = storyNewsViewModel.flowList.collectAsLazyPagingItems()

        ContentStory(
            modifier = Modifier.padding(it),
            onOpenUrl = onNavigateDetail,
            lazyListflow = lazyListFlow,
        )
    }
}
