package com.android.sivano.repo

import com.android.sivano.data.api.ApiService
import com.android.sivano.model.Fav
import javax.inject.Inject

class DefaultRepo @Inject constructor(private val apiService: ApiService){
    suspend fun homePage(token:String)=apiService.homePage(auth=token)
    suspend fun categories()=apiService.categories()
    suspend fun addToFavorite(fav: Fav, token:String)=apiService.addToFavorite(auth=token, fav = fav)
    suspend fun getFavorites(token:String)=apiService.getFavorites(auth = token)
    suspend fun deleteFromFavorite(id: Int, token:String)=apiService.deleteFromFavorite(auth=token, id = id)
    suspend fun addOrRemoveCart(fav: Fav, token:String)=apiService.addOrRemoveToCart(auth=token, fav = fav)
    suspend fun  getAllCarts(token: String)=apiService.getCarts(auth = token)
}