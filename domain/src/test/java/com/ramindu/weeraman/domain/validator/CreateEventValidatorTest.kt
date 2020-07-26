package com.ramindu.weeraman.domain.validator

import com.google.common.truth.Truth
import com.ramindu.weeraman.domain.LOGIN_INPUT_ERROR
import com.ramindu.weeraman.domain.NO_ERROR_CODE
import com.ramindu.weeraman.domain.entities.LoginUser
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateEventValidatorTest {

    private lateinit var createEventValidator: CreateEventValidatorImpl

    @BeforeAll
    fun setUp() {
        createEventValidator = CreateEventValidatorImpl()
    }

    @ParameterizedTest(name = "user login: (username : {0}) (password : {1}) result : {3}")
    @MethodSource("provideLoginUser")
    fun `test login user`(name: String, password: String, errorCode: Int, result: Boolean) {

    }

    companion object {
        @JvmStatic
        fun provideLoginUser() = Stream.of(
            Arguments.of("", "", LOGIN_INPUT_ERROR, false),
            Arguments.of(USER_NAME, "", LOGIN_INPUT_ERROR, false),
            Arguments.of("", PASSWORD, LOGIN_INPUT_ERROR, false),
            Arguments.of(USER_NAME, PASSWORD, NO_ERROR_CODE, true)
        )
    }
}

private const val USER_NAME = "RAMINDU"
private const val PASSWORD = "123abc"