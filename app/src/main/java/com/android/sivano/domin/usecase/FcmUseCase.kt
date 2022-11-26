package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toFcmModel
import com.android.sivano.domin.model.FcmModel
import com.android.sivano.entities.auth.FcmTokenOtd
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FcmUseCase @Inject constructor(val defaultRepo: DefaultRepo){
operator fun invoke(fcmToken: FcmTokenOtd):Flow<Resource<FcmModel>> = flow {
    emit(Resource.Loading())
    try {
        val fcmModel=defaultRepo.setFcmToken(fcmToken)
        emit(Resource.Success(fcmModel.data.toFcmModel()))
    }catch (e:Exception){
        emit(Resource.Error(e.localizedMessage as String))
    }
}

}