package com.ramindu.weeraman.domain.usecases.impl

import arrow.core.Either
import arrow.core.left
import com.ramindu.weeraman.domain.entities.CreateEventModel
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.usecases.GenerateEventUseCase
import com.ramindu.weeraman.domain.validator.InputValidator
import javax.inject.Inject

class CreateEventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository, private val inputValidator: InputValidator<CreateEventModel>
) : GenerateEventUseCase {

    override suspend fun createEvent(createEventModel: CreateEventModel): Either<ErrorCode, EventModel> {
        val validateResult = inputValidator.validate(createEventModel)

        return if (!validateResult.first) {
            ErrorCode(validateResult.second).left()
        } else {
            eventRepository.createEvent(createEventModel)
        }
    }

}


