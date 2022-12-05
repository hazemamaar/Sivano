package com.android.sivano.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.favorite.FavTest
import com.android.sivano.data.repo.DefaultRepo
import com.android.sivano.domin.model.FavoriteData
import com.android.sivano.domin.model.GetAllFavorites
import com.android.sivano.domin.usecase.GetAllFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    @ApplicationContext val context: Context
    , private val favoriteUseCase: GetAllFavoriteUseCase,
    val defaultRepo: DefaultRepo
) : ViewModel() {
    var favoriteMutableLiveData: MutableLiveData<Resource<FavTest>> = MutableLiveData()
    fun getFavorites() {
        favoriteUseCase().onEach {
            Log.d("getFavorites121", "getFavorites: " + it.data?.data?.get(0)?.id)
            favoriteMutableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }
}