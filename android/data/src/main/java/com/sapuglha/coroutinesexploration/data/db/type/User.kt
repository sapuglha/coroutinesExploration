package com.sapuglha.coroutinesexploration.data.db.type

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sapuglha.coroutinesexploration.domain.type.User
import java.util.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("select * from user")
    fun observeAll(): LiveData<List<UserEntity>>
}

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    val username: String,
    val firstName: String,
    val lastName: String
) {

    fun toDomain(): User = User(
        id = this.id,
        username = this.username,
        firstname = this.firstName,
        lastname = this.lastName
    )

    companion object {
        fun fromDomain(user: User): UserEntity = UserEntity(
            id = user.id ?: UUID.randomUUID().toString(),
            username = user.username,
            firstName = user.firstname,
            lastName = user.lastname
        )
    }
}
