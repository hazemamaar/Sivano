package com.android.sivano.domin.model

import android.os.Parcel
import android.os.Parcelable

data class ProductsHomePage(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val images: List<String>,
    val in_cart: Boolean,
    val in_favorites: Boolean,
    val name: String,
    val old_price: Double,
    val price: Double
)