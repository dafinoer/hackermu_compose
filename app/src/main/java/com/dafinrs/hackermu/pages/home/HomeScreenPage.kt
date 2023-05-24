package com.dafinrs.hackermu.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dafinrs.hackermu.R
import com.dafinrs.hackermu.composes.TopAppBarHacker
import com.dafinrs.hackermu.presents.news.StoryNewsViewModel
import com.dafinrs.hackermu.ui.theme.HackermuTheme


const val HOME_SCREEN_PAGE = "/home"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage(
    onNavigateDetail: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarHacker(
                titleName = stringResource(id = R.string.app_name),
            )
        },
    ) {
        ContentStory(modifier = Modifier.padding(it), onOpenUrl = onNavigateDetail)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    HackermuTheme(darkTheme = false) {
        HomeScreenPage(onNavigateDetail = {})
    }
}