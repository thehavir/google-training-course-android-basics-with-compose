package dev.havir.unit3_project1_affirmation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit3_project1_affirmation.data.Affirmation
import dev.havir.unit3_project1_affirmation.data.AffirmationsDataSource
import dev.havir.unit3_project1_affirmation.ui.theme.Unit3Project1AffirmationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3Project1AffirmationTheme {
                AffirmationApp()
            }
        }
    }
}

@Composable
fun AffirmationApp() {
    val affirmations = AffirmationsDataSource.getAffirmations()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        AffirmationList(affirmations)
    }
}

@Composable
fun AffirmationList(
    affirmations: List<Affirmation>, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.testTag("AffirmationList")) {
        items(affirmations) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@Composable
fun AffirmationCard(
    affirmation: Affirmation, modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column {
            Image(
                painterResource(affirmation.imageDrawableResId),
                contentDescription = stringResource(affirmation.titleStringResId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                stringResource(affirmation.titleStringResId),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Unit3Project1AffirmationTheme {
        AffirmationApp()
    }
}
