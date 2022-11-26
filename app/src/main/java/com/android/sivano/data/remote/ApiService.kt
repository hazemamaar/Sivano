package com.android.sivano.data.remote

import com.android.sivano.entities.*
import com.android.sivano.entities.auth.*
import com.android.sivano.entities.cart.AddOrRemoveCartDto
import com.android.sivano.entities.cart.GetCartsDto
import com.android.sivano.entities.cart.UpdateCartsOtd
import com.android.sivano.entities.cart.UpdateOrDeleteCartItemDto
import com.android.sivano.entities.favorite.*
import com.android.sivano.entities.homepage.CategoryModelResponseDto
import com.android.sivano.entities.homepage.HomePageDto
import com.android.sivano.entities.homepage.CompleteProductDto
import com.android.sivano.entities.homepage.SearchProductOtd
import com.android.sivano.entities.shared.GetFavoriteOrCartProductDto
import com.android.sivano.entities.shared.FavOrCartOtd
import com.android.sivano.entities.shared.GetAllPagesDto
import com.android.sivano.entities.shared.MyResponse
import retrofit2.http.*

interface ApiService {

    @POST("register")
    suspend fun register(@Body userInfoDto: UserInfoDto): MyResponse<UserResponse>
    @POST("login")
    suspend fun login(@Body userInfoDto: UserInfoDto): MyResponse<UserResponse>
//    @POST("verify-email")
////    suspend fun verifyEmail(@Body email:String,@Header("lang") lang: String="ar"):MyResponse<>
    @GET("home")
    suspend fun homePage(): MyResponse<HomePageDto>
    @GET("categories")
    suspend fun categories(): MyResponse<CategoryModelResponseDto>
    @POST("favorites")
    suspend fun addOrRemoveFavorite(@Body fav: FavOrCartOtd): MyResponse<AddOrRemoveFavoriteDto>
    @GET("favorites")
    suspend fun getFavorites(): MyResponse<GetAllPagesDto<FavoriteDataDto>>
    @DELETE("favorites/{id}")
    suspend fun deleteFromFavorite(@Path("id") id:Int): MyResponse<AddOrRemoveFavoriteDto>
    @POST("carts")
    suspend fun addOrRemoveToCart(@Body fav: FavOrCartOtd): MyResponse<AddOrRemoveCartDto>
    @GET("carts")
    suspend fun getCarts(): MyResponse<GetCartsDto>
    @POST("fcm-token")
    suspend fun setFcmToken(@Body fcmToken: FcmTokenOtd): MyResponse<FcmResponseDto>
    @POST("logout")
    suspend fun logout(@Body logoutFcmToken: LogoutFcmOtd): MyResponse<LogoutResponseDto>
    @GET("profile")
    suspend fun profile(): MyResponse<ProfileDto>
    @PUT("update-profile")
    suspend fun updateProfile(@Body userInfoDto: UserInfoDto): MyResponse<ProfileDto>
    @POST("change-password")
    suspend fun changePassword(@Body changePasswordOtd: ChangePasswordOtd): MyResponse<ChangePasswordResponseDto>
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): MyResponse<CompleteProductDto>
    @POST("products/search")
    suspend fun searchProduct(otd: SearchProductOtd): MyResponse<GetAllPagesDto<GetFavoriteOrCartProductDto>>
    @DELETE("carts/{id}")
    suspend fun deleteFromCart(@Path("id") id:Int): MyResponse<UpdateOrDeleteCartItemDto>
    @PUT("carts/{id}")
    suspend fun updateCart(@Path("id") id:Int,@Body updateCartsOtd: UpdateCartsOtd): MyResponse<UpdateOrDeleteCartItemDto>
}