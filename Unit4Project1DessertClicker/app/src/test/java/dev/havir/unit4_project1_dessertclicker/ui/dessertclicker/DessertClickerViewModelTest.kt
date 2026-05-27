package dev.havir.unit4_project1_dessertclicker.ui.dessertclicker

import dev.havir.unit4_project1_dessertclicker.data.Dessert
import dev.havir.unit4_project1_dessertclicker.data.DessertDatasource
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DessertClickerViewModelTest {
    lateinit var dessertDataSource: DessertDatasource
    lateinit var tested: DessertClickerViewModel

    @Before
    fun setUp() {
        dessertDataSource = mockk<DessertDatasource>()
        every { dessertDataSource.desserts } returns listOf(TestModels.dessert())
        tested = DessertClickerViewModel(dessertDataSource)
    }

    @Test
    fun soldDessertsIsZeroInTheBeginning() {
        assertEquals(0, tested.uiState.value.soldDesserts)
    }

    @Test
    fun revenueIsZeroInTheBeginning() {
        assertEquals(0.0, tested.uiState.value.revenue, 0.001)
    }

    @Test
    fun dessertIsTheFirstItemFromDessertDatasourceInTheBeginning() {
        val dessert1 = TestModels.dessert(titleStringResId = 0)
        val dessert2 = TestModels.dessert(titleStringResId = 1)
        val desserts = listOf(dessert1, dessert2)
        every { dessertDataSource.desserts } returns desserts
        tested = DessertClickerViewModel(dessertDataSource)

        assertEquals(dessert1, tested.uiState.value.dessert)
    }

    @Test
    fun onSellDessertIncreasesSoldDessertsByOne() {
        tested.sellDessert()

        assertEquals(1, tested.uiState.value.soldDesserts)
    }

    @Test
    fun onSellDessertSumsRevenueWithTheCurrentDessertPrice() {
        val dessert = TestModels.dessert(price = 15.0)
        every { dessertDataSource.desserts } returns listOf(dessert)
        tested = DessertClickerViewModel(dessertDataSource)

        tested.sellDessert()

        assertEquals(15.0, tested.uiState.value.revenue, 0.001)
    }

    @Test
    fun onSellDessertSetsDessertAsLastDessertThatItsStartProductionAmountIsEqualToUpdatedSoldDesserts() {
        val dessert1 = TestModels.dessert(startProductionAmount = 5)
        val dessert2 = TestModels.dessert(startProductionAmount = 12)
        val dessert3 = TestModels.dessert(startProductionAmount = 20)
        val desserts = listOf(dessert1, dessert2, dessert3)
        every { dessertDataSource.desserts } returns desserts
        tested = DessertClickerViewModel(dessertDataSource)

        repeat(12) { tested.sellDessert() }

        assertEquals(dessert2, tested.uiState.value.dessert)
    }

    @Test
    fun onSellDessertSetsDessertAsLastDessertThatItsStartProductionAmountIsLowerThanUpdatedSoldDesserts() {
        val dessert1 = TestModels.dessert(startProductionAmount = 5)
        val dessert2 = TestModels.dessert(startProductionAmount = 12)
        val dessert3 = TestModels.dessert(startProductionAmount = 20)
        val desserts = listOf(dessert1, dessert2, dessert3)
        every { dessertDataSource.desserts } returns desserts
        tested = DessertClickerViewModel(dessertDataSource)

        repeat(13) { tested.sellDessert() }

        assertEquals(dessert2, tested.uiState.value.dessert)
    }

    @Test
    fun onSellDessertSetsDessertAsFirstDessertFromDataSourceWhenNoDessertHasStartProductionAmountLowerThanOrEqualsToUpdatedSoldDesserts() {
        val dessert1 = TestModels.dessert(startProductionAmount = 5)
        val dessert2 = TestModels.dessert(startProductionAmount = 12)
        val dessert3 = TestModels.dessert(startProductionAmount = 20)
        val desserts = listOf(dessert1, dessert2, dessert3)
        every { dessertDataSource.desserts } returns desserts
        tested = DessertClickerViewModel(dessertDataSource)

        repeat(2) { tested.sellDessert() }

        assertEquals(dessert1, tested.uiState.value.dessert)
    }
}

private object TestModels {
    fun dessert(
        titleStringResId: Int = 0,
        startProductionAmount: Int = 0,
        price: Double = 0.0
    ) = Dessert(
        imageDrawableResId = 0,
        titleStringResId = titleStringResId,
        price = price,
        startProductionAmount = startProductionAmount,
    )
}