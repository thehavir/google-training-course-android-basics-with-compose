package dev.havir.unit4_project2_unscramble.domain.usecase

import dev.havir.unit4_project2_unscramble.domain.entity.Game
import dev.havir.unit4_project2_unscramble.domain.repository.WordRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class ResetGameUseCaseTest {
    val wordRepository = mockk<WordRepository>()
    val random = mockk<Random>()
    val game = mockk<Game>()

    @Test
    fun executeCallsFetchWordsOnTheWordRepository() {
        val list = listOf("word1", "word2", "word3")
        every { wordRepository.fetchWords() } returns list
        every { random.nextInt(list.size) } returns 0
        every { game.withNexWord("word1") } returns Game()
        val tested = ResetGameUseCase(wordRepository, random)

        tested.execute(game)

        verify(exactly = 1) { wordRepository.fetchWords() }
    }

    @Test
    fun executeCallsNextIntOnRandomWithOnFetchedWordListLengthFromWordRepository() {
        val list = listOf("word1", "word2", "word3")
        every { wordRepository.fetchWords() } returns list
        every { random.nextInt(list.size) } returns 2
        every { game.withNexWord("word3") } returns Game()
        val tested = ResetGameUseCase(wordRepository, random)

        tested.execute(game)

        verify(exactly = 1) { random.nextInt(list.size) }
    }

    @Test
    fun executeCallsWithNextWordOnPassedGameWithWordFromWordsThatPickedRandomly() {
        val list = listOf("word1", "word2", "word3")
        every { wordRepository.fetchWords() } returns list
        every { random.nextInt(list.size) } returns 1
        every { game.withNexWord("word2") } returns Game()
        val tested = ResetGameUseCase(wordRepository, random)

        tested.execute(game)

        verify { game.withNexWord("word2") }
    }

    @Test
    fun executeReturnsGameWithNextWordFromWordsThatPickedRandomly() {
        val nextWord = "word2"
        val words = listOf("word1", nextWord, "word3")
        val expectedGame = Game(word = nextWord)
        every { wordRepository.fetchWords() } returns words
        every { random.nextInt(words.size) } returns 1
        every { game.withNexWord(nextWord) } returns expectedGame
        val tested = ResetGameUseCase(wordRepository, random)

        val result = tested.execute(game)

        assertEquals(expectedGame, result)
    }
}
