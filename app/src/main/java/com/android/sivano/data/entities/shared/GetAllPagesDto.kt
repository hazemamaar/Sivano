package com.android.sivano.data.entities.shared

import com.android.sivano.data.entities.favorite.FavoriteDataDto
import com.google.gson.annotations.SerializedName

data class GetAllPagesDto(
    val current_page: Int,
    @SerializedName("data")
    val data: List<FavoriteDataDto>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: Any,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)