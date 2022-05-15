package com.example.restaurantapp.data

import com.example.restaurantapp.data.cache.UserCache
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.data.network.UserService
import com.example.restaurantapp.domain.model.User
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val userCache: UserCache,
) {

    suspend fun checkInAuth(userModel: UserModel) {
        userService.checkInAuth(userModel)
    }

    suspend fun checkInFirestore(userModel: UserModel) {
        userService.checkInFirestore(userModel)
    }

    suspend fun logIn(email: String, password: String) {
        userService.logIn(email, password)
    }

    fun logOut() {
        userService.logOut()
        userCache.clearUserCache()
    }

    suspend fun saveProfile(email: String) {
        val user = userService.saveProfile(email)
        user?.let { userCache.saveUserCache(it) }
    }

    fun getProfile(): User? {
        return userCache.getUserCache()
    }

    fun verifyLogin(): FirebaseUser? {
        return userService.verifyLogin()
    }
}