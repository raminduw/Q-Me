package com.ramindu.weeraman.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.right
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.usecases.UserLoginUseCase
import com.ramindu.weeraman.myapplication.ui.user.login.UserLoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class UserLoginViewModelTest {

    @RelaxedMockK
    private lateinit var userLoginUseCase: UserLoginUseCase

    // Set the main coroutines dispatcher for unit testing.
/*    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()*/

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    // Subject under test
    private lateinit var userLoginViewModel: UserLoginViewModel


    @BeforeEach
    fun setup() {
        userLoginViewModel = UserLoginViewModel(userLoginUseCase, provideFakeCoroutinesDispatcherProvider(testCoroutineDispatcher))
    }

    @Test
    fun test_login() {

        val loginUser = LoginUser("Ramindu", "xxx")

        coEvery { userLoginUseCase.loginUser(loginUser) } returns LoginResult("Ramindu", true).right()

        userLoginViewModel.userLogin("Ramindu", "xxx")

        //Assertions.assertEquals(1, 1)

        coVerify(exactly = 1) { userLoginUseCase.loginUser(loginUser) }

    }

}