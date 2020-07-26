package com.ramindu.weeraman.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class FileModule {

    @Provides
    @Singleton
    fun provideExternalStorage(@ApplicationContext appContext: Context, @FileStorage storageFolderName: String): File? {
        return appContext.getExternalFilesDir(storageFolderName)
    }

    @Provides
    @Singleton
    @FileStorage
    fun provideExternalStorageFolder(): String {
        return QR_CODE_STORAGE
    }

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FileStorage

private const val QR_CODE_STORAGE = "qr_code_storage"