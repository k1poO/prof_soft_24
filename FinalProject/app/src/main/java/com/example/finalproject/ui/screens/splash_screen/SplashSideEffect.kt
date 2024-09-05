package com.example.finalproject.ui.screens.splash_screen

sealed class SplashSideEffect {

    data object NavigateToAuth : SplashSideEffect()

    data object NavigateToMain: SplashSideEffect()
}