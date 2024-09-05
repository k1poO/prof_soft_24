package com.example.finalproject.ui.screens.register_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType

@Composable
fun RegisterScreen(
    onLoginButtonClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(isVisible = false, onBackClick = {})
        )
        isBottomVisible(false)
    }

    RegisterScreenContent(
        onLoginButtonClick = onLoginButtonClick,
        onRegisterSuccess = onRegisterSuccess
    )
}