package com.example.restaurantapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantapp.utils.enums.Status
import com.google.type.DateTime

@Entity(tableName = "request_detail_table")
data class RequestDetailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "request_id") val requestId: Int,
    @ColumnInfo(name = "product_id") val productId: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "total_amount") val totalAmount: Double,
    @ColumnInfo(name = "create_at") val createAt: DateTime,
    @ColumnInfo(name = "status") val status: Status,
)

//fun Request.toDatabase() = RequestEntity(status = status)