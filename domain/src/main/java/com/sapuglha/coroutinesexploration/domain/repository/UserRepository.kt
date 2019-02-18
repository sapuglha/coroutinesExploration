package com.sapuglha.coroutinesexploration.domain.repository

import androidx.lifecycle.LiveData
import com.sapuglha.coroutinesexploration.domain.type.User

interface UserRepository {
    suspend fun saveUser(user: User): Boolean

    fun getAll(): LiveData<List<User>>
}
