package com.sapuglha.coroutinesexploration.data.net

import com.sapuglha.coroutinesexploration.domain.type.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApi {

    @GET("/users")
    suspend fun getAllUsers(): List<User>

    @PUT("/user")
    suspend fun createUser(@Body user: User): User
}
