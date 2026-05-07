package dev.havir.unit3_project2_courses.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleStringResId: Int,
    val numberOfCourses: Int,
    @DrawableRes val imageDrawableResId: Int,
)
