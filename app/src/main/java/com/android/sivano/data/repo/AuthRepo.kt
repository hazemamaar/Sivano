package com.android.sivano.data.repo

import com.android.sivano.data.remote.ApiService
import com.android.sivano.data.entities.shared.MyResponse
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.data.entities.auth.UserResponse
import javax.inject.Inject

class AuthRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun register(userDto: com.android.sivano.data.entities.auth.UserInfoDto): com.android.sivano.data.entities.shared.MyResponse<com.android.sivano.data.entities.auth.UserResponse> = apiService.register(userDto)
    suspend fun login(userDto: com.android.sivano.data.entities.auth.UserInfoDto): com.android.sivano.data.entities.shared.MyResponse<com.android.sivano.data.entities.auth.UserResponse> = apiService.login(userDto)
}