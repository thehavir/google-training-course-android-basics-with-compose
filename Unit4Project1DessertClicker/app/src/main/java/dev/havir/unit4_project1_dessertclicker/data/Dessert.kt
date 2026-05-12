package dev.havir.unit4_project1_dessertclicker.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dessert(
    @DrawableRes val imageDrawableResId: Int,
    @StringRes val titleStringResId: Int,
    val price: Double,
    val startProductionAmount: Int,
)
