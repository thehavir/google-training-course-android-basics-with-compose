package dev.havir.unit2_project2_artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit2_project2_artspace.data.ArtRepository
import dev.havir.unit2_project2_artspace.ui.theme.Unit2Project2ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit2Project2ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(all = 16.dp)
    ) {
        var currentArtIndex by rememberSaveable { mutableIntStateOf(0) }
        val art = ArtRepository.arts[currentArtIndex]
        val subtitle = stringResource(art.subtitleResourceId)
        val annotatedSubtitle = createAnnotatedSubtitle(subtitle)

        ArtSpaceLayout(
            imageId = art.imageResourceId,
            titleId = art.titleResourceId,
            subtitle = annotatedSubtitle,
            onNext = {
                if (currentArtIndex == 6) {
                    currentArtIndex = 0
                } else {
                    currentArtIndex++
                }
            },
            onPrevious = {
                if (currentArtIndex == 0) {
                    currentArtIndex = 6
                } else {
                    currentArtIndex--
                }
            },
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ArtSpaceLayout(
    @DrawableRes imageId: Int,
    @StringRes titleId: Int,
    subtitle: AnnotatedString,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val title = stringResource(titleId)
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.weight(1f), shadowElevation = 8.dp
            ) {
                Image(
                    painterResource(imageId),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Thin
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onPrevious) { Text(stringResource(R.string.previous)) }
            Button(onClick = onNext) { Text(stringResource(R.string.next)) }
        }
    }
}

fun createAnnotatedSubtitle(subtitle: String): AnnotatedString {
    return buildAnnotatedString {
        val separator = "("
        val subtitleParts = subtitle.split(separator)
        val name = subtitleParts.first()
        val year = separator + subtitleParts.last()

        append(name)

        val spanStyle = SpanStyle(fontWeight = FontWeight.Light)
        withStyle(style = spanStyle) {
            if (subtitleParts.size > 1) {
                append(year)
            } else {
                append(" (?)")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    Unit2Project2ArtSpaceTheme {
        ArtSpaceApp()
    }
}
