package com.example.finalproject.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val TextGray: Color = Color(0xFF333333)
val CommentText: Color = Color(0xFF646464)

data class CustomColors(
    //MainColors
    val MainYellow: Color = Color(0xFFFFD80C),
    val ItemChoosenGray: Color = Color(0xFFD7D7D7),

    //TextColors
    val GrayT33: Color = Color(0x33333333),
    val Gray33: Color = Color(0xFF333333),
    val GrayT23: Color = Color(0x66232323),
    val Gray64: Color = Color(0xFF646464),
    val Black11: Color = Color(0xFF111111),


    //ElementsColors
    val DescColor: Color = Color(0xFF806B00),
    val GrayD7: Color = Color(0xFFD7D7D7),
)

val LocalCustomColors = staticCompositionLocalOf { CustomColors() }