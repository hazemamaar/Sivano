package com.android.sivano.entities.cart

data class UpdateOrDeleteCartItemDto(
    val cart: UpdateOrDeleteCartDataDto,
    val sub_total: Int,
    val total: Int
)