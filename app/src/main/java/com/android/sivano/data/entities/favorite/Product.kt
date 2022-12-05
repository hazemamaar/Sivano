package com.android.sivano.data.entities.favorite

data class Product(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val name: String,
    val old_price: Double,
    val price: Double
)