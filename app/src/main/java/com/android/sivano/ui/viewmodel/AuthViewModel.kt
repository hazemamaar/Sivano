package com.android.sivano.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.domin.model.User
import com.android.sivano.domin.usecase.LoginUseCase
import com.android.sivano.entities.MyResponse
import com.android.sivano.entities.UserInfoDto
import com.android.sivano.entities.UserResponse
import com.android.sivano.repo.AuthRepo
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.usecase.RegisterUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext val context:Context
    ,private val useCaseLogin: LoginUseCase
    ,private val useCaseRegister: RegisterUserCase
) : ViewModel() {

    var registerMutableLiveData:MutableLiveData<Resource<User>> = MutableLiveData()
    var loginMutableLiveData:MutableLiveData<Resource<User>> = MutableLiveData()
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

}