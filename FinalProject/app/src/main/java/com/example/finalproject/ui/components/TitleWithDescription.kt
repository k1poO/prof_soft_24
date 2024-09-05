package com.example.finalproject.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun TitleWithDescription(
    modifier: Modifier = Modifier.fillMaxWidth(),
    titleText: String = "",
    descriptionText: String = ""
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = titleText,
            style = LocalCustomTypography.current.title2832,
            modifier = Modifier.padding(bottom =  6.dp)
        )
        Text(
            text = descriptionText,
            style = LocalCustomTypography.current.text1416
        )
    }
}

@Preview
@Composable
fun TitleWithDescriptionPreview() {
    TitleWithDescription(
        titleText = "Sample",
        descriptionText = "Sample"
    )
}