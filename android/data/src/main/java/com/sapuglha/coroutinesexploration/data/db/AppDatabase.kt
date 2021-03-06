package com.sapuglha.coroutinesexploration.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sapuglha.coroutinesexploration.data.db.type.UserDao
import com.sapuglha.coroutinesexploration.data.db.type.UserEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        UserEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) = Room
            .inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
            .build()
    }
}
