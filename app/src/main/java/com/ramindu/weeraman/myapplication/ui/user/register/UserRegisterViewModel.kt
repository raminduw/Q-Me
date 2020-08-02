package com.ramindu.weeraman.myapplication.ui.user.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import com.ramindu.weeraman.domain.entities.*
import com.ramindu.weeraman.domain.usecases.UserLoginUseCase
import com.ramindu.weeraman.domain.usecases.UserRegisterUseCase
import kotlinx.coroutines.launch


class UserRegisterViewModel @ViewModelInject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val registerStatusLiveData = MutableLiveData<Boolean>()
    val registerResultLiveData = MutableLiveData<Either<ErrorCode, RegisterResult>>()

    fun userRegister(userName: String, password: String, confirmPassword: String) {
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            registerStatusLiveData.postValue(true)
            registerResultLiveData.postValue(userRegisterUseCase.registerUser(RegisterUser(name = userName, password = password, confirmPassword = confirmPassword)))
            registerStatusLiveData.postValue(false)
        }
    }

}