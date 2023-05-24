package com.dafinrs.hackermu.pages.detail

import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView


const val DETAIL_SCREEN_PAGE_ROUTE = "/detail"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPage(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    isBack: Boolean = false,
    url: String?,
) {
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
            Text(text = "Url")
        }, navigationIcon = {
            if (isBack) IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back")
            }
        })
    }) {
        if (url != null) {
            AndroidView(modifier = modifier.padding(it), factory = {
                WebView(it).apply {
                    loadUrl(url)
                }
            })
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "No Url View")
            }
        }
    }
}