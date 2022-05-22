package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.repository.UserRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.logIn(email, password)
        repository.saveProfile(email)
    }
}