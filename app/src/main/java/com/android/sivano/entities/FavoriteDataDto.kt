package com.android.sivano.entities

import com.google.gson.annotations.SerializedName

data class FavoriteDataDto(
    val id: Int,
    @SerializedName("product")
    val product: CartOrFavProduct
)