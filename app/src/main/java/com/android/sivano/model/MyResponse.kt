package com.android.sivano.model

data class MyResponse <T>(
    val data: T,
    val message: String,
    val status: Boolean
)