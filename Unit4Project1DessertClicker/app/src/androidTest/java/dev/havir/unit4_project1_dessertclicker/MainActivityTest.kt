package dev.havir.unit4_project1_dessertclicker

import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun launchesAndShowsDessertClickerScreen() {
        composeTestRule.onNodeWithText("Dessert Clicker").isDisplayed()
        composeTestRule.onNodeWithContentDescription("Cupcake").isDisplayed()
    }

    @Test
    fun soldDessertsCountSurvivesConfigurationChange() {
        repeat(3) {
            composeTestRule.onNodeWithContentDescription("Cupcake")
                .performClick()
        }

        composeTestRule.activityRule.scenario.recreate()

        composeTestRule.onNode(
            hasText("3") and hasAnySibling(hasText("Desserts sold"))
        ).isDisplayed()
    }

    @Test
    fun revenueAmountSurvivesConfigurationChange() {
        repeat(2) {
            composeTestRule.onNodeWithContentDescription("Cupcake")
                .performClick()
        }

        composeTestRule.activityRule.scenario.recreate()

        composeTestRule.onNode(
            hasText("$10.0") and hasAnySibling(hasText("Total Revenue"))
        ).isDisplayed()
    }

    @Test
    fun dessertImageSurvivesConfigurationChange() {
        repeat(5) {
            composeTestRule.onNodeWithContentDescription("Cupcake")
                .performClick()
        }

        composeTestRule.activityRule.scenario.recreate()

        composeTestRule.onNodeWithContentDescription("Donut").isDisplayed()
    }
}
