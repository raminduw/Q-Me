package com.ramindu.weeraman.data.repository

import com.google.common.truth.Truth
import com.ramindu.weeraman.data.entities.*
import com.ramindu.weeraman.data.networking.EventApi
import com.ramindu.weeraman.data.repositories.EventRepositoryImpl
import com.ramindu.weeraman.domain.NETWORK_ERROR
import com.ramindu.weeraman.domain.entities.*
import com.ramindu.weeraman.domain.mapper.Mapper
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EventRepositoryImplTest {

    private lateinit var eventRepositoryImpl: EventRepositoryImpl

    @RelaxedMockK
    lateinit var userDataRepository: UserDataRepository

    @RelaxedMockK
    lateinit var api: EventApi

    @RelaxedMockK
    lateinit var createEventRequestDTOMapper: Mapper<CreateEventModel, CreateEventRequestDTO>

    @RelaxedMockK
    lateinit var createEventResponseModelMapper: Mapper<CreateEventResponseDTO, EventModel>

    @RelaxedMockK
    lateinit var loginUserRequestDTOMapper: Mapper<LoginUser, LoginRequestDTO>

    @RelaxedMockK
    lateinit var loginUserResponseModelMapper: Mapper<LoginResponseDTO, LoginResult>

    @RelaxedMockK
    lateinit var registerUserRequestDTOMapper: Mapper<RegisterUser, RegisterRequestDTO>

    @RelaxedMockK
    lateinit var registerUserResponseModelMapper: Mapper<RegisterResponseDTO, RegisterResult>

    @RelaxedMockK
    lateinit var getEventDetailsRequestDTOMapper: Mapper<String, GetEventDetailsRequestDTO>

    @RelaxedMockK
    lateinit var getEventDetailsResponseModelMapper: Mapper<GetEventDetailsResponseDTO, EventDetailModel>

    @RelaxedMockK
    lateinit var getEventsRequestDTOMapper: Mapper<String, GetEventsRequestDTO>

    @RelaxedMockK
    lateinit var getEventsResponseModelMapper: Mapper<GetEventsResponseDTO, MutableList<EventModel>>

    @RelaxedMockK
    lateinit var loginResponseDTO: LoginResponseDTO

    lateinit var loginUser: LoginUser

    @RelaxedMockK
    lateinit var loginRequestDTO: LoginRequestDTO

    lateinit var loginResult: LoginResult

    @BeforeEach
    fun setup() {
        eventRepositoryImpl = EventRepositoryImpl(
            userDataRepository, api, createEventRequestDTOMapper,
            createEventResponseModelMapper, loginUserRequestDTOMapper, loginUserResponseModelMapper,
            registerUserRequestDTOMapper, registerUserResponseModelMapper, getEventDetailsRequestDTOMapper, getEventDetailsResponseModelMapper,
            getEventsRequestDTOMapper, getEventsResponseModelMapper
        )
        loginResult = LoginResult(USER_NAME, true)
        loginUser = LoginUser(USER_NAME, PASSWORD)
    }

    @Test
    fun `test user login success`() = runBlocking {
        coEvery { loginUserRequestDTOMapper.transform(loginUser) } returns loginRequestDTO
        coEvery { api.userLogin(loginRequestDTO) } returns Response.success(loginResponseDTO)
        coEvery { loginResponseDTO.name } returns USER_NAME
        coEvery { loginUserResponseModelMapper.transform(loginResponseDTO) } returns loginResult
        val result = eventRepositoryImpl.userLogin(loginUser)
        Truth.assertThat(result.isRight()).isTrue()

        result.fold(ifLeft = {
        }, ifRight = { loginUser ->
            Truth.assertThat(loginUser).isNotNull()
            Truth.assertThat(loginUser.name == USER_NAME).isTrue()
        })

        coVerify(exactly = 1) { userDataRepository.saveUserData(loginUser.name) }
    }

    @Test
    fun `test user login fail`() = runBlocking {
        coEvery { loginUserRequestDTOMapper.transform(loginUser) } returns loginRequestDTO
        coEvery { api.userLogin(loginRequestDTO) } returns Response.error(500, ResponseBody.create(null, ""))
        coEvery { loginResponseDTO.name } returns USER_NAME
        coEvery { loginUserResponseModelMapper.transform(loginResponseDTO) } returns loginResult
        val result = eventRepositoryImpl.userLogin(loginUser)
        Truth.assertThat(result.isLeft()).isTrue()

        result.fold(ifLeft = {
            Truth.assertThat(it.code == NETWORK_ERROR).isTrue()
        }, ifRight = {

        })

        coVerify(exactly = 0) { userDataRepository.saveUserData(USER_NAME) }
    }


}

private const val USER_NAME = "RAMINDU"
private const val PASSWORD = "abc123"