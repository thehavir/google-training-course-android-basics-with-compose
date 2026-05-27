package dev.havir.unit4_project1_dessertclicker.ui.dessertclicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.havir.unit4_project1_dessertclicker.data.DessertDatasource

class DessertClickerViewModelFactory(private val dessertDatasource: DessertDatasource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return DessertClickerViewModel(
            dessertDatasource
        ) as T
    }
}
