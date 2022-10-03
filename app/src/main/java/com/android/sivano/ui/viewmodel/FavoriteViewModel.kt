package com.android.sivano.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.entities.AllFavoriteProducts
import com.android.sivano.entities.MyResponse
import com.android.sivano.repo.DefaultRepo
import com.android.sivano.common.uitil.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(@ApplicationContext val context: Context
                        ,private val defaultRepo: DefaultRepo) : ViewModel() {
    var favoriteMutableLiveData: MutableLiveData<Resource<MyResponse<AllFavoriteProducts>>> = MutableLiveData()
    fun getFavorites(token:String){
        viewModelScope.launch {
            val response=defaultRepo.getFavorites(token)
            favoriteMutableLiveData.postValue(handleAddFavorite(response))
        }
    }
    private fun handleAddFavorite(response: MyResponse<AllFavoriteProducts>): Resource<MyResponse<AllFavoriteProducts>> {
        if (response.status) {
            response?.let { newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message)
    }
}