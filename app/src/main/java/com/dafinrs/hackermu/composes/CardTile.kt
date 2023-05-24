package com.dafinrs.hackermu.composes

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dafinrs.hackermu.ui.theme.HackermuTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTile(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title: String,
    subTitle: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.absolutePadding(left = 16.dp, right = 16.dp, top = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            maxLines = 1,
        )
        Text(
            modifier = Modifier.absolutePadding(
                right = 16.dp,
                left = 16.dp,
                top = 4.dp,
                bottom = 16.dp,
            ),
            text = subTitle,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    HackermuTheme {
        CardTile(title = "lorem", subTitle = "Ipsum")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardInDark() {
    HackermuTheme(darkTheme = true) {
        CardTile(title = "lorem", subTitle = "Ipsum")
    }
}