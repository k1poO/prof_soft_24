package com.example.lesson7.ui.edit_profile_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lesson7.R
import com.example.lesson7.ui.theme.LocalCustomTypography

@Composable
fun EditProfileInfoBlock(
    firstNameState: MutableState<String>,
    lastNameState: MutableState<String>,
    patronymicState: MutableState<String>,
    birthdayState: MutableState<String>,
) {
    Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
        EditProfileRepeatableText(
            text = stringResource(id = R.string.name),
            textState = firstNameState
        )
        EditProfileRepeatableText(
            text = stringResource(id = R.string.lastName),
            textState = lastNameState
        )
        EditProfileRepeatableText(
            text = stringResource(id = R.string.patronymic),
            textState = patronymicState
        )
        EditProfileRepeatableText(
            text = stringResource(id = R.string.date_of_birthday),
            textState = birthdayState,
            inputType = "date"
        )
    }
}

@Composable
fun EditProfileRepeatableText(
    text: String,
    textState: MutableState<String>,
    inputType: String = "text",
    paddingBottom: Dp = 16.dp
) {
    Row {
        Text(
            text = text,
            modifier = Modifier.padding(bottom = paddingBottom),
            style = LocalCustomTypography.current.title
        )
        if (inputType == "text") {
            BaseTextField(textState = textState)
        } else {
            DateTextField(textState = textState)
        }
    }

}

@Composable
fun BaseTextField(textState: MutableState<String>) {
    BasicTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        textStyle = LocalCustomTypography.current.title
    )
}

@Composable
fun DateTextField(textState: MutableState<String>) {
    BasicTextField(
        value = textState.value,
        onValueChange = {
            if (it.length <= 10) {
                val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                textState.value = filteredText
            }
        },
        textStyle = LocalCustomTypography.current.title,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}