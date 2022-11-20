package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toProfileModel
import com.android.sivano.domin.model.ProfileModel
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(): Flow<Resource<ProfileModel>> = flow {
        emit(Resource.Loading())
        try {
            val profileModel=defaultRepo.profile()
            emit(Resource.Success(profileModel.data.toProfileModel()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}