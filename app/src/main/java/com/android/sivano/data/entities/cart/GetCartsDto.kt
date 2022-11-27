package com.android.sivano.data.entities.cart

import com.android.sivano.data.entities.cart.CartItemDto

data class GetCartsDto(
    val cart_items: List<com.android.sivano.data.entities.cart.CartItemDto>,
    val sub_total: Double,
    val total: Double
)