package com.ramindu.weeraman.myapplication.ui.user.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.usecases.UserLoginUseCase
import kotlinx.coroutines.launch


class UserLoginViewModel @ViewModelInject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val loginStatusLiveData = MutableLiveData<Boolean>()
    val loginResultLiveData = MutableLiveData<Either<ErrorCode, LoginResult>>()

    fun userLogin(userName: String, password: String) {
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            loginStatusLiveData.postValue(true)
            loginResultLiveData.postValue(userLoginUseCase.loginUser(LoginUser(name = userName, password = password)))
            loginStatusLiveData.postValue(false)
        }
    }

}