package com.android.sivano.domin.model

import com.android.sivano.entities.FavoriteProductDto

data class AddOrRemoveFavorite(
    val id: Int,
    val product: FavoriteProduct
)
