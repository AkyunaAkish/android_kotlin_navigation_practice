package com.example.musicappui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val categories = listOf(
        "Hip-Hop",
        "Pop",
        "Rock",
        "R&B",
        "EDM",
        "Country",
        "Jazz",
        "Classical",
        "Metal",
        "Blues",
        "Reggae",
        "Funk",
        "Soul",
        "Folk",
        "Punk",
        "Gospel",
        "Ska",
        "Disco",
        "Techno",
        "House",
        "Dubstep",
        "Trap",
        "Rap",
        "Indie",
        "Alternative",
        "Electro",
        "Ambient",
        "Instrumental",
        "Acoustic",
        "World",
        "Latin",
        "Reggaeton",
        "Salsa",
        "Bachata",
        "Merengue",
        "Cumbia",
        "Flamenco",
        "Samba",
        "Tango",
        "Bossa Nova",
        "Bulldozer",
        "Chillout",
        "Dancehall",
        "Drill",
        "Dub",
        "Funk",
        "Gangsta Rap",
        "Hardcore",
        "Hip-Hop",
        "House",
        "Jazz",
        "K-pop",
        "Latin",
        "Lofi",
        "Metal",
        "Pop",
        "Punk",
        "R&B",
        "Rap",
        "Reggae",
        "Rock",
        "Salsa",
        "Samba",
        "Ska",
        "Soul",
        "Techno",
        "Trap",
        "World"
    )

    val grouped = listOf<String>(
        "New Release",
        "Favorites",
        "Top Rated"
    ).groupBy { it[0] }

    LazyColumn {
        grouped.forEach {
            stickyHeader {
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))

                LazyRow {
                    items(categories) { category ->
                        BrowserItem(category, drawable = R.drawable.baseline_manage_search_24)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserItem(category: String, drawable: Int) {
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