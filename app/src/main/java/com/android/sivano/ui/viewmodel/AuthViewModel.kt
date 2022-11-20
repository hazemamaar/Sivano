package com.android.sivano.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.domin.model.User
import com.android.sivano.domin.usecase.LoginUseCase
import com.android.sivano.entities.UserInfoDto
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.model.FcmModel
import com.android.sivano.domin.usecase.FcmUseCase
import com.android.sivano.domin.usecase.RegisterUserCase
import com.android.sivano.entities.FcmTokenOtd
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext val context:Context
    ,private val useCaseLogin: LoginUseCase
    ,private val useCaseRegister: RegisterUserCase,
    private val fcmUseCase:FcmUseCase
) : ViewModel() {

    var registerMutableLiveData:MutableLiveData<Resource<User>> = MutableLiveData()
    var loginMutableLiveData:MutableLiveData<Resource<User>> = MutableLiveData()

    private var _fcmTokenSharedFlow:MutableSharedFlow<Resource<FcmModel>> =MutableSharedFlow()
    var fcmTokenSharedFlow:SharedFlow<Resource<FcmModel>> = _fcmTokenSharedFlow

    fun register(userInfoDto: UserInfoDto)=viewModelScope.launch {

        useCaseRegister(userInfoDto).onEach {
            registerMutableLiveData.postValue(it)
        }.launchIn(viewModelScope)
    }
    fun login(userInfoDto: UserInfoDto){
//        val response=authRepo.login(userInfo)
//
//        loginMutableLiveData.postValue(handleRegisterAndLogin(response))
        useCaseLogin(userInfoDto).onEach {
            loginMutableLiveData.postValue(it)

        }.launchIn(viewModelScope)

    }
    fun fcmToken(fcmToken: FcmTokenOtd){
        fcmUseCase(fcmToken).onEach {
            _fcmTokenSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }

}