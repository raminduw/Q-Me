package com.ramindu.weeraman.domain.usecases.impl

import arrow.core.Either
import arrow.core.right
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import com.ramindu.weeraman.domain.usecases.UserLogoutUseCase
import javax.inject.Inject

class UserLogoutUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository
) : UserLogoutUseCase {

    override suspend fun logoutUser(): Either<ErrorCode, Boolean> {
        userDataRepository.clearUser(userDataRepository.getLoggedUser())
        return true.right()
    }
}