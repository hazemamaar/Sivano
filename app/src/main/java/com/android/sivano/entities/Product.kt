package com.android.sivano.entities

data class Product(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val images: List<String>,
    val in_cart: Boolean,
    val in_favorites: Boolean,
    val name: String,
    val old_price: Int,
    val price: Int
)