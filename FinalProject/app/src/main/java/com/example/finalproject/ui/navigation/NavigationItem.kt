package com.example.finalproject.ui.navigation

import com.example.finalproject.R

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: Int
) {

    object Home : NavigationItem(
        screen = Screen.MainPage,
        titleResId = R.string.home_page,
        icon = R.drawable.ic_home
    )

    object Favourite : NavigationItem(
        screen = Screen.FavouritePage,
        titleResId = R.string.favourite_page,
        icon = R.drawable.ic_favourite_teb
    )

    object AddNote : NavigationItem(
        screen = Screen.AddNotePage,
        titleResId = R.string.add_note,
        icon = R.drawable.ic_add
    )

    object Chat : NavigationItem(
        screen = Screen.ChatPage,
        titleResId = R.string.chat_page,
        icon = R.drawable.ic_chat
    )

    object Profile : NavigationItem(
        screen = Screen.ProfilePage,
        titleResId = R.string.profile_page,
        icon = R.drawable.ic_profile
    )

}