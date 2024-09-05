package com.example.finalproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.models.NoteVO
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: NoteVO,
    onNoteClick: (noteId: String) -> Unit
) {
    val localColors = LocalCustomColors.current
    val localTypography = LocalCustomTypography.current

    val modifierAuthor = if (note.author != null) {
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(localColors.MainYellow)
            .padding(top = 26.dp, start = 12.dp, bottom = 12.dp, end = 12.dp)
    } else {
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(localColors.MainYellow)
            .padding(12.dp)
    }

    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    radius = 175.dp,
                    color = localColors.Gray33
                )
            ) {
                onNoteClick(note.id)
            }
    ) {
        Column(
            modifier = modifierAuthor
        ) {
            Text(
                text = note.title,
                modifier = Modifier
                    .padding(bottom = 4.dp),
                style = localTypography.cardTitle2428
            )

            Text(
                text = note.content[0].text,
                modifier = Modifier.fillMaxWidth(),
                style = LocalCustomTypography.current.text1416,
                color = LocalCustomColors.current.DescColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(8.dp))
                .background(LocalCustomColors.current.Gray33)
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Text(
                text = note.date,
                style = LocalCustomTypography.current.textField,
                color = Color.White
            )
        }

        if (note.author != null) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LocalCustomColors.current.Gray33)
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    link = note.author.avatar,
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "${note.author.name} ${note.author.surname}",
                    style = LocalCustomTypography.current.textField,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }

}