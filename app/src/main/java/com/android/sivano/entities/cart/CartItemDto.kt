package com.android.sivano.entities.cart

import com.android.sivano.entities.homepage.CompleteProductDto

data class CartItemDto(
    val id: Int,
    val product: CompleteProductDto,
    val quantity: Int
)