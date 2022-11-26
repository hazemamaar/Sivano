package com.android.sivano.domin.model

data class GetAllCarts(
    val cart_items: List<CartItem>,
    val sub_total: Double,
    val total: Double
)
