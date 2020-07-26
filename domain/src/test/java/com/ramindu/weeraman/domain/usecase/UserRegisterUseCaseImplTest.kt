package com.ramindu.weeraman.domain.usecase

import arrow.core.left
import arrow.core.right
import com.google.common.truth.Truth
import com.ramindu.weeraman.domain.LOGIN_INPUT_ERROR
import com.ramindu.weeraman.domain.NETWORK_ERROR
import com.ramindu.weeraman.domain.NO_ERROR_CODE
import com.ramindu.weeraman.domain.entities.*
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.usecases.impl.UserRegisterUseCaseImpl
import com.ramindu.weeraman.domain.validator.InputValidator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserRegisterUseCaseImplTest
{
    @RelaxedMockK
    lateinit var eventRepository: EventRepository

    @RelaxedMockK
    lateinit var inputValidator: InputValidator<RegisterUser>

    private lateinit var userRegisterUseCaseImpl: UserRegisterUseCaseImpl

    private lateinit var registerUser: RegisterUser

    @BeforeEach
    fun setup() {
        userRegisterUseCaseImpl = UserRegisterUseCaseImpl(eventRepository, inputValidator)
        registerUser = RegisterUser(NAME, PASSWORD, CONFIRM_PASSWORD)
    }

    @Test
    fun `user register input error case`() = runBlocking {
        every { inputValidator.validate(registerUser) } returns Pair(false, LOGIN_INPUT_ERROR)

        val result =  userRegisterUseCaseImpl.registerUser(registerUser)

        coVerify(exactly = 0) { eventRepository.userRegister(registerUser) }

        Truth.assertThat(result.isLeft()).isTrue()
        result.fold(ifLeft = { error ->
            Truth.assertThat(error.code == LOGIN_INPUT_ERROR).isTrue()
        }, ifRight = { login ->
            Truth.assertThat(login).isNull()
        })
    }

    @Test
    fun `user register success case`() = runBlocking {
        coEvery { inputValidator.validate(registerUser) } returns Pair(true, NO_ERROR_CODE)
        coEvery { eventRepository.userRegister(registerUser) } returns RegisterResult(NAME, true).right()

        val result = userRegisterUseCaseImpl.registerUser(registerUser)

        coVerify(exactly = 1) { eventRepository.userRegister(registerUser) }

        Truth.assertThat(result.isRight()).isTrue()
        result.fold(ifLeft = { error ->
            Truth.assertThat(error.code == NO_ERROR_CODE).isTrue()
        }, ifRight = { login ->
            Truth.assertThat(login).isNotNull()
            Truth.assertThat(login.name == NAME).isTrue()
        })
    }

    @Test
    fun `user register failed case`() = runBlocking {
        coEvery { inputValidator.validate(registerUser) } returns Pair(true, NO_ERROR_CODE)
        coEvery { eventRepository.userRegister(registerUser) } returns ErrorCode(NETWORK_ERROR).left()

        val result = userRegisterUseCaseImpl.registerUser(registerUser)

        coVerify(exactly = 1) { eventRepository.userRegister(registerUser) }

        Truth.assertThat(result.isLeft()).isTrue()
        result.fold(ifLeft = { error ->
            Truth.assertThat(error.code == NETWORK_ERROR).isTrue()
        }, ifRight = { register ->
            Truth.assertThat(register).isNull()
        })


    }
}

private const val NAME = "RAMINDU"
private const val PASSWORD = "123abc"
private const val CONFIRM_PASSWORD = "123abc"