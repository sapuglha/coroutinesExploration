package com.sapuglha.coroutinesexploration.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.net.UserApi
import com.sapuglha.coroutinesexploration.domain.repository.UserRepository
import com.sapuglha.coroutinesexploration.domain.type.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val userApi: UserApi
) : UserRepository {
    override fun getAll(): LiveData<List<User>> =
        Transformations.map(db.userDao().observeAll()) { list ->
            list.map {
                it.toDomain()
            }
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
