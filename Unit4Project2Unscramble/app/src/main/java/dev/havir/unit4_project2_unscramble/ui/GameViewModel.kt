package dev.havir.unit4_project2_unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.havir.unit4_project2_unscramble.domain.entity.Game
import dev.havir.unit4_project2_unscramble.domain.usecase.CheckGuessUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.ResetGameUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.SetNextRoundUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(
    val resetGameUseCase: ResetGameUseCase,
    val setNextRoundUseCase: SetNextRoundUseCase,
    val checkGuessUseCase: CheckGuessUseCase,
) : ViewModel() {
    private var game = Game()
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun resetGame() {
        game = resetGameUseCase.execute(Game())
        userGuess = ""
        _uiState.value = game.toUiState()
    }

    fun updateUserGuess(guess: String) {
        userGuess = guess
        _uiState.update { it.copy(hasError = false) }
    }

    fun checkUserGuess() {
        when (val result = checkGuessUseCase.execute(userGuess, game)) {
            is CheckGuessUseCase.Result.Correct -> {
                game = result.game
                advanceToNextRound()
            }

            is CheckGuessUseCase.Result.Incorrect -> _uiState.update {
                it.copy(hasError = true)
            }
        }
    }

    fun skipWord() = advanceToNextRound()

    private fun advanceToNextRound() {
        when (val result = setNextRoundUseCase.execute(game)) {
            is SetNextRoundUseCase.Result.NextWord -> {
                updateUserGuess("")
                game = result.game
                _uiState.value = game.toUiState()
            }

            is SetNextRoundUseCase.Result.GameOver -> {
                _uiState.value = game.toUiState(isGameOver = true)
            }
        }
    }
}

private fun Game.toUiState(isGameOver: Boolean = false) = GameUiState(
    scrambledWord = scrambledWord,
    score = score,
    wordCount = wordCount,
    isGameOver = isGameOver,
    hasError = false,
)
