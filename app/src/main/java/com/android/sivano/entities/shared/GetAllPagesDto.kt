package com.android.sivano.entities.shared

import com.google.gson.annotations.SerializedName

data class GetAllPagesDto<T>(
    val current_page: Int,
    @SerializedName("data")
    val data: List<T>,
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