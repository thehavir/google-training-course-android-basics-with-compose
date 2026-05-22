package dev.havir.unit4_project2_unscramble

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.havir.unit4_project2_unscramble.ui.GameScreenTestTags
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun showsGameScreen() {
        composeTestRule.onNodeWithTag(GameScreenTestTags.MAIN_LAYOUT)
            .assertIsDisplayed()
    }

    @Test
    fun onExitFinishesActivity() {
        val activity = composeTestRule.activity

        composeTestRule.runOnIdle {
            activity.exitGame()

            assertTrue(activity.isFinishing)
        }
    }
}
