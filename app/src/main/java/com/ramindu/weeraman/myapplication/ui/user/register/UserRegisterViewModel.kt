package com.ramindu.weeraman.myapplication.ui.user.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.domain.entities.*
import com.ramindu.weeraman.domain.usecases.UserRegisterUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch


class UserRegisterViewModel @ViewModelInject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _registerResultLiveData = MutableLiveData<Either<ErrorCode, RegisterResult>>()
    private var _registerStatusLiveData = MutableLiveData<Boolean>()

    val registerStatusLiveData: LiveData<Boolean>
        get() = _registerStatusLiveData

    val registerResultLiveData: LiveData<Either<ErrorCode, RegisterResult>>
        get() = _registerResultLiveData


    fun userRegister(userName: String, password: String, confirmPassword: String) {
        viewModelScope.launch(dispatcher) {
            _registerStatusLiveData.postValue(true)
            _registerResultLiveData.postValue(userRegisterUseCase.registerUser(RegisterUser(name = userName, password = password, confirmPassword = confirmPassword)))
            _registerStatusLiveData.postValue(false)
        }
    }

}