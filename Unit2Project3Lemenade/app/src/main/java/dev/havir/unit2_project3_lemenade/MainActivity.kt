package dev.havir.unit2_project3_lemenade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import dev.havir.unit2_project3_lemenade.ui.theme.Unit2Project3LemenadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit2Project3LemenadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by rememberSaveable { mutableStateOf(Step.SELECT) }
    var squeezesCount by rememberSaveable { mutableIntStateOf(3) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    stringResource(R.string.app_name),
                    fontWeight = FontWeight.Bold
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentStep) {
                Step.SELECT -> ImageWithText(
                    onTap = { currentStep = Step.SQUEEZE },
                    imageId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.lemon_tree,
                    text = stringResource(R.string.select_lemon)

                )

                Step.SQUEEZE -> ImageWithText(
                    onTap = {
                        squeezesCount--
                        if (squeezesCount == 0) {
                            squeezesCount = (2..4).random()
                            currentStep = Step.DRINK
                        } else {
                            currentStep = Step.SQUEEZE
                        }
                    },
                    imageId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.lemon,
                    text = stringResource(
                        R.string.squeeze_lemon, squeezesCount
                    )
                )

                Step.DRINK -> ImageWithText(
                    onTap = { currentStep = Step.REFILL },
                    imageId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.glass_of_lemonade,
                    text = stringResource(R.string.drink_lemon)
                )

                Step.REFILL -> ImageWithText(
                    onTap = { currentStep = Step.SELECT },
                    imageId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.empty_glass,
                    text = stringResource(R.string.start_again)
                )
            }
        }
    }
}

@Composable
fun ImageWithText(
    modifier: Modifier = Modifier,
    onTap: () -> Unit,
    imageId: Int,
    contentDescriptionId: Int,
    text: String,
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.box_size))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.box_corner_radius)))
                .clickable { onTap() }
                .background(
                    MaterialTheme.colorScheme.tertiaryContainer
                ), contentAlignment = Alignment.Center) {
            Image(
                painterResource(imageId),
                contentDescription = stringResource(contentDescriptionId)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

enum class Step {
    SELECT, SQUEEZE, DRINK, REFILL
}

@Composable
@Preview(showBackground = true)
fun LemonadeAppPreview() {
    Unit2Project3LemenadeTheme {
        LemonadeApp()
    }
}
