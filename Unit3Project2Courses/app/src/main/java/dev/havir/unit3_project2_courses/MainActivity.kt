package dev.havir.unit3_project2_courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.havir.unit3_project2_courses.ui.theme.Unit3Project2CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3Project2CoursesTheme {
                CoursesApp()
            }
        }
    }
}

@Composable
fun CoursesApp() {

}

@Preview(showBackground = true)
@Composable
fun CoursesAppPreview() {
    Unit3Project2CoursesTheme {
        CoursesApp()
    }
}
