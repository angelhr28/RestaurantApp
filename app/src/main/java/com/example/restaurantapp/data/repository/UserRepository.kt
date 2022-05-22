package com.example.restaurantapp.data.repository

import com.example.restaurantapp.data.cache.DataCache
import com.example.restaurantapp.data.database.dao.*
import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.data.network.UserService
import com.example.restaurantapp.domain.model.User
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val userCache: DataCache,

    private val boardDao: BoardDao,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val requestDao: RequestDao,
    private val requestDetailDao: RequestDetailDao,
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

    suspend fun logOut() {
        boardDao.deleteAll()
        categoryDao.deleteAll()
        productDao.deleteAll()
        requestDao.deleteAll()
        requestDetailDao.deleteAll()
        userCache.clearUserCache()
        userService.logOut()
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