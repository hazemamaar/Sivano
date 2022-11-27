package com.android.sivano.data.entities.homepage

data class CategoryModelResponseDto(
    val current_page: Int,
    val data: List<com.android.sivano.data.entities.homepage.CategoriesDto>,
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