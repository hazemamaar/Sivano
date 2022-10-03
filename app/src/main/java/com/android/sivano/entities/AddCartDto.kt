package com.android.sivano.entities

data class AddCartDto(
    val id: Int,
    val product: CartOrFavProduct,
    val quantity: Int
)