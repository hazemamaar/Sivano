package com.android.sivano.repo

import com.android.sivano.data.api.ApiService
import com.android.sivano.model.MyResponse
import com.android.sivano.model.UserInfo
import com.android.sivano.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun register(user: UserInfo): MyResponse<UserResponse> = apiService.register(user)
    suspend fun login(user: UserInfo): MyResponse<UserResponse> = apiService.login(user)
}