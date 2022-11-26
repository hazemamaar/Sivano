package com.android.sivano.entities.auth

data class FcmResponseDto(
    val created_at: String,
    val id: Int,
    val token: String,
    val updated_at: String,
    val user_id: Int
)