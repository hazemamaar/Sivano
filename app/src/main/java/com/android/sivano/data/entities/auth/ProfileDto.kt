package com.android.sivano.data.entities.auth

data class ProfileDto(
    val credit: Int,
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val points: Int,
    val token: String
)