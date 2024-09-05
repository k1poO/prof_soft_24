package com.example.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.finalproject.ui.components.BottomBar
import com.example.finalproject.ui.components.tool_bar.ToolBar
import com.example.finalproject.ui.components.tool_bar.ToolBarType
import com.example.finalproject.ui.navigation.AppNavGraph
import com.example.finalproject.ui.navigation.NavigationItem
import com.example.finalproject.ui.navigation.Screen
import com.example.finalproject.ui.navigation.rememberNavigationState
import com.example.finalproject.ui.screens.add_note_screen.AddNoteScreen
import com.example.finalproject.ui.screens.auth_screen.AuthScreen
import com.example.finalproject.ui.screens.chat_screen.ChatScreen
import com.example.finalproject.ui.screens.detail_screen.DetailScreen
import com.example.finalproject.ui.screens.favourite_screen.FavouriteScreen
import com.example.finalproject.ui.screens.main_screen.MainScreen
import com.example.finalproject.ui.screens.profile_screen.ProfileScreen
import com.example.finalproject.ui.screens.register_screen.RegisterScreen
import com.example.finalproject.ui.screens.show_all_screen.ShowAllScreen
import com.example.finalproject.ui.screens.splash_screen.SplashScreen
import com.example.finalproject.ui.theme.FinalProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FinalProjectTheme {
                val navigationState = rememberNavigationState()

                val isBottomVBarVisible = remember {
                    mutableStateOf(false)
                }
                val toolBar = remember {
                    mutableStateOf(ToolBarType(onBackClick = {
                        navigationState.navHostController.popBackStack()
                    }))
                }
                Scaffold(
                    modifier = Modifier,
                    topBar = {
                        AnimatedVisibility(
                            visible = toolBar.value.isVisible,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            ToolBar(toolbar = toolBar.value)
                        }

                    },
                    bottomBar = {
                        val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                        AnimatedVisibility(
                            visible = isBottomVBarVisible.value,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            BottomBar(
                                items = listOf(
                                    NavigationItem.Home,
                                    NavigationItem.Favourite,
                                    NavigationItem.AddNote,
                                    NavigationItem.Chat,
                                    NavigationItem.Profile
                                ),
                                selected = { navItem ->
                                    navBackStackEntry?.destination?.hierarchy?.any {
                                        it.route == navItem.screen.route
                                    } ?: false
                                }
                            ) {
                                navigationState.navigateTo(it.screen.route)
                            }
                        }

                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        AppNavGraph(
                            navHostController = navigationState.navHostController,
                            splashScreen = {
                                SplashScreen(
                                    navigateToAuth = { navigationState.navigateAndClearTo(Screen.AuthPage.route) },
                                    navigateToMain = { navigationState.navigateAndClearTo(Screen.MainPage.route) },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it }
                                )
                            },
                            authPage = {
                                AuthScreen(
                                    onLoginSuccess = {
                                        navigationState.navigateAndClearTo(Screen.MainPage.route)
                                    },
                                    onRegisterButtonClick = {
                                        navigationState.navigateTo(Screen.RegisterPage.route)
                                    },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it }
                                )
                            },
                            registerPage = {
                                RegisterScreen(
                                    onRegisterSuccess = {
                                        navigationState.navigateTo(Screen.MainPage.route)
                                    },
                                    onLoginButtonClick = {
                                        navigationState.navigateTo(Screen.AuthPage.route)
                                    },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it }
                                )
                            },
                            homeScreenContent = {
                                MainScreen(
                                    paddingValues = paddingValues,
                                    isTopVisible = { toolBar.value = it },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    onAllCoursesClick = { type ->
                                        navigationState.navigateToShowAll(type = type)
                                    },
                                    onAllLocalNotesClick = { type ->
                                        navigationState.navigateToShowAll(type = type)
                                    },
                                    onAllComNotesClick = { type ->
                                        navigationState.navigateToShowAll(type = type)
                                    },
                                    onCourseClick = { id, type ->
                                        navigationState.navigateToDetail(type = type, id = id)
                                    },
                                    onComNoteClick = { id, type ->
                                        navigationState.navigateToDetail(type = type, id = id)
                                    },
                                    onLocalNoteClick = { id, type ->
                                        navigationState.navigateToDetail(type = type, id = id)
                                    }
                                )
                            },
                            favouriteScreenContent = {
                                FavouriteScreen(
                                    onNavigateToLocalNoteDetail = { id, typeClick ->
                                        navigationState.navigateToDetail(type = typeClick, id = id)
                                    },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it }
                                )
                            },
                            chatScreenContent = {
                                ChatScreen(
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it }
                                )
                            },
                            profileScreenContent = {
                                ProfileScreen(
                                    onNoteClick = { noteId, type ->
                                        navigationState.navigateToDetail(type, noteId)
                                    },
                                    onCourseClick = { noteId, type ->
                                        navigationState.navigateToDetail(type, noteId)
                                    },
                                    onAllCoursesClick = { type ->
                                        navigationState.navigateToShowAll(type)
                                    },
                                    onAllNotesClick = { type ->
                                        navigationState.navigateToShowAll(type)
                                    },
                                    onProfilesClick = { type ->
                                        navigationState.navigateToShowAll(type)
                                    },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it },
                                    onLogoutClick = {
                                        navigationState.navigateAndClearTo(Screen.AuthPage.route)
                                    }
                                )
                            },
                            showAllScreenContent = { type ->
                                ShowAllScreen(
                                    type = type,
                                    onNavigateToCourseDetail = { id, typeClick ->
                                        navigationState.navigateToDetail(type = typeClick, id = id)
                                    },
                                    onNavigateToLocalNoteDetail = { id, typeClick ->
                                        navigationState.navigateToDetail(type = typeClick, id = id)
                                    },
                                    onNavigateToComNoteDetail = { id, typeClick ->
                                        navigationState.navigateToDetail(type = typeClick, id = id)
                                    },
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it },
                                    onNavigateToProfileDetail = { id, typeClick ->
                                        navigationState.navigateToDetail(typeClick, id)
                                    },
                                    onBackClick = { navigationState.navHostController.popBackStack() }
                                )
                            },
                            detailScreen = { type, id ->
                                DetailScreen(
                                    type = type,
                                    id = id,
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it },
                                    onBackClick = { navigationState.navHostController.popBackStack() }
                                )
                            },
                            addNoteContent = {
                                AddNoteScreen(
                                    isBottomVisible = { isBottomVBarVisible.value = it },
                                    isTopVisible = { toolBar.value = it },
                                    onBackClick = { navigationState.navHostController.popBackStack() }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}