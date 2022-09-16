package com.android.sivano.domin.mapper

import com.android.sivano.domin.model.*
import com.android.sivano.model.AddCartDto
import com.android.sivano.model.GetCartsDto
import com.android.sivano.model.UserResponse

fun UserResponse.toUser():User= User(email, id, image, name, phone, token, points, credit)
fun AddCartDto.toAddCartResponse():AddOrRemoveCartModel=AddOrRemoveCartModel( id,ProductCart(product.id,product.image, price = product.price),quantity)
fun GetCartsDto.toGetAllCarts():GetAllCarts= GetAllCarts(cart_items,sub_total,total)