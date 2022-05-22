package com.example.restaurantapp.domain.model

import com.example.restaurantapp.utils.enums.Status
import java.util.*

data class Board(
    val id: String,
    val status: Status,
    val createdAt: Date = Date(System.currentTimeMillis()),
    val updatedAt: Date = Date(System.currentTimeMillis()),
)
