package com.android.sivano.model

data class GetCartsDto(
    val cart_items: List<CartItemDto>,
    val sub_total: Int,
    val total: Int
)