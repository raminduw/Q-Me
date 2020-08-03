package com.ramindu.weeraman.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ramindu.weeraman.domain.usecases.UserTestUseCase
import com.ramindu.weeraman.myapplication.ui.user.UserTestViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserTestViewModelTest2 {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var userTestUseCaseImpl: UserTestUseCase

    private lateinit var userTestViewModel: UserTestViewModel


    @Before
    fun setUp() {
        userTestViewModel = UserTestViewModel(userTestUseCaseImpl, mainCoroutineRule.testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPost actually returns the post I am expecting`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            whenever(userTestUseCaseImpl.getValue()).thenReturn(20)

            userTestViewModel.userLogin()

            then(userTestUseCaseImpl).should(times(1)).getValue()
            assert(userTestViewModel.loginStatusLiveData.value == false)
            assert(userTestViewModel.loginResultLiveData.value == 20)

        }
    }

}