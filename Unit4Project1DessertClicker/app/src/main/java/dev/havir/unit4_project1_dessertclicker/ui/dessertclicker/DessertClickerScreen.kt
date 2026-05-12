package dev.havir.unit4_project1_dessertclicker.ui.dessertclicker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit4_project1_dessertclicker.R
import dev.havir.unit4_project1_dessertclicker.data.Dessert
import dev.havir.unit4_project1_dessertclicker.data.DessertDatasource
import dev.havir.unit4_project1_dessertclicker.ui.theme.Unit4Project1DessertClickerTheme

@Composable
fun DessertClickerScreen() {
    val desserts = DessertDatasource.desserts

    var soldDesserts by rememberSaveable { mutableIntStateOf(0) }
    var revenue by rememberSaveable { mutableDoubleStateOf(0.0) }

    val currentDessert = dessertToShow(desserts, soldDesserts)

    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            AppBar(onClick = {
                shareDessertInfo(
                    context = intentContext,
                    soldDesserts = soldDesserts,
                    revenue = revenue
                )
            })
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        // In order to extend content background under the navigation bar
        Column(Modifier.padding(top = innerPadding.calculateTopPadding())) {
            DessertArea(
                dessert = currentDessert,
                onDessertClick = {
                    soldDesserts++
                    revenue += currentDessert.price
                },
                modifier = Modifier.weight(1f),
            )
            Footer(
                soldDesserts = soldDesserts,
                revenue = revenue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_title)) },
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(R.string.share),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier.testTag("APP_BAR"),
    )
}

@Composable
fun DessertArea(
    dessert: Dessert,
    onDessertClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Image(
            painter = painterResource(dessert.imageDrawableResId),
            contentDescription = stringResource(dessert.titleStringResId),
            modifier = Modifier
                .size(200.dp)
                .offset(y = (-56).dp)
                .clickable(onClick = onDessertClick)
        )
    }
}

@Composable
fun Footer(
    soldDesserts: Int, revenue: Double, modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.dessert_sold),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                "$soldDesserts",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.total_revenue),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                "$$revenue",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

// Finds and returns the dessert that has highest startProductionAmount that is
// lower than soldDesserts.
// If we have following:
//      Dessert A - startProductionAmount = 0,
//      Dessert B - startProductionAmount = 5,
//      Dessert C - startProductionAmount = 20
// And the soldDesserts is 18, then it returns Dessert B.
fun dessertToShow(desserts: List<Dessert>, soldDesserts: Int) =
    desserts.findLast { it.startProductionAmount <= soldDesserts }
        ?: desserts.first()

private fun shareDessertInfo(
    context: Context,
    soldDesserts: Int,
    revenue: Double,
) {
    val shareText =
        context.getString(R.string.share_text, soldDesserts, revenue)
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    try {
        context.startActivity(shareIntent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(
            context,
            context.getString(R.string.sharing_not_available),
            Toast.LENGTH_LONG
        ).show()
    }
}

@Preview(showBackground = true)
@Composable
fun DessertClickerScreenPreview() {
    Unit4Project1DessertClickerTheme {
        DessertClickerScreen()
    }
}
