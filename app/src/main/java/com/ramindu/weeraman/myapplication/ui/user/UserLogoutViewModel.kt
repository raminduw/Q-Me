package com.ramindu.weeraman.myapplication.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
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
    private val _userLogoutStatus = MutableLiveData<Either<ErrorCode, Boolean>>()

    val userLogoutStatus: LiveData<Either<ErrorCode, Boolean>>
        get() = _userLogoutStatus

    fun userLogout() {
        viewModelScope.launch(dispatcher) {
            _userLogoutStatus.postValue(userLogoutUseCase.logoutUser())
        }
    }

}