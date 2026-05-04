package dev.havir.unit1_project1_birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havir.unit1_project1_birthdaycard.ui.theme.Unit1Project1BirthdayCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit1Project1BirthdayCardTheme {
                HappyBirthdayApp()
            }
        }
    }
}

@Composable
fun HappyBirthdayApp(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.androidparty),
            contentDescription = null,
            alpha = 0.5f,
            contentScale = ContentScale.Crop
        )
        GreetingText(
            message = stringResource(R.string.happy_birthday_text),
            from = stringResource(R.string.signature_text),
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Composable
fun GreetingText(
    message: String,
    from: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            message,
            textAlign = TextAlign.Center,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            from,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HappyBirthdayAppPreview() {
    Unit1Project1BirthdayCardTheme {
        HappyBirthdayApp()
    }
}
