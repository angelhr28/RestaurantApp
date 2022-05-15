package com.example.restaurantapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantapp.utils.enums.Status

@Entity(tableName = "board_table")
data class BoardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "status") val status: Status,
)
