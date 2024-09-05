package com.example.finalproject.ui.components.tool_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    toolbar: ToolBarType,
) {
    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(LocalCustomColors.current.MainYellow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSearchMode) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(text = "Поиск") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = LocalCustomColors.current.MainYellow
                )
            )
            IconButton(onClick = { isSearchMode = false }) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
            }
        } else {
            if (toolbar.isBackArrow) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { toolbar.onBackClick }
                )
            }
            Text(
                text = toolbar.title,
                style = LocalCustomTypography.current.profileTitle,
                modifier = Modifier.weight(1f)
            )

            when (toolbar.type) {

                ToolBarType.SEARCH_TYPE -> {
                    IconButton(onClick = { isSearchMode = true }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                    }
                }

                ToolBarType.FAVOURITE_TYPE -> {
                    IconButton(onClick = { isSearchMode = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_favourite),
                            contentDescription = null
                        )
                    }
                }

                ToolBarType.PROFILE_TYPE -> {
                    IconButton(onClick = toolbar.onIconClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_community),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ToolBarPreview(

) {

}
