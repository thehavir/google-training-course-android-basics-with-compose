package dev.havir.unit2_project2_artspace.data

import dev.havir.unit2_project2_artspace.R


object ArtRepository {
    val arts = listOf(
        Art(
            imageResourceId = R.drawable.vase_of_flowers,
            titleResourceId = R.string.vase_of_flowers_title,
            subtitleResourceId = R.string.vase_of_flowers_subtitle
        ), Art(
            imageResourceId = R.drawable.jeremiah,
            titleResourceId = R.string.jeremiah_title,
            subtitleResourceId = R.string.jeremiah_subtitle
        ), Art(
            imageResourceId = R.drawable.driving_home,
            titleResourceId = R.string.driving_home_title,
            subtitleResourceId = R.string.driving_home_subtitle
        ), Art(
            imageResourceId = R.drawable.pears,
            titleResourceId = R.string.pears_title,
            subtitleResourceId = R.string.pears_subtitle
        ), Art(
            imageResourceId = R.drawable.woman_with_a_parasol,
            titleResourceId = R.string.woman_with_a_parasol_title,
            subtitleResourceId = R.string.woman_with_a_parasol_subtitle
        ), Art(
            imageResourceId = R.drawable.artichoke_flowers,
            titleResourceId = R.string.artichoke_flowers_title,
            subtitleResourceId = R.string.artichoke_flowers_subtitle
        ), Art(
            imageResourceId = R.drawable.courting,
            titleResourceId = R.string.courting_title,
            subtitleResourceId = R.string.courting_subtitle
        )
    )
}
