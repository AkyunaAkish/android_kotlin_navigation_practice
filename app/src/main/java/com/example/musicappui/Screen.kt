package com.example.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        data object Account : DrawerScreen("Account", "account", R.drawable.ic_account)
        data object Subscription :
            DrawerScreen("Subscription", "subscribe", R.drawable.ic_subscribe)

        data object AddAccount :
            DrawerScreen("Add Account", "add_account", R.drawable.baseline_person_add_alt_1_24)
    }

    sealed class BottomScreen(
        val bTitle: String,
        val bRoute: String,
        @DrawableRes val icon: Int
    ) : Screen(bTitle, bRoute) {
        data object Home :
            BottomScreen("Home", "home", R.drawable.baseline_music_24)

        data object Library :
            BottomScreen("Library", "library", R.drawable.baseline_library_music_24)

        data object Browse :
            BottomScreen("Browse", "browse", R.drawable.baseline_manage_search_24)
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)

val screensInBottomBar = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Library,
    Screen.BottomScreen.Browse
)