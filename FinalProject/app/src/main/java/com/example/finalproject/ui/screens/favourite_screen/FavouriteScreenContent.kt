package com.example.finalproject.ui.screens.favourite_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.R
import com.example.finalproject.ui.components.LoadingScreen
import com.example.finalproject.ui.components.NoteCard
import com.example.finalproject.ui.screens.main_screen.LOCAL_NOTE_TYPE
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun FavouriteScreenContent(
    viewModel: FavouriteScreenViewModel = hiltViewModel(),
    onNavigateToLocalNoteDetail: (noteId: String, type: String) -> Unit
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    // Обработка sideEffect
    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is FavouriteSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }

                is FavouriteSideEffect.NavigateToLocalNoteDetail -> onNavigateToLocalNoteDetail(
                    sideEffect.noteId,
                    sideEffect.type
                )
            }
        }
    }
    if (state.isLoading) {
        LoadingScreen()
    } else if (state.favouriteNotes?.notes?.isEmpty() == true) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.empty),
                style = LocalCustomTypography.current.text1619,
                color = LocalCustomColors.current.Gray64
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            state.favouriteNotes?.notes?.let { notes ->
                items(notes.size) { index ->
                    NoteCard(
                        modifier = Modifier.padding(bottom = 16.dp),
                        note = notes[index]
                    ) { localNoteId ->
                        viewModel.onLocalNoteCLick(
                            localNoteId = localNoteId,
                            type = LOCAL_NOTE_TYPE
                        )
                    }
                }
            }
        }
    }
}