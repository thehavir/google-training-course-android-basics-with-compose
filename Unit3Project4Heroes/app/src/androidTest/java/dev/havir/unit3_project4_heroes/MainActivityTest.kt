package dev.havir.unit3_project4_heroes

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.v2.createComposeRule
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
    fun hasNameAndDescriptionAndImageOfHero() {
        composeTestRule.setContent {
            HeroesApp()
        }

        composeTestRule.onNodeWithText("Samwise Gamgee").assertIsDisplayed()
        composeTestRule.onNodeWithText("Loyal gardener who prioritizes friendship and duty, carrying Frodo and hope when all seems lost.")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Samwise Gamgee")
            .assertIsDisplayed()
    }

    @Test
    fun hasScrollableHeroList() {
        composeTestRule.setContent {
            HeroesApp()
        }

        composeTestRule.onNodeWithTag("HERO_LIST").assert(hasScrollAction())
    }

    @Test
    fun onScrollShowsTheLastHero() {
        composeTestRule.setContent {
            HeroesApp()
        }

        composeTestRule.onNodeWithTag("HERO_LIST").performScrollToIndex(7)

        composeTestRule.onNodeWithText("Nikola Tesla").assertIsDisplayed()
        composeTestRule.onNodeWithText("Visionary inventor and engineer who pursued radical ideas in electricity and wireless transmission despite personal hardship.")
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Nikola Tesla")
            .assertIsDisplayed()
    }
}
