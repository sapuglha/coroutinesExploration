package com.sapuglha.coroutinesexploration.domain.usecase

import com.sapuglha.coroutinesexploration.domain.repository.UserRepository
import com.sapuglha.coroutinesexploration.domain.type.User
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    val userRepository: UserRepository
) {
    suspend fun execute(user: User) = userRepository.saveUser(user)
}
