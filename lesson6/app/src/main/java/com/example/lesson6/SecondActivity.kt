package com.example.lesson6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson6.ui.theme.Lesson6Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val toastText = intent.getStringExtra(EXTRA_KEY)
        super.onCreate(savedInstanceState)
        setContent {
            Lesson6Theme {
                Surface {
                    SecondActivityView(toastText = toastText ?: "It's empty")
                }
            }
        }
    }

    companion object {
        private const val EXTRA_KEY = "text"

        fun newIntent(context: Context, text: String): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(EXTRA_KEY, text)
            return intent
        }
    }
}

@Composable
fun SecondActivityView(toastText: String) {
    val context = LocalContext.current
    val robotoFontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_regular),
        Font(R.font.roboto_light, FontWeight.Light),
    )
    val buttonText = stringResource(id = R.string.notification)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DrawShape()
        SetImage()
        CreateButton(string = buttonText, fontFamily = robotoFontFamily, 16.dp) {
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun DrawShape() {
    Canvas(
        modifier = Modifier
            .padding(50.dp)
            .width(260.dp)
            .height(112.dp)
            .clip(
                RoundedCornerShape(
                    bottomEnd = 32.dp,
                    topStart = 16.dp
                )
            )
            .background(color = colorResource(id = R.color.red))
    ) {
    }
}

@Composable
fun SetImage() {
    Image(
        painter = painterResource(id = R.drawable.img_squirrel),
        contentDescription = stringResource(id = R.string.squirrel_content_desc),
        modifier = Modifier.size(width = 260.dp, height = 242.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Lesson6Theme {
        SecondActivityView(toastText = "")
    }
}