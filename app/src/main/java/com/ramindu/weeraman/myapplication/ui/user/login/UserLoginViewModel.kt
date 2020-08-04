package com.ramindu.weeraman.myapplication.ui.user.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.usecases.UserLoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserLoginViewModel @ViewModelInject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loginResultLiveData = MutableLiveData<Either<ErrorCode, LoginResult>>()
    private var _loginStatusLiveData = MutableLiveData<Boolean>()

    val loginStatusLiveData: LiveData<Boolean>
        get() = _loginStatusLiveData

    val loginResultLiveData: LiveData<Either<ErrorCode, LoginResult>>
        get() = _loginResultLiveData

    fun userLogin(userName: String, password: String) {

        viewModelScope.launch(dispatcher) {

            _loginStatusLiveData.postValue(true)
            _loginResultLiveData.postValue(userLoginUseCase.loginUser(LoginUser(name = userName, password = password)))
            _loginStatusLiveData.postValue(false)

        }

    }

}