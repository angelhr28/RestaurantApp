package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.UserRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke() {
        repository.logOut()
    }
}