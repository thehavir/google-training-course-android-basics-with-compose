package dev.havir.unit3_project5_thirtydays.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @StringRes val titleStringResId: Int,
    @StringRes val descriptionStringResId: Int,
    @StringRes val authorStringResId: Int,
    @DrawableRes val imageDrawableResId: Int,
)
