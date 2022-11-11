package com.android.sivano.domin.usecase

import android.util.Log
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toGetAllFavorite
import com.android.sivano.domin.model.GetAllFavorites
import com.android.sivano.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke():Flow<Resource<GetAllFavorites>> = flow{
        emit(Resource.Loading())
        try {
            Log.d("getFavoritesuseCase", "getFavorites: "+defaultRepo.getFavorites().data.favoriteData.get(0).id)
            val favoriteModel=defaultRepo.getFavorites()
            emit(Resource.Success(favoriteModel.data.toGetAllFavorite()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }

    }
}