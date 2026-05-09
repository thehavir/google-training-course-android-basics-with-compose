package dev.havir.unit3_project4_heroes

import dev.havir.unit3_project4_heroes.data.HeroesDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class HeroDataSourceTest {
    @Test
    fun heroesHasAllHeroes() {
        assertEquals(8, HeroesDataSource.heroes.size)
    }

    @Test
    fun heroesHasNonZeroStringResIdForAllHeroNames() {
        val heroes = HeroesDataSource.heroes

        heroes.forEach { hero ->
            assertNotEquals(0, hero.nameStringResId)
        }
    }

    @Test
    fun heroesHasUniqueStringResIdForAllHeroNames() {
        val heroes = HeroesDataSource.heroes
        val ids = heroes.map { it.nameStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun heroesHasNonZeroStringResIdForAllHeroDescription() {
        val heroes = HeroesDataSource.heroes

        heroes.forEach { hero ->
            assertNotEquals(0, hero.descriptionStringResId)
        }
    }

    @Test
    fun heroesHasUniqueStringResIdForAllHeroDescription() {
        val heroes = HeroesDataSource.heroes
        val ids = heroes.map { it.descriptionStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun heroesHasNonZeroDrawableResIdForAllHeroImages() {
        val heroes = HeroesDataSource.heroes

        heroes.forEach { hero ->
            assertNotEquals(0, hero.imageDrawableResId)
        }
    }

    @Test
    fun heroesHasUniqueDrawableResIdForAllHeroImages() {
        val heroes = HeroesDataSource.heroes
        val ids = heroes.map { it.imageDrawableResId }

        assertEquals(ids.distinct(), ids)
    }
}
