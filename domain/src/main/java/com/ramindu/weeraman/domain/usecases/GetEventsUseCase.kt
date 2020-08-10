package com.ramindu.weeraman.domain.usecases

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.entities.EventModel
import kotlinx.coroutines.flow.Flow


interface GetEventsUseCase {

    suspend fun getEvents(): Either<ErrorCode, List<EventModel>>

    suspend fun getEventsFlow(): Flow<EventModel>
}