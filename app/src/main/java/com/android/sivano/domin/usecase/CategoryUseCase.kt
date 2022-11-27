package com.android.sivano.domin.usecase

import android.util.Log
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.mapper.toCategoryModel
import com.android.sivano.domin.model.CategoryItemModel
import com.android.sivano.domin.model.CategoryModel
import com.android.sivano.data.repo.DefaultRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(val defaultRepo: DefaultRepo) {
    operator fun invoke():Flow<Resource<CategoryModel>> = flow{
        emit(Resource.Loading())
        try {
            val categoryData=defaultRepo.categories()
            Log.i("useCase", "invoke: "+categoryData.data.toCategoryModel().listCategory.get(1).name)
            emit(Resource.Success(categoryData.data.toCategoryModel()))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Unknown error occurred"))
        }
    }
}