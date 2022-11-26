package com.android.sivano.entities.shared

data class GetFavoriteOrCartProductDto(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val name: String,
    val old_price: Double,
    val price: Double
)