package dev.havir.unit4_project2_unscramble.domain.usecase

import dev.havir.unit4_project2_unscramble.domain.entity.Game
import org.junit.Assert.assertEquals
import org.junit.Test

class CheckGuessUseCaseTest {
    @Test
    fun executeReturnsCorrectResultWithGameWithUpdatedScoreWhenGuessIsEqualsToWord() {
        val game = Game("Individualism", score = 0)
        val tested = CheckGuessUseCase()

        val result = tested.execute(guess = "Individualism", game = game)

        assertEquals(
            CheckGuessUseCase.Result.Correct(game.copy(score = 20)),
            result,
        )
    }

    @Test
    fun executeReturnsIncorrectResultWhenGuessIsNotEqualToWord() {
        val game = Game("Individualism")
        val tested = CheckGuessUseCase()

        val result = tested.execute(guess = "Collectivism", game = game)

        assertEquals(CheckGuessUseCase.Result.Incorrect, result)
    }
}
