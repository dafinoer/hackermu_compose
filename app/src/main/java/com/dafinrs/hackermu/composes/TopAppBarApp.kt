package com.dafinrs.hackermu.composes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHacker(
    modifier: Modifier = Modifier,
    titleName: String,
    isHavePreviousPage: Boolean = false,
    onBackButton: () -> Unit = {}
) {
    MediumTopAppBar(
        modifier = modifier,
        navigationIcon = {
            if (isHavePreviousPage) {
                IconButton(onClick = onBackButton) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        title = {
            Text(text = titleName, style = MaterialTheme.typography.headlineSmall)
        },

    )
}