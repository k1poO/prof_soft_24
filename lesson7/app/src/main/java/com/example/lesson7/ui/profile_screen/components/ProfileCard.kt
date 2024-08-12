package com.example.lesson7.ui.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesson7.R
import com.example.lesson7.ui.theme.LocalCustomColors
import com.example.lesson7.ui.theme.LocalCustomTypography

@Composable
fun ProfileCard(
    firstName: String,
    lastName: String,
    patronymic: String,
    birthday: String
) {
    Row(
        modifier = Modifier
            .background(LocalCustomColors.current.GrayBG)
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_avatar),
            contentDescription = null,
            modifier = Modifier.size(width = 68.dp, height = 63.dp)

        )
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                ProfileRepeatableText(text = lastName, 6.dp)
                ProfileRepeatableText(text = firstName, 6.dp)
                ProfileRepeatableText(text = patronymic)
            }
            Column {
                Text(
                    text = stringResource(id = R.string.date_of_birthday),
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(text = birthday, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun ProfileRepeatableText(text: String, padding: Dp = 0.dp) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = padding),
        style = LocalCustomTypography.current.regularText
    )
}

@Preview
@Composable
fun ProfileCardPreview() {
    ProfileCard(
        "Иван",
        "Иванов",
        "Иванович",
        "01.01.2001"
    )
}