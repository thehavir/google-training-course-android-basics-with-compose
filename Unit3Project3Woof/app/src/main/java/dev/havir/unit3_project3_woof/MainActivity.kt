package dev.havir.unit3_project3_woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.havir.unit3_project3_woof.ui.theme.Unit3Project3WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3Project3WoofTheme {
                WoofApp()
            }
        }
    }
}

@Composable
fun WoofApp() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Unit3Project3WoofTheme {
        WoofApp()
    }
}
