package com.dafinrs.hackermu.pages.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.hackermu.composes.CardTile
import com.dafinrs.hackermu.ui.theme.HackermuTheme
import com.valentinilk.shimmer.shimmer


@Composable
fun ContentShimmerLoading(modifier: Modifier = Modifier) {
    Column(modifier = modifier.shimmer()) {
        for (item in 0 until 5) CardLoading()
    }
}

@Composable
fun CardLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .aspectRatio(3f)
            .clip(RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colorScheme.onSurface)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCardLoading() {
    HackermuTheme(darkTheme = false) {
        ContentShimmerLoading()
    }
}