package com.ramindu.weeraman.myapplication.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.usecases.UserLogoutUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserLogoutViewModel @ViewModelInject constructor(
    private val userLogoutUseCase: UserLogoutUseCase,
    private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {
    val userLogoutStatus = MutableLiveData<Either<ErrorCode, Boolean>>()

    fun userLogout() {
        viewModelScope.launch(dispatcher) {
            userLogoutStatus.postValue(userLogoutUseCase.logoutUser())
        }
    }

}