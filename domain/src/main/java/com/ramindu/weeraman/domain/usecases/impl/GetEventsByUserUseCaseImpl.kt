package com.ramindu.weeraman.domain.usecases.impl

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import com.ramindu.weeraman.domain.usecases.GetEventsUseCase
import javax.inject.Inject

class GetEventsByUserUseCaseImpl @Inject constructor(private val eventRepository: EventRepository,private val userDataRepository: UserDataRepository) : GetEventsUseCase {
    override suspend fun getEvents(): Either<ErrorCode, List<EventModel>> {
        return eventRepository.getEventsByUser(userDataRepository.getLoggedUser())
    }
}