package com.example.restaurantapp.domain.model

data class User(
    val userId: String, //Document ID is actually the user id
    val fullName: String,
    val email: String,
    val phone: String,
)