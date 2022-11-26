package com.android.sivano.entities.favorite

import com.android.sivano.entities.shared.GetFavoriteOrCartProductDto
import com.google.gson.annotations.SerializedName

data class FavoriteDataDto(
    val id: Int,
    @SerializedName("product")
    val product: GetFavoriteOrCartProductDto
)