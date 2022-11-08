package com.android.sivano.domin.usecase

import android.util.Log
import com.android.sivano.domin.model.HomePageModel
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toHomePage

class HomeUseCase @Inject constructor(val defaultRepo: DefaultRepo){
    operator fun invoke():Flow<Resource<HomePageModel>> = flow{
        emit(Resource.Loading())
        try {
           val homeData=defaultRepo.homePage()
            emit(Resource.Success(homeData.data.toHomePage()))
        }catch (e:Exception){
            Log.i("EX", "invoke: ")
        }
    }
}