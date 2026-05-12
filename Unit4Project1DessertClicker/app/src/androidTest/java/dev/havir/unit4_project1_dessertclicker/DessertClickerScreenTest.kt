package dev.havir.unit4_project1_dessertclicker

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerScreen
import dev.havir.unit4_project1_dessertclicker.ui.theme.Unit4Project1DessertClickerTheme
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DessertClickerScreenTest {
    @get:Rule(order = 0)
    val composeTestRule = createComposeRule()

    @get:Rule(order = 1)
    val intentsRule = IntentsRule()

    @Test
    fun hasAppBarTitle() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNode(
            hasText("Dessert Clicker") and hasAnyAncestor(hasTestTag("APP_BAR"))
        ).isDisplayed()
    }

    @Test
    fun hasAppBarShareAction() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNode(
            hasContentDescription("Share") and hasAnyAncestor(hasTestTag("APP_BAR"))
        ).isDisplayed()
    }

    @Test
    fun onAppBarShareActionOpensShareSheet() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }
        intending(not(isInternal()))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        composeTestRule.onNode(
            hasContentDescription("Share") and hasAnyAncestor(hasTestTag("APP_BAR"))
        ).performClick()

        intended(hasAction(Intent.ACTION_CHOOSER))
    }

    @Test
    fun hasDessertImage() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNodeWithContentDescription("Cupcake").isDisplayed()
    }

    @Test
    fun showsNextDessertWhenUserClicksMultipleTimesOnTheDessert() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        repeat(5) {
            composeTestRule.onNodeWithContentDescription("Cupcake")
                .performClick()
        }

        composeTestRule.onNodeWithContentDescription("Donut").isDisplayed()
    }

    @Test
    fun showsThirdDessertWhenUserClicksMultipleTimesOnTheDesserts() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        repeat(5) {
            composeTestRule.onNodeWithContentDescription("Cupcake")
                .performClick()
        }
        repeat(15) {
            composeTestRule.onNodeWithContentDescription("Donut").performClick()
        }

        composeTestRule.onNodeWithContentDescription("Eclair").isDisplayed()
    }

    @Test
    fun hasSoldDessertsTitle() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNodeWithText("Desserts sold").isDisplayed()
    }

    @Test
    fun hasSoldDessertsCountAsZeroWhenUserHasNotClickedOnDessert() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNode(
            hasText("0") and hasAnySibling(hasText("Desserts sold"))
        ).isDisplayed()
    }

    @Test
    fun hasSoldDessertsCountAs1WhenUserClicksOnFirstDessert() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNodeWithContentDescription("Cupcake").performClick()

        composeTestRule.onNode(
            hasText("1") and hasAnySibling(hasText("Desserts sold"))
        ).isDisplayed()
    }

    @Test
    fun hasSoldDessertsCountAsCountOfSoldDessertsWhenUserClicksOnDesserts() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        repeat(5) {
            composeTestRule.onNodeWithContentDescription("Cupcake")
                .performClick()
        }
        repeat(10) {
            composeTestRule.onNodeWithContentDescription("Donut").performClick()
        }

        composeTestRule.onNode(
            hasText("15") and hasAnySibling(hasText("Desserts sold"))
        ).isDisplayed()
    }

    @Test
    fun hasRevenueTitle() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNodeWithText("Total Revenue").isDisplayed()
    }

    @Test
    fun hasRevenueAsZeroWhenUserHasNotClickedOnDessert() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNode(
            hasText("$0.0") and hasAnySibling(hasText("Total Revenue"))
        ).isDisplayed()
    }

    @Test
    fun hasRevenueAs5WhenUserClicksOnFirstDessert() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        composeTestRule.onNode(
            hasText("$5.0") and hasAnySibling(hasText("Total Revenue"))
        ).isDisplayed()
    }

    @Test
    fun hasRevenueAsTotalRevenueWhenUserClicksOnDesserts() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen()
            }
        }

        repeat(5) {
            composeTestRule.onNodeWithContentDescription("Cupcake").performClick()
        }
        repeat(2) {
            composeTestRule.onNodeWithContentDescription("Donut").performClick()
        }

        composeTestRule.onNode(
            hasText("$45.0") and hasAnySibling(hasText("Total Revenue"))
        ).isDisplayed()
    }
}
