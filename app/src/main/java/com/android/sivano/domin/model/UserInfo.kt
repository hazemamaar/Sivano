package com.android.sivano.domin.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val email: String,
    val password: String,
    val image: String,
    val name: String,
    val phone: String,
)
