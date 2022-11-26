package com.android.sivano.domin.mapper

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.sivano.domin.model.*
import com.android.sivano.entities.*
import com.android.sivano.entities.auth.FcmResponseDto
import com.android.sivano.entities.auth.LogoutResponseDto
import com.android.sivano.entities.auth.ProfileDto
import com.android.sivano.entities.auth.UserResponse
import com.android.sivano.entities.cart.AddOrRemoveCartDto
import com.android.sivano.entities.cart.CartItemDto
import com.android.sivano.entities.cart.GetCartsDto
import com.android.sivano.entities.favorite.*
import com.android.sivano.entities.homepage.*
import com.android.sivano.entities.shared.GetAllPagesDto

fun UserResponse.toUser(): User = User(email, id, image, name, phone, token, points, credit)
fun AddOrRemoveCartDto.toAddCartResponse(): AddOrRemoveCartModel = AddOrRemoveCartModel(
    id,
    ProductCart(product.id, product.image, price = product.price),
    quantity
)
fun GetCartsDto.toGetAllCarts(): GetAllCarts =
    GetAllCarts(cart_items.map { it.toCartItem() }, sub_total, total)
fun CartItemDto.toCartItem(): CartItem = CartItem(id, product, quantity)
fun HomePageDto.toHomePage(): HomePageModel =
    HomePageModel(ad, banners.map { it.toBanner() }, products.map { it.toProductsHomePage() })
fun BannerDto.toBanner(): Banner = Banner(id, image)
fun CompleteProductDto.toProductsHomePage(): ProductsHomePage = ProductsHomePage(
    description,
    discount,
    id,
    image,
    images,
    in_cart,
    in_favorites,
    name,
    old_price,
    price
)
fun CategoryModelResponseDto.toCategoryModel(): CategoryModel =
    CategoryModel(current_page, data.map { it.toCategoryItemModel() }, first_page_url)
fun CategoriesDto.toCategoryItemModel(): CategoryItemModel = CategoryItemModel(id, image, name)
fun GetAllPagesDto<FavoriteDataDto>.toGetAllFavorite(): GetAllFavorites<FavoriteData> =
    GetAllFavorites<FavoriteData>(favoriteData = data.map { it.toFavoriteDate() })
fun FavoriteDataDto.toFavoriteDate(): FavoriteData = FavoriteData(
    id,
    FavoriteAndCartProductModel(
        product.description,
        product.discount,
        product.id,
        product.image,
        product.name,
        product.old_price,
        product.price
    )
)
fun AddOrRemoveFavoriteDto.toAddOrRemoveFavorite(): AddOrRemoveFavorite = AddOrRemoveFavorite(
    id,
    FavoriteProduct(product.discount, product.id, product.image, product.old_price, product.price)
)
fun FcmResponseDto.toFcmModel(): FcmModel = FcmModel(created_at, id, token, updated_at, user_id)
fun LogoutResponseDto.toLogoutModel(): LogoutModel = LogoutModel(id, token)
fun ProfileDto.toProfileModel(): ProfileModel = ProfileModel(credit, email, id, image, name, phone, points, token)


fun Fragment.toast(string: String){
    Toast.makeText(requireContext(),string,Toast.LENGTH_LONG).show()
}

fun String.vaid(){

}
//fun FavoriteProductDto.toFavoriteProduct():FavoriteProduct=FavoriteProduct(discount,id,image,old_price,price)
//fun UserInfoDto.toUserInfo():UserInfo=UserInfo(email,password,image,name,phone)