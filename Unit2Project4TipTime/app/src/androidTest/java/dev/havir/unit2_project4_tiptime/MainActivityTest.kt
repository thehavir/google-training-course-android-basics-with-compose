package dev.havir.unit2_project4_tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.havir.unit2_project4_tiptime.ui.theme.Unit2Project4TipTimeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun hasTitle() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Calculate Tip").assertExists()
    }

    @Test
    fun hasTextFieldForBillAmount() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Bill Amount").assertExists()
    }

    @Test
    fun hasTextFieldForTipAmount() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Tip Percentage").assertExists()
    }

    @Test
    fun hasRoundUpTipText() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Round up tip?").assertExists()
    }

    @Test
    fun hasRoundUpTipSwitch() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithTag("ROUNDUP_SWITCH").assertExists()
    }

    @Test
    fun showsZeroTipWithCurrencyWhenUserIsNotEnteredBillAndTipAmounts() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Tip Amount: $0.00").assertExists()
    }

    @Test
    fun showsNotRoundedButFormattedTipWithCurrencyWhenUserEntersBillAmountAndTipPercentage() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Bill Amount").performTextInput("43.78")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("10.00")

        composeTestRule.onNodeWithText("Tip Amount: $4.38").assertExists()
    }

    @Test
    fun showsRoundedAndFormattedTipWithCurrencyWhenUserEnterBillAmountAndTipPercentageAndSwitchesRoundUp() {
        composeTestRule.setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }

        composeTestRule.onNodeWithText("Bill Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20.49")
        composeTestRule.onNodeWithTag("ROUNDUP_SWITCH").performClick()

        composeTestRule.onNodeWithText("Tip Amount: $21.00").assertExists()
    }
}
