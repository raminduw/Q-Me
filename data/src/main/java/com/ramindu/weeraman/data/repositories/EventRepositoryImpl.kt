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
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val api: EventApi,
    private val createEventRequestDTOMapper: Mapper<CreateEventModel, CreateEventRequestDTO>,
    private val createEventResponseModelMapper: Mapper<CreateEventResponseDTO, EventModel>,
    private val loginUserRequestDTOMapper: Mapper<LoginUser, LoginRequestDTO>,
    private val loginUserResponseModelMapper: Mapper<LoginResponseDTO, LoginResult>,
    private val registerUserRequestDTOMapper: Mapper<RegisterUser, RegisterRequestDTO>,
    private val registerUserResponseModelMapper: Mapper<RegisterResponseDTO, RegisterResult>,
    private val getEventDetailsRequestDTOMapper: Mapper<String, GetEventDetailsRequestDTO>,
    private val getEventDetailsResponseModelMapper: Mapper<GetEventDetailsResponseDTO, EventDetailModel>,
    private val getEventsRequestDTOMapper: Mapper<String, GetEventsRequestDTO>,
    private val getEventsResponseModelMapper: Mapper<GetEventsResponseDTO, MutableList<EventModel>>

) : EventRepository {

    override suspend fun userLogin(loginUser: LoginUser): Either<ErrorCode, LoginResult> {
        return try {
            delay(1000)
            val response = api.userLogin(loginUserRequestDTOMapper.transform(loginUser))
            if (!response.isSuccessful) {
                ErrorCode(NETWORK_ERROR).left()
            } else {
                response.body()?.let {
                    userDataRepository.saveUserData(loginUser.name)
                    loginUserResponseModelMapper.transform(it).right()
                } ?: run {
                    ErrorCode(NETWORK_ERROR).left()
                }
            }
        } catch (e: Exception) {
            ErrorCode(NETWORK_EXCEPTION).left()
        }
    }

    override suspend fun userRegister(registerUser: RegisterUser): Either<ErrorCode, RegisterResult> {
        return try {
            delay(1000)
            val response = api.userRegister(registerUserRequestDTOMapper.transform(registerUser))
            if (!response.isSuccessful) {
                ErrorCode(NETWORK_ERROR).left()
            } else {
                response.body()?.let {
                    registerUserResponseModelMapper.transform(it).right()
                } ?: run {
                    ErrorCode(NETWORK_ERROR).left()
                }
            }
        } catch (e: Exception) {
            ErrorCode(NETWORK_EXCEPTION).left()
        }
    }

    override suspend fun createEvent(createEventModel: CreateEventModel): Either<ErrorCode, EventModel> {
        return try {
            delay(1000)
            val response = api.createEvent(createEventRequestDTOMapper.transform(createEventModel))
            if (!response.isSuccessful) {
                ErrorCode(NETWORK_ERROR).left()
            } else {
                response.body()?.let {
                    createEventResponseModelMapper.transform(it).right()
                } ?: run {
                    ErrorCode(NETWORK_ERROR).left()
                }
            }
        } catch (e: Exception) {
            ErrorCode(NETWORK_EXCEPTION).left()
        }
    }

    override suspend fun getEventsByUser(userName: String): Either<ErrorCode, MutableList<EventModel>> {

        return try {
            delay(1000)
            val response = api.getEventsByUser(getEventsRequestDTOMapper.transform(userName))
            if (!response.isSuccessful) {
                ErrorCode(NETWORK_ERROR).left()
            } else {
                response.body()?.let {
                    getEventsResponseModelMapper.transform(it).right()
                } ?: run {
                    ErrorCode(NETWORK_ERROR).left()
                }
            }
        } catch (e: Exception) {
            ErrorCode(NETWORK_EXCEPTION).left()
        }
    }

    override suspend fun getEventDetailsById(eventId: String): Either<ErrorCode, EventDetailModel> {
        return try {
            delay(1000)
            val response = api.getEventDetailsById(getEventDetailsRequestDTOMapper.transform(eventId))
            if (!response.isSuccessful) {
                ErrorCode(NETWORK_ERROR).left()
            } else {
                response.body()?.let {
                    getEventDetailsResponseModelMapper.transform(it).right()
                } ?: run {
                    ErrorCode(NETWORK_ERROR).left()
                }
            }
        } catch (e: Exception) {
            ErrorCode(NETWORK_EXCEPTION).left()
        }
    }

}
