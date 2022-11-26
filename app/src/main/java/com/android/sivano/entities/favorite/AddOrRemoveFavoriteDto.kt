package com.android.sivano.entities.favorite

import com.android.sivano.entities.shared.CartOrFavoriteProductDto

data class AddOrRemoveFavoriteDto(
    val id: Int,
    val product: CartOrFavoriteProductDto
)