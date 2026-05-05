package dev.havir.unit2_project2_artspace

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.havir.unit2_project2_artspace.ui.theme.Unit2Project2ArtSpaceTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsVaseOfFlowersArtInInit() {
        composeTestRule.setContent {
            Unit2Project2ArtSpaceTheme {
                ArtSpaceApp()
            }
        }

        composeTestRule.onNodeWithContentDescription("Vase of Flowers")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Vase of Flowers").assertExists()
        composeTestRule.onNodeWithText("Jan Davidsz de Heem (1670)")
            .assertExists()
    }

    @Test
    fun onNextShowsTheNextArtWhenTheCurrentArtIsTheFirstOne() {
        composeTestRule.setContent {
            Unit2Project2ArtSpaceTheme {
                ArtSpaceApp()
            }
        }

        composeTestRule.onNodeWithText("Next").performClick()

        composeTestRule.onNodeWithContentDescription("Jeremiah Lamenting the Destruction of Jerusalem")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Jeremiah Lamenting the Destruction of Jerusalem")
            .assertExists()
        composeTestRule.onNodeWithText("Rembrandt (1630)").assertExists()
    }

    @Test
    fun onNextShowsTheFirstArtWhenCurrentArtIsTheLastArt() {
        val totalArts = 7
        composeTestRule.setContent {
            Unit2Project2ArtSpaceTheme {
                ArtSpaceApp()
            }
        }

        repeat(totalArts) {
            composeTestRule.onNodeWithText("Next").performClick()
        }

        composeTestRule.onNodeWithContentDescription("Vase of Flowers")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Vase of Flowers").assertExists()
        composeTestRule.onNodeWithText("Jan Davidsz de Heem (1670)")
            .assertExists()
    }

    @Test
    fun onPreviousShowsTheLastArtWhenTheCurrentArtIsTheFirstOne() {
        composeTestRule.setContent {
            Unit2Project2ArtSpaceTheme {
                ArtSpaceApp()
            }
        }

        composeTestRule.onNodeWithText("Previous").performClick()

        composeTestRule.onNodeWithContentDescription("Courting")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Courting").assertExists()
        composeTestRule.onNodeWithText("Andor Dudits (1893)").assertExists()
    }

    @Test
    fun onPreviousGoesBackToThePreviousArtWhenTheCurrentArtIsNotTheFirstOne() {
        composeTestRule.setContent {
            Unit2Project2ArtSpaceTheme {
                ArtSpaceApp()
            }
        }

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Previous").performClick()

        composeTestRule.onNodeWithContentDescription("Vase of Flowers")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Vase of Flowers").assertExists()
        composeTestRule.onNodeWithText("Jan Davidsz de Heem (1670)")
            .assertExists()
    }
}