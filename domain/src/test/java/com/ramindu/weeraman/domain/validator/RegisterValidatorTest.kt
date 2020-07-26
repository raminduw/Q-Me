package com.ramindu.weeraman.domain.validator

import com.google.common.truth.Truth
import com.ramindu.weeraman.domain.LOGIN_INPUT_ERROR
import com.ramindu.weeraman.domain.NO_ERROR_CODE
import com.ramindu.weeraman.domain.entities.RegisterUser
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisterValidatorTest {

    private lateinit var registerValidatorImpl: RegisterValidatorImpl

    @BeforeAll
    fun setUp() {
        registerValidatorImpl = RegisterValidatorImpl()
    }

    @ParameterizedTest(name = "user register: (username : {0}) (password : {1}) (confirm password : {2}) result : {4}")
    @MethodSource("provideRegisterUser")
    fun `test register user`(
        name: String, password: String, confirmPassword: String,
        errorCode: Int, result: Boolean
    ) {
        val registerUser = RegisterUser(name, password, confirmPassword)
        val pair = registerValidatorImpl.validate(registerUser)

        Truth.assertThat(pair).isNotNull()
        Truth.assertThat(errorCode == pair.second).isTrue()
        if (result) {
            Truth.assertThat(result).isTrue()
        } else {
            Truth.assertThat(result).isFalse()
        }
    }

    companion object {
        @JvmStatic
        fun provideRegisterUser() = Stream.of(
            Arguments.of("", "", "", LOGIN_INPUT_ERROR, false),
            Arguments.of(USER_NAME, "", "", LOGIN_INPUT_ERROR, false),
            Arguments.of("", PASSWORD, CONFIRM_PASSWORD_RIGHT, LOGIN_INPUT_ERROR, false),
            Arguments.of(USER_NAME, PASSWORD, CONFIRM_PASSWORD_WRONG, LOGIN_INPUT_ERROR, false),
            Arguments.of(USER_NAME, PASSWORD, CONFIRM_PASSWORD_RIGHT, NO_ERROR_CODE, true)
        )
    }
}

private const val USER_NAME = "RAMINDU"
private const val PASSWORD = "123abc"
private const val CONFIRM_PASSWORD_RIGHT = "123abc"
private const val CONFIRM_PASSWORD_WRONG = "123xyz"