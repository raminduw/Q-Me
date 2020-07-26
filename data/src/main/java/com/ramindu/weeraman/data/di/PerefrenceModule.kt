package com.ramindu.weeraman.data.di

import android.content.Context
import android.content.SharedPreferences
import com.ramindu.weeraman.data.BuildConfig
import com.ramindu.weeraman.data.networking.EventApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext appContext: Context, @SharedPref prefName: String): SharedPreferences {
        return appContext.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @SharedPref
    fun provideSharedPreferenceName(): String {
        return SHARED_PREF
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SharedPref

private const val SHARED_PREF = "q_me_pref"

