package com.android.sivano.data.entities.favorite

import com.android.sivano.data.entities.shared.GetFavoriteOrCartProductDto
import com.google.gson.annotations.SerializedName

data class FavoriteDataDto(
    val id: Int,
    @SerializedName("product")
    val product: GetFavoriteOrCartProductDto
)