package com.sapuglha.coroutinesexploration.domain.usecase

import androidx.lifecycle.LiveData
import com.sapuglha.coroutinesexploration.domain.repository.UserRepository
import com.sapuglha.coroutinesexploration.domain.type.User
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(): LiveData<List<User>> = userRepository.getAll()
}
