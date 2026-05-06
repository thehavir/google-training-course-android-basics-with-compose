package dev.havir.unit3_project1_affirmation

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
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
            AffirmationApp()
        }

        composeTestRule.onNodeWithContentDescription("I am strong.")
            .assertExists()
        composeTestRule.onNodeWithText("I am strong.").assertExists()
    }

    @Test
    fun listIsScrollable() {
        composeTestRule.setContent {
            AffirmationApp()
        }

        composeTestRule.onNodeWithTag("AffirmationList")
            .assert(hasScrollAction())
    }

    @Test
    fun lastItemIsShownWhenScrollingToTheBottomOfTheList() {
        composeTestRule.setContent {
            AffirmationApp()
        }

        composeTestRule.onNodeWithTag("AffirmationList").performScrollToIndex(9)

        composeTestRule.onNodeWithContentDescription(
            "I will be present in all the moments that this day brings."
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            "I will be present in all the moments that this day brings."
        ).assertIsDisplayed()
    }
}
