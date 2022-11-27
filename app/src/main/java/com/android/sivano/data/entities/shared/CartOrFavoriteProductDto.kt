package com.android.sivano.data.entities.shared

data class CartOrFavoriteProductDto(
    val discount: Int,
    val id: Int,
    val image: String,
    val old_price: Double,
    val price: Double
)