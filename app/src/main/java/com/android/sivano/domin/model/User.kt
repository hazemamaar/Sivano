package com.android.sivano.domin.model

import com.google.gson.annotations.SerializedName

data class User(
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val token: String,
    val points: Int,
    val credit: Int,
)
