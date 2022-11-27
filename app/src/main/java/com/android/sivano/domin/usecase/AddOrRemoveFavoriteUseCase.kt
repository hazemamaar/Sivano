package com.android.sivano.domin.usecase

import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toAddOrRemoveFavorite
import com.android.sivano.domin.model.AddOrRemoveFavorite
import com.android.sivano.data.entities.shared.FavOrCartOtd
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class AddOrRemoveFavoriteUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke(fav: com.android.sivano.data.entities.shared.FavOrCartOtd): Flow<Resource<AddOrRemoveFavorite>> =flow{
         emit(Resource.Loading())
        try {
            val favoriteModel=defaultRepo.addOrRemoveFavorite(fav)
            emit(Resource.Success(favoriteModel.data.toAddOrRemoveFavorite()))
        }catch(e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }
    }
}