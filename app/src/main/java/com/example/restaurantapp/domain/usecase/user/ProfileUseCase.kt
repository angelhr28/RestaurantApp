package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.repository.UserRepository
import com.example.restaurantapp.domain.model.User
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): User? {
        return repository.getProfile()
    }
}