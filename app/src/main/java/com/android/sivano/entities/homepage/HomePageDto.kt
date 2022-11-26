package com.android.sivano.entities.homepage

data class HomePageDto(
    val ad: String,
    val banners: List<BannerDto>,
    val products: List<CompleteProductDto>
)