package com.android.sivano.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sivano.common.uitil.Resource
import com.android.sivano.data.entities.auth.ChangePasswordOtd
import com.android.sivano.data.entities.auth.ChangePasswordResponseDto
import com.android.sivano.domin.usecase.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(val changePasswordUseCase: ChangePasswordUseCase) :
    ViewModel() {
    private var _changePasswordSharedFlow: MutableSharedFlow<Resource<ChangePasswordResponseDto>> =
        MutableSharedFlow()
    var changePasswordSharedFlow: SharedFlow<Resource<ChangePasswordResponseDto>> =
        _changePasswordSharedFlow

    fun changePassword(changePasswordOtd: ChangePasswordOtd) {
        changePasswordUseCase(changePasswordOtd).onEach {
           _changePasswordSharedFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}