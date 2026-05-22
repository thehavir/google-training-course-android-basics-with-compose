package dev.havir.unit4_project2_unscramble.data

import org.junit.Assert.assertEquals
import org.junit.Test

class LocalWordRepositoryTest {
    @Test
    fun returnsAllWordsFromLocal() {
        val tested = LocalWordRepository()

        val result = tested.fetchWords()

        assertEquals(allWords.toList(), result)
    }
}
