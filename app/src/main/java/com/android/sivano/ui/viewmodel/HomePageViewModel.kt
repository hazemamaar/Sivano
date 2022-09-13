package com.android.sivano.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.model.*
import com.android.sivano.repo.DefaultRepo
import com.android.sivano.common.uitil.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    @ApplicationContext val context: Context
    ,private val defaultRepo: DefaultRepo

) : ViewModel() {
    var homeMutableLiveData: MutableLiveData<Resource<MyResponse<HomePage>>> = MutableLiveData()
    var categoriesMutableLiveData: MutableLiveData<Resource<MyResponse<CategoryModel>>> = MutableLiveData()
    var addFavoriteMutableLiveData: MutableLiveData<Resource<MyResponse<AddFavoriteModel>>> = MutableLiveData()
    var deleteFavoriteMutableLiveData: MutableLiveData<Resource<MyResponse<AddFavoriteModel>>> = MutableLiveData()
    fun addToFavorite(token:String,fav: Fav){
        viewModelScope.launch {
            val response=defaultRepo.addToFavorite(fav,token)
            addFavoriteMutableLiveData.postValue(handleAddFavorite(response))
        }
    }
    private fun handleAddFavorite(response: MyResponse<AddFavoriteModel>): Resource<MyResponse<AddFavoriteModel>> {
        if (response.status) {
            response?.let { favResponse ->
                return Resource.Success(favResponse)
            }
        }
        return Resource.Error(response.message)
    }
    fun deleteFromFavorite(token:String,id: Int){
        viewModelScope.launch {
            val response=defaultRepo.deleteFromFavorite(id,token)
            deleteFavoriteMutableLiveData.postValue(handleDeleteFavorite(response))
        }
    }
    private fun handleDeleteFavorite(response: MyResponse<AddFavoriteModel>): Resource<MyResponse<AddFavoriteModel>> {
        if (response.status) {
            response?.let { favResponse ->
                return Resource.Success(favResponse)
            }
        }
        return Resource.Error(response.message)
    }
    fun homePage(token:String)=viewModelScope.launch {
        val response=defaultRepo.homePage(token)
        homeMutableLiveData.postValue(handleHomePage(response))
    }

    fun categories()=viewModelScope.launch {
        val response =defaultRepo.categories()
        categoriesMutableLiveData.postValue(handleCategories(response))
    }

    private fun handleHomePage(response: MyResponse<HomePage>): Resource<MyResponse<HomePage>> {
        if (response.status) {
            response?.let { homePageResponse ->
                return Resource.Success(homePageResponse)
            }
        }
        return Resource.Error(response.message)
    }
    private fun handleCategories(response: MyResponse<CategoryModel>): Resource<MyResponse<CategoryModel>> {
        if (response.status) {
            response?.let { newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message)
    }
}