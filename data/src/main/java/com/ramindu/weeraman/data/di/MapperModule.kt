package com.ramindu.weeraman.data.di

import com.ramindu.weeraman.data.entities.*
import com.ramindu.weeraman.data.mapper.*
import com.ramindu.weeraman.domain.entities.*
import com.ramindu.weeraman.domain.mapper.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class MapperModule {

    @Singleton
    @Binds
    abstract fun bindCreateEventRequestDTOMapper(impl: CreateEventRequestDTOMapper): Mapper<CreateEventModel, CreateEventRequestDTO>

    @Singleton
    @Binds
    abstract fun bindCreateEventResponseModelMapper(impl: CreateEventResponseModelMapper): Mapper<CreateEventResponseDTO, EventModel>

    @Singleton
    @Binds
    abstract fun bindLoginUserRequestDTOMapper(impl: LoginUserRequestDTOMapper): Mapper<LoginUser, LoginRequestDTO>

    @Singleton
    @Binds
    abstract fun bindLoginUserResponseModelMapper(impl: LoginUserResponseModelMapper): Mapper<LoginResponseDTO, LoginResult>

    @Singleton
    @Binds
    abstract fun bindRegisterUserRequestDTOMapper(impl: RegisterUserRequestDTOMapper): Mapper<RegisterUser, RegisterRequestDTO>

    @Singleton
    @Binds
    abstract fun bindRegisterUserResponseModelMapper(impl: RegisterUserResponseModelMapper): Mapper<RegisterResponseDTO, RegisterResult>

    @Singleton
    @Binds
    abstract fun bindGetEventDetailsRequestDTOMapper(impl: GetEventDetailsRequestDTOMapper): Mapper<String, GetEventDetailsRequestDTO>

    @Singleton
    @Binds
    abstract fun bindGetEventDetailsResponseModelMapper(impl: GetEventDetailsResponseModelMapper): Mapper<GetEventDetailsResponseDTO, EventDetailModel>

    @Singleton
    @Binds
    abstract fun bindGetEventsRequestDTOMapper(impl: GetEventsRequestDTOMapper): Mapper<String, GetEventsRequestDTO>

    @Singleton
    @Binds
    abstract fun bindGetEventsResponseModelMapper(impl: GetEventsResponseModelMapper): Mapper<GetEventsResponseDTO, MutableList<EventModel>>
}