package com.dafinrs.hackermu.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.dafinrs.hackermu.composes.CardTile
import com.dafinrs.hackermu.domains.models.StoryModel
import com.dafinrs.hackermu.presents.news.StoryNewsViewModel
import com.dafinrs.hackermu.ui.theme.HackermuTheme


@Composable
fun ContentStory(
    modifier: Modifier = Modifier,
    lazyListflow: LazyPagingItems<StoryModel>,
    onOpenUrl: (String) -> Unit,
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val state = lazyListflow.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    ContentShimmerLoading()
                }
            }

            is LoadState.Error -> {
                item {
                    Text(text = state.error.message ?: "")
                }
            }
        }

        when (val state = lazyListflow.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item { LoadingContent() }
            }

            is LoadState.Error -> {
                item { Text(text = state.error.message ?: "") }
            }
        }

        items(
            count = lazyListflow.itemCount,
            contentType = lazyListflow.itemContentType(),
        ) {
            CardTile(
                title = lazyListflow[it]?.title ?: "",
                subTitle = lazyListflow[it]?.url ?: "",
                onClick = {
                    val urlStory = lazyListflow[it]?.url
                    if (urlStory != null) {
                        onOpenUrl(urlStory)
                    }
                },
            )
        }

        when (val state = lazyListflow.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> item { CircularProgressIndicator() }
            is LoadState.Error -> item { Text(text = state.error.message ?: "") }
        }
    }
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewList() {
    HackermuTheme {
        LoadingContent()
    }
}