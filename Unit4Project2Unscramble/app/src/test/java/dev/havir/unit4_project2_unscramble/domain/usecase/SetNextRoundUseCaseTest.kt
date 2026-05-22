package dev.havir.unit4_project2_unscramble.domain.usecase

import dev.havir.unit4_project2_unscramble.domain.entity.Game
import dev.havir.unit4_project2_unscramble.domain.repository.WordRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class SetNextRoundUseCaseTest {
    val wordRepository = mockk<WordRepository>()
    val random = mockk<Random>()
    val game = mockk<Game>()

    @Test
    fun executeReturnsGameOverResultWhenIsGameOverOnGameIsTrue() {
        every { game.isGameOver } returns true
        val tested = SetNextRoundUseCase(wordRepository, random)

        val result = tested.execute(game)

        assertEquals(SetNextRoundUseCase.Result.GameOver, result)
    }

    @Test
    fun executeCallsFetchWordsOnWordRepositoryWhenIsGameOverOnGameIsFalse() {
        val words = listOf("word")
        every { game.isGameOver } returns false
        every { game.isWordUsed("word") } returns false
        every { game.withNexWord("word") } returns Game()
        every { wordRepository.fetchWords() } returns words
        every { random.nextInt(words.size) } returns 0
        val tested = SetNextRoundUseCase(wordRepository, random)

        tested.execute(game)

        verify(exactly = 1) { wordRepository.fetchWords() }
    }

    @Test
    fun executeCallsNextIntOnRandomAsLongAsIsWordUsedOnGameReturnsTrueWhenIsGameOverOnGameIsFalse() {
        val words = listOf("word1", "word2", "word3", "word4")
        every { game.isGameOver } returns false
        every { game.isWordUsed("word1") } returns true
        every { game.isWordUsed("word2") } returns true
        every { game.isWordUsed("word3") } returns false
        every { game.withNexWord("word3") } returns Game()
        every { wordRepository.fetchWords() } returns words
        every { random.nextInt(words.size) } returns 0 andThen 1 andThen 2
        val tested = SetNextRoundUseCase(wordRepository, random)

        tested.execute(game)

        verify(exactly = 3) { random.nextInt(words.size) }
    }

    @Test
    fun executeReturnsNextWordResultWithGameReturnedFromWithNextWordWithNextWordFromWordsPickedRandomlyWhenIsGameOverOnGameIsFalse() {
        val expectedGame = Game(word = "word2")
        val words = listOf("word1", "word2", "word3")
        every { game.isGameOver } returns false
        every { game.isWordUsed("word1") } returns true
        every { game.isWordUsed("word2") } returns false
        every { game.withNexWord("word2") } returns expectedGame
        every { wordRepository.fetchWords() } returns words
        every { random.nextInt(words.size) } returns 0 andThen 1
        val tested = SetNextRoundUseCase(wordRepository, random)

        val result = tested.execute(game)

        assertEquals(SetNextRoundUseCase.Result.NextWord(expectedGame), result)
    }
}
