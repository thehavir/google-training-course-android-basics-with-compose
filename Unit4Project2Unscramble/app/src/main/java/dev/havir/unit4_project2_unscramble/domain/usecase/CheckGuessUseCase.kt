package dev.havir.unit4_project2_unscramble.domain.usecase

import dev.havir.unit4_project2_unscramble.domain.entity.Game

class CheckGuessUseCase {
    sealed class Result {
        data class Correct(val game: Game) : Result()
        object Incorrect : Result()
    }

    fun execute(guess: String, game: Game): Result {
        if (game.isGuessCorrect(guess)) {
            return Result.Correct(game.scored())
        }

        return Result.Incorrect
    }
}
