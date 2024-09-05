package com.example.finalproject.ui.screens.splash_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.R
import com.example.finalproject.ui.components.tool_bar.ToolBarType
import com.example.finalproject.ui.theme.LocalCustomColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit,
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(
                isVisible = false,
                onBackClick = {}
            )
        )
        isBottomVisible(false)
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            handleSideEffect(
                sideEffect = sideEffect,
                navigateToAuth = navigateToAuth,
                navigateToMain = navigateToMain
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(2000L) // Задержка на 3 секунды
        viewModel.checkUserLoggedIn()
    }

    val alphaAnimation = remember { Animatable(0f) }
    val scaleAnimation = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
        scaleAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCustomColors.current.MainYellow)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    alpha = alphaAnimation.value,
                    scaleX = scaleAnimation.value,
                    scaleY = scaleAnimation.value
                ),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_together),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp)
                .graphicsLayer(
                    alpha = alphaAnimation.value,
                    scaleX = scaleAnimation.value,
                    scaleY = scaleAnimation.value
                ),
        )
    }
}

private fun handleSideEffect(
    sideEffect: SplashSideEffect,
    navigateToAuth: () -> Unit,
    navigateToMain: () -> Unit
) {
    when (sideEffect) {
        SplashSideEffect.NavigateToAuth -> navigateToAuth()
        SplashSideEffect.NavigateToMain -> navigateToMain()
    }
}