package com.android.sivano.repo

import com.android.sivano.data.remote.ApiService
import com.android.sivano.entities.Fav
import com.android.sivano.entities.FcmTokenOtd
import com.android.sivano.entities.LogoutFcmOtd
import javax.inject.Inject

class DefaultRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun homePage() = apiService.homePage()
    suspend fun categories() = apiService.categories()
    suspend fun addOrRemoveFavorite(fav: Fav) = apiService.addOrRemoveFavorite(fav = fav)
    suspend fun getFavorites() = apiService.getFavorites()
    suspend fun deleteFromFavorite(id: Int) = apiService.deleteFromFavorite(id = id)
    suspend fun addOrRemoveCart(fav: Fav) = apiService.addOrRemoveToCart(fav = fav)
    suspend fun getAllCarts() = apiService.getCarts()
    suspend fun setFcmToken(fcmToken: FcmTokenOtd)=apiService.setFcmToken(fcmToken)
    suspend fun logout(logoutFcmOtd: LogoutFcmOtd)=apiService.logout(logoutFcmOtd)
    suspend fun profile()=apiService.profile()
}