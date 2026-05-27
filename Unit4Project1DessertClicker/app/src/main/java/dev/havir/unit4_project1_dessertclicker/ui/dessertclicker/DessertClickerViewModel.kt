package dev.havir.unit4_project1_dessertclicker.ui.dessertclicker

import androidx.lifecycle.ViewModel
import dev.havir.unit4_project1_dessertclicker.data.DessertDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel(dessertDatasource: DessertDatasource) :
    ViewModel() {
    private val desserts = dessertDatasource.desserts
    private var _uiState =
        MutableStateFlow(DessertClickerUiState(dessert = desserts.first()))
    var uiState = _uiState.asStateFlow()

    fun sellDessert() {
        _uiState.update { currentState ->
            val newSoldDesserts = currentState.soldDesserts.inc()
            currentState.copy(
                dessert = dessertForSoldAmount(newSoldDesserts),
                soldDesserts = newSoldDesserts,
                revenue = currentState.revenue.plus(currentState.dessert.price)
            )
        }
    }

    // Finds and sets the dessert that has highest startProductionAmount that is
    // lower than soldDesserts.
    // If we have following:
    //      Dessert A - startProductionAmount = 0,
    //      Dessert B - startProductionAmount = 5,
    //      Dessert C - startProductionAmount = 20
    // And the soldDesserts is 18, then it returns Dessert B.
    private fun dessertForSoldAmount(soldDesserts: Int) =
        desserts.findLast { it.startProductionAmount <= soldDesserts }
            ?: desserts.first()
}
