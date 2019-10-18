package com.sapuglha.coroutinesexploration.presentation.user.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapuglha.coroutinesexploration.domain.type.User
import com.sapuglha.coroutinesexploration.domain.usecase.user.GetAllUsersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    val users = MutableLiveData<List<User>>()

    fun refreshUserList() {
        viewModelScope.launch {
            users.postValue(getAllUsersUseCase.execute())
        }
    }
}
