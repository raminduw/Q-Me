package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel


interface GetEventsUseCase {

    suspend fun getEvents(): Either<ErrorCode, List<EventModel>>
}