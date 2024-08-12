package com.example.lesson7.ui.profile_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson7.R
import com.example.lesson7.ui.theme.LocalCustomColors
import com.example.lesson7.ui.theme.LocalCustomTypography

@Composable
fun ProfileInfo() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        ProfileTitleText(text = stringResource(id = R.string.city))
        ProfileRegularText(text = "Саратов")
        ProfileTitleText(text = stringResource(id = R.string.about_myself))
        ProfileRegularText(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
    }
}

@Composable
fun ProfileRegularText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = 20.dp, top = 8.dp),
        style = LocalCustomTypography.current.regularText
    )
}

@Composable
fun ProfileTitleText(text: String) {
    Text(
        text = text,
        color = LocalCustomColors.current.GrayText,
        style = LocalCustomTypography.current.regularText
    )
}

@Preview
@Composable
fun ProfileInfoPreview() {
    ProfileInfo()
}