package com.android.sivano.entities

import com.google.gson.annotations.SerializedName

data class AllFavoriteProducts(
    val current_page: Int,
    @SerializedName("data")
    val favoriteData: List<FavoriteData>,
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