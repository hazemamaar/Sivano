package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.cart.UpdateCartsOtd
import com.android.sivano.data.entities.cart.UpdateOrDeleteCartItemDto
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(id:Int,updateCartsOtd: UpdateCartsOtd) : Flow<Resource<UpdateOrDeleteCartItemDto>> = flow{
           emit(Resource.Loading())
        try {
            emit(Resource.Success(defaultRepo.updateCart(id,updateCartsOtd).data))
        }catch(e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }

    }
}