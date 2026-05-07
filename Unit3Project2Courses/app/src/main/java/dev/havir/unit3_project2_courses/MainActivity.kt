package dev.havir.unit3_project2_courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit3_project2_courses.data.Topic
import dev.havir.unit3_project2_courses.data.TopicsDataSource
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
fun CoursesApp(modifier: Modifier = Modifier) {
    val topics = TopicsDataSource.topics
    Surface(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(8.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.testTag("CourseGrid")
        ) {
            items(topics) { topic ->
                TopicCard(
                    topic = topic,
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.Bottom) {
            Image(
                painterResource(topic.imageDrawableResId),
                contentDescription = stringResource(topic.titleStringResId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(68.dp),
            )
            Column(
                modifier = Modifier.padding(
                    top = 16.dp, end = 16.dp, start = 16.dp
                )
            ) {
                Text(
                    stringResource(topic.titleStringResId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(Modifier.padding(bottom = 8.dp))
                Row(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painterResource(R.drawable.ic_grain),
                        colorFilter = ColorFilter.tint(Color.Black),
                        contentDescription = null
                    )
                    Spacer(Modifier.padding(start = 8.dp))
                    Text(
                        "${topic.numberOfCourses}",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Unit3Project2CoursesTheme {
        CoursesApp()
    }
}
