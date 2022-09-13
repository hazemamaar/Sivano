package com.android.sivano.model

import com.google.gson.annotations.SerializedName

data class FavoriteData(
    val id: Int,
    @SerializedName("product")
    val product: FavoriteProducts
)