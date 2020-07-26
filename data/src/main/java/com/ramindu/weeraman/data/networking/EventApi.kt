package com.ramindu.weeraman.data.networking

import com.ramindu.weeraman.data.di.API_KEY
import com.ramindu.weeraman.data.entities.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {

    @GET("event")
    suspend fun createEvent(createEventRequestDTO: CreateEventRequestDTO): Response<CreateEventResponseDTO>

    @GET("weather")
    suspend fun userLogin(loginRequestDTO: LoginRequestDTO):Response<LoginResponseDTO>

    @GET("weather")
    suspend fun userRegister(registerRequestDTO: RegisterRequestDTO):Response<RegisterResponseDTO>

    @GET("weather")
    suspend fun getEventsByUser(getEventsRequestDTO: GetEventsRequestDTO):Response<GetEventsResponseDTO>

    @GET("weather")
    suspend fun getEventDetailsById(getEventDetailsRequestDTO: GetEventDetailsRequestDTO):Response<GetEventDetailsResponseDTO>


    @GET("weather")
    suspend fun getMyEvents(
        @Query("q") location: String,
        @Query("appid") apiKey: String = API_KEY
    ):Response<CreateEventResponseDTO>

}