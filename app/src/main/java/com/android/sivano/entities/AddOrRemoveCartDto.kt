package com.android.sivano.entities

data class AddOrRemoveCartDto(
    val id: Int,
    val product: CartOrFavProduct,
    val quantity: Int
)