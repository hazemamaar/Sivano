package com.android.sivano.domin.usecase

import com.android.sivano.domin.mapper.toUser
import com.android.sivano.domin.model.User
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.data.repo.AuthRepo
import com.android.sivano.common.uitil.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class LoginUseCase @Inject constructor(val authRepo: AuthRepo){
    operator fun invoke(userInfoDto:UserInfoDto):Flow<Resource<User>> =flow{
        emit(Resource.Loading())
        try {
            val loginData = authRepo.login(userInfoDto)
            emit(Resource.Success(loginData.data.toUser()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }
    }


}