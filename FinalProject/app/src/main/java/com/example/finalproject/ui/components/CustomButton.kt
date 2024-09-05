package com.example.finalproject.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.theme.LocalCustomColors

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    text: String = "",
    isLoading: Boolean = false,
    textColor: Color = Color.Black,
    buttonColor: Color = LocalCustomColors.current.MainYellow,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(buttonColor),
        shape = shape
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = LocalCustomColors.current.MainYellow,
            )
        } else {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(
        text = "Sample",
        isLoading = true,
        textColor = Color.Black,
        buttonColor = LocalCustomColors.current.MainYellow
    )
}