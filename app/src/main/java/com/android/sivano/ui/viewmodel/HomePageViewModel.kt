package com.android.sivano.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.entities.*
import com.android.sivano.repo.DefaultRepo
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.model.AddOrRemoveCartModel
import com.android.sivano.domin.model.AddOrRemoveFavorite
import com.android.sivano.domin.model.CategoryModel
import com.android.sivano.domin.model.HomePageModel
import com.android.sivano.domin.usecase.AddOrRemoveCartUseCase
import com.android.sivano.domin.usecase.AddOrRemoveFavoriteUseCase
import com.android.sivano.domin.usecase.CategoryUseCase
import com.android.sivano.domin.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    @ApplicationContext val context: Context
    ,private val defaultRepo: DefaultRepo
    ,private val addOrRemoveCartUseCase: AddOrRemoveCartUseCase
    ,private val homeUseCase: HomeUseCase
    ,private val categoryUseCase: CategoryUseCase
    ,private val addOrRemoveFavoriteUseCase: AddOrRemoveFavoriteUseCase
) : ViewModel() {
    var homeMutableLiveData: MutableLiveData<Resource<HomePageModel>> = MutableLiveData()
    var categoriesMutableLiveData: MutableLiveData<Resource<CategoryModel>> = MutableLiveData()
    var addFavoriteMutableLiveData: MutableLiveData<Resource<AddOrRemoveFavorite>> = MutableLiveData()
    var addOrRemoveCartMutableLiveData: MutableLiveData<Resource<AddOrRemoveCartModel>> = MutableLiveData()
    var deleteFavoriteMutableLiveData: MutableLiveData<Resource<MyResponse<AddOrRemoveFavoriteDto>>> = MutableLiveData()
    fun addToFavorite(fav: Fav){
        addOrRemoveFavoriteUseCase(fav).onEach {
            addFavoriteMutableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }
    fun addToCart(fav:Fav){
        addOrRemoveCartUseCase(fav).onEach {
            addOrRemoveCartMutableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }
    fun deleteFromFavorite(token:String,id: Int){
        viewModelScope.launch {
            val response=defaultRepo.deleteFromFavorite(id)
            deleteFavoriteMutableLiveData.postValue(handleDeleteFavorite(response))
        }
    }
    private fun handleDeleteFavorite(response: MyResponse<AddOrRemoveFavoriteDto>) : Resource<MyResponse<AddOrRemoveFavoriteDto>> {
        if (response.status) {
            response?.let { favResponse ->
                return Resource.Success(favResponse)
            }
        }
        return Resource.Error(response.message)
    }
    fun homePage(){
        homeUseCase().onEach {
            homeMutableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun categories()=viewModelScope.launch {
        categoryUseCase().onEach {
            Log.i("getCategories", "categories: ")
            categoriesMutableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }

//    fun banners()=viewModelScope.launch {
//        homeUseCase().onEach {
//            bannerMutableLiveData.postValue(it.data?.banners)
//        }
//    }
}