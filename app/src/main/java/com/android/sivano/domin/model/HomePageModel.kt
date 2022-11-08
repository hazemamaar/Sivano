package com.android.sivano.domin.model

data class HomePageModel(
    val ad: String,
    val banners: List<Banner>,
    val products: List<ProductsHomePage>
)