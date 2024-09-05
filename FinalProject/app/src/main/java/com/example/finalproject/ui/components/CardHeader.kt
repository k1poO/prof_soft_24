package com.example.finalproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun CardHeader(
    modifier: Modifier = Modifier,
    title: String = "",
    onAllClickListener: () -> Unit
) {
    val localColors = LocalCustomColors.current
    val localTypography = LocalCustomTypography.current

    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .background(
                    localColors.GrayD7,
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 11.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = localTypography.text1416,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

        Box(
            modifier = Modifier
                .background(
                    localColors.GrayD7,
                    shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 15.dp,
                        color = localColors.Gray33
                    )
                ) { onAllClickListener() }
        ) {
            Text(
                text = stringResource(id = R.string.all),
                style = localTypography.text1416,
                fontWeight = FontWeight.Bold,
                color = localColors.Gray64
            )
        }
    }
}