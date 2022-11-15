package com.android.sivano.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.model.GetAllCarts
import com.android.sivano.domin.usecase.GetCartsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val getCartsUseCase:GetCartsUseCase

) :ViewModel() {
    public var cartsMutableLiveData:MutableLiveData<Resource<GetAllCarts>> = MutableLiveData()
    private var _cartsSharedFlow= MutableSharedFlow<Resource<GetAllCarts>>()
    val cardSharedFlow : SharedFlow<Resource<GetAllCarts>> =_cartsSharedFlow
    public fun getAllCarts(){
        getCartsUseCase().onEach {
            _cartsSharedFlow.emit(Resource.Loading())
            _cartsSharedFlow.emit(it)
            Log.d("cartsviewModel", "getAllCarts: "+it.data?.cart_items?.size)
        }.launchIn(viewModelScope)
    }
}