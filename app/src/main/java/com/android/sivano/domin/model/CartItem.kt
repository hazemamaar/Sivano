package com.android.sivano.domin.model

import com.android.sivano.model.Product

data class CartItem(
    val id: Int,
    val product: Product,
    val quantity: Int
)