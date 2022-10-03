package com.android.sivano.entities

data class HomePageDto(
    val ad: String,
    val banners: List<BannerDto>,
    val products: List<Products>
)