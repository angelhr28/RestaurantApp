package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.data.repository.UserRepository
import javax.inject.Inject

class CheckInUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(user: UserModel) {
        repository.checkInAuth(user)
        repository.checkInFirestore(user)
        repository.saveProfile(user.email)
    }
}