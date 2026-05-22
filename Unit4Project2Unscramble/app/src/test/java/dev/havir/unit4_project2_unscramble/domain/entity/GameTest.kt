package dev.havir.unit4_project2_unscramble.domain.entity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameTest {
    @Test
    fun isGameOverReturnsTrueWhenWordCountIsEqualToMaxNoOfWords() {
        val tested = Game(wordCount = 10)

        assertTrue(tested.isGameOver)
    }

    @Test
    fun isGameOverReturnsFalseWhenWordCountIsLessThanMaxNoOfWords() {
        val tested = Game(wordCount = 9)

        assertFalse(tested.isGameOver)
    }

    @Test
    fun isGameOverReturnsFalseWhenWordCountIsBiggerThanMaxNoOfWords() {
        val tested = Game(wordCount = 11)

        assertFalse(tested.isGameOver)
    }

    @Test
    fun isWordUsedReturnsTrueWhenUsedWordsContainsPassedWord() {
        val tested = Game(usedWords = setOf("Individualism", "Egoism"))

        assertTrue(tested.isWordUsed("Individualism"))
    }

    @Test
    fun isWordUsedReturnsFalseWhenUsedWordsDoesNotContainPassedWord() {
        val tested = Game(usedWords = setOf("Individualism", "Egoism"))

        assertFalse(tested.isWordUsed("Collectivism"))
    }

    @Test
    fun isGuessCorrectReturnsTrueWhenPassedGuessIsEqualToWord() {
        val tested = Game(word = "Hero")

        assertTrue(tested.isGuessCorrect("Hero"))
    }

    @Test
    fun isGuessCorrectReturnsTrueWhenPassedGuessIsEqualToWordButHasSpaceAround() {
        val tested = Game(word = "Hero")

        assertTrue(tested.isGuessCorrect("    Hero "))
    }

    @Test
    fun isGuessCorrectReturnsTrueWhenPassedGuessIsEqualToWordButHasCapitalChars() {
        val tested = Game(word = "Hero")

        assertTrue(tested.isGuessCorrect("HErO"))
    }

    @Test
    fun isGuessCorrectReturnsFalseWhenPassedGuessIsNotEqualToWord() {
        val tested = Game(word = "Hero")

        assertFalse(tested.isGuessCorrect("Superhero"))
    }

    @Test
    fun scoredReturnsGameWithUpdatedScore() {
        val tested = Game(score = 10)

        val updated = tested.scored()

        assertEquals(30, updated.score)
    }

    @Test
    fun withNextWordReturnGameWithUpdatedWord() {
        val tested = Game(word = "Objectivism")

        val updated = tested.withNexWord("Individualism")

        assertEquals("Individualism", updated.word)
    }

    @Test
    fun withNextWordReturnGameWithUpdatedUsedWords() {
        val tested = Game(usedWords = setOf("Excellence"))

        val updated = tested.withNexWord("Hero")

        assertTrue(updated.isWordUsed("Hero"))
    }

    @Test
    fun withNextWordReturnGameWithUpdatedWordCount() {
        val tested = Game(wordCount = 5)

        val updated = tested.withNexWord("Philosophy")

        assertEquals(6, updated.wordCount)
    }

    @Test
    fun withNextWordReturnGameWithUpdatedScrambledWord() {
        val tested = Game(word = "word", scrambledWord = "wodr")

        val updated = tested.withNexWord("next")

        assertEquals(
            updated.word.toList().sorted(),
            updated.scrambledWord.toList().sorted()
        )
    }

    @Test
    fun withNextWordReturnGameWithUpdatedScrambledWordThatIsNotEqualToWord() {
        val tested = Game(word = "word", scrambledWord = "wodr")

        val updated = tested.withNexWord("next")

        assertNotEquals(updated.word, updated.scrambledWord)
    }
}
