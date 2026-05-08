package dev.havir.unit3_project3_woof.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @DrawableRes val imageDrawableResId: Int,
    @StringRes val nameStringResId: Int,
    val age: Int,
    @StringRes val hobbiesStringResId: Int
)
