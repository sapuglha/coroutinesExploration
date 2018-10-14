package com.sapuglha.coroutinesexploration.data.type

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val username: String,
    val firstName: String,
    val lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val fullname: String
        get() = "$firstName $lastName"
}
