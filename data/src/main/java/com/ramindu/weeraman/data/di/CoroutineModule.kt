package com.ramindu.weeraman.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CoroutineModule {

    @Provides
    @Singleton
    fun provideDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}