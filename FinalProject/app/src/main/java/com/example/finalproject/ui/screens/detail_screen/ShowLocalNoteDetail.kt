package com.example.finalproject.ui.screens.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.components.CoilImage
import com.example.finalproject.ui.components.fillWidthOfParent
import com.example.finalproject.ui.models.NoteVO
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowLocalNoteDetail(localNote: NoteVO) {
    LazyColumn(
        modifier = Modifier
            .fillWidthOfParent(16.dp)
            .fillMaxWidth()
            .background(LocalCustomColors.current.MainYellow)
            .padding(top = 44.dp, bottom = 12.dp)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Text(
                text = localNote.date,
                style = LocalCustomTypography.current.text1214,
                color = LocalCustomColors.current.Gray33
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = localNote.title, style = LocalCustomTypography.current.cardTitle2428)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.text),
                style = LocalCustomTypography.current.text1619,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            // Контент заметки с изображениями
            FlowColumn {
                for (content in localNote.content) {
                    Text(
                        text = content.text,
                        style = LocalCustomTypography.current.text1416,
                        color = LocalCustomColors.current.Gray64
                    )
                    if (content.image != "") {
                        CoilImage(
                            link = content.image,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RectangleShape)
                        )
                    }
                }
            }
        }
    }
}