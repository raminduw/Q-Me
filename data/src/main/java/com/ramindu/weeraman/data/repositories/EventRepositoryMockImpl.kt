package com.ramindu.weeraman.data.repositories

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ramindu.weeraman.data.entities.*
import com.ramindu.weeraman.data.networking.EventApi
import com.ramindu.weeraman.domain.NETWORK_ERROR
import com.ramindu.weeraman.domain.NETWORK_EXCEPTION
import com.ramindu.weeraman.domain.entities.*
import com.ramindu.weeraman.domain.mapper.Mapper
import com.ramindu.weeraman.domain.repositories.EventRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class EventRepositoryMockImpl @Inject constructor() : EventRepository {

    override suspend fun userLogin(loginUser: LoginUser): Either<ErrorCode, LoginResult> {
        delay(1000)
        return LoginResult("Ramindu", true).right()
    }

    override suspend fun userRegister(registerUser: RegisterUser): Either<ErrorCode, RegisterResult> {
        delay(1000)
        return RegisterResult("Ramindu", true).right()
    }

    override suspend fun createEvent(createEventModel: CreateEventModel): Either<ErrorCode, EventModel> {
        delay(1000)
        return EventModel("ABC123", "Test Event", "Test Event Desc").right()
    }

    override suspend fun getEventsByUser(userName: String): Either<ErrorCode, MutableList<EventModel>> {

        delay(1000)
        val list = mutableListOf<EventModel>()
        list.add(EventModel("ABC123", "Test Event 1", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 2", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 3", "Test Event Desc"))

        list.add(EventModel("ABC123", "Test Event 4", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 5", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 6", "Test Event Desc"))

        list.add(EventModel("ABC123", "Test Event 7", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 8", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 9", "Test Event Desc"))

        list.add(EventModel("ABC123", "Test Event 10", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 11", "Test Event Desc"))
        list.add(EventModel("ABC123", "Test Event 12", "Test Event Desc"))

        return list.right()
    }

    override suspend fun getEventDetailsById(eventId: String): Either<ErrorCode, EventDetailModel> {
        delay(1000)
        return EventDetailModel("ABC123", "Test Event", "Test Event Desc").right()
    }

}
