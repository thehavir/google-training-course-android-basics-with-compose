package dev.havir.unit3_project2_courses

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsCardThatContainsImageAndText() {
        composeTestRule.setContent {
            CoursesApp()
        }

        composeTestRule.onNodeWithText("Architecture").assertExists()
        composeTestRule.onNodeWithContentDescription("Architecture")
            .assertExists()
        composeTestRule.onNode(
            hasText("58") and hasAnySibling(hasText("Architecture"))
        ).assertExists()
    }

    @Test
    fun listIsScrollable() {
        composeTestRule.setContent {
            CoursesApp()
        }

        composeTestRule.onNodeWithTag("CourseGrid").assert(hasScrollAction())
    }

    @Test
    fun lastItemIsShownWhenScrollingToTheBottomOfTheList() {
        composeTestRule.setContent {
            CoursesApp()
        }

        composeTestRule.onNodeWithTag("CourseGrid").performScrollToIndex(23)

        composeTestRule.onNodeWithText("Tech").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Tech").assertIsDisplayed()
        composeTestRule.onNode(
            hasText("118") and hasAnySibling(hasText("Tech"))
        ).assertIsDisplayed()
    }
}