package com.android.sivano.domin.model

import com.android.sivano.entities.Products

data class CartItem(
    val id: Int,
    val product: Products,
    val quantity: Int
)