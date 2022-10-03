package com.android.sivano.repo

import com.android.sivano.data.remote.ApiService
import com.android.sivano.entities.MyResponse
import com.android.sivano.entities.UserInfoDto
import com.android.sivano.entities.UserResponse
import javax.inject.Inject

class AuthRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun register(userDto: UserInfoDto): MyResponse<UserResponse> = apiService.register(userDto)
    suspend fun login(userDto: UserInfoDto): MyResponse<UserResponse> = apiService.login(userDto)
}