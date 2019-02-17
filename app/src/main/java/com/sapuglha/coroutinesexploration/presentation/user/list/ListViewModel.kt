package com.sapuglha.coroutinesexploration.presentation.user.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.type.UserEntity
import javax.inject.Inject

class ListViewModel @Inject constructor(
    val db: AppDatabase
) : ViewModel() {

    val users: LiveData<List<UserEntity>> =
        db.userDao().observeAll()
}
