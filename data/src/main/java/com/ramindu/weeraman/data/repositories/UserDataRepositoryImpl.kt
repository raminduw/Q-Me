package com.ramindu.weeraman.data.repositories

import android.content.SharedPreferences
import com.ramindu.weeraman.domain.repositories.UserDataRepository
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) : UserDataRepository {

    override suspend fun getLoggedUser(): String {
        return sharedPreferences.getString(USER_NAME, "") ?: ""
    }

    override suspend fun saveUserData(username: String) {
        sharedPreferences.edit().putString(USER_NAME, username).apply()
    }

    override suspend fun clearUser(username: String) {
        sharedPreferences.edit().remove(username).apply()
    }
}

private const val USER_NAME = "user_name"