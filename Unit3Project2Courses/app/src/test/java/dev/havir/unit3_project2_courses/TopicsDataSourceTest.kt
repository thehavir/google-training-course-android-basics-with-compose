package dev.havir.unit3_project2_courses

import dev.havir.unit3_project2_courses.data.TopicsDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class TopicsDataSourceTest {
    @Test
    fun topicsHasAllTopics() {
        assertEquals(24, TopicsDataSource.topics.size)
    }

    @Test
    fun topicsHasTitleResIdWithValidStringResForEveryTopic() {
        TopicsDataSource.topics.forEach { topic ->
            assertNotEquals(0, topic.titleStringResId)
        }
    }

    @Test
    fun topicsHasImageResIdWithValidDrawableResForEveryTopic() {
        TopicsDataSource.topics.forEach { topic ->
            assertNotEquals(0, topic.imageDrawableResId)
        }
    }

    @Test
    fun topicsHasTopicsWithUniqueTitleStringResId() {
        val topics = TopicsDataSource.topics
        val ids = topics.map { it.titleStringResId }

        assertEquals(ids.distinct(), ids)
    }

    @Test
    fun topicsHasTopicsWithUniqueImageDrawableResId() {
        val topics = TopicsDataSource.topics
        val ids = topics.map { it.imageDrawableResId }

        assertEquals(ids.distinct(), ids)
    }
}
