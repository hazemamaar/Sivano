package com.android.sivano.entities

data class FavoriteProductDto(
    val discount: Int,
    val id: Int,
    val image: String,
    val old_price: Double,
    val price: Double
)