package dev.havir.unit4_project2_unscramble.ui

import dev.havir.unit4_project2_unscramble.domain.entity.Game
import dev.havir.unit4_project2_unscramble.domain.usecase.CheckGuessUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.ResetGameUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.SetNextRoundUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GameViewModelTest {
    lateinit var resetGameUseCase: ResetGameUseCase
    lateinit var setNextRoundUseCase: SetNextRoundUseCase
    lateinit var checkGuessUseCase: CheckGuessUseCase
    lateinit var tested: GameViewModel

    @Before
    fun setUp() {
        resetGameUseCase = mockk<ResetGameUseCase>()
        setNextRoundUseCase = mockk<SetNextRoundUseCase>()
        checkGuessUseCase = mockk<CheckGuessUseCase>()
        every { resetGameUseCase.execute(any()) } returns Game()
        tested = GameViewModel(
            resetGameUseCase,
            setNextRoundUseCase,
            checkGuessUseCase,
        )
    }

    @Test
    fun onInitCallsResetCallsExecuteOnResetGameUseCaseWithANewGameInstance() {
        verify(exactly = 1) { resetGameUseCase.execute(Game()) }
    }

    @Test
    fun onInitSetsUserGuessToEmptyString() {
        assertTrue(tested.userGuess.isEmpty())
    }

    @Test
    fun resetGameCallsExecuteOnResetGameUseCaseWithANewGameInstance() {
        val game = Game()
        every { resetGameUseCase.execute(game) } returns Game()

        tested.resetGame()

        // First call is on init
        verify(exactly = 2) { resetGameUseCase.execute(game) }
    }

    @Test
    fun resetGameSetsUserGuessToEmptyString() {
        tested.updateUserGuess("guess 1")

        tested.resetGame()

        assertTrue(tested.userGuess.isEmpty())
    }

    @Test
    fun updateUserGuessUnsetHasErrorFlag() {
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Incorrect
        tested.checkUserGuess()

        tested.updateUserGuess("word")

        assertFalse(tested.uiState.value.hasError)
    }

    @Test
    fun updateUserGuessUpdatesUserGuess() {
        val expected = "Guess 1"
        tested.updateUserGuess(expected)

        assertEquals(expected, tested.userGuess)
    }

    @Test
    fun checkUserGuessCallsExecuteOnCheckGuessUseCaseWithUserGuessAndGame() {
        val game = Game(word = "word1")
        val guess = "word2"
        every { resetGameUseCase.execute(any()) } returns game
        every {
            checkGuessUseCase.execute(guess, game)
        } returns CheckGuessUseCase.Result.Incorrect
        tested.resetGame()
        tested.updateUserGuess(guess)

        tested.checkUserGuess()

        verify(exactly = 1) { checkGuessUseCase.execute(guess, game) }
    }

    @Test
    fun checkUserGuessSetsHasErrorFlagWhenResultIsIncorrect() {
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Incorrect

        tested.checkUserGuess()

        assertTrue(tested.uiState.value.hasError)
    }

    @Test
    fun checkUserGuessCallsSetNextRoundUseCaseWithGameReturnedFromCheckGuessUseCaseWhenResultIsCorrect() {
        val game = Game(score = 100)
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Correct(game)
        every {
            setNextRoundUseCase.execute(game)
        } returns SetNextRoundUseCase.Result.GameOver

        tested.checkUserGuess()

        verify(exactly = 1) { setNextRoundUseCase.execute(game) }
    }

    @Test
    fun checkUserGuessUpdatesStateWithGameReturnsFromCheckGuessUseCaseWithIsGameOverTrueWhenResultIsCorrectAndSetNextRoundUserCaseResultIsGameOver() {
        val game = Game(score = 100)
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Correct(game)
        every {
            setNextRoundUseCase.execute(game)
        } returns SetNextRoundUseCase.Result.GameOver

        tested.checkUserGuess()

        assertTrue(tested.uiState.value.isGameOver)
    }

    @Test
    fun checkUserGuessSetsUserGuessToEmptyWhenCheckUseGuessUseCaseReturnsCorrectAndSetNextRoundUseCaseReturnsNextWord() {
        val game = Game(word = "word1", score = 100)
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Correct(game)
        every {
            setNextRoundUseCase.execute(game)
        } returns SetNextRoundUseCase.Result.NextWord(game.copy(word = "word2"))
        tested.updateUserGuess("word1")

        tested.checkUserGuess()

        assertTrue(tested.userGuess.isEmpty())
    }

    @Test
    fun checkUserGuessUnsetsHasErrorFlagWhenCheckUseGuessUseCaseReturnsCorrectAndSetNextRoundUseCaseReturnsNextWord() {
        val game = Game(word = "word1", score = 100)
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Correct(game)
        every {
            setNextRoundUseCase.execute(game)
        } returns SetNextRoundUseCase.Result.NextWord(game.copy(word = "word2"))
        tested.updateUserGuess("word1")

        tested.checkUserGuess()

        assertFalse(tested.uiState.value.hasError)
    }

    @Test
    fun checkUserGuessUpdatesGameWithTheGameReturnedFromSetNextRoundUseCaseWhenCheckUseGuessUseCaseReturnsCorrectAndSetNextRoundUseCaseReturnsNextWord() {
        val game = Game(
            word = "word1",
            scrambledWord = "1word",
            wordCount = 1,
        )
        val expected = Game(
            word = "word2",
            scrambledWord = "2word",
            wordCount = 2,
        )
        every {
            checkGuessUseCase.execute(any(), any())
        } returns CheckGuessUseCase.Result.Correct(game)
        every {
            setNextRoundUseCase.execute(game)
        } returns SetNextRoundUseCase.Result.NextWord(expected)
        tested.updateUserGuess("word1")

        tested.checkUserGuess()

        assertEquals(expected.scrambledWord, tested.uiState.value.scrambledWord)
        assertEquals(expected.wordCount, tested.uiState.value.wordCount)
    }

    @Test
    fun skipWordSetsIsGameOverWhenSetNextRoundUserCaseResultIsGameOver() {
        every {
            setNextRoundUseCase.execute(any())
        } returns SetNextRoundUseCase.Result.GameOver

        tested.skipWord()

        assertTrue(tested.uiState.value.isGameOver)
    }

    @Test
    fun skipWordSetsUserGuessToEmptyWhenSetNextRoundUseCaseReturnsNextWord() {
        val game = Game(word = "word2", score = 100)
        every {
            setNextRoundUseCase.execute(any())
        } returns SetNextRoundUseCase.Result.NextWord(game)

        tested.skipWord()

        assertTrue(tested.userGuess.isEmpty())
    }

    @Test
    fun skipWordUnsetsHasErrorFlagWhenSetNextRoundUseCaseReturnsNextWord() {
        val game = Game(word = "word2", score = 100)
        every {
            setNextRoundUseCase.execute(any())
        } returns SetNextRoundUseCase.Result.NextWord(game)

        tested.skipWord()

        assertFalse(tested.uiState.value.hasError)
    }

    @Test
    fun skipWordUpdatesGameWithTheGameReturnedFromSetNextRoundUseCaseWhenItReturnsNextWord() {
        val expected = Game(scrambledWord = "2word", wordCount = 2)
        every {
            setNextRoundUseCase.execute(any())
        } returns SetNextRoundUseCase.Result.NextWord(expected)

        tested.skipWord()

        assertEquals(expected.scrambledWord, tested.uiState.value.scrambledWord)
        assertEquals(expected.wordCount, tested.uiState.value.wordCount)
    }
}
