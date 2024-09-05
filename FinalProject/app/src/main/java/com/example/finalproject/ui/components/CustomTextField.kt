package com.example.finalproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun CustomTextField(
    paddingValues: PaddingValues = PaddingValues(),
    hint: String = "",
    text: MutableState<String>
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .height(36.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(LocalCustomColors.current.GrayT33)
            .padding(start = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = { newValue -> text.value = newValue },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalCustomTypography.current.textField,
            singleLine = true,
            decorationBox = {innerTextField ->
                Box(
                    Modifier
                        .background(Color.Transparent)
                ) {

                    if (text.value.isEmpty()) {
                        Text(text = hint, style = LocalCustomTypography.current.textHint)
                    }
                    innerTextField()
                }
            }
        )
    }
}


@Preview
@Composable
fun CustomTextFieldPreview() {
    val textState = remember {
        mutableStateOf("")
    }
    CustomTextField(
        hint = "HINT HERE",
        text = textState
    )
}