package dev.havir.unit3_project1_affirmation

import dev.havir.unit3_project1_affirmation.data.AffirmationsDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AffirmationDataSourceTest {
    private val tested = AffirmationsDataSource

    @Test
    fun getAffirmationsReturnsNonEmptyList() {
        val affirmations = tested.getAffirmations()

        assertTrue(affirmations.isNotEmpty())
    }

    @Test
    fun getAffirmationsReturnsCorrectCount() {
        val affirmations = tested.getAffirmations()

        assertEquals(10, affirmations.size)
    }

    @Test
    fun getAffirmationsEveryItemHasValidStringRes() {
        val affirmations = tested.getAffirmations()

        affirmations.forEach { affirmation ->
            assertNotEquals(
                0, affirmation.titleStringResId
            )
        }
    }

    @Test
    fun getAffirmationsEveryItemHasValidDrawableRes() {
        val affirmations = tested.getAffirmations()

        affirmations.forEach { affirmation ->
            assertNotEquals(
                0, affirmation.imageDrawableResId
            )
        }
    }

    @Test
    fun getAffirmationsAllStringResIdsAreUnique() {
        val affirmations = tested.getAffirmations()

        val ids = affirmations.map { it.titleStringResId }
        assertEquals(ids.distinct().size, ids.size)
    }

    @Test
    fun getAffirmationsAllDrawableResIdsAreUnique() {
        val affirmations = tested.getAffirmations()

        val ids = affirmations.map { it.imageDrawableResId }
        assertEquals(ids.distinct().size, ids.size)
    }
}
