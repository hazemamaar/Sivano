package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toAddCartResponse
import com.android.sivano.domin.model.AddOrRemoveCartModel
import com.android.sivano.data.entities.shared.FavOrCartOtd
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddOrRemoveCartUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(fav: com.android.sivano.data.entities.shared.FavOrCartOtd):Flow<Resource<AddOrRemoveCartModel>> = flow {
        emit(Resource.Loading())
        try {
            val addOrRemoveCartData= defaultRepo.addOrRemoveCart(fav)
            emit(Resource.Success(addOrRemoveCartData.data.toAddCartResponse()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }
    }
}