package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toUser
import com.android.sivano.domin.model.User
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.data.repo.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserCase @Inject constructor(val authRepo: AuthRepo) {
    operator fun invoke(userInfoDto: com.android.sivano.data.entities.auth.UserInfoDto): Flow<Resource<User>> = flow{
        emit(Resource.Loading())
        try {
            val loginData = authRepo.register(userInfoDto)
            emit(Resource.Success(loginData.data.toUser()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }}
}