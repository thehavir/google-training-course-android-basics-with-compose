package dev.havir.unit2_project2_artspace.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Art(
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val subtitleResourceId: Int
)
