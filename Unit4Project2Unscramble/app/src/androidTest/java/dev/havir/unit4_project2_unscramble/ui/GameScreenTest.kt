package dev.havir.unit4_project2_unscramble.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import dev.havir.unit4_project2_unscramble.ui.theme.Unit4Project2UnscrambleTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameScreenTest {
    val gameViewModelFactory = mockk<GameViewModelFactory>()
    val gameViewModel = mockk<GameViewModel>()

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setGameScreenContent(onExit: () -> Unit = {}) {
        composeTestRule.setContent {
            Unit4Project2UnscrambleTheme {
                GameScreen(gameViewModelFactory, gameViewModel, onExit = onExit)
            }
        }
    }

    @Before
    fun setUp() {
        every { gameViewModel.resetGame() } returns Unit
        every { gameViewModel.uiState } returns MutableStateFlow(GameUiState())
        every { gameViewModel.userGuess } returns ""
    }

    @Test
    fun isScrollable() {
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.MAIN_COLUMN).assert(
            hasScrollAction()
        )
    }

    @Test
    fun hasTitle() {
        setGameScreenContent()

        composeTestRule.onNodeWithText("Unscramble").assertIsDisplayed()
    }

    @Test
    fun hasGameLayoutCard() {
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.MAIN_LAYOUT).assertIsDisplayed()
    }

    @Test
    fun hasWordCountCardInsideGameLayoutCardShowingWordCountFromViewModelUiState() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            value = GameUiState(wordCount = 4)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("4/10") and hasParent(
                hasTestTag(GameScreenTestTags.WORD_COUNT_CARD) and hasParent(
                    hasTestTag(GameScreenTestTags.MAIN_LAYOUT)
                )
            )
        ).assertIsDisplayed()
    }

    @Test
    fun hasScrambledWordTextInsideGameLayoutCardFromViewModelUiState() {
        val scrambledWord = "wrod"
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(scrambledWord = scrambledWord)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasText(scrambledWord) and hasParent(hasTestTag(GameScreenTestTags.MAIN_LAYOUT))
        ).assertIsDisplayed()
    }

    @Test
    fun hasInstructionTextInsideGameLayoutCard() {
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Unscramble the word using all the letters.") and hasParent(
                hasTestTag(GameScreenTestTags.MAIN_LAYOUT)
            )
        ).assertIsDisplayed()
    }

    @Test
    fun hasTextField() {
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.GUESS_TEXT_FIELD).assertIsDisplayed()
    }

    @Test
    fun setsValueOfTextFieldAsUserGuessFromGameViewModel() {
        val userInput = "user input 1"
        every { gameViewModel.userGuess } returns userInput
        setGameScreenContent()

        composeTestRule.onNode(
            hasText(userInput) and hasTestTag(GameScreenTestTags.GUESS_TEXT_FIELD)
        ).assertIsDisplayed()
    }

    @Test
    fun callsUpdateUserGuessWithUserInputWhenUserTypesInTextField() {
        val userInput = "my guess 1"
        every { gameViewModel.updateUserGuess(userInput) } returns Unit
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.GUESS_TEXT_FIELD).performTextInput(userInput)

        verify(exactly = 1) { gameViewModel.updateUserGuess(userInput) }
    }

    @Test
    fun showsErrorTextOnTheTextFieldWhenHasErrorIsTrueOnGameViewModel() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(hasError = true)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Wrong Guess!") and hasTestTag(GameScreenTestTags.GUESS_TEXT_FIELD)
        ).assertIsDisplayed()
    }

    @Test
    fun textFieldHasLabel() {
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Enter your word") and hasTestTag(GameScreenTestTags.GUESS_TEXT_FIELD)
        ).assertIsDisplayed()
    }

    @Test
    fun onKeyboardDoneCallsCheckUserGuessOnGameViewModel() {
        every { gameViewModel.checkUserGuess() } returns Unit
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.GUESS_TEXT_FIELD).performClick()
            .performImeAction()

        verify(exactly = 1) { gameViewModel.checkUserGuess() }
    }

    @Test
    fun hasSubmitButton() {
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.SUBMIT_BUTTON).assertIsDisplayed()
    }

    @Test
    fun submitButtonHasText() {
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Submit") and hasTestTag(GameScreenTestTags.SUBMIT_BUTTON)
        ).assertIsDisplayed()
    }

    @Test
    fun onSubmitButtonCallsCheckUserGuessOnGameViewModel() {
        every { gameViewModel.checkUserGuess() } returns Unit
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.SUBMIT_BUTTON).performClick()

        verify(exactly = 1) { gameViewModel.checkUserGuess() }
    }

    @Test
    fun hasSkipButton() {
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.SKIP_BUTTON).assertIsDisplayed()
    }

    @Test
    fun skipButtonHasText() {
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Skip") and hasTestTag(GameScreenTestTags.SKIP_BUTTON)
        ).assertIsDisplayed()
    }

    @Test
    fun onSkipButtonCallsSkipWordOnGameViewModel() {
        every { gameViewModel.skipWord() } returns Unit
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.SKIP_BUTTON).performClick()

        verify(exactly = 1) { gameViewModel.skipWord() }
    }

    @Test
    fun hasScoreTextWithScoreFromGameViewModel() {
        val score = 150
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(score = score)
        )
        setGameScreenContent()

        composeTestRule.onNodeWithText("Score: $score").assertIsDisplayed()
    }

    @Test
    fun doesNotHaveGameOverDialogWhenIsGameOverIsFalseFromGameViewModel() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = false)
        )
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.GAME_OVER_DIALOG).isNotDisplayed()
    }

    @Test
    fun hasGameOverDialogWhenIsGameOverIsTrueFromGameViewModel() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.GAME_OVER_DIALOG).assertIsDisplayed()
    }

    @Test
    fun gameOverDialogIsNotDismissible() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        Espresso.pressBack()

        composeTestRule.onNodeWithTag(GameScreenTestTags.GAME_OVER_DIALOG).assertIsDisplayed()
    }

    @Test
    fun gameOverDialogHasTitle() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNodeWithText("Congratulations!").assertIsDisplayed()
    }

    @Test
    fun gameOverDialogHasScoreTextWithScoreFromGameViewModel() {
        val score = 445
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true, score = score)
        )
        setGameScreenContent()

        composeTestRule.onNodeWithText("You scored: $score").assertIsDisplayed()
    }

    @Test
    fun gameOverDialogHasPlayAgainButton() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasTestTag(GameScreenTestTags.PLAY_AGAIN_DIALOG_BUTTON) and hasParent(hasTestTag(GameScreenTestTags.GAME_OVER_DIALOG))
        ).assertIsDisplayed()
    }

    @Test
    fun playAgainButtonHasText() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Play Again") and hasTestTag(GameScreenTestTags.PLAY_AGAIN_DIALOG_BUTTON)
        ).assertIsDisplayed()
    }

    @Test
    fun onPlayAgainCallsResetGameOnGameViewModel() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNodeWithTag(GameScreenTestTags.PLAY_AGAIN_DIALOG_BUTTON).performClick()

        verify(exactly = 1) { gameViewModel.resetGame() }
    }

    @Test
    fun gameOverDialogHasExitButton() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasTestTag(GameScreenTestTags.EXIT_DIALOG_BUTTON) and hasParent(hasTestTag(GameScreenTestTags.GAME_OVER_DIALOG))
        ).assertIsDisplayed()
    }

    @Test
    fun exitButtonHasText() {
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent()

        composeTestRule.onNode(
            hasText("Exit") and hasTestTag(GameScreenTestTags.EXIT_DIALOG_BUTTON)
        ).assertIsDisplayed()
    }

    @Test
    fun onExitCallsPassedOnExit() {
        var isCallbackCalled = false
        every { gameViewModel.uiState } returns MutableStateFlow(
            GameUiState(isGameOver = true)
        )
        setGameScreenContent(onExit = { isCallbackCalled = true })

        composeTestRule.onNodeWithTag(GameScreenTestTags.EXIT_DIALOG_BUTTON).performClick()

        Assert.assertTrue(isCallbackCalled)
    }
}
