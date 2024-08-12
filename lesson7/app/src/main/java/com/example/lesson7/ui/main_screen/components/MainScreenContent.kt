package com.example.lesson7.ui.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lesson7.R
import com.example.lesson7.ui.components.CustomButton
import com.example.lesson7.ui.components.CustomToolBar

@Composable
fun MainScreenContent(onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomToolBar(text = stringResource(id = R.string.title_main), Color.White)
        Box(modifier = Modifier.weight(1f))
        CustomButton(text = stringResource(id = R.string.button_profile), onClick)
    }
}

@Preview
@Composable
fun MainScreenContentPreview() {
    MainScreenContent {

    }
}