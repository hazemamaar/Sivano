package com.android.sivano.data.entities.homepage

data class HomePageDto(
    val ad: String,
    val banners: List<com.android.sivano.data.entities.homepage.BannerDto>,
    val products: List<com.android.sivano.data.entities.homepage.CompleteProductDto>
)