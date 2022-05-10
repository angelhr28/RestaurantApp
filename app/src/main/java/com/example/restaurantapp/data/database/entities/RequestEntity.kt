package com.example.restaurantapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantapp.domain.model.Request


@Entity(tableName = "request_table")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "quote") val mesa: Int,
)

fun Request.toDatabase() = RequestEntity(mesa = mesa)