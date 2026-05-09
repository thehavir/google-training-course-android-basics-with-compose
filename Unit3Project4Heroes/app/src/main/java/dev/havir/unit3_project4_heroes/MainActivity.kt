package dev.havir.unit3_project4_heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit3_project4_heroes.data.Hero
import dev.havir.unit3_project4_heroes.data.HeroesDataSource
import dev.havir.unit3_project4_heroes.ui.theme.Unit3Project4HeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3Project4HeroesTheme {
                HeroesApp()
            }
        }
    }
}

@Composable
fun HeroesApp() {
    val heroes = HeroesDataSource.heroes
    Scaffold(topBar = { HeroesAppBar() }) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .testTag("HERO_LIST"),
        ) {
            items(heroes) { hero ->
                HeroItem(
                    hero = hero,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesAppBar(modifier: Modifier = Modifier) {
    val title = buildAppBarTitle(
        superPart = stringResource(R.string.app_title_super_part),
        heroesPart = stringResource(R.string.app_title_hero_part),
        color = MaterialTheme.colorScheme.secondary
    )
    CenterAlignedTopAppBar(
        title = {
            Text(
                title, style = MaterialTheme.typography.displayLarge
            )
        }, modifier = modifier
    )
}

fun buildAppBarTitle(superPart: String, heroesPart: String, color: Color) =
    buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.LineThrough,
                color = color,
            )
        ) {
            append(superPart)
        }

        append(heroesPart)
    }

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    stringResource(hero.nameStringResId),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    stringResource(hero.descriptionStringResId),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Image(
                painterResource(hero.imageDrawableResId),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(hero.nameStringResId),
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = MaterialTheme.shapes.small)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesAppPreview() {
    Unit3Project4HeroesTheme {
        HeroesApp()
    }
}
