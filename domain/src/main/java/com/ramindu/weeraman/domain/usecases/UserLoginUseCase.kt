package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.LoginResult
import com.ramindu.weeraman.domain.entities.LoginUser

interface UserLoginUseCase {

    suspend fun loginUser(loginUser: LoginUser): Either<ErrorCode, LoginResult>
}