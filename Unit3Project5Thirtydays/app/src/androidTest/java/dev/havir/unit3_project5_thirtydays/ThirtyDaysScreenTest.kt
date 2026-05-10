package dev.havir.unit3_project5_thirtydays

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.havir.unit3_project5_thirtydays.data.Tip
import dev.havir.unit3_project5_thirtydays.ui.theme.Unit3Project5ThirtyDaysTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ThirtyDayScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun thirtyDaysItemHasDayNumberText() {
        val dayNumber = 16
        val tip = TestModels.tip()
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = tip, dayNumber = dayNumber)
            }
        }

        composeTestRule.onNodeWithText("Day $dayNumber").assertIsDisplayed()
    }

    @Test
    fun thirtyDaysItemHasTitleText() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val titleId = R.string.tip4_title
        val tip = TestModels.tip(titleStringResId = titleId)
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = tip, dayNumber = 4)
            }
        }

        composeTestRule.onNodeWithText(context.getString(titleId))
            .assertIsDisplayed()
    }

    @Test
    fun thirtyDaysItemHasImage() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val titleId = R.string.tip2_title
        val tip = TestModels.tip(
            titleStringResId = titleId, imageDrawableResId = R.drawable.victory
        )
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = tip, dayNumber = 13)
            }
        }

        composeTestRule.onNodeWithContentDescription(context.getString(titleId))
            .assertIsDisplayed()
    }

    @Test
    fun thirtyDaysItemIsClickable() {
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = TestModels.tip(), dayNumber = 1)
            }
        }

        composeTestRule.onNodeWithTag("THIRTY_DAYS_CARD").assertHasClickAction()
    }

    @Test
    fun thirtyDaysItemDoesNotHaveDescriptionAndAuthorWhenItIsNotClicked() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val descriptionId = R.string.tip10_subtitle
        val authorId = R.string.tip10_author
        val tip = TestModels.tip(
            descriptionStringResId = descriptionId,
            authorStringResId = authorId,
        )
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = tip, dayNumber = 1)
            }
        }

        composeTestRule.onNodeWithText(context.getString(descriptionId))
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithText(context.getString(authorId))
            .assertIsNotDisplayed()
    }

    @Test
    fun thirtyDaysItemDoesHasDescriptionWhenAndAuthorItIsClicked() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val descriptionId = R.string.tip10_subtitle
        val authorId = R.string.tip10_author
        val tip = TestModels.tip(
            descriptionStringResId = descriptionId,
            authorStringResId = authorId,
        )
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = tip, dayNumber = 1)
            }
        }

        composeTestRule.onNodeWithTag("THIRTY_DAYS_CARD").performClick()

        composeTestRule.onNodeWithText(context.getString(descriptionId))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(authorId))
            .assertIsDisplayed()
    }

    @Test
    fun thirtyDaysItemDoesNotHaveDescriptionAndAuthorWhenItClickedAlreadyAndClickedAgain() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val descriptionId = R.string.tip10_subtitle
        val authorId = R.string.tip10_author
        val tip = TestModels.tip(
            descriptionStringResId = descriptionId,
            authorStringResId = authorId,
        )
        composeTestRule.setContent {
            Unit3Project5ThirtyDaysTheme {
                ThirtyDaysItem(tip = tip, dayNumber = 1)
            }
        }

        composeTestRule.onNodeWithTag("THIRTY_DAYS_CARD").performClick()
            .performClick()

        composeTestRule.onNodeWithText(context.getString(descriptionId))
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithText(context.getString(authorId))
            .assertIsNotDisplayed()
    }

    @Test
    fun thirtyDaysListHasThirtyDayItem() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val titleId = R.string.tip2_title
        val imageId = R.drawable.river2
        val tips = listOf(
            TestModels.tip(
                titleStringResId = titleId, imageDrawableResId = imageId
            )
        )
        composeTestRule.setContent {
            ThirtyDaysList(tips)
        }

        composeTestRule.onNodeWithText(context.getString(titleId))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.getString(titleId))
            .assertIsDisplayed()
    }

    @Test
    fun thirtyDaysListIsScrollable() {
        val tips = listOf(TestModels.tip())
        composeTestRule.setContent {
            ThirtyDaysList(tips)
        }

        composeTestRule.onNodeWithTag("THIRTY_DAYS_LIST")
            .assert(hasScrollAction())
    }

    @Test
    fun thirtyDaysListShowsLastItemOnScroll() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val titleId = R.string.tip2_title
        val imageId = R.drawable.river2
        val tips = listOf(
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(),
            TestModels.tip(
                titleStringResId = titleId, imageDrawableResId = imageId
            )
        )
        composeTestRule.setContent {
            ThirtyDaysList(tips)
        }

        composeTestRule.onNodeWithTag("THIRTY_DAYS_LIST")
            .performScrollToIndex(12)

        composeTestRule.onNodeWithText(context.getString(titleId))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.getString(titleId))
            .assertIsDisplayed()
    }
}

object TestModels {
    fun tip(
        @StringRes titleStringResId: Int? = null,
        @StringRes descriptionStringResId: Int? = null,
        @StringRes authorStringResId: Int? = null,
        @DrawableRes imageDrawableResId: Int? = null,
    ): Tip = Tip(
        titleStringResId = titleStringResId ?: R.string.tip1_title,
        descriptionStringResId = descriptionStringResId
            ?: R.string.tip1_subtitle,
        authorStringResId = authorStringResId ?: R.string.tip1_author,
        imageDrawableResId = imageDrawableResId ?: R.drawable.victory
    )
}
