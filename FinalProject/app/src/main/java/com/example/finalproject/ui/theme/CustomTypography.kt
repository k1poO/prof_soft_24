package com.example.finalproject.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class CustomTypography(
    val title2832: TextStyle = TextStyle(
        fontSize = 28.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold
    ),
    val text1416: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    val textHint: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.sp,
        color = TextGray,
        fontWeight = FontWeight.Medium
    ),
    val textField: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    val text1214: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.sp,
    ),
    val text1619: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.sp,
        fontWeight = FontWeight.Medium
    ),
    val cardTitle2428: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    val profileTitle: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold
    ),
)

val LocalCustomTypography = staticCompositionLocalOf { CustomTypography() }