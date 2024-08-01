package com.example.lesson6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesson6.ui.theme.Lesson6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lesson6Theme {
                Surface {
                    FirstActivityCompose(notificationText = NOTIFICATION_TEXT)
                }
            }
        }
    }

    companion object {
        private const val NOTIFICATION_TEXT = "Notification"
    }
}

@Composable
fun FirstActivityCompose(notificationText: String) {
    val context = LocalContext.current
    val robotoFontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_regular),
        Font(R.font.roboto_light, FontWeight.Light),
    )
    val buttonText = stringResource(id = R.string.activity_two)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.hello_world),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = robotoFontFamily
        )
        CreateButton(string = buttonText, fontFamily = robotoFontFamily, paddingTop = 40.dp) {
            context.startActivity(SecondActivity.newIntent(context, notificationText))
        }
    }
}

@Composable
fun CreateButton(
    string: String,
    fontFamily: FontFamily,
    paddingTop: Dp = 0.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(top = paddingTop),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.gray))
    ) {
        Text(
            text = string,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lesson6Theme {
        FirstActivityCompose("")
    }
}