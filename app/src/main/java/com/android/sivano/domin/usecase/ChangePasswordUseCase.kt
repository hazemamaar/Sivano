package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.auth.ChangePasswordOtd
import com.android.sivano.data.entities.auth.ChangePasswordResponseDto
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(val defaultRepo: DefaultRepo) {

    operator fun invoke( changePasswordOtd: ChangePasswordOtd): Flow<Resource<ChangePasswordResponseDto>> =flow{
        emit(Resource.Loading())
        try{
            val response=defaultRepo.changePassword(changePasswordOtd)
            emit(Resource.Success(response.data))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}