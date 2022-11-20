package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.domin.mapper.toLogoutModel
import com.android.sivano.domin.model.LogoutModel
import com.android.sivano.entities.LogoutFcmOtd
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    val defaultRepo: DefaultRepo,
    val complexPreferences: ComplexPreferences
) {


    operator fun invoke(): Flow<Resource<LogoutModel>> = flow {
        emit(Resource.Loading())
        try {
            val logoutModel =
                defaultRepo.logout(LogoutFcmOtd(complexPreferences.getString("fcm_token")))
            emit(Resource.Success(logoutModel.data.toLogoutModel()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}