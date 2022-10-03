package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toGetAllCarts
import com.android.sivano.domin.model.GetAllCarts
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartsUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(token:String) :Flow<Resource<GetAllCarts>> = flow {
        emit(Resource.Loading())
        try {
            val getCarts=defaultRepo.getAllCarts(token)
            emit(Resource.Success(getCarts.data.toGetAllCarts()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }
    }
}