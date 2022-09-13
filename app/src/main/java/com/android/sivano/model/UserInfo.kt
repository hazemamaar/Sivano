package com.android.sivano.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    val image: String,
    val name: String,
    val phone: String,

)