package com.example.musicappui

import androidx.annotation.DrawableRes

data class Library(
    @DrawableRes val icon: Int,
    val name: String
)

val libraries = listOf<Library>(
    Library(
        icon = R.drawable.baseline_music_24,
        name = "Playlist"
    ),
    Library(
        icon = R.drawable.baseline_manage_search_24,
        name = "Artists"
    ),
    Library(
        icon = R.drawable.ic_subscribe,
        name = "Album"
    ),
    Library(
        icon = R.drawable.baseline_library_music_24,
        name = "Songs"
    ),
    Library(
        icon = R.drawable.baseline_person_add_alt_1_24,
        name = "Genres"
    )
)
