package com.android.sivano.entities.auth

data class ChangePasswordOtd(
    val current_password: String,
    val new_password: String
)