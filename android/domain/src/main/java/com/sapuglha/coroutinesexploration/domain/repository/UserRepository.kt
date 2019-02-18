package com.sapuglha.coroutinesexploration.domain.repository

import com.sapuglha.coroutinesexploration.domain.type.User

interface UserRepository {
    suspend fun saveUser(user: User): Boolean

    suspend fun getAll(): List<User>
}
