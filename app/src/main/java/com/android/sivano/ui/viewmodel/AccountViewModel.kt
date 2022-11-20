package com.android.sivano.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.model.LogoutModel
import com.android.sivano.domin.model.ProfileModel
import com.android.sivano.domin.usecase.LogoutUseCase
import com.android.sivano.domin.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val profileUseCase: ProfileUseCase
) :ViewModel() {
   private var _logoutSharedFlow:MutableSharedFlow<Resource<LogoutModel>> = MutableSharedFlow()
    var logoutSharedFlow:SharedFlow<Resource<LogoutModel>> =_logoutSharedFlow
    private var _profileSharedFlow:MutableSharedFlow<Resource<ProfileModel>> = MutableSharedFlow()
    var profileSharedFlow:SharedFlow<Resource<ProfileModel>> =_profileSharedFlow
    fun logOut(){
        logoutUseCase().onEach {
            _logoutSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }
   fun profile(){
       profileUseCase().onEach {
           _profileSharedFlow.emit(it)
       }.launchIn(viewModelScope)
   }

}