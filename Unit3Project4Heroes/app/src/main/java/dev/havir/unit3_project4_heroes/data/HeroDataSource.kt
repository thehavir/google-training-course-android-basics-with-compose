package dev.havir.unit3_project4_heroes.data

import dev.havir.unit3_project4_heroes.R

object HeroesDataSource {
    val heroes = listOf(
        Hero(
            nameStringResId = R.string.hero_name1,
            descriptionStringResId = R.string.hero_description1,
            imageDrawableResId = R.drawable.sam,
        ),
        Hero(
            nameStringResId = R.string.hero_name2,
            descriptionStringResId = R.string.hero_description2,
            imageDrawableResId = R.drawable.spinoza,
        ),
        Hero(
            nameStringResId = R.string.hero_name3,
            descriptionStringResId = R.string.hero_description3,
            imageDrawableResId = R.drawable.schindler,
        ),
        Hero(
            nameStringResId = R.string.hero_name4,
            descriptionStringResId = R.string.hero_description4,
            imageDrawableResId = R.drawable.ford,
        ),
        Hero(
            nameStringResId = R.string.hero_name5,
            descriptionStringResId = R.string.hero_description5,
            imageDrawableResId = R.drawable.tim,
        ),
        Hero(
            nameStringResId = R.string.hero_name6,
            descriptionStringResId = R.string.hero_description6,
            imageDrawableResId = R.drawable.wright_brothers,
        ),
        Hero(
            nameStringResId = R.string.hero_name7,
            descriptionStringResId = R.string.hero_description7,
            imageDrawableResId = R.drawable.marie_curie,
        ),
        Hero(
            nameStringResId = R.string.hero_name8,
            descriptionStringResId = R.string.hero_description8,
            imageDrawableResId = R.drawable.tesla,
        ),
    )
}
