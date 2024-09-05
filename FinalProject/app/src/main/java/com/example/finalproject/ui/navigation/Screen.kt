package com.example.finalproject.ui.navigation

sealed class Screen(
    val route: String
) {

    object MainTab : Screen(MAIN_TAB)
    object FavouriteTab : Screen(FAVOURITE_TAB)
    object ChatTab : Screen(CHAT_TAB)
    object ProfileTab : Screen(PROFILE_TAB)

    object SplashScreen : Screen(ROUTE_SPLASH_SCREEN)

    object AuthPage : Screen(ROUTE_AUTH_PAGE)
    object RegisterPage : Screen(ROUTE_REGISTER_PAGE)

    object MainPage : Screen(ROUTE_HOME_PAGE)

    object ShowAllPage : Screen(ROUTE_SHOW_ALL_PAGE) {

        private const val ROUTE_FOR_ARGS = "show_all_page"

        fun getRouteWithArgs(type: String): String {
            return "$ROUTE_FOR_ARGS/$type"
        }
    }

    object DetailPage : Screen(ROUTE_DETAIL_PAGE) {

        private const val ROUTE_FOR_ARGS = "detail_page"

        fun getRouteWithArgs(type: String, id: String): String {
            return "$ROUTE_FOR_ARGS/$type/$id"
        }
    }


    object FavouritePage : Screen(ROUTE_FAVOURITE_PAGE)
    object AddNotePage : Screen(ROUTE_ADD_NOTE_PAGE)
    object ChatPage : Screen(ROUTE_CHAT_PAGE)
    object ProfilePage : Screen(ROUTE_PROFILE_PAGE)

    companion object {

        //Tabs
        const val MAIN_TAB = "main_tab"
        const val CHAT_TAB = "chat_tab"
        const val FAVOURITE_TAB = "favourite_tab"
        const val PROFILE_TAB = "profile_tab"

        // Arg keys
        const val KEY_SHOW_ALL_TYPE = "type_of_info"
        const val KEY_ID = "id"

        // Screen routes
        const val ROUTE_SPLASH_SCREEN = "splash_screen"

        const val ROUTE_AUTH_PAGE = "auth_page"
        const val ROUTE_REGISTER_PAGE = "register_page"

        const val ROUTE_HOME_PAGE = "home_page"
        const val ROUTE_SHOW_ALL_PAGE = "show_all_page/{$KEY_SHOW_ALL_TYPE}"
        const val ROUTE_DETAIL_PAGE = "detail_page/{$KEY_SHOW_ALL_TYPE}/{$KEY_ID}"

        const val ROUTE_FAVOURITE_PAGE = "favourite_page"
        const val ROUTE_ADD_NOTE_PAGE = "add_note_page"
        const val ROUTE_CHAT_PAGE = "chat_page"
        const val ROUTE_PROFILE_PAGE = "profile_page"
    }
}