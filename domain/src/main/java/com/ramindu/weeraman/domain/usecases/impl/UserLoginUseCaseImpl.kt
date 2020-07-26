package com.ramindu.weeraman.domain.usecases.impl

import arrow.core.Either
import arrow.core.left
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import com.ramindu.weeraman.domain.usecases.UserLoginUseCase
import com.ramindu.weeraman.domain.validator.InputValidator
import javax.inject.Inject

class UserLoginUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository,
    private val inputValidator: InputValidator<LoginUser>
) : UserLoginUseCase {

    override suspend fun loginUser(loginUser: LoginUser): Either<ErrorCode, LoginResult> {

        val validateResult = inputValidator.validate(loginUser)
        return if (!validateResult.first) {
            ErrorCode(validateResult.second).left()
        } else {
            eventRepository.userLogin(loginUser)
        }
    }
}