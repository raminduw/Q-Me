package com.ramindu.weeraman.domain.usecases.impl

import arrow.core.Either
import arrow.core.left
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.RegisterResult
import com.ramindu.weeraman.domain.entities.RegisterUser
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.usecases.UserRegisterUseCase
import com.ramindu.weeraman.domain.validator.InputValidator
import javax.inject.Inject

class UserRegisterUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository,
    private val inputValidator: InputValidator<RegisterUser>
) : UserRegisterUseCase {

    override suspend fun registerUser(registerUser: RegisterUser): Either<ErrorCode, RegisterResult> {
        val validateResult = inputValidator.validate(registerUser)
        return if (!validateResult.first) {
            ErrorCode(validateResult.second).left()
        } else {
            eventRepository.userRegister(registerUser)
        }
    }
}