package com.sapuglha.coroutinesexploration.data.repository

import com.sapuglha.coroutinesexploration.data.net.UserApi
import com.sapuglha.coroutinesexploration.domain.repository.UserRepository
import com.sapuglha.coroutinesexploration.domain.type.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getAll(): List<User> =
        try {
            userApi.getAllUsers()
        } catch (e: Exception) {
            emptyList()
        }

    override suspend fun saveUser(user: User): Boolean {
        try {
            userApi.createUser(user)
        } catch (e: Exception) {
            return false
        }

        return true
    }
}
