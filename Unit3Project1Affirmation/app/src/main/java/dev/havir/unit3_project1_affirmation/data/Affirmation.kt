package dev.havir.unit3_project1_affirmation.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val titleStringResId: Int,
    @DrawableRes val imageDrawableResId: Int,
)
