package com.example.finalproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.theme.LocalCustomColors

@Composable
fun ErrorScreen(
    retry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error_logo),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(text = stringResource(id = R.string.oops), modifier = Modifier.padding(bottom = 5.dp))
        Text(
            text = stringResource(id = R.string.something_wrong),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        CustomButton(
            text = stringResource(id = R.string.retry),
            buttonColor = LocalCustomColors.current.Gray33,
            textColor = Color.White
        ) {
            retry()
        }
    }
}