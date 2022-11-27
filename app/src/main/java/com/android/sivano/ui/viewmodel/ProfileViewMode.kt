package com.android.sivano.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.domin.model.ProfileModel
import com.android.sivano.domin.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewMode @Inject constructor(
    val profileUseCase: ProfileUseCase
) : ViewModel() {
    private var _profileSharedFlow: MutableSharedFlow<Resource<ProfileModel>> = MutableSharedFlow()
    var profileSharedFlow: SharedFlow<Resource<ProfileModel>> =_profileSharedFlow
    fun profile(){
        profileUseCase().onEach {
            _profileSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }

}