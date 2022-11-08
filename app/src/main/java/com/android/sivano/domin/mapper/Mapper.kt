package com.android.sivano.domin.mapper

import com.android.sivano.domin.model.*
import com.android.sivano.entities.*

fun UserResponse.toUser():User= User(email, id, image, name, phone, token, points, credit)
fun AddCartDto.toAddCartResponse():AddOrRemoveCartModel=AddOrRemoveCartModel( id,ProductCart(product.id,product.image, price = product.price),quantity)
fun GetCartsDto.toGetAllCarts():GetAllCarts= GetAllCarts(cart_items.map{it.toCartItem()},sub_total,total)
fun CartItemDto.toCartItem():CartItem= CartItem(id,product,quantity)
fun HomePageDto.toHomePage():HomePageModel= HomePageModel(ad, banners.map {it.toBanner()},products.map { it.toProductsHomePage() })
fun BannerDto.toBanner():Banner= Banner(id,image)
fun Products.toProductsHomePage():ProductsHomePage= ProductsHomePage(description,discount,id,image,images,in_cart,in_favorites,name,old_price,price)
fun CategoryModelResponseDto.toCategoryModel():CategoryModel= CategoryModel(current_page,data.map { it.toCategoryItemModel() }
    ,first_page_url)
fun CategoriesDto.toCategoryItemModel():CategoryItemModel= CategoryItemModel(id, image, name)
//fun UserInfoDto.toUserInfo():UserInfo=UserInfo(email,password,image,name,phone)