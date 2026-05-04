package dev.havir.unit1_project2_businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havir.unit1_project2_businesscard.ui.theme.Unit1Project2BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit1Project2BusinessCardTheme {
                BusinessCardApp()
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCDE4D0))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Avatar()
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.name),
                fontSize = 48.sp,
                fontWeight = FontWeight.W300,
                color = Color(0xFF101C14)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.job_title),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF126137)
            )
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                BusinessCardItem(
                    imageVector = Icons.Filled.Call,
                    title = stringResource(R.string.phone_number)
                )
                BusinessCardItem(
                    imageVector = Icons.Filled.Share,
                    title = stringResource(R.string.social_media_id)
                )
                BusinessCardItem(
                    imageVector = Icons.Filled.Email,
                    title = stringResource(R.string.email)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Avatar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = Color(0xFF0D2A37))
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_android_24),
            contentDescription = null,
            tint = Color(0xFF42D680),
            modifier = Modifier
                .size(78.dp)
                .offset(x = 0.dp, y = (-10).dp)
        )
        Text(
            text = stringResource(R.string.android),
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.offset(x = 4.dp, y = 52.dp)
        )
    }
}

@Composable
fun BusinessCardItem(
    modifier: Modifier = Modifier, imageVector: ImageVector, title: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color(0xFF126137)
        )
        Text(text = title, color = Color(0xFF126137))
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardAppPreview() {
    Unit1Project2BusinessCardTheme {
        BusinessCardApp()
    }
}
