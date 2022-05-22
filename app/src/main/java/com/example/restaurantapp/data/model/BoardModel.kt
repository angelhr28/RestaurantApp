package com.example.restaurantapp.data.model

import com.example.restaurantapp.utils.enums.Status
import java.util.*

data class BoardModel(
    var id: String,
    val status: Status,
    val createdAt: Date = Date(System.currentTimeMillis()),
    val updatedAt: Date = Date(System.currentTimeMillis()),
)
