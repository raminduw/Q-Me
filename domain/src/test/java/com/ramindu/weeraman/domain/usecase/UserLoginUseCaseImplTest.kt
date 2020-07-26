package com.ramindu.weeraman.domain.usecase

import arrow.core.left
import arrow.core.right
import com.google.common.truth.Truth
import com.ramindu.weeraman.domain.LOGIN_INPUT_ERROR
import com.ramindu.weeraman.domain.NETWORK_ERROR
import com.ramindu.weeraman.domain.NO_ERROR_CODE
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.usecases.impl.UserLoginUseCaseImpl
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
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.TestInstance

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserLoginUseCaseImplTest {

    @RelaxedMockK
    private lateinit var eventRepository: EventRepository

    @RelaxedMockK
    private lateinit var inputValidator: InputValidator<LoginUser>

    private lateinit var userLoginUseCaseImpl: UserLoginUseCaseImpl

    private lateinit var loginUser: LoginUser

    @BeforeEach
    fun setup() {
        userLoginUseCaseImpl = UserLoginUseCaseImpl(eventRepository, inputValidator)
        loginUser = LoginUser(NAME, PASSWORD)
    }

    @Test
    fun `user login input error case`() = runBlocking {
        every { inputValidator.validate(loginUser) } returns Pair(false, LOGIN_INPUT_ERROR)

        val result = userLoginUseCaseImpl.loginUser(loginUser)

        coVerify(exactly = 0) { eventRepository.userLogin(loginUser) }

        Truth.assertThat(result.isLeft()).isTrue()
        result.fold(ifLeft = { error ->
            Truth.assertThat(error.code == LOGIN_INPUT_ERROR).isTrue()
        }, ifRight = { login ->
            Truth.assertThat(login).isNull()
        })
    }

    @Test
    fun `user login success case`() = runBlocking {
        coEvery { inputValidator.validate(loginUser) } returns Pair(true, NO_ERROR_CODE)
        coEvery { eventRepository.userLogin(loginUser) } returns LoginResult(NAME, true).right()

        val result = userLoginUseCaseImpl.loginUser(loginUser)

        coVerify(exactly = 1) { eventRepository.userLogin(loginUser) }

        Truth.assertThat(result.isRight()).isTrue()
        result.fold(ifLeft = { error ->
            Truth.assertThat(error.code == NO_ERROR_CODE).isTrue()
        }, ifRight = { login ->
            Truth.assertThat(login).isNotNull()
            Truth.assertThat(login.name == NAME).isTrue()
        })
    }

    @Test
    fun `user login failed case`() = runBlocking {
        coEvery { inputValidator.validate(loginUser) } returns Pair(true, NO_ERROR_CODE)
        coEvery { eventRepository.userLogin(loginUser) } returns ErrorCode(NETWORK_ERROR).left()

        val result = userLoginUseCaseImpl.loginUser(loginUser)

        coVerify(exactly = 1) { eventRepository.userLogin(loginUser) }

        Truth.assertThat(result.isLeft()).isTrue()
        result.fold(ifLeft = { error ->
            Truth.assertThat(error.code == NETWORK_ERROR).isTrue()
        }, ifRight = { login ->
            Truth.assertThat(login).isNull()
        })

    }
}

private const val NAME = "RAMINDU"
private const val PASSWORD = "123abc"