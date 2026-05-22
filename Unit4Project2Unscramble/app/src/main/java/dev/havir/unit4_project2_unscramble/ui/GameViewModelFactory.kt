package dev.havir.unit4_project2_unscramble.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.havir.unit4_project2_unscramble.domain.usecase.CheckGuessUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.ResetGameUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.SetNextRoundUseCase

class GameViewModelFactory(
    private val resetGameUseCase: ResetGameUseCase,
    private val setNextRoundUseCase: SetNextRoundUseCase,
    private val checkGuessUseCase: CheckGuessUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return GameViewModel(
            resetGameUseCase = resetGameUseCase,
            setNextRoundUseCase = setNextRoundUseCase,
            checkGuessUseCase = checkGuessUseCase,
        ) as T
    }
}
