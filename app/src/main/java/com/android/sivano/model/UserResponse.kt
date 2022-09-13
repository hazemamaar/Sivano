package com.android.sivano.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val token: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("credit")
    val credit: Int,
)