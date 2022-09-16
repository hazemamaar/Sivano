package com.android.sivano.domin.model

import com.android.sivano.model.CartItemDto

data class GetAllCarts(
    val cart_items: List<CartItemDto>,
    val sub_total: Int,
    val total: Int
)
