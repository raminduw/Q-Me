package com.ramindu.weeraman.domain.di


import com.ramindu.weeraman.domain.dispacther.DispatcherProvider
import com.ramindu.weeraman.domain.dispacther.DispatcherProviderImpl
import com.ramindu.weeraman.domain.entities.CreateEventModel
import com.ramindu.weeraman.domain.entities.LoginUser
import com.ramindu.weeraman.domain.entities.RegisterUser
import com.ramindu.weeraman.domain.usecases.*
import com.ramindu.weeraman.domain.usecases.impl.*
import com.ramindu.weeraman.domain.validator.InputValidator
import com.ramindu.weeraman.domain.validator.CreateEventValidatorImpl
import com.ramindu.weeraman.domain.validator.LoginValidatorImpl
import com.ramindu.weeraman.domain.validator.RegisterValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@InstallIn(ActivityComponent::class)
@Module
abstract class InteractionModule {

    @ActivityScoped
    @Binds
    abstract fun bindGenerateEventUseCase(impl: CreateEventUseCaseImpl): GenerateEventUseCase

    @ActivityScoped
    @Binds
    abstract fun bindUserLoginUseCase(impl: UserLoginUseCaseImpl): UserLoginUseCase

    @ActivityScoped
    @Binds
    abstract fun bindUserRegisterUseCase(impl: UserRegisterUseCaseImpl): UserRegisterUseCase

    @ActivityScoped
    @Binds
    abstract fun bindUserLogoutUseCase(impl: UserLogoutUseCaseImpl): UserLogoutUseCase

    @ActivityScoped
    @Binds
    abstract fun bindFileSaveUseCase(impl: FileSaveUseCaseImpl): FileSaveUseCase

    @ActivityScoped
    @Binds
    abstract fun bindGetEventsUseCase(impl: GetEventsByUserUseCaseImpl): GetEventsUseCase

    @ActivityScoped
    @Binds
    abstract fun bindGetEventByIdUseCase(impl: GetEventDetailsByIdUseCaseImpl): GetEventByIdUseCase

    @ActivityScoped
    @Binds
    abstract fun bindEventValidator(impl: CreateEventValidatorImpl): InputValidator<CreateEventModel>

    @ActivityScoped
    @Binds
    abstract fun bindLoginValidator(impl: LoginValidatorImpl): InputValidator<LoginUser>

    @ActivityScoped
    @Binds
    abstract fun bindRegisterValidator(impl: RegisterValidatorImpl): InputValidator<RegisterUser>


    @ActivityScoped
    @Binds
    abstract fun bindUserTestUseCase(impl: UserTestUseCaseImpl): UserTestUseCase

    @ActivityScoped
    @Binds
    abstract fun bindDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider


}