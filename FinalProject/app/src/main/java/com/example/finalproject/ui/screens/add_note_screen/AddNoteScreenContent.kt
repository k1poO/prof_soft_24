package com.example.finalproject.ui.screens.add_note_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.theme.LocalCustomColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreenContent() {
    val currentColors = LocalCustomColors.current

    // State for managing the bottom sheet
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                onTextSelected = { /* Logic for adding text */ },
                onLinkSelected = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp, // Initially hidden
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
            ) {
                NoteSwitch(Modifier.padding(top = 16.dp)) {
                    // Handle switch between Local and Community
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title Input
                TextField(
                    value = "Название",
                    onValueChange = {},
                    label = { Text("Название") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description Input
                TextField(
                    value = "Описание заметки",
                    onValueChange = {},
                    label = { Text("Описание заметки") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // This will be a placeholder for the added content like images or text
                // You can replace this with your actual list of content
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Add your content preview here
                }

                Spacer(modifier = Modifier.weight(1f)) // Push buttons to the bottom

                NoteAddButtons(
                    onAddClick = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    onTextSelected: () -> Unit,
    onLinkSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Что добавить?",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onTextSelected() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_text),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Текст")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLinkSelected() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Ссылка на фото")
        }
    }
}

@Composable
fun LinkInputDialog(
    onDismiss: () -> Unit,
    onLinkSubmitted: (String) -> Unit
) {
    var link by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Введите ссылку на фото") },
        text = {
            TextField(
                value = link,
                onValueChange = { link = it },
                label = { Text("Ссылка") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onLinkSubmitted(link)
                }
            ) {
                Text("Добавить")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Отмена")
            }
        }
    )
}

@Composable
fun NoteSwitch(
    modifier: Modifier = Modifier,
    onSwitch: (type: String) -> Unit
) {
    val currentColors = LocalCustomColors.current
    var selectedType by remember { mutableStateOf("local") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(currentColors.GrayD7, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Local Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selectedType = "local"
                        onSwitch(selectedType)
                    }
                    .background(
                        if (selectedType == "local") currentColors.MainYellow else Color.Transparent,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Локально", color = Color.Black)
            }

            // Community Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selectedType = "community"
                        onSwitch(selectedType)
                    }
                    .background(
                        if (selectedType == "community") currentColors.MainYellow else Color.Transparent,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Сообщество", color = Color.Black)
            }
        }
    }
}

@Composable
fun NoteAddButtons(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    val currentColors = LocalCustomColors.current
    Row(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(currentColors.Gray33, RoundedCornerShape(8.dp))
                .clickable { onAddClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(currentColors.Gray33, RoundedCornerShape(8.dp))
                .clickable {
                    // Confirm action
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Готово", color = Color.White)
        }
    }
}



@Preview
@Composable
fun AddNoteScreenContentPreview() {
    AddNoteScreenContent()
}
