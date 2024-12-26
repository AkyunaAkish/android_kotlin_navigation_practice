package com.example.musicappui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BrowseView() {
    val categories = listOf(
        "Hip-Hop",
        "Ska",
        "Soul",
        "Techno",
        "Trap",
        "World"
    )

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories) { category ->
            BrowseViewItem(category = category, drawable = R.drawable.baseline_music_24)
        }
    }
}

@Composable
fun BrowseViewItem(category: String, drawable: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp),
        border = BorderStroke(3.dp, color = Color.Gray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = category, modifier = Modifier.padding(16.dp))
            Image(
                painterResource(id = drawable),
                contentDescription = category,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun BrowseViewPreview() {
    BrowseView()
}