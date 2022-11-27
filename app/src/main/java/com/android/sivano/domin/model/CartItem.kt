package com.android.sivano.domin.model

import com.android.sivano.data.entities.homepage.CompleteProductDto

data class CartItem(
    val id: Int,
    val product: com.android.sivano.data.entities.homepage.CompleteProductDto,
    val quantity: Int
)