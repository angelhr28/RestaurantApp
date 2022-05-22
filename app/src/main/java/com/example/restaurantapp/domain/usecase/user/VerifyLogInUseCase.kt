package com.example.restaurantapp.domain.usecase.user

import com.example.restaurantapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class VerifyLogInUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): FirebaseUser? {
        return repository.verifyLogin()
    }
}