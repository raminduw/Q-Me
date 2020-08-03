package com.ramindu.weeraman.domain.usecases.impl

import com.ramindu.weeraman.domain.usecases.UserTestUseCase
import javax.inject.Inject

class UserTestUseCaseImpl @Inject constructor(): UserTestUseCase {

    override suspend fun getValue(): Int {
        return 10
    }
}