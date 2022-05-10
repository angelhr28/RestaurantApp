package com.example.restaurantapp.data.model

data class UserModel(
    val email: String,
    val fullName: String,
    val phone: String,
    val password: String,
    var uuid: String? = null,
)
