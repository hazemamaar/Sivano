package com.android.sivano.entities.shared

data class MyResponse <T>(
    val data: T,
    val message: String,
    val status: Boolean
)