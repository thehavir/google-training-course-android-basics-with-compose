package dev.havir.unit4_project1_dessertclicker.dessertclicker

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
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.havir.unit4_project1_dessertclicker.R
import dev.havir.unit4_project1_dessertclicker.data.Dessert
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerScreen
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerUiState
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerViewModel
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerViewModelFactory
import dev.havir.unit4_project1_dessertclicker.ui.theme.Unit4Project1DessertClickerTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DessertDtoClickerScreenTest {
    private val factory = mockk<DessertClickerViewModelFactory>()
    private val dessertClickerViewModel = mockk<DessertClickerViewModel>()

    @get:Rule(order = 0)
    val composeTestRule = createComposeRule()

    @get:Rule(order = 1)
    val intentsRule = IntentsRule()

    @Before
    fun setUp() {
        every { dessertClickerViewModel.uiState } returns MutableStateFlow(
            TestModels.dessertClickerUiState()
        )
        every { dessertClickerViewModel.sellDessert() } returns Unit
    }

    private fun setContent() {
        composeTestRule.setContent {
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen(factory, dessertClickerViewModel)
            }
        }
    }

    @Test
    fun hasAppBarTitle() {
        setContent()

        composeTestRule.onNode(
            hasText("Dessert Clicker") and hasAnyAncestor(hasTestTag("APP_BAR"))
        ).isDisplayed()
    }

    @Test
    fun hasAppBarShareAction() {
        setContent()

        composeTestRule.onNode(
            hasContentDescription("Share") and hasAnyAncestor(hasTestTag("APP_BAR"))
        ).isDisplayed()
    }

    @Test
    fun onAppBarShareActionOpensShareSheet() {
        setContent()
        Intents.intending(Matchers.not(IntentMatchers.isInternal()))
            .respondWith(
                Instrumentation.ActivityResult(
                    Activity.RESULT_OK, null
                )
            )

        composeTestRule.onNode(
            hasContentDescription("Share") and hasAnyAncestor(hasTestTag("APP_BAR"))
        ).performClick()

        Intents.intended(IntentMatchers.hasAction(Intent.ACTION_CHOOSER))
    }

    @Test
    fun hasDessertImage() {
        val dessert = TestModels.dessert(
            imageDrawableResId = R.drawable.kitkat,
            titleStringResId = R.string.dessert_kitkat
        )
        val uiState = MutableStateFlow(
            TestModels.dessertClickerUiState(dessert)
        )
        every { dessertClickerViewModel.uiState } returns uiState
        setContent()

        composeTestRule.onNodeWithContentDescription("Kitkat").isDisplayed()
    }

    @Test
    fun onDessertClickCallsSellDessertOnDessertClickerViewModel() {
        val dessert = TestModels.dessert(
            imageDrawableResId = R.drawable.eclair,
            titleStringResId = R.string.dessert_eclair
        )
        val uiState = MutableStateFlow(
            TestModels.dessertClickerUiState(dessert)
        )
        every { dessertClickerViewModel.uiState } returns uiState
        setContent()

        composeTestRule.onNodeWithContentDescription("Eclair").performClick()

        verify(exactly = 1) { dessertClickerViewModel.sellDessert() }
    }

    @Test
    fun hasSoldDessertsTitle() {
        setContent()

        composeTestRule.onNodeWithText("Desserts sold").isDisplayed()
    }

    @Test
    fun hasSoldDessertsCountFromViewModel() {
        val uiState = MutableStateFlow(
            TestModels.dessertClickerUiState(soldDesserts = 26)
        )
        every { dessertClickerViewModel.uiState } returns uiState
        setContent()

        composeTestRule.onNode(
            hasText("26") and hasAnySibling(hasText("Desserts sold"))
        ).isDisplayed()
    }

    @Test
    fun hasRevenueTitle() {
        setContent()

        composeTestRule.onNodeWithText("Total Revenue").isDisplayed()
    }

    @Test
    fun hasRevenueFromViewModel() {
        val uiState = MutableStateFlow(
            TestModels.dessertClickerUiState(revenue = 72.84)
        )
        every { dessertClickerViewModel.uiState } returns uiState
        setContent()

        composeTestRule.onNode(
            hasText("$72.84") and hasAnySibling(hasText("Total Revenue"))
        ).isDisplayed()
    }
}

private object TestModels {
    fun dessertClickerUiState(
        dessert: Dessert = dessert(),
        soldDesserts: Int = 0,
        revenue: Double = 0.0
    ) = DessertClickerUiState(
        dessert = dessert,
        soldDesserts = soldDesserts,
        revenue = revenue,
    )

    fun dessert(
        titleStringResId: Int = R.string.dessert_cupcake,
        imageDrawableResId: Int = R.drawable.cupcake,
        startProductionAmount: Int = 0,
        price: Double = 0.0
    ) = Dessert(
        imageDrawableResId = imageDrawableResId,
        titleStringResId = titleStringResId,
        price = price,
        startProductionAmount = startProductionAmount,
    )
}
