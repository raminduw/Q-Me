package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode


interface UserLogoutUseCase {

    suspend fun logoutUser(): Either<ErrorCode, Boolean>
}