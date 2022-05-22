package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.repository.UserRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke() {
        repository.logOut()
    }
}