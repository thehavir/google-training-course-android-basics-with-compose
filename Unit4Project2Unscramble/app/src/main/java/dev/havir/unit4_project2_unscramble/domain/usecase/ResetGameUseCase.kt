package dev.havir.unit4_project2_unscramble.domain.usecase

import dev.havir.unit4_project2_unscramble.domain.entity.Game
import dev.havir.unit4_project2_unscramble.domain.repository.WordRepository
import kotlin.random.Random

class ResetGameUseCase(
    private val wordRepository: WordRepository,
    private val random: Random
) {
    fun execute(game: Game): Game {
        val words = wordRepository.fetchWords()
        val word = words[random.nextInt(words.size)]
        return game.withNexWord(word)
    }
}
