package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.RegisterResult
import com.ramindu.weeraman.domain.entities.RegisterUser

interface UserRegisterUseCase {

    suspend fun registerUser(registerUser: RegisterUser): Either<ErrorCode, RegisterResult>
}