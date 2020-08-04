package com.ramindu.weeraman.myapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.ramindu.weeraman.domain.NETWORK_ERROR
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.usecases.UserLoginUseCase
import com.ramindu.weeraman.myapplication.common.MainCoroutineRule
import com.ramindu.weeraman.myapplication.ui.user.login.UserLoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)

class UserLoginViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var userLoginUseCase: UserLoginUseCase

    private lateinit var userLoginViewModel: UserLoginViewModel

    private lateinit var loginUser: LoginUser


    @Before
    fun setUp() {
        userLoginViewModel = UserLoginViewModel(userLoginUseCase, mainCoroutineRule.testDispatcher)
        loginUser = LoginUser(NAME, PASSWORD)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test user login success case`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {

            whenever(userLoginUseCase.loginUser(loginUser)).thenReturn(LoginResult(NAME, true).right())

            userLoginViewModel.userLogin(NAME, PASSWORD)

            then(userLoginUseCase).should(times(1)).loginUser(loginUser)
            assert(userLoginViewModel.loginStatusLiveData.value == false)
            assert(userLoginViewModel.loginResultLiveData.value == LoginResult(NAME, true).right())

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test user login fail case`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {

            whenever(userLoginUseCase.loginUser(loginUser)).thenReturn(ErrorCode(NETWORK_ERROR).left())

            userLoginViewModel.userLogin(NAME, PASSWORD)

            then(userLoginUseCase).should(times(1)).loginUser(loginUser)
            assert(userLoginViewModel.loginStatusLiveData.value == false)
            assert(userLoginViewModel.loginResultLiveData.value == ErrorCode(NETWORK_ERROR).left())

        }
    }
}

private const val NAME = "RAMINDU"
private const val PASSWORD = "123abc"