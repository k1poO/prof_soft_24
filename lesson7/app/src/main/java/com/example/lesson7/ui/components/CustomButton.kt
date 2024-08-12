package com.example.lesson7.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesson7.ui.theme.LocalCustomColors

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LocalCustomColors.current.ButtonBlue),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(20.dp),
            color = Color.White,
            fontSize = 14.sp,
            lineHeight = 16.sp
        )
    }

}

@Preview
@Composable
fun PreviewCustomButton() {
    CustomButton("Example") {}
}