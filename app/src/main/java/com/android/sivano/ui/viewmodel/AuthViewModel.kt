package com.android.sivano.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.domin.model.User
import com.android.sivano.domin.usecase.LoginUseCase
import com.android.sivano.model.MyResponse
import com.android.sivano.model.UserInfo
import com.android.sivano.model.UserResponse
import com.android.sivano.repo.AuthRepo
import com.android.sivano.common.uitil.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext val context:Context
    ,private val authRepo: AuthRepo
    ,private val useCaseLogin: LoginUseCase
) : ViewModel() {

    var registerMutableLiveData:MutableLiveData<Resource<MyResponse<UserResponse>>> = MutableLiveData()
    var loginMutableLiveData:MutableLiveData<Resource<User>> = MutableLiveData()
    fun register(userInfo: UserInfo)=viewModelScope.launch {
       val response= authRepo.register(userInfo)
        registerMutableLiveData.postValue(handleRegisterAndLogin(response))
    }
    fun login(userInfo: UserInfo){
//        val response=authRepo.login(userInfo)
//
//        loginMutableLiveData.postValue(handleRegisterAndLogin(response))
        useCaseLogin(userInfo).onEach {
            loginMutableLiveData.postValue(it)

        }.launchIn(viewModelScope)

    }
    private fun handleRegisterAndLogin(response: MyResponse<UserResponse>): Resource<MyResponse<UserResponse>> {
        if (response.status) {
            response?.let { userResponse ->
                return Resource.Success(userResponse)
            }
        }
        return Resource.Error(response.message)
    }

}