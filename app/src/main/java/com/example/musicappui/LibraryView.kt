package com.example.musicappui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LibraryView() {
    LazyColumn {
        items(libraries) { library ->
            LibraryItemView(library = library)
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun LibraryItemView(library: Library) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.Start
            ) {
                Row {
                    Icon(
                        painter = painterResource(library.icon),
                        contentDescription = library.name,
                    )
                    Text(library.name, color = Color.Black)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = androidx.compose.ui.Alignment.End
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "enter"
                )
            }
        }
    }
}