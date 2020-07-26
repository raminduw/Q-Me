package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.CreateEventModel
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel


interface GenerateEventUseCase {

    suspend fun createEvent(createEventModel: CreateEventModel): Either<ErrorCode, EventModel>
}