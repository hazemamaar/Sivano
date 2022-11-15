package com.android.sivano.domin.model

data class ProductsHomePage(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val images: List<String>,
    val in_cart: Boolean,
    var in_favorites: Boolean,
    val name: String,
    val old_price: Double,
    val price: Double
)