package com.android.sivano.data.api

import com.android.sivano.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("register")
    suspend fun register(@Body userInfo:UserInfo, @Header("lang") lang:String="ar"): MyResponse<UserResponse>
    @POST("login")
    suspend fun login(@Body userInfo: UserInfo,@Header("lang") lang:String="ar"):MyResponse<UserResponse>
//    @POST("verify-email")
////    suspend fun verifyEmail(@Body email:String,@Header("lang") lang: String="ar"):MyResponse<>
    @GET("home")
    suspend fun homePage(@Header("lang") lang:String="en",@Header("Authorization") auth:String):MyResponse<HomePage>
    @GET("categories")
    suspend fun categories(@Header("lang") lang:String="en"):MyResponse<CategoryModel>
    @POST("favorites")
    suspend fun addToFavorite(@Body fav: Fav, @Header("lang") lang:String="en", @Header("Authorization") auth:String):MyResponse<AddFavoriteModel>
    @GET("favorites")
    suspend fun getFavorites(@Header("lang") lang:String="en",@Header("Authorization") auth:String):MyResponse<AllFavoriteProducts>
    @DELETE("favorites/{id}")
    suspend fun deleteFromFavorite(@Path("id") id:Int,@Header("lang") lang:String="en",@Header("Authorization") auth:String):MyResponse<AddFavoriteModel>
}