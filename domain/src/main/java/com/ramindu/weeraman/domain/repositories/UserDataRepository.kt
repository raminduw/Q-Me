package com.ramindu.weeraman.domain.repositories

interface UserDataRepository {

    suspend fun getLoggedUser():String

    suspend fun saveUserData(username:String)

    suspend fun clearUser(username:String)

}