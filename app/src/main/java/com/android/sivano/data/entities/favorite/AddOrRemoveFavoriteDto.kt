package com.android.sivano.data.entities.favorite

import com.android.sivano.data.entities.shared.CartOrFavoriteProductDto

data class AddOrRemoveFavoriteDto(
    val id: Int,
    val product: CartOrFavoriteProductDto
)