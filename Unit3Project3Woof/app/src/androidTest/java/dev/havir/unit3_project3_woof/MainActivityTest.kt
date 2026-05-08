package dev.havir.unit3_project3_woof

import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
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
    val composeTestRule = createComposeRule()

    @Test
    fun hasCardWithDogImageAndNameAndAge() {
        composeTestRule.setContent {
            WoofApp()
        }

        composeTestRule.onNodeWithText("Koda").isDisplayed()
        composeTestRule.onNodeWithContentDescription("Koda").isDisplayed()
        composeTestRule.onNode(
            hasText("2 years old") and hasAnySibling(hasText("Koda"))
        ).isDisplayed()
    }

    @Test
    fun cardDoesNotHaveDogHobbiesWhenItIsNotExpanded() {
        composeTestRule.setContent {
            WoofApp()
        }

        composeTestRule.onNodeWithText("Eating treats on the terrace")
            .assertDoesNotExist()
    }

    @Test
    fun cardHasDogHobbiesWhenUserTapsOnExpand() {
        composeTestRule.setContent {
            WoofApp()
        }

        composeTestRule.onNode(
            hasContentDescription("Expand dog description") and hasAnySibling(
                hasText("Koda")
            )
        ).performClick()

        composeTestRule.onNodeWithText("Eating treats on the terrace")
            .isDisplayed()
    }

    @Test
    fun cardDoesNotHaveDogHobbiesWhenUserTapsOnExpandAndThenTapsOnCollapse() {
        composeTestRule.setContent {
            WoofApp()
        }

        composeTestRule.onNode(
            hasContentDescription("Expand dog description") and hasAnySibling(
                hasText("Koda")
            )
        ).performClick()
        composeTestRule.onNode(
            hasText("Collapse dog description") and hasAnySibling(
                hasText("Koda")
            )
        ).performClick()

        composeTestRule.onNodeWithText("Eating treats on the terrace")
            .assertDoesNotExist()
    }
}
