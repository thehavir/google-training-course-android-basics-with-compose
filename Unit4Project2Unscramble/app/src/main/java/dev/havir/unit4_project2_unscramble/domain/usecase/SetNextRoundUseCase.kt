package dev.havir.unit4_project2_unscramble.domain.usecase

import dev.havir.unit4_project2_unscramble.domain.entity.Game
import dev.havir.unit4_project2_unscramble.domain.repository.WordRepository
import kotlin.random.Random

class SetNextRoundUseCase(
    private val wordRepository: WordRepository,
    private val random: Random
) {
    sealed class Result {
        data class NextWord(val game: Game) : Result()
        object GameOver : Result()
    }

    fun execute(game: Game): Result {
        if (game.isGameOver) {
            return Result.GameOver
        }

        val words = wordRepository.fetchWords()
        var nextWord: String
        do {
            nextWord = words[random.nextInt(words.size)]
        } while (game.isWordUsed(nextWord))

        return Result.NextWord(game.withNexWord(nextWord))
    }
}
