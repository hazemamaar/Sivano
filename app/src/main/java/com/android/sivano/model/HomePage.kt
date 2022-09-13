package com.android.sivano.model

data class HomePage(
    val ad: String,
    val banners: List<Banner>,
    val products: List<Products>
)