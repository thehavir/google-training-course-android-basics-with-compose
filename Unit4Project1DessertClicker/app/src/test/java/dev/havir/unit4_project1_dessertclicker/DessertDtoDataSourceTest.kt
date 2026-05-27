package dev.havir.unit4_project1_dessertclicker

import dev.havir.unit4_project1_dessertclicker.data.DessertDatasource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DessertDataSourceTest {
    @Test
    fun dessertsHasAllDesserts() {
        assertEquals(13, DessertDatasource().desserts.size)
    }

    @Test
    fun dessertsHasNonZeroDrawableResIdForImageIdForAllDesserts() {
        val desserts = DessertDatasource().desserts
        desserts.forEach { dessert ->
            assertNotEquals(0, dessert.imageDrawableResId)
        }
    }

    @Test
    fun dessertsHasNonZeroStringResIdForTitleIdForAllDesserts() {
        val desserts = DessertDatasource().desserts
        desserts.forEach { dessert ->
            assertNotEquals(0, dessert.titleStringResId)
        }
    }

    @Test
    fun dessertsHasUniqueImageDrawableResIdForAllDesserts() {
        val desserts = DessertDatasource().desserts
        val ids = desserts.map { it.imageDrawableResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun dessertsHasUniqueTitleStringResIdForAllDesserts() {
        val desserts = DessertDatasource().desserts
        val ids = desserts.map { it.titleStringResId }

        assertEquals(ids.distinct(), ids)
    }
}
