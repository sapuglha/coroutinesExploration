package com.sapuglha.coroutinesexploration.presentation.user.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.domain.type.User
import com.sapuglha.coroutinesexploration.domain.usecase.GetAllUsersUseCase
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    val users: LiveData<List<User>> = getAllUsersUseCase.execute()
}
