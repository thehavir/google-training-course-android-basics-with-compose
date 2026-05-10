package dev.havir.unit3_project5_thirtydays

import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dev.havir.unit3_project5_thirtydays.ui.theme.Unit3Project5ThirtyDaysTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun thirtyDayAppHasTipsCountInTheAppBar() {
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysApp()
            }
        }

        composeTestRule.onNode(
            hasText("15 Tips...") and hasAnyAncestor(hasTestTag("THIRTY_DAYS_APP_BAR"))
        ).isDisplayed()
    }

    @Test
    fun thirtyDayAppHasThirtyDaysList() {
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysApp()
            }
        }

        composeTestRule.onNodeWithTag("THIRTY_DAYS_LIST").isDisplayed()
    }
}
