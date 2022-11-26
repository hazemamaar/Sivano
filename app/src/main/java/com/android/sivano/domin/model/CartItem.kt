package com.android.sivano.domin.model

import com.android.sivano.entities.homepage.CompleteProductDto

data class CartItem(
    val id: Int,
    val product: CompleteProductDto,
    val quantity: Int
)