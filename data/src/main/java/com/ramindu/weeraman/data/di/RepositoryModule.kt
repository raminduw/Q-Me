package com.ramindu.weeraman.data.di

import com.ramindu.weeraman.data.repositories.EventRepositoryImpl
import com.ramindu.weeraman.data.repositories.EventRepositoryMockImpl
import com.ramindu.weeraman.data.repositories.UserDataRepositoryImpl
import com.ramindu.weeraman.domain.repositories.EventRepository
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

/*    @Singleton
    @Binds
    abstract fun bindEventRepository(impl: EventRepositoryImpl): EventRepository*/

    @Singleton
    @Binds
    abstract fun bindEventRepository(impl: EventRepositoryMockImpl): EventRepository


    @Singleton
    @Binds
    abstract fun bindUserDataRepository(impl: UserDataRepositoryImpl): UserDataRepository
}