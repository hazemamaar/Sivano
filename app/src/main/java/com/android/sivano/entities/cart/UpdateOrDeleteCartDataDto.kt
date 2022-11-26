package com.android.sivano.entities.cart

import com.android.sivano.entities.shared.CartOrFavoriteProductDto

data class UpdateOrDeleteCartDataDto(
    val id: Int,
    val product: CartOrFavoriteProductDto,
    val quantity: Int
)