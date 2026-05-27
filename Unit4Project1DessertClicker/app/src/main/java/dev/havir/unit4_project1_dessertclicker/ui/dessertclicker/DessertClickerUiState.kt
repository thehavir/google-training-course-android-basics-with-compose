package dev.havir.unit4_project1_dessertclicker.ui.dessertclicker

import dev.havir.unit4_project1_dessertclicker.data.Dessert

data class DessertClickerUiState(
    val dessert: Dessert,
    val soldDesserts: Int = 0,
    val revenue: Double = 0.0,
)
