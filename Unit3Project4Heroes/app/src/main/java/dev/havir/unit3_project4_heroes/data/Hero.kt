package dev.havir.unit3_project4_heroes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val nameStringResId: Int,
    @StringRes val descriptionStringResId: Int,
    @DrawableRes val imageDrawableResId: Int,
)
