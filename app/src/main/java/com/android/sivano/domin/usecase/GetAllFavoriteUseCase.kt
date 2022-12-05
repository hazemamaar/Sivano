package com.android.sivano.domin.usecase

import android.util.Log
import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.favorite.FavTest
import com.android.sivano.data.entities.shared.GetAllPagesDto
import com.android.sivano.domin.mapper.toGetAllFavorite
import com.android.sivano.domin.model.FavoriteData
import com.android.sivano.domin.model.GetAllFavorites
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke():Flow<Resource<FavTest>> = flow{
        emit(Resource.Loading())
        try {
            val favoriteModel=defaultRepo.getFavorites()
            emit(Resource.Success(favoriteModel.data))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }

    }
}