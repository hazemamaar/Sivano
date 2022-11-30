package com.android.sivano.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.domin.model.ProfileModel
import com.android.sivano.domin.model.User
import com.android.sivano.domin.usecase.ProfileUseCase
import com.android.sivano.domin.usecase.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewMode @Inject constructor(
    val profileUseCase: ProfileUseCase,
    val updateUseCase: UpdateUseCase
) : ViewModel() {
    private var _profileSharedFlow: MutableSharedFlow<Resource<ProfileModel>> = MutableSharedFlow()
    var profileSharedFlow: SharedFlow<Resource<ProfileModel>> =_profileSharedFlow

    private var _updateProfileSharedFlow: MutableSharedFlow<Resource<User>> = MutableSharedFlow()
    var updateProfileSharedFlow: SharedFlow<Resource<User>> =_updateProfileSharedFlow
    fun profile(){
        profileUseCase().onEach {
            _profileSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }

    fun updateProfile(userInfoDto: UserInfoDto){
        updateUseCase(userInfoDto).onEach {
            _updateProfileSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}