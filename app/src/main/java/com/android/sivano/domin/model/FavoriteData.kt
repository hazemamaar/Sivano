package com.android.sivano.domin.model

import com.android.sivano.entities.CartOrFavProduct
import com.google.gson.annotations.SerializedName

data class FavoriteData(
    val id: Int,
    val product: FavoriteAndCartProductModel
)
