package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.data.repo.DefaultRepo
import com.android.sivano.domin.mapper.toUser
import com.android.sivano.domin.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class UpdateUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(userInfo:UserInfoDto): Flow<Resource<User>> =flow {
        emit(Resource.Loading())
        try {
            val profile=defaultRepo.updateProfile(userInfo)
            emit(Resource.Success(profile.data.toUser()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }

}