package com.android.sivano.data.entities.cart

import com.android.sivano.data.entities.homepage.CompleteProductDto

data class CartItemDto(
    val id: Int,
    val product: com.android.sivano.data.entities.homepage.CompleteProductDto,
    val quantity: Int
)