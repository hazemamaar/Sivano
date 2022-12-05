package com.android.sivano.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.cart.UpdateCartsOtd
import com.android.sivano.data.entities.cart.UpdateOrDeleteCartItemDto
import com.android.sivano.domin.model.GetAllCarts
import com.android.sivano.domin.usecase.DeleteFromCartUseCase
import com.android.sivano.domin.usecase.GetCartsUseCase
import com.android.sivano.domin.usecase.UpdateCartUseCase
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
    private val getCartsUseCase:GetCartsUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val deleteCartUseCase: DeleteFromCartUseCase
) :ViewModel() {
    private var _cartsSharedFlow= MutableSharedFlow<Resource<GetAllCarts>>()
    val cardSharedFlow : SharedFlow<Resource<GetAllCarts>> =_cartsSharedFlow

    private var _updateCartSharedFlow= MutableSharedFlow<Resource<UpdateOrDeleteCartItemDto>>()
    val updateCartSharedFlow : SharedFlow<Resource<UpdateOrDeleteCartItemDto>> =_updateCartSharedFlow

    private var _deleteCartSharedFlow= MutableSharedFlow<Resource<UpdateOrDeleteCartItemDto>>()
    val deleteCartSharedFlow : SharedFlow<Resource<UpdateOrDeleteCartItemDto>> =_deleteCartSharedFlow

    fun getAllCarts(){
        getCartsUseCase().onEach {
            _cartsSharedFlow.emit(Resource.Loading())
            _cartsSharedFlow.emit(it)
            Log.d("cartsviewModel", "getAllCarts: "+it.data?.cart_items?.size)
        }.launchIn(viewModelScope)
    }
 fun updateCart(id:Int,updateCartsOtd: UpdateCartsOtd){
     updateCartUseCase(id,updateCartsOtd).onEach {
         _updateCartSharedFlow.emit(it)
     }.launchIn(viewModelScope)
 }
    fun deleteCart(id:Int){
        deleteCartUseCase(id).onEach {
            _deleteCartSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}