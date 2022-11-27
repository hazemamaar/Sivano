package com.android.sivano.data.entities.cart

import com.android.sivano.data.entities.shared.GetFavoriteOrCartProductDto

data class AddOrRemoveCartDto(
    val id: Int,
    val product: com.android.sivano.data.entities.shared.GetFavoriteOrCartProductDto,
    val quantity: Int
)