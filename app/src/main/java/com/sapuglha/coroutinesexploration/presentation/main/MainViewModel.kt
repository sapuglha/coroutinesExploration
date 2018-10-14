package com.sapuglha.coroutinesexploration.presentation.main

import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.type.UserEntity
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val db: AppDatabase
) : ViewModel() {

    fun updateUser() {
        db.userDao().insert(
            UserEntity(
                id = 1,
                username = "bobson",
                firstName = "Bob",
                lastName = "Bobson"
            )
        )
    }
}
