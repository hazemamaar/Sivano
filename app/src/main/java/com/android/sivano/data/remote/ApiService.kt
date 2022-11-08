package com.android.sivano.data.remote

import com.android.sivano.entities.*
import retrofit2.http.*

interface ApiService {

    @POST("register")
    suspend fun register(@Body userInfoDto:UserInfoDto): MyResponse<UserResponse>
    @POST("login")
    suspend fun login(@Body userInfoDto: UserInfoDto):MyResponse<UserResponse>
//    @POST("verify-email")
////    suspend fun verifyEmail(@Body email:String,@Header("lang") lang: String="ar"):MyResponse<>
    @GET("home")
    suspend fun homePage():MyResponse<HomePageDto>
    @GET("categories")
    suspend fun categories():MyResponse<CategoryModelResponseDto>
    @POST("favorites")
    suspend fun addToFavorite(@Body fav: Fav):MyResponse<AddorRemoveFavoriteDto>
    @GET("favorites")
    suspend fun getFavorites():MyResponse<AllFavoriteProducts>
    @DELETE("favorites/{id}")
    suspend fun deleteFromFavorite(@Path("id") id:Int):MyResponse<AddorRemoveFavoriteDto>
    @POST("carts")
    suspend fun addOrRemoveToCart(@Body fav: Fav):MyResponse<AddCartDto>
    @GET("carts")
    suspend fun getCarts():MyResponse<GetCartsDto>
}