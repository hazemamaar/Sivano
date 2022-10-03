package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toAddCartResponse
import com.android.sivano.domin.model.AddOrRemoveCartModel
import com.android.sivano.entities.Fav
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddOrRemoveCartUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(fav:Fav,token:String):Flow<Resource<AddOrRemoveCartModel>> = flow {
        emit(Resource.Loading())
        try {
            val addOrRemoveCartData= defaultRepo.addOrRemoveCart(fav,token)
            emit(Resource.Success(addOrRemoveCartData.data.toAddCartResponse()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }

    }
}