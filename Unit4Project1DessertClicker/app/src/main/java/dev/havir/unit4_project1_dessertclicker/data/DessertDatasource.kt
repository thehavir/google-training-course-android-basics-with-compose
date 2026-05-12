package dev.havir.unit4_project1_dessertclicker.data

import dev.havir.unit4_project1_dessertclicker.R

object DessertDatasource {
    val desserts = listOf(
        Dessert(
            imageDrawableResId = R.drawable.cupcake,
            titleStringResId = R.string.dessert_cupcake,
            price = 5.0,
            startProductionAmount = 0,
        ), Dessert(
            imageDrawableResId = R.drawable.donut,
            titleStringResId = R.string.dessert_donut,
            price = 10.0,
            startProductionAmount = 5,
        ), Dessert(
            imageDrawableResId = R.drawable.eclair,
            titleStringResId = R.string.dessert_eclair,
            price = 15.0,
            startProductionAmount = 20,
        ), Dessert(
            imageDrawableResId = R.drawable.froyo,
            titleStringResId = R.string.dessert_froyo,
            price = 30.0,
            startProductionAmount = 50,
        ), Dessert(
            imageDrawableResId = R.drawable.gingerbread,
            titleStringResId = R.string.dessert_gingerbread,
            price = 50.0,
            startProductionAmount = 100,
        ), Dessert(
            imageDrawableResId = R.drawable.honeycomb,
            titleStringResId = R.string.dessert_honeycomb,
            price = 100.0,
            startProductionAmount = 200,
        ), Dessert(
            imageDrawableResId = R.drawable.icecreamsandwich,
            titleStringResId = R.string.dessert_icecreamsandwich,
            price = 500.0,
            startProductionAmount = 500,
        ), Dessert(
            imageDrawableResId = R.drawable.jellybean,
            titleStringResId = R.string.dessert_jellybean,
            price = 1000.0,
            startProductionAmount = 1000,
        ), Dessert(
            imageDrawableResId = R.drawable.kitkat,
            titleStringResId = R.string.dessert_kitkat,
            price = 2000.0,
            startProductionAmount = 2000,
        ), Dessert(
            imageDrawableResId = R.drawable.lollipop,
            titleStringResId = R.string.dessert_lollipop,
            price = 3000.0,
            startProductionAmount = 4000,
        ), Dessert(
            imageDrawableResId = R.drawable.marshmallow,
            titleStringResId = R.string.dessert_marshmallow,
            price = 4000.0,
            startProductionAmount = 8000,
        ), Dessert(
            imageDrawableResId = R.drawable.nougat,
            titleStringResId = R.string.dessert_nougat,
            price = 5000.0,
            startProductionAmount = 16000,
        ), Dessert(
            imageDrawableResId = R.drawable.oreo,
            titleStringResId = R.string.dessert_oreo,
            price = 6000.0,
            startProductionAmount = 20000,
        )
    )
}
