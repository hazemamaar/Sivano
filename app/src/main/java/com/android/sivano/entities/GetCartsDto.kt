package com.android.sivano.entities

data class GetCartsDto(
    val cart_items: List<CartItemDto>,
    val sub_total: Int,
    val total: Int
)