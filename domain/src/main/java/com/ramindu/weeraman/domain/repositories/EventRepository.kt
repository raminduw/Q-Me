package com.ramindu.weeraman.domain.repositories

import arrow.core.Either
import com.ramindu.weeraman.domain.entities.*


interface EventRepository {

    suspend fun createEvent(createEventModel: CreateEventModel): Either<ErrorCode, EventModel>

    suspend fun userLogin(loginUser: LoginUser): Either<ErrorCode, LoginResult>

    suspend fun userRegister(registerUser: RegisterUser): Either<ErrorCode, RegisterResult>

    suspend fun getEventsByUser(userName: String): Either<ErrorCode, MutableList<EventModel>>

    suspend fun getEventDetailsById(eventId: String): Either<ErrorCode, EventDetailModel>

}