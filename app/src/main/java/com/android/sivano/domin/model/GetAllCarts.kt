package com.android.sivano.domin.model

import com.android.sivano.entities.CartItemDto

data class GetAllCarts(
    val cart_items: List<CartItem>,
    val sub_total: Int,
    val total: Int
)
