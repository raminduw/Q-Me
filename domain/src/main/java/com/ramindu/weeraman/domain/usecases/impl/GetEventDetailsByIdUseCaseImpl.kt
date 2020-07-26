package com.ramindu.weeraman.domain.usecases.impl

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventDetailModel
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.usecases.GetEventByIdUseCase
import javax.inject.Inject

class GetEventDetailsByIdUseCaseImpl @Inject constructor(private val eventRepository: EventRepository) : GetEventByIdUseCase {

    override suspend fun getEventDetailsById(eventId: String): Either<ErrorCode, EventDetailModel> {
       return eventRepository.getEventDetailsById(eventId)
    }
}