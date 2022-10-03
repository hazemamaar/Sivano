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
    suspend fun homePage(@Header("lang") lang:String="en",@Header("Authorization") auth:String):MyResponse<HomePageDto>
    @GET("categories")
    suspend fun categories(@Header("lang") lang:String="en"):MyResponse<CategoryModel>
    @POST("favorites")
    suspend fun addToFavorite(@Body fav: Fav, @Header("lang") lang:String="en", @Header("Authorization") auth:String):MyResponse<AddorRemoveFavoriteDto>
    @GET("favorites")
    suspend fun getFavorites(@Header("lang") lang:String="en",@Header("Authorization") auth:String):MyResponse<AllFavoriteProducts>
    @DELETE("favorites/{id}")
    suspend fun deleteFromFavorite(@Path("id") id:Int,@Header("lang") lang:String="en",@Header("Authorization") auth:String):MyResponse<AddorRemoveFavoriteDto>
    @POST("carts")
    suspend fun addOrRemoveToCart(@Body fav: Fav, @Header("lang") lang:String="en", @Header("Authorization") auth:String):MyResponse<AddCartDto>
    @GET("carts")
    suspend fun getCarts(@Header("lang") lang:String="en", @Header("Authorization") auth:String):MyResponse<GetCartsDto>
}