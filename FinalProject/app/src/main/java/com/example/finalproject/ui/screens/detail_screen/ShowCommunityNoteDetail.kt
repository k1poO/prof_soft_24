package com.example.finalproject.ui.screens.detail_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.R
import com.example.finalproject.ui.components.CoilImage
import com.example.finalproject.ui.components.fillWidthOfParent
import com.example.finalproject.ui.models.NoteVO
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowCommunityNoteDetail(comNote: NoteVO, viewModel: DetailViewModel) {
    val comments by rememberUpdatedState(comNote.comments)
    val commentText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // LazyColumn будет использоваться для всего контента
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            // Заголовки и основная информация
            item {
                Column(
                    modifier = Modifier
                        .fillWidthOfParent(16.dp)
                        .fillMaxWidth()
                        .background(LocalCustomColors.current.MainYellow)
                        .padding(top = 44.dp, bottom = 12.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = comNote.date,
                        style = LocalCustomTypography.current.text1214,
                        color = LocalCustomColors.current.Gray33
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = comNote.title, style = LocalCustomTypography.current.cardTitle2428)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Информация об авторе
            item {
                Row(
                    modifier = Modifier
                        .background(LocalCustomColors.current.Gray33, RoundedCornerShape(8.dp))
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                ) {
                    val link = comNote.author?.avatar
                    if (link != null) {
                        CoilImage(
                            link = link, modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${comNote.author?.name} ${comNote.author?.surname}",
                        style = LocalCustomTypography.current.text1619,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Контент заметки
            item {
                Text(
                    text = stringResource(id = R.string.text),
                    style = LocalCustomTypography.current.text1619,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Контент заметки с изображениями
                FlowColumn {
                    for (content in comNote.content) {
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
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Заголовок для комментариев
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LocalCustomColors.current.GrayD7, RoundedCornerShape(8.dp))
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.comments),
                        style = LocalCustomTypography.current.text1416,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
            }

            // Список комментариев
            items(comments.size) { index ->
                var backgroundColor = Color.White
                if (comments[index].author.role == stringResource(id = R.string.admin)) {
                    backgroundColor = LocalCustomColors.current.MainYellow
                }
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
                ) {
                    CoilImage(
                        link = comments[index].author.avatar, modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    val name = "${comments[index].author.name} ${comments[index].author.surname}"
                    Column {
                        Text(
                            text = name,
                            style = LocalCustomTypography.current.text1214,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = comments[index].text,
                            style = LocalCustomTypography.current.text1214,
                            color = LocalCustomColors.current.Gray64,
                        )
                    }
                }
            }
        }
        CommentTextField(
            modifier = Modifier,
            hint = stringResource(id = R.string.comment),
            text = commentText
        ) {
            Log.d("TAG", "ShowCommunityNoteDetail: ${commentText.value}")
            viewModel.addComment(comNote.id, commentText.value)
            commentText.value = ""
        }
    }
}


@Composable
fun CommentTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    text: MutableState<String>,
    onSendClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillWidthOfParent(16.dp)
            .fillMaxWidth()
            .background(LocalCustomColors.current.Gray33)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = { newValue -> text.value = newValue },
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 19.sp,
                color = LocalCustomColors.current.Gray64
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .background(Color.Transparent)
                ) {
                    if (text.value.isEmpty()) {
                        Text(
                            text = hint,
                            style = LocalCustomTypography.current.text1619,
                            color = LocalCustomColors.current.Gray64
                        )
                    }
                    innerTextField()
                }
            }
        )
        IconButton(onClick = onSendClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                tint = LocalCustomColors.current.Gray64,
                contentDescription = "Send"
            )
        }
    }
}