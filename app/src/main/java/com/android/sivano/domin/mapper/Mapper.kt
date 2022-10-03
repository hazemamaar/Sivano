package com.android.sivano.domin.mapper

import com.android.sivano.domin.model.*
import com.android.sivano.entities.*

fun UserResponse.toUser():User= User(email, id, image, name, phone, token, points, credit)
fun AddCartDto.toAddCartResponse():AddOrRemoveCartModel=AddOrRemoveCartModel( id,ProductCart(product.id,product.image, price = product.price),quantity)
fun GetCartsDto.toGetAllCarts():GetAllCarts= GetAllCarts(cart_items.map{it.toCartItem()},sub_total,total)
fun CartItemDto.toCartItem():CartItem= CartItem(id,product,quantity)
//fun UserInfoDto.toUserInfo():UserInfo=UserInfo(email,password,image,name,phone)