package com.android.sivano.entities.cart

import com.android.sivano.entities.cart.CartItemDto

data class GetCartsDto(
    val cart_items: List<CartItemDto>,
    val sub_total: Double,
    val total: Double
)