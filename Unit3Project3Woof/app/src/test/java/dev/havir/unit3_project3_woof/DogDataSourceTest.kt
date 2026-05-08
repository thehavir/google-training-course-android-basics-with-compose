package dev.havir.unit3_project3_woof

import dev.havir.unit3_project3_woof.data.DogDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DogDataSourceTest {
    @Test
    fun dogsHasAllDogs() {
        assertEquals(9, DogDataSource.dogs.size)
    }

    @Test
    fun dogsHasNameAsNonZeroStringResIdForAllDogs() {
        val dogs = DogDataSource.dogs

        dogs.forEach { dog ->
            assertNotEquals(0, dog.nameStringResId)
        }
    }

    @Test
    fun dogsHasImageAsNonZeroDrawableResIdForAllDogs() {
        val dogs = DogDataSource.dogs

        dogs.forEach { dog ->
            assertNotEquals(0, dog.imageDrawableResId)
        }
    }

    @Test
    fun dogsHasHobbiesAsNonZeroStringResIdForAllDogs() {
        val dogs = DogDataSource.dogs

        dogs.forEach { dog ->
            assertNotEquals(0, dog.hobbiesStringResId)
        }
    }

    @Test
    fun dogsHasDogsWithUniqueNameStringResIds() {
        val dogs = DogDataSource.dogs
        val ids = dogs.map { it.nameStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun dogsHasDogsWithUniqueImageDrawableResIds() {
        val dogs = DogDataSource.dogs
        val ids = dogs.map { it.imageDrawableResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun dogsHasDogsWithUniqueHobbiesStringResIds() {
        val dogs = DogDataSource.dogs
        val ids = dogs.map { it.hobbiesStringResId }

        assertEquals(ids.distinct(), ids)
    }
}
