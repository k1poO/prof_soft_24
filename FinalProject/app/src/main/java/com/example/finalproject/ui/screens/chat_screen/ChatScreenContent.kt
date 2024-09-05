package com.example.finalproject.ui.screens.chat_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.R
import com.example.finalproject.ui.components.CoilImage
import com.example.finalproject.ui.components.fillWidthOfParent
import com.example.finalproject.ui.models.ChatItemVO
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun ChatScreenContent(viewModel: ChatViewModel = hiltViewModel()) {
    val state by viewModel.container.stateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            val chat = state.chat?.listChatItems ?: listOf()
            val reversedChat = chat.reversed()
            items(reversedChat.size) { index ->
                MessageItem(reversedChat[index], state.currentUserId)
            }
        }

        ChatTextField(
            onRefreshClicked = { viewModel.onRefreshClicked() },
            onSendClicked = { message -> viewModel.onSendClicked(message) }
        )
    }
}

@Composable
fun MessageItem(chat: ChatItemVO, currentUserId: String) {
    var backgroundColor = LocalCustomColors.current.GrayD7
    Log.d("ROLE", "MessageItem: ${chat.author.role}")

    if (chat.author.role == stringResource(id = R.string.admin)) {
        backgroundColor = LocalCustomColors.current.MainYellow
    }

    if (chat.author.id == currentUserId) {
        CurrentUserMessage(backgroundColor = backgroundColor, chat = chat)
    } else {
        OtherUserMessage(backgroundColor = backgroundColor, chat = chat)
    }
}

@Composable
fun OtherUserMessage(backgroundColor: Color, chat: ChatItemVO) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(backgroundColor)
                .widthIn(max = 250.dp)
        ) {
            CoilImage(
                link = chat.author.avatar, modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Column(
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 12.dp, bottom = 4.dp)
            ) {
                val name = chat.author.name + chat.author.surname
                Text(
                    text = name,
                    style = LocalCustomTypography.current.text1214,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = chat.message,
                    style = LocalCustomTypography.current.text1214,
                    color = LocalCustomColors.current.Gray64
                )
            }
        }
        Text(
            text = chat.date, modifier = Modifier
                .align(Alignment.Bottom)
                .padding(start = 4.dp)
        )
    }
}

@Composable
fun CurrentUserMessage(backgroundColor: Color, chat: ChatItemVO) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = chat.date, modifier = Modifier
                .align(Alignment.Bottom)
                .padding(end = 4.dp)
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(backgroundColor)
                .widthIn(max = 260.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                modifier = Modifier.padding(top = 4.dp, start = 12.dp, end = 8.dp, bottom = 4.dp)
            ) {
                val name = chat.author.name + chat.author.surname
                Text(
                    text = name,
                    style = LocalCustomTypography.current.text1214,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = chat.message,
                    style = LocalCustomTypography.current.text1214,
                    color = LocalCustomColors.current.Gray64
                )
            }
            CoilImage(link = chat.author.avatar, modifier = Modifier
                .size(40.dp)
                .clip(CircleShape))
        }
    }
}

@Composable
fun ChatTextField(
    onRefreshClicked: () -> Unit,
    onSendClicked: (String) -> Unit,
) {
    var textState by remember { mutableStateOf("") }

    Spacer(
        modifier = Modifier
            .fillWidthOfParent(16.dp)
            .fillMaxWidth()
            .background(LocalCustomColors.current.GrayT33)
            .height(1.dp)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillWidthOfParent(16.dp)
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 16.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            BasicTextField(
                value = textState,
                onValueChange = { newText -> textState = newText },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    color = LocalCustomColors.current.Gray64
                )
            )

            if (textState.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.message),
                    style = LocalCustomTypography.current.text1619,
                    color = LocalCustomColors.current.Gray64,
                )
            }
        }

        IconButton(onClick = onRefreshClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = null,
                tint = LocalCustomColors.current.Gray64
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        IconButton(onClick = {
            onSendClicked(textState)
            textState = ""
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = null,
                tint = LocalCustomColors.current.Gray64
            )
        }
    }
}


@Preview
@Composable
fun ChatScreenContentPreview() {
    ChatScreenContent()
}