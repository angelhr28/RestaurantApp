package com.example.restaurantapp.domain.model

import com.google.firebase.firestore.DocumentSnapshot

data class User(
    val userId: String, //Document ID is actually the user id
    val name: String,
    val bio: String,
    val imageUrl: String,
)

fun DocumentSnapshot.toUser(): User? {
    return try {
        val name = getString("name")!!
        val imageUrl = getString("profile_image")!!
        val bio = getString("user_bio")!!
        User(id, name, bio, imageUrl)
    } catch (e: Exception) {
        null
    }
}