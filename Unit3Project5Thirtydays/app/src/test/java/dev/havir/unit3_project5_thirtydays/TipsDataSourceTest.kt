package dev.havir.unit3_project5_thirtydays

import dev.havir.unit3_project5_thirtydays.data.TipsDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class TipsDataSourceTest {
    @Test
    fun tipsHasAllTips() {
        assertEquals(15, TipsDataSource.tips.size)
    }

    @Test
    fun tipsHasAllTipsWithNonZeroStringResIdForTitle() {
        val tips = TipsDataSource.tips

        tips.forEach { tips ->
            assertNotEquals(0, tips.titleStringResId)
        }
    }

    @Test
    fun tipsHasAllTipsWithNonZeroStringResIdForDescription() {
        val tips = TipsDataSource.tips

        tips.forEach { tips ->
            assertNotEquals(0, tips.descriptionStringResId)
        }
    }

    @Test
    fun tipsHasAllTipsWithNonZeroStringResIdForAuthor() {
        val tips = TipsDataSource.tips

        tips.forEach { tips ->
            assertNotEquals(0, tips.authorStringResId)
        }
    }

    @Test
    fun tipsHasAllTipsAllTipsWithUniqueStringResIdForTitle() {
        val tips = TipsDataSource.tips
        val ids = tips.map { it.titleStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun tipsHasAllTipsAllTipsWithUniqueStringResIdForDescription() {
        val tips = TipsDataSource.tips
        val ids = tips.map { it.descriptionStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun tipsHasAllTipsAllTipsWithUniqueStringResIdForAuthor() {
        val tips = TipsDataSource.tips
        val ids = tips.map { it.authorStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun tipsHasAllTipsAllTipsWithUniqueDrawableResIdForImage() {
        val tips = TipsDataSource.tips
        val ids = tips.map { it.imageDrawableResId }

        assertEquals(ids.distinct(), ids)
    }
}
