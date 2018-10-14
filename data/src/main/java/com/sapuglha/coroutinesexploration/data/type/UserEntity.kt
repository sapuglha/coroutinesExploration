package com.sapuglha.coroutinesexploration.data.type

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val username: String,
    val firstName: String,
    val lastName: String
)
