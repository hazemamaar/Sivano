package com.android.sivano.model

data class AddCartDto(
    val id: Int,
    val product: CartOrFavProduct,
    val quantity: Int
)