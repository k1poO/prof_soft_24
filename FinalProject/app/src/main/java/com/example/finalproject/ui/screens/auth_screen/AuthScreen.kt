package com.example.finalproject.ui.screens.auth_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType

@Composable
fun AuthScreen(
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    onLoginSuccess: () -> Unit,
    onRegisterButtonClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(isVisible = false, onBackClick = {})
        )
        isBottomVisible(false)
    }

    AuthScreenContent(
        onLoginSuccess = onLoginSuccess,
        onRegisterButtonClick = onRegisterButtonClick
    )
}