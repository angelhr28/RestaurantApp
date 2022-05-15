package com.example.restaurantapp.domain.model

import com.example.restaurantapp.utils.enums.Status

data class Request(
    val id: Int,
    val status: Status,
)
