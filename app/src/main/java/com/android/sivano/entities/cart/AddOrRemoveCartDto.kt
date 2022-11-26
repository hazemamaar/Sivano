package com.android.sivano.entities.cart

import com.android.sivano.entities.shared.GetFavoriteOrCartProductDto

data class AddOrRemoveCartDto(
    val id: Int,
    val product: GetFavoriteOrCartProductDto,
    val quantity: Int
)