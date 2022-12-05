package com.android.sivano.domin.usecase

import android.util.Log
import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.cart.UpdateOrDeleteCartItemDto
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFromCartUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(id:Int) : Flow<Resource<UpdateOrDeleteCartItemDto>> = flow{
        emit(Resource.Loading())
        try {
            Log.e("deleteuseCase", "invoke:${defaultRepo.deleteFromCart(id).data} ", )
            emit(Resource.Success(defaultRepo.deleteFromCart(id).data))
        }catch(e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }

    }
}