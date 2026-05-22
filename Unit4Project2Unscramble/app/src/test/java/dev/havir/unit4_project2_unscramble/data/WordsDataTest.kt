package dev.havir.unit4_project2_unscramble.data

import org.junit.Assert.assertEquals
import org.junit.Test

class WordsDataTest {
    @Test
    fun allWordsAreUnique() {
        assertEquals(allWords, allWords.toSet())
    }

    @Test
    fun hasAllWords() {
        assertEquals(179, allWords.size)
    }
}
