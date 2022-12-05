package com.android.sivano.data.entities.cart

data class UpdateOrDeleteCartItemDto(
    val cart: UpdateOrDeleteCartDataDto,
    val sub_total: Int,
    val total: Int
)