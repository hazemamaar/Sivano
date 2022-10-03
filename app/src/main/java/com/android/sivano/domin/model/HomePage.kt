package com.android.sivano.domin.model

data class HomePage(
    val ad: String,
    val banners: List<Banner>,
    val products: List<ProductsHomePage>
)