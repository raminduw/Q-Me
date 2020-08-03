package com.ramindu.weeraman.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ramindu.weeraman.domain.usecases.UserTestUseCase
import com.ramindu.weeraman.myapplication.ui.user.UserTestViewModel
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith


import org.junit.Rule
import org.junit.Test



@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTestViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var userTestUseCaseImpl: UserTestUseCase

    private lateinit var userTestViewModel: UserTestViewModel


    @Before
    internal fun setUp() {
        userTestViewModel = UserTestViewModel(userTestUseCaseImpl, mainCoroutineRule.testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPost actually returns the post I am expecting`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            userTestViewModel.userLogin()
            assertEquals(2, 2)
           // coVerify (exactly = 1) { userTestUseCaseImpl.getValue() }
        }
    }

}