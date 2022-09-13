package com.android.sivano.model

import android.os.Parcel
import android.os.Parcelable

data class Products(
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
    :Parcelable {
    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }
}