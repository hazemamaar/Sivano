package com.android.sivano.model

data class CartOrFavProduct(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val name: String,
    val old_price: Int,
    val price: Int
)