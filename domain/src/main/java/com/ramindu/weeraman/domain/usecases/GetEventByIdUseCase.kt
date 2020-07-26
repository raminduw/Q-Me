package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventDetailModel


interface GetEventByIdUseCase {

    suspend fun getEventDetailsById(eventId: String): Either<ErrorCode, EventDetailModel>
}