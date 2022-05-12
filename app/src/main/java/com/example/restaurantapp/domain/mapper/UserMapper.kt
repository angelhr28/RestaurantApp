package com.example.restaurantapp.domain.mapper

import com.example.restaurantapp.data.model.UserModel
import com.example.restaurantapp.domain.model.User
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toUser(): User? {
    return try {
        val fullName = getString("full_name") ?: ""
        val email = getString("email") ?: ""
        val phone = getString("phone") ?: ""
        User(id, fullName, email, phone)
    } catch (e: Exception) {
        null
    }
}

fun UserModel.toUser(): HashMap<String, String> {
    return hashMapOf(
        "full_name" to fullName,
        "phone" to phone,
        "email" to email,
        "password" to password
    )
}