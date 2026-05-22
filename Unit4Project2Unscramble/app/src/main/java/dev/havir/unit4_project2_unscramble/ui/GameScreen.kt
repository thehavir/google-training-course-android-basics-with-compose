package dev.havir.unit4_project2_unscramble.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.havir.unit4_project2_unscramble.R
import dev.havir.unit4_project2_unscramble.data.LocalWordRepository
import dev.havir.unit4_project2_unscramble.domain.usecase.CheckGuessUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.ResetGameUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.SetNextRoundUseCase
import dev.havir.unit4_project2_unscramble.ui.theme.Unit4Project2UnscrambleTheme
import kotlin.random.Random

@Composable
fun GameScreen(
    factory: GameViewModelFactory,
    gameViewModel: GameViewModel = viewModel(factory = factory),
    onExit: () -> Unit,
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_medium),
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(dimensionResource(R.dimen.padding_xxx_large))
            .testTag(GameScreenTestTags.MAIN_COLUMN),
    ) {
        Text(stringResource(R.string.app_title), style = typography.titleLarge)
        GameLayout(
            scrambleWord = gameUiState.scrambledWord,
            guess = gameViewModel.userGuess,
            onGuessChanged = gameViewModel::updateUserGuess,
            onKeyboardDone = gameViewModel::checkUserGuess,
            wordsCount = gameUiState.wordCount,
            hasError = gameUiState.hasError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Actions(
            onSubmit = gameViewModel::checkUserGuess,
            onSkip = gameViewModel::skipWord,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Score(score = gameUiState.score)
    }

    if (gameUiState.isGameOver) {
        GameOverDialog(
            score = gameUiState.score,
            onExit = onExit,
            onPlayAgain = gameViewModel::resetGame,
        )
    }
}

@Composable
fun GameLayout(
    scrambleWord: String,
    guess: String,
    onGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    wordsCount: Int,
    hasError: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation)),
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_medium),
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .testTag(GameScreenTestTags.MAIN_LAYOUT)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.primary
                ),
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .testTag(GameScreenTestTags.WORD_COUNT_CARD)
            ) {
                Text(
                    stringResource(R.string.word_count, wordsCount),
                    style = typography.titleMedium,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_small),
                        vertical = dimensionResource(R.dimen.padding_xx_small),
                    )
                )
            }
            Text(scrambleWord, style = typography.displayMedium)
            Text(
                stringResource(R.string.instructions),
                style = typography.titleMedium,
                modifier = Modifier.align(Alignment.Start),
            )
            OutlinedTextField(
                value = guess,
                onValueChange = onGuessChanged,
                isError = hasError,
                supportingText = {
                    if (hasError) {
                        Text(
                            stringResource(R.string.wrong_guess),
                            color = colorScheme.error,
                        )
                    }
                },
                singleLine = true,
                shape = shapes.large,
                label = { Text(stringResource(R.string.enter_your_word)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() },
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(GameScreenTestTags.GUESS_TEXT_FIELD),
            )
        }
    }
}

@Composable
fun Actions(
    onSubmit: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_medium),
        ),
        modifier = modifier,
    ) {
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(GameScreenTestTags.SUBMIT_BUTTON)
        ) {
            Text(stringResource(R.string.submit))
        }
        OutlinedButton(
            onClick = onSkip,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(GameScreenTestTags.SKIP_BUTTON)
        ) {
            Text(stringResource(R.string.skip))
        }
    }
}

@Composable
fun Score(score: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(
            stringResource(R.string.score, score),
            style = typography.headlineMedium,
            modifier = Modifier.padding(
                dimensionResource(R.dimen.padding_x_small)
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameOverDialog(
    score: Int,
    onExit: () -> Unit,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = {},
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .testTag(GameScreenTestTags.GAME_OVER_DIALOG),
            shape = shapes.large,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(
                        R.dimen.padding_x_large
                    )
                ), modifier = Modifier.padding(
                    dimensionResource(R.dimen.padding_medium),
                )
            ) {
                Text(
                    stringResource(R.string.congratulations),
                    style = typography.headlineSmall
                )
                Text(stringResource(R.string.you_scored, score))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.padding_xx_small),
                        alignment = Alignment.End
                    ), modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = onExit,
                        modifier = Modifier.testTag(GameScreenTestTags.EXIT_DIALOG_BUTTON)
                    ) {
                        Text(stringResource(R.string.exit))
                    }
                    TextButton(
                        onClick = onPlayAgain,
                        modifier = Modifier.testTag(GameScreenTestTags.PLAY_AGAIN_DIALOG_BUTTON)
                    ) {
                        Text(stringResource(R.string.play_again))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    Unit4Project2UnscrambleTheme {
        val wordRepository = LocalWordRepository()
        val factory = GameViewModelFactory(
            ResetGameUseCase(
                wordRepository = wordRepository,
                random = Random,
            ),
            SetNextRoundUseCase(
                wordRepository = wordRepository,
                random = Random,
            ),
            CheckGuessUseCase(),
        )
        GameScreen(factory = factory, onExit = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameOverDialogPreview() {
    Unit4Project2UnscrambleTheme {
        GameOverDialog(
            score = 100,
            onExit = {},
            onPlayAgain = {},
        )
    }
}
