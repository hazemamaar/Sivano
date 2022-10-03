package com.android.sivano.entities

import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    val image: String,
    val name: String,
    val phone: String,

)