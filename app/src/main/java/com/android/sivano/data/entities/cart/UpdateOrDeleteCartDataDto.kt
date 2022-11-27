package com.android.sivano.data.entities.cart

import com.android.sivano.data.entities.shared.CartOrFavoriteProductDto

data class UpdateOrDeleteCartDataDto(
    val id: Int,
    val product: com.android.sivano.data.entities.shared.CartOrFavoriteProductDto,
    val quantity: Int
)