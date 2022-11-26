package com.android.sivano.repo

import com.android.sivano.data.remote.ApiService
import com.android.sivano.entities.auth.ChangePasswordOtd
import com.android.sivano.entities.shared.FavOrCartOtd
import com.android.sivano.entities.auth.FcmTokenOtd
import com.android.sivano.entities.auth.LogoutFcmOtd
import com.android.sivano.entities.auth.UserInfoDto
import com.android.sivano.entities.cart.UpdateCartsOtd
import com.android.sivano.entities.homepage.SearchProductOtd
import javax.inject.Inject

class DefaultRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun homePage() = apiService.homePage()
    suspend fun categories() = apiService.categories()
    suspend fun addOrRemoveFavorite(fav: FavOrCartOtd) = apiService.addOrRemoveFavorite(fav = fav)
    suspend fun getFavorites() = apiService.getFavorites()
    suspend fun deleteFromFavorite(id: Int) = apiService.deleteFromFavorite(id = id)
    suspend fun addOrRemoveCart(fav: FavOrCartOtd) = apiService.addOrRemoveToCart(fav = fav)
    suspend fun getAllCarts() = apiService.getCarts()
    suspend fun setFcmToken(fcmToken: FcmTokenOtd)=apiService.setFcmToken(fcmToken)
    suspend fun logout(logoutFcmOtd: LogoutFcmOtd)=apiService.logout(logoutFcmOtd)
    suspend fun profile()=apiService.profile()
    suspend fun updateProfile(userInfoDto: UserInfoDto)=apiService.updateProfile(userInfoDto)
    suspend fun changePassword(changePasswordOtd: ChangePasswordOtd)=apiService.changePassword(changePasswordOtd)
    suspend fun getProduct(id: Int)=apiService.getProduct(id)
    suspend fun searchProduct(searchProductOtd: SearchProductOtd)=apiService.searchProduct(searchProductOtd)
    suspend fun deleteFromCart(id: Int)=apiService.deleteFromCart(id)
    suspend fun updateCart(id: Int,updateCartsOtd: UpdateCartsOtd)=apiService.updateCart(id,updateCartsOtd)


}